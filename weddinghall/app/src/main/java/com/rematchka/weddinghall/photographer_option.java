package com.rematchka.weddinghall;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class photographer_option extends AppCompatActivity {
    Button add,edit,delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photographer_option);
        add=(Button)findViewById(R.id.add_photographer);
        edit=(Button)findViewById(R.id.edit_photographer);
        delete=(Button)findViewById(R.id.delete_photographer);
        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent logointent= new Intent(photographer_option.this,add_photographer.class);
                photographer_option.this.startActivity(logointent);



            }
        });

        delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
               //execute delete
                Intent logointent= new Intent(photographer_option.this,delete_photographer.class);
                photographer_option.this.startActivity(logointent);



            }
        });
        edit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //execute delete
                Intent logointent= new Intent(photographer_option.this,_edit_photographer.class);
                photographer_option.this.startActivity(logointent);



            }
        });

    }
}
