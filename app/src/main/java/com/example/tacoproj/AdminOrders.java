package com.example.tacoproj;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
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

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import org.w3c.dom.CDATASection;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class AdminOrders extends AppCompatActivity {

    DatabaseManager dbManager;
    ArrayList<String> orders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // This callback will only be called when MyFragment is at least Started.

        dbManager = new DatabaseManager(this);
        orders = dbManager.getAllOrders();
        Log.w("test",orders.toString());
        setContentView(R.layout.activity_cart);
        updateView();
    }

    public void updateView() {
        orders = dbManager.getAllOrders();
//        ScrollView outerScrollView;
//
        RelativeLayout relativeLayout = new RelativeLayout(this);

        GridLayout gridLayout = new GridLayout(this);
        gridLayout.setColumnCount(2);

        Button[] buttons = new Button[orders.size()];

        for(int i = 0; i<orders.size();i++)
        {
            TextView deez = new TextView(this);
            deez.setText(orders.get(i).toString());
            //deez.setId(dbManager.getOrderIdByName(orders.get(i)));
            deez.setTextSize(25);

            buttons[i] = new Button(this);
            buttons[i].setId(dbManager.getOrderIdByName(orders.get(i)));
            buttons[i].setText("FINISH ORDER");
            buttons[i].setTextSize(25);

            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dbManager.deleteOrderById(view.getId());
                    updateView();
                }
            });

            gridLayout.addView(deez);
            gridLayout.addView(buttons[i]);

        }

//
        relativeLayout.addView(gridLayout);
//        relativeLayout.addView(innerScrollView);
        setContentView(relativeLayout);
    }

    public void goBack(View v) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("newOrder", orders);
        setResult(Activity.RESULT_OK, resultIntent);
        this.finish();
    }
    @Override
    public void onBackPressed() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("newOrder", orders);
        setResult(Activity.RESULT_OK, resultIntent);
        this.finish();
    }

}



