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


    private static final String FILENAME = "src/main/res/layout/activity_custom_item.xml";

    public static void main(String[] args) throws ParserConfigurationException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try(InputStream is = new FileInputStream(FILENAME)){
            DocumentBuilder db= dbf.newDocumentBuilder();
            Document doc = db.parse(is);
            NodeList menuSelections = doc.getElementsByTagName("selection");

            for
        }
    }




    private RadioGroup bMainOptions;
    //private RadioButton option;
    private Button continueOrder;

    private DatabaseManager dbManager;


    //use from each database
    private RadioButton[] breakfastItems;
    public List<String> bTacos;

    private RadioButton [] rbs;
    private RadioGroup.LayoutParams [] params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_item);

       selectionMenu(this, bTacos, "Select Breakfast Taco"/*, "bMainOptions"*/);
       addListenerOnButton(radioGroup);
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


    public RadioGroup radioGroup;
    //activity is activity_custom_item.xml or CustomItem.java
    //menuItems is a String list of the customizations/selections for the group
    //selectionLabel is the label of the selection group
    //radioGroupName should fit the type of selection items ex: bMainItems or bMainOptions -->
    //                                                    --> for main type of taco for breakfast menu
    public void selectionMenu(Context context, List<String> menuItems, String selectionLabel/*, String radioGroupName*/){
        //call --> selectionMenu(bTacos);
        //ADD label
        TextView label = new TextView(context);
        LinearLayout.LayoutParams labelParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        label.setText(selectionLabel);
        addContentView(label,labelParams);

        //ADD items radio Buttons
        radioGroup = new RadioGroup(context);
        rbs = new RadioButton[menuItems.size()];
        params = new RadioGroup.LayoutParams[rbs.length];
        for (int i = 0; i < rbs.length; i++){

            rbs[i] = new RadioButton(context);
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


}



//https://mkyong.com/android/android-radio-buttons-example/