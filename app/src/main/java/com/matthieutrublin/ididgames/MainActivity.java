package com.matthieutrublin.ididgames;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText editTextTableWidth = null;
    Integer tableWidth = 0;
    EditText editTextTableHeight = null;
    Integer tableHeight = 0;
    TableLayout matrice =null;
    Context contextThis = null;
    View layoutTable =null;
    Boolean matriceBool[][]=new Boolean[25][25];
    //Integer matriceForm[][]=new Integer[25][25];
    TextView pixel[][]=new TextView[25][25];
    Integer colorPlain = Color.argb(255, 0, 175, 0);
    Integer colorEmpty = Color.argb(255, 175, 64, 64);
    TextView nbForm;
    TextView textViewPerimeter;

    //List<Integer> form = new ArrayList<Integer>();


    Integer perimeter=0;

    private void initTable() {

        tableWidth=Integer.parseInt(editTextTableWidth.getText().toString());
        tableHeight=Integer.parseInt(editTextTableHeight.getText().toString());
        textViewPerimeter.setText("0");
        matrice.removeAllViews();
        for(int i = 0; i < tableHeight; i++){
            TableRow row=new TableRow(contextThis);
            for(int j = 0; j < tableWidth; j++)
            {
                matriceBool[i][j]=false;
                //matriceForm[i][j]=0;
                pixel[i][j] =new TextView(contextThis);
                pixel[i][j].setText("    ");
                pixel[i][j].setClickable(true);
                pixel[i][j].setBackgroundColor(colorEmpty);
                pixel[i][j].setOnClickListener(clickListenerPixel);
                row.addView(pixel[i][j]);
            }
            matrice.addView(row,i);
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
            updateMatrice();
            updateData();
        }
    };

    private void updateMatrice() {
        for(int i = 0; i < tableHeight; i++){
            for(int j = 0; j < tableWidth; j++)
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

    private void updateData() {
        perimeter=0;
        for(int i = 0; i < tableHeight; i++){
            for(int j = 0; j < tableWidth; j++)
            {
                if(matriceBool[i][j]){

                    //Up
                    if(i==0){
                        perimeter++;
                    } else if (!matriceBool[i-1][j]){
                        perimeter++;
                    }

                    //Down
                    if(i+1==tableHeight){
                        perimeter++;
                    } else if (!matriceBool[i+1][j]){
                        perimeter++;
                    }
                    //Left
                    if(i==0){
                        perimeter++;
                    } else if (!matriceBool[i][j-1]){
                        perimeter++;
                    }

                    //Right
                    if(j+1==tableWidth){
                        perimeter++;
                    } else if (!matriceBool[i][j-1]){
                        perimeter++;
                    }
                }
            }
        }
        textViewPerimeter.setText(perimeter+" ");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contextThis = this;

        editTextTableWidth = findViewById(R.id.editTextTableWidth);
        editTextTableHeight = findViewById(R.id.editTextTableHeight);
        matrice = findViewById(R.id.matrice);
        layoutTable = findViewById(R.id.layoutTable);
        nbForm = findViewById(R.id.textViewNbForm);
        textViewPerimeter = findViewById(R.id.textViewPerimeter);

        editTextTableWidth.setText("10");
        editTextTableHeight.setText("10");
        tableWidth=Integer.parseInt(editTextTableWidth.getText().toString());
        tableHeight=Integer.parseInt(editTextTableHeight.getText().toString());

        initTable();

        editTextTableWidth.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if(editTextTableWidth.getText().toString().isEmpty()){
                } else if(Integer.parseInt(editTextTableWidth.getText().toString())>25){
                    editTextTableWidth.setText("25");
                    initTable();
                } else {
                    initTable();
                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
        editTextTableHeight.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if(editTextTableHeight.getText().toString().isEmpty()){
                } else if(Integer.parseInt(editTextTableHeight.getText().toString())>25){
                    editTextTableHeight.setText("25");
                    initTable();
                } else {
                    initTable();
                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });




    }
}
