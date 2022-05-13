package com.example.tacoproj;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.ArrayList;

import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private DatabaseManager dbManager;
    private ArrayList<String> order;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        order = new ArrayList<String>();
        order.add("Taco egg");
        order.add("Topping cheese");
        order.add("Topping peppers");

        dbManager = new DatabaseManager(this);
        dbManager.deleteTacoByName("egg");
        Taco testTaco = dbManager.selectTacoByName("egg");
        if(testTaco == null){
            testTaco = new Taco(5,"egg",2.55,"true","false");
            dbManager.insertTaco(testTaco);
        }
        Log.w("test",testTaco.toString());
        Topping testTopping = new Topping(5,"cheese",.50,"true","true");
        Topping testTopping1 = new Topping(5,"peppers",.50,"true","true");
        dbManager.deleteToppingByName("cheese");
        dbManager.deleteToppingByName("peppers");
        dbManager.insertTopping(testTopping);
        dbManager.insertTopping(testTopping1);
        ArrayList<Topping> toppings = dbManager.selectAllToppings();
        for(Topping t: toppings){
            Log.w("test", t.toString());
        }



        testTaco = dbManager.selectTacoByName("egg");
        if(testTaco != null) {
            Log.w("test", testTaco.toString());
        } else{
            Log.w("test", "No TACO");
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);






    }



    //static String menu;
     Intent customItem;
    static Button menuButton;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        menuButton = (Button)item;

        switch (item.getItemId()) {

            case R.id.AddDrinkButton:
                //menu = "drinks";
                customItem = new Intent(this, CustomItem.class);
                //menuIntent.putExtra("menu","drinks");
                this.startActivity( customItem );
                break;
            case R.id.AddSideButton:
                Log.w("Deez", "Nuts Delete");

                //menu = "sides";
                customItem = new Intent(this, CustomItem.class);
               //menuIntent.putExtra("menu","sides");
                this.startActivity(customItem);
                break;

            case R.id.AddTacoButton:
                Log.w("Deez", "Nuts Update");

                //menu = "dtaco";
                customItem = new Intent(this, CustomItem.class );
                //menuIntent.putExtra("menu","dtaco");
                this.startActivity(customItem );
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
        Intent cartIntent = new Intent(this, CartActivity.class);

        cartIntent.putExtra("order",order);
        startActivityForResult(cartIntent,1);
    }

    public void ClickDevMenu(View view) {
        Log.w("Deez", "SECRET BUTTON!");
        Intent adminIntent = new Intent(this, PasscodeActivity.class);
        order.add("test12345");
        adminIntent.putExtra("order",order);
        this.startActivity(adminIntent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (1) : {
                if (resultCode == Activity.RESULT_OK) {
                    ArrayList<String> newOrder = data.getStringArrayListExtra("newOrder");
                    order = newOrder;
                    // TODO Update your TextView.
                }
                break;
            }
        }
    }
    //hello
}