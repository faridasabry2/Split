package com.smith.split;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class EqualSplit extends AppCompatActivity {

    private EditText input1;
    private EditText input2;
    private EditText input3;

    private TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Intent intent = getIntent();

        input1 = (EditText) findViewById(R.id.people);
        input2 = (EditText) findViewById(R.id.total);
        input3 = (EditText) findViewById(R.id.editText3);

        Button bt_calculate = (Button) findViewById(R.id.BUTTON);

        tv_result = (TextView) findViewById(R.id.tv_result);
    }
    public void splitAction (View view) {

        Button bt_calculate = (Button) findViewById(R.id.BUTTON);
        makeCalculations();
    }
    private void makeCalculations() {

     float n1 = Float.valueOf(input1.getText().toString());
     float n2 = Float.valueOf(input2.getText().toString());
     float n3 = Float.valueOf(input3.getText().toString());

     float result = (n2/n1)+(n3/n1);

     result = (float) (Math.round(result*100)/100);

     tv_result.setText("Each person pays " + result);
     }

}
