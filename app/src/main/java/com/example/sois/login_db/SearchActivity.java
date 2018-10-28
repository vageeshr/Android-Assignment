package com.example.sois.login_db;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    EditText searchInput;
    Button searchButton;
    DBManager database;
    String name;
    List itm;
    ListView result_list;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        searchInput = findViewById(R.id.search_input);
        searchButton = findViewById(R.id.search_button);
        result_list = findViewById(R.id.result_list);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchButtonClick();
            }
        });

        itm = new ArrayList<>();
//        String[] listItems = new String[]{name};

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                itm);
        result_list.setAdapter(adapter);


    }

    void searchButtonClick() {
        String searchInputText = (searchInput.getText().toString());

        database = new DBManager(getApplicationContext());
        database.open();

        name = database.fetchByID(searchInputText);
        if (name != null) {
            Toast.makeText(getApplicationContext(), "Found", Toast.LENGTH_LONG).show();

//            String[] listItems = new String[]{name};
//
//            adapter=new ArrayAdapter<String>(this,
//                    android.R.layout.simple_list_item_1,
//                    listItems);
//            result_list.setAdapter(adapter);

            adapter.add(name);

        } else {
            Toast.makeText(getApplicationContext(), "Not Found", Toast.LENGTH_LONG).show();
        }


        database.close();

    }
}