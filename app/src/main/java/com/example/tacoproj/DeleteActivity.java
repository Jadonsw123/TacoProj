package com.example.tacoproj;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.CDATASection;

import java.util.ArrayList;

public class DeleteActivity extends AppCompatActivity {

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
        RadioGroup tacoRadioGroup = new RadioGroup(this);
        RadioGroup toppingRadioGroup = new RadioGroup(this);
        RadioGroup drinkRadioGroup = new RadioGroup(this);
        RadioGroup sideRadioGroup = new RadioGroup(this);


        tacoRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedID) {
                dbManager.deleteTacoById( checkedID );
                Toast.makeText( DeleteActivity.this, "Candy deleted", Toast.LENGTH_SHORT ).show( );
                updateView( );

            }
        });
        toppingRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedID) {
                dbManager.deleteToppingById( checkedID );
                Toast.makeText( DeleteActivity.this, "Candy deleted", Toast.LENGTH_SHORT ).show( );
                updateView( );

            }
        });
        drinkRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedID) {
                dbManager.deleteDrinkById( checkedID );
                Toast.makeText( DeleteActivity.this, "Candy deleted", Toast.LENGTH_SHORT ).show( );
                updateView( );

            }
        });
        sideRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedID) {
                dbManager.deleteSideById( checkedID );
                Toast.makeText( DeleteActivity.this, "Candy deleted", Toast.LENGTH_SHORT ).show( );
                updateView( );

            }
        });


        ArrayList<Taco> tacos = dbManager.selectAllTacos();
        ArrayList<Topping> toppings = dbManager.selectAllToppings();
        ArrayList<Drink> drinks = dbManager.selectAllDrinks();
        ArrayList<Side> sides = dbManager.selectAllSides();

        for (Taco thing : tacos) {
            RadioButton rb = new RadioButton(this);
            rb.setId(thing.getId());
            rb.setText(thing.toString());

            tacoRadioGroup.addView(rb);
        }
        for (Topping thing : toppings) {
            RadioButton rb = new RadioButton(this);
            rb.setId(thing.getId());
            rb.setText(thing.toString());

            tacoRadioGroup.addView(rb);
        }
        for (Drink thing : drinks) {
            RadioButton rb = new RadioButton(this);
            rb.setId(thing.getId());
            rb.setText(thing.toString());

            tacoRadioGroup.addView(rb);
        }
        for (Side thing : sides) {
            RadioButton rb = new RadioButton(this);
            rb.setId(thing.getId());
            rb.setText(thing.toString());

            tacoRadioGroup.addView(rb);
        }

        scrollView.addView(tacoRadioGroup);
        scrollView.addView(sideRadioGroup);
        scrollView.addView(drinkRadioGroup);
        scrollView.addView(toppingRadioGroup);
        relativeLayout.addView(scrollView);


        Button goBackButton = new Button(this);
        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack(view);
            }
        });

        goBackButton.setText("Go Back!");

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.setMargins(0,0,0,50);

        relativeLayout.addView(goBackButton,params);




        setContentView(relativeLayout);


    }
    public void goBack(View v){
        this.finish();
    }





}



