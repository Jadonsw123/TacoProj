package com.example.tacoproj;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class DaytimeTacoMenu extends CustomItem {

    DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daytime_taco_menu);

        dbManager = new DatabaseManager(this);


        radioButtonHandler(radioGroup, continueOrder);

        RadioGroup meatRadioGroup = findViewById(R.id.tacoList);

        for (Taco x : dbManager.selectAllTacos())
        {
            Log.w("deez",x.toString());
            RadioButton button = new RadioButton(this);
            button.setText(x.getName());
        }


    }

}