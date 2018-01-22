package com.smith.split;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class AssignItems extends AppCompatActivity {

    ArrayList<String> itemList;
    ArrayList<String> priceList;

    ArrayAdapter<String> itemAdapter;
    ArrayAdapter<String> priceAdapter;

    ListView itemsView;
    EditText itemName;
    EditText itemPrice;
    Button addButton;

    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_items);

        manager=getFragmentManager();

        itemsView = (ListView)findViewById(R.id.listView);
        addButton = (Button) findViewById(R.id.addItemButton);

        itemName = (EditText) findViewById(R.id.itemEditText);
        itemPrice = (EditText) findViewById(R.id.priceEditText);

//        itemName.addTextChangedListener(watcher);
//        itemPrice.addTextChangedListener(watcher);

        itemList = new ArrayList<>();
        priceList = new ArrayList<>();

//        loadItemList();

        // ADD ITEM WHEN button is clicked
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                itemList.add(itemName.getText().toString());
                itemName.setText("");
                itemAdapter.notifyDataSetChanged();

                priceList.add(itemPrice.getText().toString());
                itemPrice.setText("");
                priceAdapter.notifyDataSetChanged();

                Log.i("whatevs", itemList.toString() + " -- " + priceList.toString());
            }
        });
    }


    /*public void addA (View view){
        FragmentA f1 = new FragmentA();
        FragmentTransaction transaction= manager.beginTransaction();
        transaction.add(R.id.,f1,"A");
    }*/
}
