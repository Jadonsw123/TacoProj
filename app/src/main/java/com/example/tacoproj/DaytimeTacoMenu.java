package com.example.tacoproj;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class DaytimeTacoMenu extends CustomItem {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daytime_taco_menu);

        radioButtonHandler(radioGroup, continueOrder);

    }

}