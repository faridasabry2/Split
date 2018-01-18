package com.smith.split;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EqualSplit extends AppCompatActivity {

    private Check check;
    private EditText numberOfPeopleEditText;
    private EditText subTotalEditText;
    private EditText tipEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Intent intent = getIntent();

        numberOfPeopleEditText = (EditText) findViewById(R.id.people);
        subTotalEditText = (EditText) findViewById(R.id.total);
        tipEditText = (EditText) findViewById(R.id.tip);
    }

    public void splitAction (View view) {

        int numPeople = Integer.parseInt(numberOfPeopleEditText.getText().toString());
        float subTotal = Float.valueOf(subTotalEditText.getText().toString());
        float tip = Float.valueOf(tipEditText.getText().toString());

        check = new Check(subTotal, tip, numPeople);

        TextView splitTextView = (TextView) findViewById(R.id.person_pay_textView);

        float splitResult = check.splitEqually();
        splitTextView.setText(String.format("Each person pays $%.2f", splitResult));
    }
}