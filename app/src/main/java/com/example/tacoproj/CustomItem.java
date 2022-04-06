package com.example.tacoproj;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

public class CustomItem extends AppCompatActivity {

    RadioGroup options;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_item);
    }



    //class for each selection section
    //label "Select ____"
    //   radio buttons
    // solid line


    public CustomItem(){
        

    }



    /*public void selection(String label, String [] items){

        options = new
        for(int i = 0; i < items.length; i++){
            options.

        }
    }*/




}