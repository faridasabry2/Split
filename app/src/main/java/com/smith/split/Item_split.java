package com.smith.split;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.Arrays;

public class Item_split extends AppCompatActivity {
    //ListView listView;
    private ArrayList<String> arrayList;
    private ArrayAdapter<String> adapter;
    private EditText txtInput;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_split);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ListView listView = (ListView)findViewById(R.id.listv);
        String[] names = {""};
        arrayList = new ArrayList<>(Arrays.asList(names));
        adapter = new ArrayAdapter<String>(this, R.layout. activity_main);
        listView.setAdapter(adapter);
        txtInput =(EditText) findViewById(R.id.name_item_split);
        Button addbutton = (Button) findViewById(R.id.add_button);
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newName =txtInput.getText().toString();
                arrayList.add(newName);
                adapter.notifyDataSetChanged();
            }
        });

    }
}
