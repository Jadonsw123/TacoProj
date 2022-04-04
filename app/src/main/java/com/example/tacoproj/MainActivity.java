package com.example.tacoproj;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.MenuItem;
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
        Taco testTaco = dbManager.selectTacoByName("egg");
        if(testTaco == null){
            testTaco = new Taco(5,"egg",2.55,"true","false");
            dbManager.insertTaco(testTaco);
        } /*else{
            Log.w("test",String.valueOf(testTaco.getId()));
            dbManager.deleteTacoById(5);
            testTaco = new Taco(5,"egg",2.55,"true","false");
            dbManager.insertTaco(testTaco);
        }*/

        testTaco = dbManager.selectTacoByName("egg");
        if(testTaco != null) {
            Log.w("test", testTaco.toString());
        } else{
            Log.w("test", "No TACO");
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);






    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.AddDrinkButton:
                //Intent insertIntent = new Intent(this, InsertActivity.class );
                //this.startActivity( insertIntent );
                break;
            case R.id.AddSideButton:
                Log.w("Deez", "Nuts Delete");
                //Intent deleteIntent = new Intent(this, DeleteActivity.class);
                //this.startActivity(deleteIntent);
                break;

            case R.id.AddTacoButton:
                Log.w("Deez", "Nuts Update");
                //Intent updateIntent = new Intent(this, UpdateActivity.class );
                //this.startActivity( updateIntent );
                break;


            case R.id.ProceedToCart:
                Log.w("Deez", "Nuts Update");
                //totalAmount=0.0;
                Log.w("Deez", "Proceed to cart pressed");
                return true;


            case R.id.button:
                Log.w("Deez", "INVISIBLE BUTTON PRESSED");



        }
        return super.onOptionsItemSelected(item);
    }

    public void ClickProceed(View view) {
        Log.w("Deez", "Proceed to cart pressed");
    }

    public void ClickDevMenu(View view) {
        Log.w("Deez", "SECRET BUTTON!");
    }
    //hello
}