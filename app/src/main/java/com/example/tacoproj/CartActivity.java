package com.example.tacoproj;

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

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.CDATASection;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    DatabaseManager dbManager;
    ArrayList<String> order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager = new DatabaseManager(this);
        Bundle b=this.getIntent().getExtras();
        order =b.getStringArrayList("order");
        Log.w("test",order.toString());
        updateView();
    }

    public void updateView() {
        RelativeLayout relativeLayout = new RelativeLayout(this);
        ScrollView scrollView = new ScrollView(this);


        ArrayList<Drink> drinks = dbManager.selectAllDrinks();
        ArrayList<Topping> toppings = dbManager.selectAllToppings();
        ArrayList<Side> sides = dbManager.selectAllSides();
        ArrayList<Taco> tacos = dbManager.selectAllTacos();

        int numOfTotalItems = order.size();

        GridLayout grid = new GridLayout(this);
        grid.setRowCount(numOfTotalItems + 5);
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
                    break;
                case "Topping":
                    itemPrice =dbManager.selectToppingByName(itemName).getPrice();
                    itemId =dbManager.selectToppingByName(itemName).getId();
                    itemName = "   "+itemName;

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

            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
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
            grid.addView(namesAndPrices[i][0], width /100*50, ViewGroup.LayoutParams.WRAP_CONTENT);
            grid.addView(namesAndPrices[i][1], width /100*25, ViewGroup.LayoutParams.WRAP_CONTENT);
            //.addView(namesAndPrices[i][2], width/100*15, ViewGroup.LayoutParams.WRAP_CONTENT);
            grid.addView(buttons[i], width / 100*25, ViewGroup.LayoutParams.WRAP_CONTENT);

        }
        scrollView.addView(grid);

        setContentView(scrollView);
    }

    public void goBack(View v) {
        this.finish();
    }
}



