package com.example.tacoproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

public class DrinksMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinks_menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if(item.equals(R.id.AddTacoButton)){
            setContentView(R.layout.activity_custom_item);
        }

        switch (item.getItemId()) {

            case R.id.sprite:
                Log.w("Deez", "INVISIBLE BUTTON PRESSED");

                CustomItem.addToOrder()
        }
        return super.onOptionsItemSelected(item);
    }
}