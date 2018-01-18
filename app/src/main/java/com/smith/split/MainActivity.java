package com.smith.split;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG =
            MainActivity.class.getSimpleName();
    ;

    //public static final String POST_KEY = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();


    }

    public void equalSplitButtonClick(View view) {

        Intent intent = new Intent(this, EqualSplit.class);
        startActivity(intent);

        Log.i(TAG, "We pressed the button!");
    }

    public void splitByItemButtonClick(View view) {

        Intent intent = new Intent(this, ItemSplit.class);
        startActivity(intent);

    }
}