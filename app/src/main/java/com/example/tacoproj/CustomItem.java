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
    private Button contOrder;

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



    public void selectionMenu(Activity activity, List<String> menuItems, String selectionLabel){
        //call --> selectionMenu(bTacos);
        //ADD label
        TextView label = new TextView(activity);
        LinearLayout.LayoutParams labelParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        label.setText(selectionLabel);
        addContentView(label,labelParams);

        //ADD items radio Buttons
        rbs = new RadioButton[menuItems.size()];
        params = new RadioGroup.LayoutParams[rbs.length];
        for (int i = 0; i < rbs.length; i++){

            rbs[i] = new RadioButton(activity);
            rbs[i].setGravity(Gravity.CENTER);
            rbs[i].setText(menuItems.get(i));

            params[i] = new RadioGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, menuItems.size());
            params[i].leftMargin = 0;
            params[i].topMargin = 10;
            addContentView(rbs[i], params[i]);
        }
    }

    public void addListenerOnButton(){

      /*  bMainOptions = (RadioGroup) findViewById(R.id.radioName);
        contOrder = (Button) findViewById(R.id.buttonName);
       */

        contOrder.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedId = bMainOptions.getCheckedRadioButtonId();
                //option = (RadioButton) findViewById(selectedId);
                //action to happen after button is pressed

            }
        });

    }


}



//https://mkyong.com/android/android-radio-buttons-example/