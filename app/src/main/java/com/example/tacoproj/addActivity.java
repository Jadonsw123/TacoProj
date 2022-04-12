package com.example.tacoproj;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.CDATASection;

import java.util.ArrayList;

public class addActivity extends AppCompatActivity {

    DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        dbManager = new DatabaseManager(this);

    }

    public void goBack(View v){
        this.finish();
    }

    public void insert(View v)
    {
        //retrieve name  and  price
        EditText nameEditText =
                ( EditText) findViewById( R.id.input_name );
        EditText priceEditText =
                ( EditText) findViewById( R.id.input_price );
        EditText breakfastEditText =
                ( EditText) findViewById( R.id.input_breakfast );
        EditText typeEditText =
                ( EditText) findViewById( R.id.input_type );
        String name = nameEditText.getText( ).toString( );
        String priceString = priceEditText.getText( ).toString( );
        String breakfast = breakfastEditText.getText( ).toString( ).toLowerCase();
        String type = typeEditText.getText( ).toString( );



        //insert new candy in database
        //dbManager.insert(new Candy(0, name, Double.parseDouble(priceString)));
        //Toast.makeText( this, "Candy added", 	Toast.LENGTH_LONG ).show( );

        switch(type) {
            case "Taco":
                dbManager.insertTaco( new Taco(3453, name, Double.parseDouble(priceString), "true", breakfast));
                Log.w("test:", dbManager.selectTacoByName(name).toString());
                break;
            case "Drink":
                dbManager.insertDrink(new Drink(2222, name, Double.parseDouble(priceString), "true", breakfast));
                Log.w("test:", dbManager.selectDrinkByName(name).toString());
                break;
            case "Side":
                dbManager.insertSide(new Side(423, name, Double.parseDouble(priceString), "true", breakfast));
                Log.w("test:", dbManager.selectSideByName(name).toString());
                break;
            case "Topping":
                dbManager.insertTopping(new Topping(543, name, Double.parseDouble(priceString), "true", breakfast));
                Log.w("test:", dbManager.selectToppingByName(name).toString());
                break;
        }


        //ArrayList<Candy> candies = dbManager.selectAll( );
        //for( Candy candy : candies )
        //    Log.w( "MainActivity", "candy = " + candy.toString( ) );



        //clear data in the two EditTexts
        nameEditText.setText( "" );
        priceEditText.setText( "" );
        breakfastEditText.setText("");
        typeEditText.setText("");
    }
}
