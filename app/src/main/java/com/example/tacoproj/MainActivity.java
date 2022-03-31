package com.example.tacoproj;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private DatabaseManager dbManager;
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
    }

    //hello
}