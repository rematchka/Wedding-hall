package com.rematchka.weddinghall;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class accs_option extends AppCompatActivity {
    Button add;
    Button edit;
    Button delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accs_option);
        add=(Button)findViewById(R.id.add_accs);
        edit=(Button)findViewById(R.id.edit_accs);
        delete=(Button)findViewById(R.id.delete_acces);
        add.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                //execute query
                Intent numbersIntent = new Intent(accs_option.this, _add_accs.class);
                startActivity(numbersIntent);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                //execute query
                Intent numbersIntent = new Intent(accs_option.this,_delete_accs.class);
                startActivity(numbersIntent);
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                //execute query
                Intent numbersIntent = new Intent(accs_option.this,_edit_accs.class);
                startActivity(numbersIntent);
            }
        });

    }
}
