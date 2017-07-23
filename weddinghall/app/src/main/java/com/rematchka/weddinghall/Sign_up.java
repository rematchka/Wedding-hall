package com.rematchka.weddinghall;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Sign_up extends AppCompatActivity {
    EditText f_name;
    EditText M_name;
    EditText L_name;
    EditText phone_no;
    EditText pass;
    EditText check_pass;
    Button  sign_up;
    EditText email;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        f_name=(EditText)findViewById(R.id.First_Name);
        M_name=(EditText)findViewById(R.id.Middle_name);
        L_name=(EditText)findViewById(R.id.last_name);
        phone_no=(EditText)findViewById(R.id.Phone_no);
        pass=(EditText)findViewById(R.id.password_sign_up);
        check_pass=(EditText)findViewById(R.id.password_check_sign_up);
        sign_up=(Button)findViewById(R.id.to_sign_up);
        email=(EditText)findViewById(R.id.email_sign_up);

    }
    public void onsignup(View view)
    {   sendtochecklogin x=new sendtochecklogin(this);
        String Type="signup";
        String usr_f_name=f_name.getText().toString();
        String usr_l_name=L_name.getText().toString();
        String usr_m_name=M_name.getText().toString();
        String usr_pass=pass.getText().toString();
        String usr_check_pass=check_pass.getText().toString();
        String usr_email1=email.getText().toString();
        String usr_no=phone_no.getText().toString();
        x.execute(Type,usr_f_name,usr_m_name,usr_l_name,usr_no,usr_email1,usr_pass,usr_check_pass);

    }
}
