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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passcode);

        Bundle b=this.getIntent().getExtras();
        ArrayList<String> order =b.getStringArrayList("order");
        Log.w("test",order.get(0));


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

                        startActivity(new Intent(PasscodeActivity.this,UpdateActivity.class));
                        finish();
                    }
                });
    }
}

//https://github.com/hanks-zyh/PasscodeView