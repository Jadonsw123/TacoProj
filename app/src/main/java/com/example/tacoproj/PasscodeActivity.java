package com.example.tacoproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;

import com.hanks.passcodeview.PasscodeView;

import java.util.ArrayList;

public class PasscodeActivity extends AppCompatActivity {
    PasscodeView passcodeView;

    DatabaseManager dbManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passcode);

        //Bundle b=this.getIntent().getExtras();
        //ArrayList<String> order =b.getStringArrayList("order");
        //Log.w("test",order.get(0));

        dbManager = new DatabaseManager(this);


        passcodeView= findViewById(R.id.passcodeView);
        passcodeView.setPasscodeLength(4)
                .setLocalPasscode("1111")
                .setListener(new PasscodeView.PasscodeViewListener() {
                    @Override
                    public void onFail() {
                        Log.w("deez","failed password");
                        finish();
                    }

                    @Override
                    public void onSuccess(String number) {
                        for(Drink x: dbManager.selectAllDrinks())
                            Log.w("deez",x.getName());
                        Log.w("deez","THE THING ISNT WORKING");

                        startActivity(new Intent(PasscodeActivity.this,activity_admin.class));
                        finish();
                    }
                });
    }
}

//https://github.com/hanks-zyh/PasscodeView