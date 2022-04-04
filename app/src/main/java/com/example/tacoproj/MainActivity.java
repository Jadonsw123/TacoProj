package com.example.tacoproj;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import java.text.NumberFormat;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private DatabaseManager dbManager;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dbManager = new DatabaseManager(this);
        Taco testTaco = dbManager.selectTacoById(5);
        if(testTaco == null){
            testTaco = new Taco(5,"egg",2.55);
        }

        dbManager.insertTaco(testTaco);
        Log.w("test",testTaco.toString());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridLayout gridLayout = new GridLayout(this);

        Point size = new Point();
        this.getWindowManager().getDefaultDisplay().getSize(size);
        int buttonWidth = size.x/2;




    }

    //hello
}