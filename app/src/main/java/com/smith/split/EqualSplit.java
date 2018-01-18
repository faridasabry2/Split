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
import android.widget.TextView;

public class EqualSplit extends AppCompatActivity {

    private Check check;
    private EditText numberOfPeopleEditText;
    private EditText subTotalEditText;
    private EditText tipEditText;
    private Button splitButton;

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
            if (isEmpty(numberOfPeopleEditText) || isEmpty(subTotalEditText) || isEmpty(tipEditText)) {
                splitButton.setEnabled(false);
            } else {
                splitButton.setEnabled(true);
            }
        }
    };

    private boolean isEmpty(EditText editText) {
        return editText.getText().toString().trim().length() == 0;
    }

    public void splitAction (View view) {

        int numPeople = Integer.parseInt(numberOfPeopleEditText.getText().toString().trim());
        float subTotal = Float.valueOf(subTotalEditText.getText().toString().trim());
        float tip = Float.valueOf(tipEditText.getText().toString().trim());

        check = new Check(subTotal, tip, numPeople);

        TextView splitTextView = (TextView) findViewById(R.id.person_pay_textView);

        float splitResult = check.splitEqually();
        splitTextView.setText(String.format("Each person pays $%.2f", splitResult));

    }
}