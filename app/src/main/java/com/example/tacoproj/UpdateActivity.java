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

public class UpdateActivity extends AppCompatActivity {

    DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager = new DatabaseManager(this);
        updateView();
    }

    public void updateView() {
        RelativeLayout relativeLayout = new RelativeLayout(this);
        ScrollView scrollView = new ScrollView(this);


        ArrayList<Drink> drinks = dbManager.selectAllDrinks();
        ArrayList<Topping> toppings = dbManager.selectAllToppings();
        ArrayList<Side> sides = dbManager.selectAllSides();
        ArrayList<Taco> tacos = dbManager.selectAllTacos();

        int numOfTotalItems = drinks.size() + toppings.size() + sides.size() + tacos.size();

        GridLayout grid = new GridLayout(this);
        grid.setRowCount(numOfTotalItems + 5);
        grid.setColumnCount(4);

        EditText[][] namesAndPrices = new EditText[numOfTotalItems][3];
        Button[] buttons = new Button[numOfTotalItems];
        //TextView[] ids = new TextView[candies.size()];

        Point size = new Point();
        this.getWindowManager().getDefaultDisplay().getSize(size);
        int width = size.x;


        for (int i = 0; i < drinks.size(); i++) {
            Drink drink = drinks.get(i);

            //ids[i] = new TextView(this);
            //ids[i].setText("" + candy.getId());

            namesAndPrices[i][0] = new EditText(this);
            namesAndPrices[i][1] = new EditText(this);
            namesAndPrices[i][2] = new EditText(this);
            namesAndPrices[i][0].setText(drink.getName());
            namesAndPrices[i][1].setText("" + drink.getPrice());
            namesAndPrices[i][2].setText("" + drink.getBreakfast());

            namesAndPrices[i][0].setId(10 * drink.getId());
            namesAndPrices[i][1].setId(10 * drink.getId() + 1);
            namesAndPrices[i][2].setId(10*drink.getId()+2);

            buttons[i] = new Button(this);
            buttons[i].setText("Update");
            buttons[i].setId(drink.getId());

            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int drinkId = drink.getId();
                    //goBack(view);
                    EditText nameET = (EditText) findViewById(10 * drinkId);
                    EditText priceET = (EditText) findViewById(10 * drinkId + 1);
                    EditText breakfastET = (EditText) findViewById(10*drinkId+2);

                    String name = nameET.getText().toString();
                    String priceString = priceET.getText().toString();
                    String breakfast = breakfastET.getText().toString();

                    Double price = Double.parseDouble(priceString);
                    dbManager.updateDrinkByName(drink.getName(), new Drink(2222, name, Double.parseDouble(priceString), "true", breakfast));

                    //Toast.makeText(UpdateActivity.this, "Candy updated", Toast.LENGTH_SHORT).show();
                    //updateView();
                }
            });

            //grid.addView(ids[i], width / 10, ViewGroup.LayoutParams.WRAP_CONTENT);
            grid.addView(namesAndPrices[i][0], width / 2, ViewGroup.LayoutParams.WRAP_CONTENT);
            grid.addView(namesAndPrices[i][1], width /100*15, ViewGroup.LayoutParams.WRAP_CONTENT);
            grid.addView(namesAndPrices[i][2], width/100*15, ViewGroup.LayoutParams.WRAP_CONTENT);
            grid.addView(buttons[i], width / 4, ViewGroup.LayoutParams.WRAP_CONTENT);

        }
        scrollView.addView(grid);

        setContentView(scrollView);
    }

    public void goBack(View v) {
        this.finish();
    }
}



