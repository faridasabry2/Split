package com.smith.split;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;

public class ItemSplit extends AppCompatActivity {
    ArrayList<String> nameList;
    ArrayAdapter<String> adapter;
    ListView namesView;
    EditText nameText;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_split);

        namesView = (ListView)findViewById(R.id.listView);
        addButton = (Button) findViewById(R.id.addItemButton);
        nameText = (EditText) findViewById(R.id.nameEditText);

        nameList = new ArrayList<>();
        loadNameList();

        // ADD ITEM WHEN button is clicked
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                nameList.add(nameText.getText().toString());
                nameText.setText("");
                adapter.notifyDataSetChanged();
            }
        });

        // SWITCH TO CAMERA VIEW TO SCAN
        Button scanButton = (Button) findViewById(R.id.scanButton);

        scanButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Context packageContext = getApplicationContext();
                Intent intent = new Intent(packageContext, CameraCapture.class);
                startActivity(intent);
            }
        });

        // SWITCH TO MANUALLY ENTER ITEMS
        Button enterItemsButton = (Button) findViewById(R.id.enterItemsButton);

        enterItemsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Context packageContext = getApplicationContext();
                Intent intent = new Intent(packageContext, AssignItems.class);
                startActivity(intent);
            }
        });

    }

    private void loadNameList() {
        if (adapter == null) {
            adapter = new ArrayAdapter<>(this, R.layout.row, R.id.person_name, nameList);
            namesView.setAdapter(adapter);
        } else {
            adapter.clear();
            adapter.addAll(nameList);
            adapter.notifyDataSetChanged();
        }
    }


    public void deleteEntry(View view) {
        View parent = (View) view.getParent();
        TextView personTextView = (TextView) findViewById(R.id.person_name);
        String person = String.valueOf(personTextView.getText());
        nameList.remove(person);
        adapter.notifyDataSetChanged();

//        loadNameList();
    }

}

