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

    Context context;
    TableLayout table;
    int width;
    int height;
    TextView pixel[][]=new TextView[25][25];
    boolean matriceBool[][]=new boolean[25][25];
    Integer colorPlain = Color.argb(255, 0, 175, 0);
    Integer colorEmpty = Color.argb(255, 175, 64, 64);
    int perimeter=0;
    TextView textViewPerimeter;

    public int getPerimeter(){
        return perimeter;
    }

    public int getHeight(){
        return height;
    }

    public int getWidth(){
        return width;
    }

    public boolean[][] getMatriceBool(){
        return matriceBool;
    }

    public void setMatriceValue(boolean[][] data){
        matriceBool=data;
    }


    public Matrice(Context fromContext, int x, int y, TableLayout tableLayout, TextView tvPerimeter)
    {
        context=fromContext;
        width=x;
        height=y;
        table=tableLayout;
        textViewPerimeter=tvPerimeter;
        table.removeAllViews();
        textViewPerimeter.setText("0");
        for(int i = 0; i < height; i++){
            TableRow row=new TableRow(context);
            for(int j = 0; j < width; j++)
            {
                matriceBool[i][j]=false;
                //matriceForm[i][j]=0;
                pixel[i][j] =new TextView(context);
                pixel[i][j].setText("    ");
                pixel[i][j].setClickable(true);
                pixel[i][j].setBackgroundColor(colorEmpty);
                pixel[i][j].setOnClickListener(clickListenerPixel);
                row.addView(pixel[i][j]);
            }
            table.addView(row,i);
        }
    }

    private View.OnClickListener clickListenerPixel = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            ColorDrawable pixelColor = (ColorDrawable) v.getBackground();
            if(pixelColor.getColor()==colorPlain){
                v.setBackgroundColor(colorEmpty);
            } else {
                v.setBackgroundColor(colorPlain);
            }
            updateMatriceFromColor();
            updateData();
        }
    };

    private void updateMatriceFromColor() {
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++)
            {
                ColorDrawable pixelColor = (ColorDrawable) pixel[i][j].getBackground();
                if(pixelColor.getColor()==colorPlain){
                    matriceBool[i][j]=true;
                } else {
                    matriceBool[i][j]=false;
                }
            }
        }
    }

    public void updateColorFromMatrice() {
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++)
            {
                if(matriceBool[i][j]){
                    pixel[i][j].setBackgroundColor(colorPlain);
                } else {
                    pixel[i][j].setBackgroundColor(colorEmpty);
                }
            }
        }
    }

    public void updateData() {
        perimeter=0;
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++)
            {
                if(matriceBool[i][j]){

                    //Up
                    if(i==0){
                        perimeter++;
                    } else if (!matriceBool[i-1][j]){
                        perimeter++;
                    }

                    //Down
                    if(i+1==height){
                        perimeter++;
                    } else if (!matriceBool[i+1][j]){
                        perimeter++;
                    }
                    //Left
                    if(j==0){
                        perimeter++;
                    } else if (!matriceBool[i][j-1]){
                        perimeter++;
                    }

                    //Right
                    if(j+1==width){
                        perimeter++;
                    } else if (!matriceBool[i][j+1]){
                        perimeter++;
                    }
                }
            }
        }
        textViewPerimeter.setText(perimeter+" ");
    }

}
