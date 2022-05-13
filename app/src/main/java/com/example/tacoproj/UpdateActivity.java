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
        for(Taco taco: tacos)
            Log.w("thing", taco.toString());

        int numOfTotalItems = drinks.size() + toppings.size() + sides.size() + tacos.size();

        GridLayout grid = new GridLayout(this);
        grid.setRowCount(numOfTotalItems + 50);
        grid.setColumnCount(6);

        EditText[][] namesAndPrices = new EditText[numOfTotalItems][5];
        Button[] buttons = new Button[numOfTotalItems];
        Button[] deleteButtons = new Button[numOfTotalItems];
        //TextView[] ids = new TextView[candies.size()];

        Point size = new Point();
        this.getWindowManager().getDefaultDisplay().getSize(size);
        int width = size.x;

        int buttonWidth = width/100*10;
        int nameWidth = width/100*20;
        int priceWidth = width/100*20;
        int breakfastWidth = width/100*20;
        int availableWidth = width/100*10;




        TextView nameTextView = new TextView(this);
        nameTextView.setText("Name");
        nameTextView.setTextSize(20);
        TextView priceTextView = new TextView(this);
        priceTextView.setText("Price");
        priceTextView.setTextSize(20);
        TextView breakfastTextView = new TextView(this);
        breakfastTextView.setText("Breakfast only?");
        breakfastTextView.setTextSize(20);
        TextView availabilityTextView = new TextView(this);
        availabilityTextView.setText("Available?");
        availabilityTextView.setTextSize(20);


        grid.addView(nameTextView, nameWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
        grid.addView(priceTextView, priceWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
        grid.addView(breakfastTextView, breakfastWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
        grid.addView(availabilityTextView, availableWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
        grid.addView(new TextView(this), buttonWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
        grid.addView(new TextView(this), buttonWidth, ViewGroup.LayoutParams.WRAP_CONTENT);



        //DRINKS
        TextView DrinkTextView = new TextView(this);
        DrinkTextView.setText("DRINKS:");
        DrinkTextView.setTextSize(20);
        grid.addView(DrinkTextView);

        grid.addView(new TextView(this), nameWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
        grid.addView(new TextView(this), priceWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
        grid.addView(new TextView(this), breakfastWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
        grid.addView(new TextView(this), availableWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
        grid.addView(new TextView(this), buttonWidth, ViewGroup.LayoutParams.WRAP_CONTENT);



        for (int i = 0; i < drinks.size(); i++) {
            Drink drink = drinks.get(i);

            //ids[i] = new TextView(this);
            //ids[i].setText("" + candy.getId());

            namesAndPrices[i][0] = new EditText(this);
            namesAndPrices[i][1] = new EditText(this);
            namesAndPrices[i][2] = new EditText(this);
            namesAndPrices[i][3] = new EditText(this);
            namesAndPrices[i][0].setText(drink.getName());
            namesAndPrices[i][1].setText("" + drink.getPrice());
            namesAndPrices[i][2].setText("" + drink.getBreakfast());
            namesAndPrices[i][3].setText("" + drink.getAvailability());

            namesAndPrices[i][0].setId(10 * drink.getId());
            namesAndPrices[i][1].setId(10 * drink.getId() + 1);
            namesAndPrices[i][2].setId(10*drink.getId()+2);
            namesAndPrices[i][3].setId(10*drink.getId()+3);

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
                    EditText availabilityET = (EditText) findViewById(10*drinkId+3);

                    String name = nameET.getText().toString();
                    String priceString = priceET.getText().toString();
                    String breakfast = breakfastET.getText().toString();
                    String availability = availabilityET.getText().toString();

                    Double price = Double.parseDouble(priceString);
                    dbManager.updateDrinkByName(drink.getName(), new Drink(2222, name, Double.parseDouble(priceString), availability, breakfast));

                    Toast.makeText(UpdateActivity.this, "Drink updated", Toast.LENGTH_SHORT).show();
                    updateView();
                }
            });

            deleteButtons[i] = new Button(this);
            deleteButtons[i].setText("Delete");
            deleteButtons[i].setId(drink.getId());
            deleteButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int drinkId = drink.getId();
                    //goBack(view);
                    dbManager.deleteDrinkById(drinkId);

                    Toast.makeText(UpdateActivity.this, "Drink updated", Toast.LENGTH_SHORT).show();
                    updateView();
                }
            });

            //grid.addView(ids[i], width / 10, ViewGroup.LayoutParams.WRAP_CONTENT);
            grid.addView(namesAndPrices[i][0], nameWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
            grid.addView(namesAndPrices[i][1], priceWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
            grid.addView(namesAndPrices[i][2], breakfastWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
            grid.addView(namesAndPrices[i][3], availableWidth, ViewGroup.LayoutParams.WRAP_CONTENT);

            grid.addView(buttons[i], buttonWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
            grid.addView(deleteButtons[i], buttonWidth, ViewGroup.LayoutParams.WRAP_CONTENT);

        }



        //TACOS
        TextView TacoTextView = new TextView(this);
        TacoTextView.setText("TACOS:");
        TacoTextView.setTextSize(20);
        grid.addView(TacoTextView);
        grid.addView(new TextView(this), nameWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
        grid.addView(new TextView(this), priceWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
        grid.addView(new TextView(this), breakfastWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
        grid.addView(new TextView(this), availableWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
        grid.addView(new TextView(this), buttonWidth, ViewGroup.LayoutParams.WRAP_CONTENT);




        for (int i = 0; i < tacos.size(); i++) {
            Taco taco = tacos.get(i);

            //ids[i] = new TextView(this);
            //ids[i].setText("" + candy.getId());

            namesAndPrices[i][0] = new EditText(this);
            namesAndPrices[i][1] = new EditText(this);
            namesAndPrices[i][2] = new EditText(this);
            namesAndPrices[i][3] = new EditText(this);
            namesAndPrices[i][0].setText(taco.getName());
            namesAndPrices[i][1].setText("" + taco.getPrice());
            namesAndPrices[i][2].setText("" + taco.getBreakfast());
            namesAndPrices[i][3].setText("" + taco.getAvailability());

            namesAndPrices[i][0].setId(10 * taco.getId());
            namesAndPrices[i][1].setId(10 * taco.getId() + 1);
            namesAndPrices[i][2].setId(10*taco.getId()+2);
            namesAndPrices[i][3].setId(10*taco.getId()+3);

            buttons[i] = new Button(this);
            buttons[i].setText("Update");
            buttons[i].setId(taco.getId());

            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int tacoId = taco.getId();
                    //goBack(view);
                    EditText nameET = (EditText) findViewById(10 * tacoId);
                    EditText priceET = (EditText) findViewById(10 * tacoId + 1);
                    EditText breakfastET = (EditText) findViewById(10*tacoId+2);
                    EditText availabilityET = (EditText) findViewById(10*tacoId+3);

                    String name = nameET.getText().toString();
                    String priceString = priceET.getText().toString();
                    String breakfast = breakfastET.getText().toString();
                    String availability = availabilityET.getText().toString();

                    Double price = Double.parseDouble(priceString);
                    dbManager.updateTacoByName(taco.getName(), new Taco(2222, name, Double.parseDouble(priceString), availability, breakfast));

                    Toast.makeText(UpdateActivity.this, "taco updated", Toast.LENGTH_SHORT).show();
                    updateView();
                }
            });
            deleteButtons[i] = new Button(this);
            deleteButtons[i].setText("Delete");
            deleteButtons[i].setId(taco.getId());
            deleteButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int drinkId = taco.getId();
                    //goBack(view);
                    dbManager.deleteTacoById(drinkId);

                    Toast.makeText(UpdateActivity.this, "Drink updated", Toast.LENGTH_SHORT).show();
                    updateView();
                }
            });


            //grid.addView(ids[i], width / 10, ViewGroup.LayoutParams.WRAP_CONTENT);
            grid.addView(namesAndPrices[i][0], nameWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
            grid.addView(namesAndPrices[i][1], priceWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
            grid.addView(namesAndPrices[i][2], breakfastWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
            grid.addView(namesAndPrices[i][3], availableWidth, ViewGroup.LayoutParams.WRAP_CONTENT);

            grid.addView(buttons[i], buttonWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
            grid.addView(deleteButtons[i], buttonWidth, ViewGroup.LayoutParams.WRAP_CONTENT);

        }


        //TOPPINGS
        TextView ToppingTextView = new TextView(this);
        ToppingTextView.setText("TOPPINGS:");
        ToppingTextView.setTextSize(20);
        grid.addView(ToppingTextView);
        grid.addView(new TextView(this), nameWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
        grid.addView(new TextView(this), priceWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
        grid.addView(new TextView(this), breakfastWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
        grid.addView(new TextView(this), availableWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
        grid.addView(new TextView(this), buttonWidth, ViewGroup.LayoutParams.WRAP_CONTENT);




        for (int i = 0; i < toppings.size(); i++) {
            Topping topping = toppings.get(i);

            //ids[i] = new TextView(this);
            //ids[i].setText("" + candy.getId());

            namesAndPrices[i][0] = new EditText(this);
            namesAndPrices[i][1] = new EditText(this);
            namesAndPrices[i][2] = new EditText(this);
            namesAndPrices[i][3] = new EditText(this);
            namesAndPrices[i][0].setText(topping.getName());
            namesAndPrices[i][1].setText("" + topping.getPrice());
            namesAndPrices[i][2].setText("" + topping.getBreakfast());
            namesAndPrices[i][3].setText("" + topping.getAvailability());

            namesAndPrices[i][0].setId(10 * topping.getId());
            namesAndPrices[i][1].setId(10 * topping.getId() + 1);
            namesAndPrices[i][2].setId(10*topping.getId()+2);
            namesAndPrices[i][3].setId(10*topping.getId()+3);

            buttons[i] = new Button(this);
            buttons[i].setText("Update");
            buttons[i].setId(topping.getId());

            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int toppingId = topping.getId();
                    //goBack(view);
                    EditText nameET = (EditText) findViewById(10 * toppingId);
                    EditText priceET = (EditText) findViewById(10 * toppingId + 1);
                    EditText breakfastET = (EditText) findViewById(10*toppingId+2);
                    EditText availabilityET = (EditText) findViewById(10*toppingId+3);

                    String name = nameET.getText().toString();
                    String priceString = priceET.getText().toString();
                    String breakfast = breakfastET.getText().toString();
                    String availability = availabilityET.getText().toString();

                    Double price = Double.parseDouble(priceString);
                    dbManager.updateToppingByName(topping.getName(), new Topping(2222, name, Double.parseDouble(priceString), availability, breakfast));

                    Toast.makeText(UpdateActivity.this, "topping updated", Toast.LENGTH_SHORT).show();
                    updateView();
                }
            });

            deleteButtons[i] = new Button(this);
            deleteButtons[i].setText("Delete");
            deleteButtons[i].setId(topping.getId());
            deleteButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int drinkId = topping.getId();
                    //goBack(view);
                    dbManager.deleteToppingById(drinkId);

                    Toast.makeText(UpdateActivity.this, "Drink updated", Toast.LENGTH_SHORT).show();
                    updateView();
                }
            });


            //grid.addView(ids[i], width / 10, ViewGroup.LayoutParams.WRAP_CONTENT);
            grid.addView(namesAndPrices[i][0], nameWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
            grid.addView(namesAndPrices[i][1], priceWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
            grid.addView(namesAndPrices[i][2], breakfastWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
            grid.addView(namesAndPrices[i][3], availableWidth, ViewGroup.LayoutParams.WRAP_CONTENT);

            grid.addView(buttons[i], buttonWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
            grid.addView(deleteButtons[i], buttonWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
        }



        //SIDES
        TextView SideTextView = new TextView(this);
        SideTextView.setText("SIDES:");
        SideTextView.setTextSize(20);
        grid.addView(SideTextView);
        grid.addView(new TextView(this), nameWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
        grid.addView(new TextView(this), priceWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
        grid.addView(new TextView(this), breakfastWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
        grid.addView(new TextView(this), availableWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
        grid.addView(new TextView(this), buttonWidth, ViewGroup.LayoutParams.WRAP_CONTENT);



        for (int i = 0; i < sides.size(); i++) {
            Side side = sides.get(i);

            //ids[i] = new TextView(this);
            //ids[i].setText("" + candy.getId());

            namesAndPrices[i][0] = new EditText(this);
            namesAndPrices[i][1] = new EditText(this);
            namesAndPrices[i][2] = new EditText(this);
            namesAndPrices[i][3] = new EditText(this);
            namesAndPrices[i][0].setText(side.getName());
            namesAndPrices[i][1].setText("" + side.getPrice());
            namesAndPrices[i][2].setText("" + side.getBreakfast());
            namesAndPrices[i][3].setText("" + side.getAvailability());

            namesAndPrices[i][0].setId(10 * side.getId());
            namesAndPrices[i][1].setId(10 * side.getId() + 1);
            namesAndPrices[i][2].setId(10*side.getId()+2);
            namesAndPrices[i][3].setId(10*side.getId()+3);

            buttons[i] = new Button(this);
            buttons[i].setText("Update");
            buttons[i].setId(side.getId());

            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int sideId = side.getId();
                    //goBack(view);
                    EditText nameET = (EditText) findViewById(10 * sideId);
                    EditText priceET = (EditText) findViewById(10 * sideId + 1);
                    EditText breakfastET = (EditText) findViewById(10*sideId+2);
                    EditText availabilityET = (EditText) findViewById(10*sideId+3);

                    String name = nameET.getText().toString();
                    String priceString = priceET.getText().toString();
                    String breakfast = breakfastET.getText().toString();
                    String availability = availabilityET.getText().toString();

                    Double price = Double.parseDouble(priceString);
                    dbManager.updateSideByName(side.getName(), new Side(2222, name, Double.parseDouble(priceString), availability, breakfast));

                    Toast.makeText(UpdateActivity.this, "side updated", Toast.LENGTH_SHORT).show();
                    updateView();
                }
            });
            deleteButtons[i] = new Button(this);
            deleteButtons[i].setText("Delete");
            deleteButtons[i].setId(side.getId());
            deleteButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int drinkId = side.getId();
                    //goBack(view);
                    dbManager.deleteSideById(drinkId);

                    Toast.makeText(UpdateActivity.this, "Drink updated", Toast.LENGTH_SHORT).show();
                    updateView();
                }
            });


            //grid.addView(ids[i], width / 10, ViewGroup.LayoutParams.WRAP_CONTENT);
            grid.addView(namesAndPrices[i][0], nameWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
            grid.addView(namesAndPrices[i][1], priceWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
            grid.addView(namesAndPrices[i][2], breakfastWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
            grid.addView(namesAndPrices[i][3], availableWidth, ViewGroup.LayoutParams.WRAP_CONTENT);

            grid.addView(buttons[i], buttonWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
            grid.addView(deleteButtons[i], buttonWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        //HELP THIS IS TO ADD A SPACER THING
//        TextView ToppingTextView = new TextView(this);
//        ToppingTextView.setText("TOPPINGS:");
//        ToppingTextView.setTextSize(20);
//        grid.addView(TacoTextView);
//        grid.addView(new TextView(this), width / 10, ViewGroup.LayoutParams.WRAP_CONTENT);
//        grid.addView(new TextView(this), width / 10, ViewGroup.LayoutParams.WRAP_CONTENT);
//        grid.addView(new TextView(this), width / 10, ViewGroup.LayoutParams.WRAP_CONTENT);
//        grid.addView(new TextView(this), width / 10, ViewGroup.LayoutParams.WRAP_CONTENT);




        scrollView.addView(grid);

        setContentView(scrollView);
    }

    public void goBack(View v) {
        this.finish();
    }
}



