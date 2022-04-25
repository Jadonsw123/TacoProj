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


   /* private static final String FILENAME = "src/main/res/layout/activity_custom_item.xml";

    public static void main(String[] args) throws ParserConfigurationException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try(InputStream is = new FileInputStream(FILENAME)){
            DocumentBuilder db= dbf.newDocumentBuilder();
            Document doc = db.parse(is);
            NodeList menuSelections = doc.getElementsByTagName("selection");

            for
        }
    }*/





    private Button continueOrder;

    private DatabaseManager dbManager;


    //use from each database
    private RadioButton [] rbs;
    private RadioGroup.LayoutParams [] params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*setContentView(R.layout.activity_custom_item);*/

       if(MainActivity.get)
        if(isMorning()){
           setContentView(R.layout.activity_breakfast_menu);

       }
       else{
           setContentView(R.layout.activity_daytime_taco_menu);

       }


    }

    public CustomItem(){

    }




    //radioGroup is the name of the radio group in the scroll view
    // *make for loop to access all Radio Groups in ScrollView*
    public void addListenerOnButton(RadioGroup radioGroup){

        //int radioGroupId = radioGroup.getId();
        ScrollView menuOptions = (ScrollView) findViewById(R.id.selection);
        menuOptions.addView(radioGroup);
        continueOrder = (Button) findViewById(R.id.completeSelection);


        continueOrder.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                //int selectedId = radioGroup.getCheckedRadioButtonId();
                //option = (RadioButton) findViewById(selectedId);
                //action to happen after button is pressed
                setContentView(R.layout.activity_main);

            }
        });

    }


    public List<String> order;
    public void addToOrder(RadioButton selection){
        String addItem = (String) selection.getText();
        order.add(addItem);
    }

    public boolean isDrinksMenu(boolean displayed){



    }


}



//https://mkyong.com/android/android-radio-buttons-example/