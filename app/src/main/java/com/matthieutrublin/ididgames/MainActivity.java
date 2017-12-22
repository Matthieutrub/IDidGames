package com.matthieutrublin.ididgames;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;

public class MainActivity extends AppCompatActivity {
    EditText editTextTableWidth = null;
    Integer tableWidth = 0;
    EditText editTextTableHeight = null;
    Integer tableHeight = 0;
    TableLayout matrice =null;
    Context contextThis = null;
    View layoutTable =null;

    private void initTable() {
        int layoutWidth = layoutTable.getWidth();
        int layoutHeight = layoutTable.getHeight();

        tableWidth=Integer.parseInt(editTextTableWidth.getText().toString());
        tableHeight=Integer.parseInt(editTextTableHeight.getText().toString());

        int maxPixelWidth = layoutWidth / tableWidth;
        int maxPixelHeight = layoutHeight / tableHeight;
        Log.d("Min", "Find the min between "+ maxPixelHeight +" and "+ maxPixelWidth);
        int pixelSize;
        if(maxPixelWidth>maxPixelHeight){
            pixelSize = maxPixelHeight;
        }
        else {
            pixelSize = maxPixelWidth;
        }


        ViewGroup.LayoutParams params=matrice.getLayoutParams();
        params.width=pixelSize * tableWidth;
        params.height=pixelSize * tableHeight;
        matrice.setLayoutParams(params);
        matrice.removeAllViews();
        for(int i = 0; i < tableHeight; i++){
            TableRow row=new TableRow(this);
            for(int j = 0; j < tableWidth; j++)
            {
                boolean plain=false;
                View pixel = new Pixel(this,pixelSize,false);
                int color = Color.argb(255, 0, 175, 64);
                pixel.setBackgroundColor(color);
                row.addView(new Pixel(this,pixelSize,false));
            }
            matrice.addView(row,i);
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contextThis = this;

        editTextTableWidth = findViewById(R.id.editTextTableWidth);
        editTextTableHeight = findViewById(R.id.editTextTableHeight);
        matrice = findViewById(R.id.matrice);

        int color = Color.argb(255, 255, 175, 64);
        matrice.setBackgroundColor(color);
        layoutTable = findViewById(R.id.layoutTable);

        editTextTableWidth.setText("10");
        editTextTableHeight.setText("10");
        tableWidth=Integer.parseInt(editTextTableWidth.getText().toString());
        tableHeight=Integer.parseInt(editTextTableHeight.getText().toString());



        editTextTableWidth.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if(!(editTextTableHeight.getText().toString()=="")){
                    initTable();
                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
        editTextTableHeight.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if(!(editTextTableHeight.getText().toString()=="")){
                    initTable();
                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });


        initTable();

    }
}
