package com.rematchka.weddinghall;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class decor_option extends AppCompatActivity {
     Button add;
    Button edit;
    Button delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decor_option);
        add=(Button)findViewById(R.id.add_decor_option);
        edit=(Button)findViewById(R.id.edit_decor_option);
        delete=(Button)findViewById(R.id.delete_decor_option);
        delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent logointent= new Intent(decor_option.this,delete_decor.class);
                decor_option.this.startActivity(logointent);



            }
        });
        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent logointent= new Intent(decor_option.this,add_decor.class);
                decor_option.this.startActivity(logointent);



            }
        });
        edit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent logointent= new Intent(decor_option.this,_edit_decoration.class);
                decor_option.this.startActivity(logointent);



            }
        });
    }
}
