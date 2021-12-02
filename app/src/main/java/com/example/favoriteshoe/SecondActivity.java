package com.example.favoriteshoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private Shoe currentShoe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        currentShoe = new Shoe();
        initSaveButton();
        initTextChangeEvents();
    }

    public void onResume() {
        super.onResume();
        Intent intent = getIntent();
        int position = intent.getIntExtra("position", -1);
        if (position != -1) {
            ShoeDataSource ds = new ShoeDataSource(this);
            try {
                ds.open();
                currentShoe = ds.getShoe(position + 1);
                ds.close();
                EditText shoeEdit = findViewById(R.id.editBrand);
                shoeEdit.setText(currentShoe.getShoeBrand());
                EditText colorwayEdit = findViewById(R.id.editColor);
                colorwayEdit.setText(currentShoe.getColorway());
                EditText releaseDateEdit = findViewById(R.id.editRD);
                releaseDateEdit.setText(currentShoe.getReleaseDate());
            } catch (Exception e) {
                Toast.makeText(this, "Error accessing shoe", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void initTextChangeEvents() {
        EditText brandEdit = findViewById(R.id.editBrand);
        brandEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentShoe.setShoeBrand(brandEdit.getText().toString());
            }
        });

        EditText colorwayEdit = findViewById(R.id.editColor);
        brandEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentShoe.setShoeBrand(colorwayEdit.getText().toString());
            }
        });

        EditText releaseDateEdit = findViewById(R.id.editRD);
        brandEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentShoe.setShoeBrand(releaseDateEdit.getText().toString());
            }
        });

    }

    private void initSaveButton() {
        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean wasSuccessful;
                ShoeDataSource ds = new ShoeDataSource(SecondActivity.this);
                try {
                    ds.open();
                    if (currentShoe.getShoeID() == -1) {
                        wasSuccessful = ds.insertShoe(currentShoe);
                        if(wasSuccessful) {
                            int newId = ds.getLastShoeID();
                            currentShoe.setShoeID(newId);
                        }
                    } else {
                        wasSuccessful = ds.updateShoe(currentShoe);
                    }
                    ds.close();
                } catch (Exception e) {
                    wasSuccessful = false;
                }
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

}