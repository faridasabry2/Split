package com.smith.split;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG =
    MainActivity.class.getSimpleName();;

    //public static final String POST_KEY = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonAction (View view) {

        Intent intent = new Intent(this, EqualSplit.class);
        startActivity(intent);
        Log.i(TAG,"We pressed the button!");
    }
}
