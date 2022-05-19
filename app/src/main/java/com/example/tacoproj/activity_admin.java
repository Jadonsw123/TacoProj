package com.example.tacoproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class activity_admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

    }

    public void clickAdd(View view)
    {
        startActivity(new Intent(activity_admin.this,addActivity.class));
        finish();
    }

    public void clickEdit(View view)
    {
        startActivity(new Intent(activity_admin.this,UpdateActivity.class));
        finish();
    }

    public void clickOrders(View view)
    {
        startActivity(new Intent(activity_admin.this,AdminOrders.class));
        finish();
    }

}