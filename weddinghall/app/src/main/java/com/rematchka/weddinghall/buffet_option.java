package com.rematchka.weddinghall;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class buffet_option extends AppCompatActivity {
    Button add,edit,delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buffet_option);
        add=(Button)findViewById(R.id.add);
        edit=(Button)findViewById(R.id.edit);
        delete=(Button)findViewById(R.id.delete);
        add.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                //execute query
                Intent numbersIntent = new Intent(buffet_option.this, _add_buffet_item.class);
                startActivity(numbersIntent);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                //execute query
                Intent numbersIntent = new Intent(buffet_option.this, _delete_buffet_item.class);
                startActivity(numbersIntent);
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                //execute query
                Intent numbersIntent = new Intent(buffet_option.this, _edit_buffet_items.class);
                startActivity(numbersIntent);
            }
        });
    }
}
