package com.example.sois.login_db;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText id;
    EditText name;
    Button InsertButton;
    String Id;
    String Name;

    private DBManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        id = (EditText) findViewById(R.id.editText2);
        name = (EditText) findViewById(R.id.editText3);

        InsertButton = (Button) findViewById(R.id.BtnInsert);


        //ImageButton viewB = findViewById(R.id.listOpB);



        InsertButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                db = new DBManager(getApplicationContext());
                db.open();
                Id = (id.getText().toString());
                Name = (name.getText().toString());
                int userId = db.Insert1(Id, Name);
                if (userId != -1) {


                    Toast.makeText(getApplicationContext(), "inserted", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getApplicationContext(), SearchActivity.class);
                    db.close();
                    startActivity(i);
                } else {
                    Toast.makeText(getBaseContext(), "ID already exists", Toast.LENGTH_LONG).show();
                    db.close();
                }
            }
        });

    }

}


