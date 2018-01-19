package com.smith.split;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class ItemSplit extends AppCompatActivity {
    ArrayList<String> nameList;
    ArrayAdapter<String> adapter;
    EditText nameText;
    Button addButton;
    ListView lview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_split);

        lview = (ListView)findViewById(R.id.listView);
        nameText = findViewById(R.id.editText);
        addButton = (Button) findViewById(R.id.addButton);

        nameList = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice,nameList);

        View.OnClickListener addListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameList.add(nameText.getText().toString());
                nameText.setText("");
                adapter.notifyDataSetChanged();
            }
        };
   //     addButton.setOnClickListener(addListener);

 //       lview.setAdapter(adapter);
        lview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                SparseBooleanArray checkposition = lview.getCheckedItemPositions();
                int count = lview.getCount();

                for(int item = count-1; item >=0; item--){
                    if (checkposition.get(item)){
                        adapter.remove(nameList.get(item));
                    }
                }
                checkposition.clear();
                adapter.notifyDataSetChanged();

               // return false;
            }
        });
        addButton.setOnClickListener(addListener);

        lview.setAdapter(adapter);
    }

}

