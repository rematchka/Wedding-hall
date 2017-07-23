package com.rematchka.weddinghall;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Log_in extends AppCompatActivity {
   public  EditText username;
   public EditText pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
         username=(EditText)findViewById(R.id.email);
         pass=(EditText)findViewById(R.id.password);

    }

    public void onlogin(View view)
    {
        String user_name=username.getText().toString();
        String User_password=pass.getText().toString();
        sendtochecklogin x=new sendtochecklogin(this);
        String Type="login";
        x.execute(Type,user_name,User_password);
    }
}
