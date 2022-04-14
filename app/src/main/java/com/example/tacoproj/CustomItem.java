package com.example.tacoproj;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
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
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.event.*;


public class CustomItem extends Activity {

    private RadioGroup bMainOptions;
    //private RadioButton option;
    private Button continueOrder;

    private DatabaseManager dbManager;


    //use from each database
    private RadioButton[] breakfastItems;
    private List<String> bTacos;

    private RadioButton [] rbs;
    private RadioGroup.LayoutParams [] params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_item);

        addListenerOnButton();
    }

    public CustomItem(){
        //http://www.fredosaurus.com/notes-java/GUI/components/50radio_buttons/25radiobuttons.html

        ArrayList<Taco> tempTacos = dbManager.selectAllTacos();

        //BREAKFAST MENU
        for(Taco taco : tempTacos ){
            if(taco.getBreakfast().equals("true")){
                bTacos.add(taco.getName()) ;
            }
        }
        //BREAKFAST MENU

    }



    //activity is activity_custom_item.xml or CustomItem.java
    //menuItems is a String list of the customizations/selections for the group
    //selectionLabel is the label of the selection group
    //radioGroupName should fit the type of selection items ex: bMainItems or bMainOptions -->
    //                                                    --> for main type of taco for breakfast menu
    public void selectionMenu(Activity activity, List<String> menuItems, String selectionLabel, String radioGroupName){
        //call --> selectionMenu(bTacos);
        //ADD label
        TextView label = new TextView(activity);
        LinearLayout.LayoutParams labelParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        label.setText(selectionLabel);
        addContentView(label,labelParams);

        //ADD items radio Buttons
        RadioGroup radioGroup = new RadioGroup(activity);
        rbs = new RadioButton[menuItems.size()];
        params = new RadioGroup.LayoutParams[rbs.length];
        for (int i = 0; i < rbs.length; i++){

            rbs[i] = new RadioButton(activity);
            rbs[i].setGravity(Gravity.CENTER);
            rbs[i].setText(menuItems.get(i));
            radioGroup.addView(rbs[i]);


            params[i] = new RadioGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, menuItems.size());
            params[i].leftMargin = 0;
            params[i].topMargin = 10;
            addContentView(rbs[i], params[i]);
        }
    }

    //radioGroup is the name of the radio group in the scroll view
    // *make for loop to access all Radio Groups in ScrollView*
    public void addListenerOnButton(RadioGroup radioGroup){

        //int radioGroupId = radioGroup.getId();
        ScrollView menuOptions = (ScrollView) findViewById(R.id.selection);
        continueOrder = (Button) findViewById(R.id.completeSelection);


        continueOrder.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedId = radioGroup.getCheckedRadioButtonId();
                option = (RadioButton) findViewById(selectedId);
                //action to happen after button is pressed
                setContentView(R.layout.activity_main);

            }
        });

    }


}



//https://mkyong.com/android/android-radio-buttons-example/