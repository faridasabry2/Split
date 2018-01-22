package com.smith.split;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class AssignItems extends AppCompatActivity {

    ArrayList<String> itemList;
    ArrayList<String> priceList;

    ArrayAdapter<String> itemAdapter;
    ArrayAdapter<String> priceAdapter;

    ListView itemsView;
    EditText itemName;
    EditText itemPrice;
    Button addButton;

    Button finalButton;

    //FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_items);


        itemsView = (ListView) findViewById(R.id.listView);
        addButton = (Button) findViewById(R.id.addItemButton);

        itemName = (EditText) findViewById(R.id.itemEditText);
        itemPrice = (EditText) findViewById(R.id.priceEditText);

        itemName.addTextChangedListener(watcher);
        itemPrice.addTextChangedListener(watcher);

        itemList = new ArrayList<>();
        priceList = new ArrayList<>();
        ArrayList <HashMap<String,String>> hashBrown = new ArrayList<HashMap<String, String>>();

        /*for(int i=0; i<itemName.length(); i++){
            HashMap<String,String> maps = new HashMap<>();
            HashMap.put("name", itemList.get(int));
            arrayList.add(hashBrown);
        }*/

        loadItemList();

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

        //send to finalActivity when pressing doneButton
        finalButton = (Button) findViewById(R.id.doneButton);

        finalButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Context packageContext = getApplicationContext();
                Intent intent = new Intent(packageContext, finalActivity.class);
                startActivity(intent);
            }
        });
    }

    private final TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (isEmpty(itemName) || isEmpty(itemPrice)) {
                addButton.setEnabled(false);
            } else {
                addButton.setEnabled(true);
            }
        }
    };

    private boolean isEmpty(EditText editText) {
        return editText.getText().toString().trim().length() == 0;
    }

    private void loadItemList() {
        if (itemAdapter == null) {
            itemAdapter = new SimpleAdapter<String>(this, R.layout.item_row, R.id.item_name, itemList);
            itemsView.setAdapter(itemAdapter);
        } else {
            itemAdapter.clear();
            itemAdapter.addAll(itemList);
            itemAdapter.notifyDataSetChanged();
        }

        if (priceAdapter == null) {
            priceAdapter = new ArrayAdapter<String>(this, R.layout.item_row, R.id.item_price, priceList);
            itemsView.setAdapter(priceAdapter);
        } else {
            priceAdapter.clear();
            priceAdapter.addAll(priceList);
            priceAdapter.notifyDataSetChanged();
        }
    }

    public void deleteEntry(View view) {
        View parent = (View) view.getParent();

        TextView itemTextView = (TextView) findViewById(R.id.item_name);
        TextView priceTextView = (TextView) findViewById(R.id.item_price);

        String item = String.valueOf(itemTextView.getText());
        String price = String.valueOf(priceTextView.getText());

        itemList.remove(item);
        priceList.remove(price);

        itemAdapter.notifyDataSetChanged();
        priceAdapter.notifyDataSetChanged();
    }
}


