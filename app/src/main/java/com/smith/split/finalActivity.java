package com.smith.split;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class finalActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button ctBtn;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        Intent intent = getIntent();

        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        Spinner spinner2 = (Spinner) findViewById(R.id.spinner3);

        Spinner spinner3 = (Spinner) findViewById(R.id.spinner4);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.items_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner2.setAdapter(adapter);

        spinner3.setAdapter(adapter);

        ctBtn = (Button) findViewById(R.id.calculate);

        ctBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Context packageContext = getApplicationContext();
                Intent intent = new Intent(packageContext, PersonOwes.class);
                startActivity(intent);
            }
        });

    }
    public void onItemSelected(AdapterView<?> parent, View view,
    int pos, long id) {

        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)

        String sSelected = parent.getItemAtPosition(pos).toString();
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

}
