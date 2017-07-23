package com.rematchka.weddinghall;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class _log_in_menu_user extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__log_in_menu_user);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle(" MENU ");
        ;
        actionBar.show();
        TextView editaccount = ( TextView) findViewById(R.id.edit_account);
        TextView makesongorder = ( TextView) findViewById(R.id.make_order);
        TextView viewacc=(TextView)findViewById(R.id.view_account) ;


// Set a click listener on that View
       editaccount.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                Intent numbersIntent = new Intent(_log_in_menu_user.this, EditAccountClient.class);
                startActivity(numbersIntent);
            }
        });
        makesongorder.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                Intent numbersIntent = new Intent(_log_in_menu_user.this,_payment.class);
                startActivity(numbersIntent);
            }
        });
        viewacc.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                Intent numbersIntent = new Intent(_log_in_menu_user.this,_user_view_reservation.class);
                startActivity(numbersIntent);
            }
        });
//Fetching email from shared preferences
      /*  SharedPreferences sharedPreferences = this.getSharedPreferences(userinfo.SHARED_PREF_NAME,this.MODE_PRIVATE);
        String email = sharedPreferences.getString(userinfo.EMAIL_SHARED_PREF,"Not Available");
        String pass= sharedPreferences.getString(userinfo.pass_SHARED_PREF,"Not Available");

        //Showing the current logged in email to textview
        textView.setText("Current User: " + email);
        textView1.setText("Current User pass : " + pass);*/

        // actionBar.hide();

    }
}
