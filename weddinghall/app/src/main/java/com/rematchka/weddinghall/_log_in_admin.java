package com.rematchka.weddinghall;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class _log_in_admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__log_in_admin);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle(" MENU ");
        actionBar.show();

        TextView editaccount = ( TextView) findViewById(R.id.edit_account_admin);
        editaccount.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                Intent numbersIntent = new Intent(_log_in_admin.this,_Edit_Account_Admin.class);
                startActivity(numbersIntent);
            }
        });

        TextView addadmin=(TextView)findViewById(R.id.add_admin_account);
        addadmin.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                Intent numbersIntent = new Intent(_log_in_admin.this, _add_admins.class);
                startActivity(numbersIntent);
            }
        });

        TextView option=(TextView)findViewById(R.id.edit_order);
        option.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                Intent numbersIntent = new Intent(_log_in_admin.this, edit_admin_db.class);
                startActivity(numbersIntent);
            }
        });

        TextView vieworders=(TextView)findViewById(R.id.view_orders);
        vieworders.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                Intent numbersIntent = new Intent(_log_in_admin.this, _admin_view_reser.class);
                startActivity(numbersIntent);
            }
        });

    }
}
