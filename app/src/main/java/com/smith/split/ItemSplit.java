package com.smith.split;

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

        addButton.setEnabled(false);
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

        nameText.addTextChangedListener(watcher);

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
                Intent intent = new Intent(packageContext, EnterItems.class);
                startActivity(intent);
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
            if (isEmpty(nameText)) {
                addButton.setEnabled(false);
            } else {
                addButton.setEnabled(true);
            }
        }
    };

    private boolean isEmpty(EditText editText) {
        return editText.getText().toString().trim().length() == 0;
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

        Log.i("TAG", person.toString());

        nameList.remove(person);
        adapter.notifyDataSetChanged();
    }

}

