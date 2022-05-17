package com.example.tacoproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;


public class DrinksMenu extends CustomItem {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinks_menu);
        radioGroup = rgDrinks;
        continueOrder = findViewById(R.id.completeDrinkSelection);
        radioButtonHandler(radioGroup, continueOrder);

    }
    /*radioGroup = rgDrinks;
    radioButtonHandler();*/


}