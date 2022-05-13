package com.example.tacoproj;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import static com.example.tacoproj.MainActivity.menuButton;

//https://mkyong.com/java/how-to-modify-xml-file-in-java-dom-parser/
public class CustomItem extends AppCompatActivity {

    Button continueOrder;

    DatabaseManager dbManager = new DatabaseManager(this);

    //VIEWS
    RadioGroup radioGroup;
    RadioGroup rgDrinks;
    RadioGroup rgSides;
    RadioGroup rgBTacos;
    RadioGroup rgDTacos;

    String menu;
    Bundle b;

//https://www.geeksforgeeks.org/android-how-to-add-radio-buttons-in-an-android-application/
    //'menu' input can be: "drinks", "sides", "btaco", "dtaco"
//    public CustomItem(){
//
//        rgDrinks = findViewById(R.id.drinks);
//        rgSides = findViewById(R.id.sides);
//        rgBTacos = findViewById(R.id.mainBreakfast);
//        rgDTacos = findViewById(R.id.mainDaytime);
//        /*if(menu.equals("drinks")){
//            radioGroup =
//        }*/
//    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b=this.getIntent().getExtras();
        menu =b.getString("menu");
        /*b=MainActivity.menuIntent.getExtras();
        menu =b.getString("menu");*/


        switch (menu) {
            case "drinks":
                radioGroup = rgDrinks;
                continueOrder = findViewById(R.id.completeDrinkSelection);
                setContentView(R.layout.activity_drinks_menu);
                break;
            case "sides":
                radioGroup = rgSides;
                continueOrder = findViewById(R.id.completeSideSelection);
                setContentView(R.layout.activity_sides_menu);
                break;
            case "btaco":
                radioGroup = rgBTacos;
                continueOrder = findViewById(R.id.completeBTacoSelection);
                setContentView(R.layout.activity_breakfast_menu);
                break;
            case "dtaco":
                radioGroup = rgDTacos;
                continueOrder = findViewById(R.id.completeDTacoSelection);
                setContentView(R.layout.activity_daytime_taco_menu);
                break;
        }

        //buttonClick(menuButton);

        //continueOrder = findViewById(R.id.completeSelection);

        /*radioGroup.clearCheck();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = group.findViewById(checkedId);
            }
        });
        continueOrder.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v)
            {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                if (selectedId == -1){
                    Toast.makeText(CustomItem.this*//*activity*//*,"No item has been selected", Toast.LENGTH_SHORT).show();
                }
                else{
                    RadioButton radioButton = radioGroup.findViewById(selectedId);
                    //ADD TO ORDER
                    addToOrder(radioButton);
                }
            }
        });*/


    }

    public void radioButtonHandler(RadioGroup rgMenu, Button contButton){
        radioGroup.clearCheck();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = group.findViewById(checkedId);
            }
        });
        continueOrder.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v)
            {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                if (selectedId == -1){
                    Toast.makeText(CustomItem.this/*activity*/,"No item has been selected", Toast.LENGTH_SHORT).show();
                }
                else{
                    RadioButton radioButton = radioGroup.findViewById(selectedId);
                    //ADD TO ORDER
                    addToOrder(radioButton);
                }
            }
        });
    }



    /*public void updateMenuItems(RadioGroup menuItems, String change){
        radioGroup = menuItems;

    }*/


    public void addListenerOnButton(RadioGroup radioGroup){

        //int radioGroupId = radioGroup.getId();
        ScrollView menuOptions = (ScrollView) findViewById(R.id.selection);
        menuOptions.addView(radioGroup);
        continueOrder = (Button) findViewById(R.id.completeSelection);


        continueOrder.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {


                setContentView(R.layout.activity_main);

            }
        });

    }


    public List<String> order;
    public void addToOrder(RadioButton selection){
        String addItem = (String) selection.getText();
        order.add(addItem);
    }
    
    public void onMenuButtonClick(Button button){
        Intent menuView;
        switch (button.getId()){

            case R.id.AddDrinkButton:
                menuView = new Intent(this,DrinksMenu.class);
                this.startActivity(menuView);
                break;
            case R.id.AddSideButton:
                menuView = new Intent(this,SidesMenu.class);
                this.startActivity(menuView);
                break;
            case R.id.AddTacoButton:
                menuView = new Intent(this,DaytimeTacoMenu.class);
                this.startActivity(menuView);
                break;
        }

    }

     //Intent menuIntent;
//     public void buttonClick (Button button) {
//if(button == null)return;
//         switch (button.getId()) {
//
//            case R.id.AddDrinkButton:
//                Log.w("Deez", "Nuts drink");
//                radioGroup = rgDrinks;
//                continueOrder = findViewById(R.id.completeDrinkSelection);
//                menuIntent = new Intent(this, DrinksMenu.class);
//                this.startActivity(menuIntent);
//                break;
//            case R.id.AddSideButton:
//                Log.w("Deez", "Nuts side");
//                radioGroup = rgSides;
//                continueOrder = findViewById(R.id.completeSideSelection);
//                menuIntent = new Intent(this, Side.class);
//                this.startActivity(menuIntent);
//                break;
//
//            case R.id.AddTacoButton:
//                Log.w("Deez", "Nuts taco");
//                radioGroup = rgDTacos;
//                continueOrder = findViewById(R.id.completeDTacoSelection);
//                menuIntent = new Intent(this, DaytimeTacoMenu.class);
//                this.startActivity(menuIntent);
//                break;
//
//        }
//        //return super.buttonClick(button);
//    }


}





//https://mkyong.com/android/android-radio-buttons-example/