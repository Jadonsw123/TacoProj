package com.example.tacoproj;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
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

//https://mkyong.com/java/how-to-modify-xml-file-in-java-dom-parser/
public class CustomItem extends AppCompatActivity {

    Button continueOrder;

    DatabaseManager dbManager = new DatabaseManager(this);

    //VIEWS
    String menu;
    RadioGroup radioGroup;
    RadioGroup rgDrinks = (RadioGroup)findViewById(R.id.drinks);
    RadioGroup rgSides = (RadioGroup)findViewById(R.id.sides);
    RadioGroup rgBTacos = (RadioGroup)findViewById(R.id.mainBreakfast);
    RadioGroup rgDTacos = (RadioGroup)findViewById(R.id.mainDaytime);
//https://www.geeksforgeeks.org/android-how-to-add-radio-buttons-in-an-android-application/
    //'menu' input can be: "drinks", "sides", "btaco", "dtaco"
    public CustomItem(){
        /*if(menu.equals("drinks")){
            radioGroup
        }*/
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*setContentView(R.layout.activity_custom_item);*/
        continueOrder = (Button)findViewById(R.id.completeSelection);
        radioGroup = rgDrinks;

        radioGroup.clearCheck();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton)group.findViewById(checkedId);
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
                    RadioButton radioButton = (RadioButton)radioGroup.findViewById(selectedId);
                    //ADD TO ORDER
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


}



//https://mkyong.com/android/android-radio-buttons-example/