package com.matthieutrublin.ididgames;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * Created by matthieutrublin on 28/12/2017.
 */

public class Matrice {

    private final static Integer COLORPLAIN = Color.argb(255, 0, 175, 0);
    private final static Integer COLOREMPTY = Color.argb(255, 175, 64, 64);
    private Context context;
    private TableLayout table;
    private int width;
    private int height;
    private TextView pixel[][] = new TextView[25][25];
    private boolean matriceBool[][] = new boolean[25][25];
    private int perimeter;
    private TextView textViewPerimeter;

    private View.OnClickListener clickListenerPixel = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            ColorDrawable pixelColor = (ColorDrawable) v.getBackground();
            if (pixelColor.getColor() == COLORPLAIN) {
                v.setBackgroundColor(COLOREMPTY);
            } else {
                v.setBackgroundColor(COLORPLAIN);
            }
            updateMatriceFromColor();
            updateData();
        }
    };

    public Matrice(Context fromContext, int x, int y, TableLayout tableLayout, TextView tvPerimeter) {
        context = fromContext;
        width = x;
        height = y;
        table = tableLayout;
        textViewPerimeter = tvPerimeter;

        perimeter=0;
        table.removeAllViews();
        textViewPerimeter.setText("0");
        for (int i = 0; i < height; i++) {
            TableRow row = new TableRow(context);
            for (int j = 0; j < width; j++) {
                matriceBool[i][j] = false;
                pixel[i][j] = new TextView(context);
                pixel[i][j].setText("    ");
                pixel[i][j].setClickable(true);
                pixel[i][j].setBackgroundColor(COLOREMPTY);
                pixel[i][j].setOnClickListener(clickListenerPixel);
                row.addView(pixel[i][j]);
            }
            table.addView(row, i);
        }
    }

    public int getPerimeter() {
        return perimeter;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public boolean[][] getMatriceBool() {
        return matriceBool;
    }

    public void setMatriceValue(boolean[][] data) {
        matriceBool = data;
    }

    private void updateMatriceFromColor() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                ColorDrawable pixelColor = (ColorDrawable) pixel[i][j].getBackground();
                if (pixelColor.getColor() == COLORPLAIN) {
                    matriceBool[i][j] = true;
                } else {
                    matriceBool[i][j] = false;
                }
            }
        }
    }

    public void updateColorFromMatrice() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (matriceBool[i][j]) {
                    pixel[i][j].setBackgroundColor(COLORPLAIN);
                } else {
                    pixel[i][j].setBackgroundColor(COLOREMPTY);
                }
            }
        }
    }

    public void updateData() {
        perimeter = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (matriceBool[i][j]) {

                    //Up
                    if (i == 0) {
                        perimeter++;
                    } else if (!matriceBool[i - 1][j]) {
                        perimeter++;
                    }

                    //Down
                    if (i + 1 == height) {
                        perimeter++;
                    } else if (!matriceBool[i + 1][j]) {
                        perimeter++;
                    }
                    //Left
                    if (j == 0) {
                        perimeter++;
                    } else if (!matriceBool[i][j - 1]) {
                        perimeter++;
                    }

                    //Right
                    if (j + 1 == width) {
                        perimeter++;
                    } else if (!matriceBool[i][j + 1]) {
                        perimeter++;
                    }
                }
            }
        }
        textViewPerimeter.setText(perimeter + " ");
    }

}
