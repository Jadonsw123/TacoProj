package com.example.tacoproj;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class SidesMenu extends CustomItem {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sides_menu);
        radioGroup = rgSides;
        continueOrder = findViewById(R.id.completeSideSelection);
        radioButtonHandler(radioGroup, continueOrder);

    }

}