package com.matthieutrublin.ididgames;

import android.app.IntentService;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLConnection;

/**
 * Created by matthieutrublin on 29/12/2017.
 */

public class SaveImagesService extends IntentService {
    public static final String URL = "urlpath";
    public static final String NAME = "name";
    public static final String SOURCE_URL = "destination_source";

    public SaveImagesService() {
        super("DownloadService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String urlPath[] = intent.getStringArrayExtra(URL);
        String name[] = intent.getStringArrayExtra(NAME);

        for(int i=0; i < name.length;i++) {
            Bitmap bitmap = null;
            try {
                InputStream in = new java.net.URL(urlPath[i]).openStream();
                bitmap = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                e.printStackTrace();
            }

            String root = Environment.getExternalStorageDirectory().toString();
            File myDir = new File(root);
            myDir.mkdirs();
            String fname = "/Image-" + name[i]+ ".jpg";
            File file = new File(myDir, fname);
            if (file.exists()) file.delete();
            Log.i("LOAD", root + fname);
            FileOutputStream out = null;
            try {
                out = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out); // bmp is your Bitmap instance
                // PNG is a lossless format, the compression factor (100) is ignored
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (out != null) {
                        out.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        /*// maintenant on transmet le résultat
        // on pourrait avoir un Handler, BroadCast, Notification, etc.
        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction(MyReceiver.ACTION_RESP);
        broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
        broadcastIntent.putExtra(SOURCE_URL, result.toString());
        sendBroadcast(broadcastIntent);*/

    }
}
