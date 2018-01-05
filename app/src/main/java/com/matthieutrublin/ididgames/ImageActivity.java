package com.matthieutrublin.ididgames;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONObject;

/**
 * Created by matthieutrublin on 29/12/2017.
 */

public class ImageActivity extends AppCompatActivity {
    int position;
    ImageInstagram theOne = new ImageInstagram(new JSONObject());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        ImageView imageView = findViewById(R.id.imageView);
        TextView id = findViewById(R.id.textview_id);
        TextView owner = findViewById(R.id.textview_owner);
        TextView caption = findViewById(R.id.textview_caption);
        TextView date = findViewById(R.id.textview_date);
        TextView likes = findViewById(R.id.textview_likes);
        TextView comments = findViewById(R.id.textview_comments);

        Intent intent = getIntent();
        position = intent.getIntExtra("position",0);

        theOne = MainActivity.mDataset[position];
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;

        new DownloadImageTask(imageView).execute(theOne.url);

        id.setText(theOne.identifier);
        owner.setText(theOne.owner);
        caption.setText(theOne.caption);
        date.setText(theOne.date);
        likes.setText(Integer.toString(theOne.likes));
        comments.setText(Integer.toString(theOne.comments));
    }
}
