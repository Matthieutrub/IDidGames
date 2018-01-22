package com.matthieutrublin.ididgames;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.LruCache;
import android.view.Display;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static Button buttonLoad;
    public static int width;
    public static int height;
    public static String search;
    public static Context context;
    public static ImageInstagram[] mDataset=new ImageInstagram[0];
    public static RecyclerView mRecyclerView;

    Button buttonSave;
    EditText tvSearch;
    int MY_PERMISSIONS_REQUEST_READ_STORAGE;
    private RecyclerView.LayoutManager mLayoutManager;
    private static LruCache<String, Bitmap> mMemoryCache;

    public static String makeHashTag(String string) {
        string = string.replaceAll("\\s+", "");
        string = string.replaceAll("\\W", "");
        if (string.isEmpty() || string.length() == 0) {
            string = "boat";
        }
        return string.toLowerCase();
    }

    private View.OnClickListener clickListenerLoad = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            search = makeHashTag(tvSearch.getText().toString());
            FetchData process = new FetchData();
            process.execute();
            mRecyclerView.removeAllViews();
            hideSoftKeyboard(MainActivity.this);
        }
    };
    private View.OnClickListener clickListenerSave = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            checkPermissionWriteStorage(MainActivity.this);
            saveImage();
        }
    };

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

    public void checkPermissionWriteStorage(Activity activity) {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_READ_STORAGE);

        }
    }

    public void saveImage() {
        String url[] = new String[mDataset.length];
        String name[] = new String[mDataset.length];
        for (int i = 0; i < mDataset.length; i++) {
            url[i] = mDataset[i].url;
            name[i] = mDataset[i].identifier;
        }
        Intent msgIntent = new Intent(MainActivity.this, SaveImagesService.class);
        msgIntent.putExtra(SaveImagesService.URL, url);
        msgIntent.putExtra(SaveImagesService.NAME, name);
        startService(msgIntent);
    }

    public void openPage(int position) {
        Intent intent = new Intent(this, ImageActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }

    public static void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    public static Bitmap getBitmapFromMemCache(String key) {
        return mMemoryCache.get(key);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        tvSearch = findViewById(R.id.edit_search);
        buttonLoad = findViewById(R.id.button_load);
        buttonSave = findViewById(R.id.button_save);


        buttonLoad.setOnClickListener(clickListenerLoad);
        buttonSave.setOnClickListener(clickListenerSave);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;

        // Get max available VM memory, exceeding this amount will throw an
        // OutOfMemory exception. Stored in kilobytes as LruCache takes an
        // int in its constructor.
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

        // Use 1/8th of the available memory for this memory cache.
        final int cacheSize = maxMemory / 2;
        Log.e("maxMemory ",Integer.toString(maxMemory));

        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                // The cache size will be measured in kilobytes rather than
                // number of items.
                return bitmap.getByteCount() / 1024;
            }
        };

        mRecyclerView = findViewById(R.id.my_recycler_view);


        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new GridLayoutManager(this, 3);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(context, mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        openPage(position);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );


    }


}
