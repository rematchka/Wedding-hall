package com.rematchka.weddinghall;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

public class _add_admins extends AppCompatActivity {
    EditText f_name;
    EditText M_name;
    EditText L_name;
    EditText phone_no;
    EditText pass;
    EditText check_pass;
    Button add_admin;
    EditText email;
    private static int SPLASH_TIME_OUT=2000;
    AlertDialog alertDialog;
    String type;
    String result;
    private boolean loggedIn = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__add_admins);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        f_name=(EditText)findViewById(R.id.First_Name_new_admin);
        M_name=(EditText)findViewById(R.id.Middle_name_new_admin);
        L_name=(EditText)findViewById(R.id.last_name_new_admin);
        phone_no=(EditText)findViewById(R.id.Phone_no_new_admin);
        pass=(EditText)findViewById(R.id.password_sign_up_new_admin);
        check_pass=(EditText)findViewById(R.id.password_check_sign_up_new_admin);
        add_admin=(Button)findViewById(R.id._new_admin);
        email=(EditText)findViewById(R.id.email_sign_up_new_admin);
        add_admin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                type="set";
                String usr_f_name=f_name.getText().toString();
                String usr_l_name=L_name.getText().toString();
                String usr_m_name=M_name.getText().toString();
                String usr_pass=pass.getText().toString();
                String usr_email1=email.getText().toString();
                String usr_no=phone_no.getText().toString();
                String usr_chckpass=check_pass.getText().toString();
                new JSONParse().execute(usr_f_name,usr_m_name,usr_l_name,usr_no,usr_email1,usr_pass,usr_chckpass);

            }
        });
    }
    private class JSONParse extends AsyncTask<String, String, String> {
        private ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            alertDialog=new AlertDialog.Builder(_add_admins.this).create();
            alertDialog.setTitle("Upadting Data");
            pDialog = new ProgressDialog(_add_admins.this);
            pDialog.setMessage("Getting Data ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

        }


        @Override
        protected String doInBackground(String... args) {

           {


               //Fetching the boolean value form sharedpreferences

                String login_URL = "http://2f179dfb.ngrok.io/addadmin.php";
                String usr_f_name=args[0];
                String usr_l_name=args[2];
                String usr_m_name=args[1];
                String usr_pass=args[5];
                String usr_check_pass=args[6];
                String usr_email1=args[4];
                String usr_no=args[3];
               if(!(usr_check_pass.equals(usr_pass))) {

                   return "Passwords doesn't match";
               }
                try {
                    URL url = new URL(login_URL);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data= URLEncoder.encode("usr_f_name","UTF-8")+"="+URLEncoder.encode(usr_f_name,"UTF-8")+"&"+URLEncoder.encode("usr_l_name","UTF-8")+"="+URLEncoder.encode(usr_l_name,"UTF-8")+"&"+ URLEncoder.encode("usr_m_name","UTF-8")+"="+URLEncoder.encode(usr_m_name,"UTF-8")+"&"+ URLEncoder.encode("usr_pass","UTF-8")+"="+URLEncoder.encode(usr_pass,"UTF-8")+"&"+ URLEncoder.encode("usr_no","UTF-8")+"="+URLEncoder.encode(usr_no,"UTF-8")+"&"+ URLEncoder.encode("usr_email1","UTF-8")+"="+URLEncoder.encode(usr_email1,"UTF-8");
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    result = "";
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        result += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                  return result;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            pDialog.dismiss();
           if(result.equals(" sign up succsseful !!! hello"))
           {
               new Handler().postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       Intent logointent= new Intent(_add_admins.this,_log_in_admin.class);
                       _add_admins.this.startActivity(logointent);
                   }
               },SPLASH_TIME_OUT);
               alertDialog.setMessage("Added Successfully");
               alertDialog.show();
           }
            else {
               alertDialog.setMessage(result);
               alertDialog.show();
           }

        }
    }
}
