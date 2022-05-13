package com.example.tacoproj;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import org.w3c.dom.CDATASection;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    DatabaseManager dbManager;
    ArrayList<String> order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // This callback will only be called when MyFragment is at least Started.

        dbManager = new DatabaseManager(this);
        Bundle b=this.getIntent().getExtras();
        order =b.getStringArrayList("order");
        Log.w("test",order.toString());
        setContentView(R.layout.activity_cart);
        updateView();
    }

    public void updateView() {
        RelativeLayout relativeLayout = new RelativeLayout(this);
        ScrollView scrollView = new ScrollView(this);

        double total = 0.0;
        ArrayList<Drink> drinks = dbManager.selectAllDrinks();
        ArrayList<Topping> toppings = dbManager.selectAllToppings();
        ArrayList<Side> sides = dbManager.selectAllSides();
        ArrayList<Taco> tacos = dbManager.selectAllTacos();

        int numOfTotalItems = order.size();

        GridLayout grid = new GridLayout(this);
        grid.setRowCount(numOfTotalItems + 6);
        grid.setColumnCount(3);

        TextView[][] namesAndPrices = new TextView[numOfTotalItems][2];
        Button[] buttons = new Button[numOfTotalItems];
        //TextView[] ids = new TextView[candies.size()];

        Point size = new Point();
        this.getWindowManager().getDefaultDisplay().getSize(size);
        int width = size.x;


        for (int i = 0; i < order.size(); i++) {
            String[] fullItem = order.get(i).split(" ");
            String type = fullItem[0];
            String itemName = fullItem[1];
            Double itemPrice;
            Integer itemId;
            switch (type){
                case "Taco":
                    itemPrice = dbManager.selectTacoByName(itemName).getPrice();
                    itemId = dbManager.selectTacoByName(itemName).getId();
                    itemName += " taco";
                    break;
                case "Topping":
                    itemPrice =dbManager.selectToppingByName(itemName).getPrice();
                    itemId =dbManager.selectToppingByName(itemName).getId();
                    itemName = "  - "+itemName;

                    break;
                case "Drink":
                    itemPrice =dbManager.selectDrinkByName(itemName).getPrice();
                    itemId =dbManager.selectDrinkByName(itemName).getId();

                    break;
                case "Side":
                    itemPrice =dbManager.selectSideByName(itemName).getPrice();
                    itemId =dbManager.selectSideByName(itemName).getId();
                    break;
                default:
                    itemPrice = 0.0;
                    itemId = 0;
            }
            total += itemPrice;


            //ids[i] = new TextView(this);
            //ids[i].setText("" + candy.getId());

            namesAndPrices[i][0] = new TextView(this);
            namesAndPrices[i][1] = new TextView(this);

            namesAndPrices[i][0].setText(itemName);
            namesAndPrices[i][0].setTextSize(50);

            namesAndPrices[i][1].setText("" + itemPrice);
            namesAndPrices[i][1].setTextSize(50);


            namesAndPrices[i][0].setId(10 * itemId);
            namesAndPrices[i][1].setId(10 * itemId + 1);
            //namesAndPrices[i][2].setId(10*itemId+2);

            buttons[i] = new Button(this);
            buttons[i].setText("Delete");
            buttons[i].setId(itemId);
            buttons[i].setTextSize(50);
            buttons[i].setNextFocusUpId(i);

            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    order.remove(view.getNextFocusUpId());
                    updateView();
//                    int drinkId = itemId;
//                    //goBack(view);
//                    EditText nameET = (EditText) findViewById(10 * drinkId);
//                    EditText priceET = (EditText) findViewById(10 * drinkId + 1);
//                    EditText breakfastET = (EditText) findViewById(10*drinkId+2);
//
//                    String name = nameET.getText().toString();
//                    String priceString = priceET.getText().toString();
//                    String breakfast = breakfastET.getText().toString();
//
//                    Double price = Double.parseDouble(priceString);
//                    dbManager.updateDrinkByName(itemName, new Drink(2222, name, Double.parseDouble(priceString), "true", breakfast));
//
//                    //Toast.makeText(UpdateActivity.this, "Candy updated", Toast.LENGTH_SHORT).show();
//                    //updateView();
                }
            });

            //grid.addView(ids[i], width / 10, ViewGroup.LayoutParams.WRAP_CONTENT);
            grid.addView(namesAndPrices[i][0], width /100*40, ViewGroup.LayoutParams.WRAP_CONTENT);
            grid.addView(namesAndPrices[i][1], width /100*35, ViewGroup.LayoutParams.WRAP_CONTENT);
            //.addView(namesAndPrices[i][2], width/100*15, ViewGroup.LayoutParams.WRAP_CONTENT);
            grid.addView(buttons[i], width / 100*25, ViewGroup.LayoutParams.WRAP_CONTENT);

        }

        scrollView.addView(grid);
        TextView totalView = new TextView(this);
        totalView.setText("Total: " + total + "");
        totalView.setTextSize(50);
        //totalView.setPadding(width/100*50,0,0,0);
        TextView totalView1 = new TextView(this);
        totalView1.setText("");
        //totalView1.setTextSize(50);
        //totalView1.setPadding(width/100*50,0,0,0);
        grid.addView(totalView1,width/100*40,ViewGroup.LayoutParams.WRAP_CONTENT);
        grid.addView(totalView, width/100*35, ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView totalView2 = new TextView(this);
        grid.addView(totalView2,width/100*25,ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView spacer = new TextView(this);
        grid.addView(spacer, width/100*40,ViewGroup.LayoutParams.WRAP_CONTENT);

        Button submitButton = new Button(this);
        submitButton.setText("Check Out");

        submitButton.setTextSize(50);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String orderString = "";
                for(String indOrder:order){
                    orderString += indOrder + ",";
                }
                int orderNumber = dbManager.insertOrder(orderString);




                // 1. Instantiate an <code><a href="/reference/android/app/AlertDialog.Builder.html">AlertDialog.Builder</a></code> with its constructor
                AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this);

// 2. Chain together various setter methods to set the dialog characteristics
                builder.setMessage("Your order number is "+orderNumber)
                        .setTitle("Order");

                builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                        onBackPressed();
                    }
                });
//                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        // User cancelled the dialog
//                    }
//                });

// 3. Get the <code><a href="/reference/android/app/AlertDialog.html">AlertDialog</a></code> from <code><a href="/reference/android/app/AlertDialog.Builder.html#create()">create()</a></code>
                AlertDialog dialog = builder.create();
                dialog.show();


                //updateView();
                //Log.w("test","Toast");
                //Toast.makeText(CartActivity.this,"Your order number is "+orderNumber,Toast.LENGTH_LONG).show();
                //order = new ArrayList<String>();
                //onBackPressed();
            }
        });
    grid.addView(submitButton,width/100*35,ViewGroup.LayoutParams.WRAP_CONTENT);


        relativeLayout.addView(scrollView);
        setContentView(relativeLayout);
    }

    public void goBack(View v) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("newOrder", order);
        setResult(Activity.RESULT_OK, resultIntent);
        this.finish();
    }
    @Override
    public void onBackPressed() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("newOrder", order);
        setResult(Activity.RESULT_OK, resultIntent);
        this.finish();
    }

}



