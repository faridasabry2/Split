package com.smith.split;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class EnterItems extends AppCompatActivity {

//    ArrayList<String> itemList;
//    ArrayList<String> priceList;
//
    ArrayAdapter<String> itemAdapter;
//    ArrayAdapter<String> priceAdapter;
//
//    EditText itemName;
//    EditText itemPrice;
//
//    Button addButton;
    ListView itemListView;

    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_items);

        dbHelper = new DbHelper(this);

        itemListView = (ListView) findViewById(R.id.listView);

//        itemsView = (ListView)findViewById(R.id.listView);
//        addButton = (Button) findViewById(R.id.addItemButton);
//
//        itemName = (EditText) findViewById(R.id.itemEditText);
//        itemPrice = (EditText) findViewById(R.id.priceEditText);
//
//        itemName.addTextChangedListener(watcher);
//        itemPrice.addTextChangedListener(watcher);
//
//        itemList = new ArrayList<>();
//        priceList = new ArrayList<>();

        loadItemList();

        // ADD ITEM WHEN button is clicked
//        addButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                itemList.add(itemName.getText().toString());
//                itemName.setText("");
//                itemAdapter.notifyDataSetChanged();
//
//                priceList.add(itemPrice.getText().toString());
//                itemPrice.setText("");
//                priceAdapter.notifyDataSetChanged();
//
//                Log.i("whatevs", itemList.toString() + " -- " + priceList.toString());
//            }
//        });
    }

//    private final TextWatcher watcher = new TextWatcher() {
//        @Override
//        public void beforeTextChanged(CharSequence s, int start, int count, int after)
//        { }
//        @Override
//        public void onTextChanged(CharSequence s, int start, int before, int count)
//        {}
//        @Override
//        public void afterTextChanged(Editable s) {
//            if (isEmpty(itemName) || isEmpty(itemPrice)) {
//                addButton.setEnabled(false);
//            } else {
//                addButton.setEnabled(true);
//            }
//        }
//    };

    private boolean isEmpty(EditText editText) {
        return editText.getText().toString().trim().length() == 0;
    }

    private void loadItemList() {
        ArrayList<String> itemList = dbHelper.getItemList();
        if(itemAdapter == null) {
            itemAdapter = new ArrayAdapter<String>(this, R.layout.row, R.id.item_name);
            itemListView.setAdapter(itemAdapter);
        } else {
            itemAdapter.clear();
            itemAdapter.addAll(itemList);
            itemAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        // Change menu icon
        Drawable icon = menu.getItem(0).getIcon();
        icon.mutate();
//        icon.setColorFilter(getResources().getColor(android.R.color.white, PorterDuff.Mode.SRC_IN));
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_item:
                final EditText itemEditText = new EditText(this);
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("Add New Item")
                        .setMessage("Enter Item Name")
                        .setView(itemEditText)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                String item = String.valueOf(itemEditText.getText());
                                dbHelper.insertNewItem(item);
                                loadItemList();
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                dialog.show();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

//   public void deleteItem(View view) {
//        View parent = (View) view.getParent();
//        TextView
//   }

}


