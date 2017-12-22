package com.matthieutrublin.ididgames;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

public class Pixel extends View {
    Paint paint = new Paint();
    private Rect rectangle;
    boolean isPlain;

    public Pixel(Context context, int size, boolean plain) {
        super(context);
        // create a rectangle that we'll draw later
        rectangle = new Rect(0, 0, size, size);

        // create the Paint and set its color
        paint = new Paint();
        paint.setColor(Color.GRAY);
        isPlain=plain;
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLUE);
        canvas.drawRect(rectangle, paint);

    }

}
