package com.rematchka.weddinghall;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class pckg_option extends AppCompatActivity {
    Button add;
    Button delete;
    Button edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pckg_option);
        add=(Button)findViewById(R.id.add_package);
        delete=(Button)findViewById(R.id.delete_package);
        edit=(Button) findViewById(R.id.edit_package);
        add.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                //execute query
                Intent numbersIntent = new Intent(pckg_option.this, _add_package.class);
                startActivity(numbersIntent);
            }
        });
       delete.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                //execute query
                Intent numbersIntent = new Intent(pckg_option.this, delete_package.class);
                startActivity(numbersIntent);
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                //execute query
                Intent numbersIntent = new Intent(pckg_option.this, _edit_packg.class);
                startActivity(numbersIntent);
            }
        });

    }
    }

