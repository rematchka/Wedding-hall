package com.rematchka.weddinghall;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class edit_admin_db extends AppCompatActivity {
    Button buffet_option,photograpghe_roption,packagk_option,acc_option,decor_option;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_admin_db);
        buffet_option=(Button)findViewById(R.id.buffetOptions);
        photograpghe_roption=(Button)findViewById(R.id.photographyOptions);
        packagk_option=(Button)findViewById(R.id.packagesOptions);
        acc_option=(Button)findViewById(R.id.accessoriesOptions);
        decor_option=(Button)findViewById(R.id.decorationOptions);

        buffet_option.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent logointent= new Intent(edit_admin_db.this,buffet_option.class);
                edit_admin_db.this.startActivity(logointent);



            }
        });

        photograpghe_roption.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent logointent= new Intent(edit_admin_db.this,photographer_option.class);
                edit_admin_db.this.startActivity(logointent);



            }
        });
       packagk_option.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent logointent= new Intent(edit_admin_db.this,pckg_option.class);
                edit_admin_db.this.startActivity(logointent);



            }
        });
        acc_option.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent logointent= new Intent(edit_admin_db.this,accs_option.class);
                edit_admin_db.this.startActivity(logointent);



            }
        });
        decor_option.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent logointent= new Intent(edit_admin_db.this,decor_option.class);
                edit_admin_db.this.startActivity(logointent);



            }
        });


    }
}
