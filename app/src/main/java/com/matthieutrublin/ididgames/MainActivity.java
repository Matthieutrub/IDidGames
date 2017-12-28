package com.matthieutrublin.ididgames;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Context contextThis;
    EditText editTextTableWidth;
    EditText editTextTableHeight;
    TableLayout matrice;
    TextView nbForm;
    TextView textViewPerimeter;
    Matrice myMatrice;

    private void initTable() {

        Integer tableWidth = Integer.parseInt(editTextTableWidth.getText().toString());
        Integer tableHeight = Integer.parseInt(editTextTableHeight.getText().toString());
        myMatrice = new Matrice(contextThis, tableWidth, tableHeight, matrice, textViewPerimeter);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contextThis = this;

        editTextTableWidth = findViewById(R.id.editTextTableWidth);
        editTextTableHeight = findViewById(R.id.editTextTableHeight);
        matrice = findViewById(R.id.matrice);
        nbForm = findViewById(R.id.textViewNbForm);
        textViewPerimeter = findViewById(R.id.textViewPerimeter);

        editTextTableWidth.setText("10");
        editTextTableHeight.setText("10");

        initTable();

        editTextTableWidth.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if (editTextTableWidth.getText().toString().isEmpty()) {
                } else if (Integer.parseInt(editTextTableWidth.getText().toString()) > 25) {
                    editTextTableWidth.setText("25");
                    initTable();
                } else {
                    initTable();
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
        editTextTableHeight.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if (editTextTableHeight.getText().toString().isEmpty()) {
                } else if (Integer.parseInt(editTextTableHeight.getText().toString()) > 25) {
                    editTextTableHeight.setText("25");
                    initTable();
                } else {
                    initTable();
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("height", myMatrice.getHeight());
        outState.putInt("width", myMatrice.getWidth());
        for (int i = 0; i < myMatrice.getHeight(); i++) {
            outState.putBooleanArray("MatriceBool" + i, myMatrice.getMatriceBool()[i]);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        int height = savedInstanceState.getInt("height", 10);
        int width = savedInstanceState.getInt("width", 10);
        boolean table[][] = new boolean[25][25];
        for (int i = 0; i < height; i++) {
            table[i] = savedInstanceState.getBooleanArray("MatriceBool" + i);
        }

        myMatrice = new Matrice(contextThis, width, height, matrice, textViewPerimeter);
        myMatrice.setMatriceValue(table);
        myMatrice.updateColorFromMatrice();
        myMatrice.updateData();


    }
}
