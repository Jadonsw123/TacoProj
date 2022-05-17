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

        //SIDES
        Side sideOne = new Side(1,"Potatoes",0.80,"true", "true");
        Side sideTwo = new Side(2,"Pickles",0.80,"true", "true");
        Side sideThree = new Side(3,"Mix Fruit Cup",0.80,"true", "true");

        dbManager.insertSide(sideOne);
        dbManager.insertSide(sideTwo);
        dbManager.insertSide(sideThree);

        //TOPPINGS
        Topping toppingOne = new Topping(1, "Cheese", 0.25, "true", "true");
        Topping toppingTwo = new Topping(2, "Sour Cream", 0.25, "true", "true");
        Topping toppingThree = new Topping(3, "Avocado", 0.25, "true", "true");
        Topping toppingFour = new Topping(4, "Refried Beans", 0.25, "true", "true");
        Topping toppingFive = new Topping(5, "Lettuce", 0.25, "true", "false");
        Topping toppingSix = new Topping(6, "Tomato", 0.25, "true", "false");
        Topping toppingSeven = new Topping(7, "Salsa", 0.25, "true", "false");
        Topping toppingEight = new Topping(8, "Pico De Gallo", 0.25, "true", "false");
        Topping toppingNine = new Topping(9, "Cooked Veggies", 0.25, "true", "false");

        dbManager.insertTopping(toppingOne);
        dbManager.insertTopping(toppingTwo);
        dbManager.insertTopping(toppingThree);
        dbManager.insertTopping(toppingFour);
        dbManager.insertTopping(toppingFive);
        dbManager.insertTopping(toppingSix);
        dbManager.insertTopping(toppingSeven);
        dbManager.insertTopping(toppingEight);
        dbManager.insertTopping(toppingNine);

        //TACOS
        Taco tacoOne = new Taco(1, "Scrambled Eggs", 2.00, "true", "true");
        Taco tacoTwo = new Taco(2, "Bean", 2.00, "true", "true");
        Taco tacoThree = new Taco(3, "Bacon", 2.00, "true", "true");
        Taco tacoFour = new Taco(4, "Sausage", 2.00, "true", "true");
        Taco tacoFive = new Taco(5, "Beef", 2.00, "true", "false");
        Taco tacoSix = new Taco(6, "Chicken", 2.00, "true", "false");
        Taco tacoSeven = new Taco(7, "Veggie Mix", 2.00, "true", "false");

        dbManager.insertTaco(tacoOne);
        dbManager.insertTaco(tacoTwo);
        dbManager.insertTaco(tacoThree);
        dbManager.insertTaco(tacoFour);
        dbManager.insertTaco(tacoFive);
        dbManager.insertTaco(tacoSix);
        dbManager.insertTaco(tacoSeven);

        //DRINKS
        Drink drinkOne = new Drink(1, "Water", 0.00, "true", "true");
        Drink drinkTwo = new Drink(2, "Coke-a-Cola", 1.00, "true", "true");
        Drink drinkThree = new Drink(3, "Sprite", 1.00, "true", "true");
        Drink drinkFour = new Drink(4, "Dr.Pepper", 1.00, "true", "true");
        Drink drinkFive = new Drink(5, "Lemonade", 1.00, "true", "true");
        Drink drinkSix = new Drink(6, "Milk", 1.00, "true", "true");

        dbManager.insertDrink(drinkOne);
        dbManager.insertDrink(drinkTwo);
        dbManager.insertDrink(drinkThree);
        dbManager.insertDrink(drinkFour);
        dbManager.insertDrink(drinkFive);
        dbManager.insertDrink(drinkSix);

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
                customItem.putExtra("menu","drinks");
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


    public void ClickAddDrink(View view) {
        Log.w("Deez", "addDrink");
        Intent newIntent = new Intent(this, CustomItem.class);
        newIntent.putExtra("menu","drinks");
        this.startActivity( newIntent );
    }
    public void ClickAddTaco(View view) {
        Log.w("Deez", "addDrink");
        Intent newIntent = new Intent(this, CustomItem.class);
        newIntent.putExtra("menu","btaco");
        this.startActivity( newIntent );
    }
    public void ClickAddSide(View view) {
        Log.w("Deez", "addDrink");
        Intent newIntent = new Intent(this, CustomItem.class);
        newIntent.putExtra("menu","sides");
        this.startActivity( newIntent );
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