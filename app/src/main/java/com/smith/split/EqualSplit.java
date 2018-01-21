package com.smith.split;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.smith.split.Models.Check;

public class EqualSplit extends AppCompatActivity {

    private Check check;
    private EditText numberOfPeopleEditText;
    private EditText subTotalEditText;
    private EditText tipEditText;
    private Button splitButton;
    private RadioGroup radioGroup;

    private double percentage;
    private int numPeople;
    private float tip;
    private float subTotal;
    private boolean customTip = false;
    private boolean defaultTip = false;

    private final String TAG = "Equal Split";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Intent intent = getIntent();

        numberOfPeopleEditText = (EditText) findViewById(R.id.people);
        subTotalEditText = (EditText) findViewById(R.id.total);
        tipEditText = (EditText) findViewById(R.id.tip);

        splitButton = (Button) findViewById(R.id.BUTTON);
        splitButton.setEnabled(false);

        numberOfPeopleEditText.addTextChangedListener(watcher);
        subTotalEditText.addTextChangedListener(watcher);
        tipEditText.addTextChangedListener(watcher);

        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Log.i(TAG, "default tip is checked");
                defaultTip = true;

                if(customTip) {
                    tipEditText.setText("");
                    customTip = false;
                }

                switch (checkedId) {
                    case R.id.radioButton10:
                        percentage = 0.1;
                        break;
                    case R.id.radioButton15:
                        percentage = 0.15;
                        break;
                    case R.id.radioButton20:
                        percentage = 0.2;
                        break;
                    default:
                        break;
                }
            }
        });


    }

    private final TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after)
        { }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count)
        {}
        @Override
        public void afterTextChanged(Editable s) {

            if (isEmpty(numberOfPeopleEditText) || isEmpty(subTotalEditText)) {
                splitButton.setEnabled(false);
            } else {
                splitButton.setEnabled(true);
            }

            if(!isEmpty(tipEditText)) {
                customTip = true;
                if(defaultTip) {
                    radioGroup.clearCheck();
                    defaultTip = false;
                }
            }
        }
    };

    private boolean isEmpty(EditText editText) {
        return editText.getText().toString().trim().length() == 0;
    }

    public void splitAction (View view) {

        numPeople = Integer.parseInt(numberOfPeopleEditText.getText().toString().trim());
        subTotal = Float.valueOf(subTotalEditText.getText().toString().trim());

        if(radioGroup.getCheckedRadioButtonId() != -1) {
            tip = subTotal * (float) percentage;
        } else if(!isEmpty(tipEditText)) {
            tip = Float.valueOf(tipEditText.getText().toString().trim());
        } else {
            tip = 0;
        }

        Log.i(TAG, "tip = " + String.valueOf(tip));

        check = new Check(subTotal, tip, numPeople);
        TextView splitTextView = (TextView) findViewById(R.id.person_pay_textView);

        float splitResult = check.splitEqually();
        splitTextView.setText(String.format("Each person pays $%.2f", splitResult));

    }
}