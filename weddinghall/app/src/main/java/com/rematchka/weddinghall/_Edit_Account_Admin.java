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

public class _Edit_Account_Admin extends AppCompatActivity {

    EditText f_name;
    EditText M_name;
    EditText L_name;
    EditText phone_no;
    EditText pass;
    Button edit;
    EditText email;
    private static int SPLASH_TIME_OUT=2000;
    AlertDialog alertDialog;
    private boolean loggedIn = false;




    private static final String TAG_pass = "userPassword";
    private static final String TAG_l_name = "lName";
    private static final String TAG_NAME = "fName";
    private static final String TAG_EMAIL = "email";
    private static final String TAG_m_name = "mInint";
    private static final String TAG_no = "phoneNum";
    String type;
    String result;



    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account_client);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(" Edit Account ");
        actionBar.show();
        f_name = (EditText) findViewById(R.id.First_Name_Edit);
        M_name = (EditText) findViewById(R.id.Middle_name_Edit);
        L_name = (EditText) findViewById(R.id.last_name_Edit);
        phone_no = (EditText) findViewById(R.id.Phone_no_Edit);
        pass = (EditText) findViewById(R.id.password_sign_up_Edit);
        edit = (Button) findViewById(R.id.edit);
        email = (EditText) findViewById(R.id.email_sign_up_Edit);

        SharedPreferences sharedPreferences = this.getSharedPreferences(userinfo.SHARED_PREF_NAME, this.MODE_PRIVATE);
        final String email1 = sharedPreferences.getString(userinfo.EMAIL_SHARED_PREF, "Not Available");
        final String pass1 = sharedPreferences.getString(userinfo.pass_SHARED_PREF, "Not Available");
        email.setText(email1);
        pass.setText(pass1);
        type="get";
        new JSONParse().execute(email1, pass1,type);
        edit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                type="set";
                String usr_f_name=f_name.getText().toString();
                String usr_l_name=L_name.getText().toString();
                String usr_m_name=M_name.getText().toString();
                String usr_pass=pass.getText().toString();
                String usr_email1=email.getText().toString();
                String usr_no=phone_no.getText().toString();
                new JSONParse().execute(usr_f_name,usr_m_name,usr_l_name,usr_no,usr_email1,usr_pass,type);

            }
        });



    }


    private class JSONParse extends AsyncTask<String, String, JSONObject> {
        private ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            alertDialog=new AlertDialog.Builder(_Edit_Account_Admin.this).create();
            alertDialog.setTitle("Upadting Data");
            pDialog = new ProgressDialog(_Edit_Account_Admin.this);
            pDialog.setMessage("Getting Data ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

        }


        @Override
        protected JSONObject doInBackground(String... args) {
            String t = args[2];
            if (t.equals("get")) {
                HTTPHandler sh = new HTTPHandler();
                String login_URL = "http://2f179dfb.ngrok.io/editacoountuser.php";
                try {
                    SharedPreferences sharedPreferences;
                    sharedPreferences = _Edit_Account_Admin.this.getSharedPreferences(userinfo.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                    SharedPreferences sharedPreferences1;
                    sharedPreferences1 =_Edit_Account_Admin.this.getSharedPreferences(userinfo.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                    //Fetching the boolean value form sharedpreferences
                    loggedIn = sharedPreferences.getBoolean(userinfo.LOGGEDIN_SHARED_PREF, false);
                    String email1 = args[0];
                    String pass = args[1];
                    URL url = new URL(login_URL);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("email1", "UTF-8") + "=" + URLEncoder.encode(email1, "UTF-8") + "&" + URLEncoder.encode("pass", "UTF-8") + "=" + URLEncoder.encode(pass, "UTF-8");
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
                    SharedPreferences sharedPreferences2 = _Edit_Account_Admin.this.getSharedPreferences(userinfo.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                    //Creating editor to store values to shared preferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    //Adding values to editor
                    editor.putBoolean(userinfo.LOGGEDIN_SHARED_PREF, true);
                    editor.putString(userinfo.EMAIL_SHARED_PREF, email1);


                    SharedPreferences sharedPreferences3 = _Edit_Account_Admin.this.getSharedPreferences(userinfo.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                    //Creating editor to store values to shared preferences
                    SharedPreferences.Editor editor1 = sharedPreferences.edit();

                    //Adding values to editor
                    editor1.putBoolean(userinfo.LOGGEDIN_SHARED_PREF, true);
                    editor1.putString(userinfo.pass_SHARED_PREF, pass);

                    editor1.commit();
                    //Saving values to editor
                    editor.commit();
                    Log.e("RESULT", result);
                    JSONObject jsonObject = new JSONObject(result);

                    JSONArray result1 = jsonObject.getJSONArray("result");
                    JSONObject collegeData = result1.getJSONObject(0);
                    return collegeData;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;

            }
            else
            {  SharedPreferences sharedPreferences;
                sharedPreferences = _Edit_Account_Admin.this.getSharedPreferences(userinfo.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                SharedPreferences sharedPreferences1;
                sharedPreferences1 = _Edit_Account_Admin.this.getSharedPreferences(userinfo.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                //Fetching the boolean value form sharedpreferences
                loggedIn = sharedPreferences.getBoolean(userinfo.LOGGEDIN_SHARED_PREF, false);
                String login_URL = "http://2f179dfb.ngrok.io/edituserdata.php";
                String usr_f_name=args[0];
                String usr_l_name=args[2];
                String usr_m_name=args[1];
                String usr_pass=args[5];
                String usr_check_pass=args[6];
                String usr_email1=args[4];
                String usr_no=args[3];
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
                    SharedPreferences sharedPreferences2 = _Edit_Account_Admin.this.getSharedPreferences(userinfo.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                    //Creating editor to store values to shared preferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    //Adding values to editor
                    editor.putBoolean(userinfo.LOGGEDIN_SHARED_PREF, true);
                    editor.putString(userinfo.EMAIL_SHARED_PREF, usr_email1);


                    SharedPreferences sharedPreferences3 = _Edit_Account_Admin.this.getSharedPreferences(userinfo.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                    //Creating editor to store values to shared preferences
                    SharedPreferences.Editor editor1 = sharedPreferences.edit();

                    //Adding values to editor
                    editor1.putBoolean(userinfo.LOGGEDIN_SHARED_PREF, true);
                    editor1.putString(userinfo.pass_SHARED_PREF, usr_pass);

                    editor1.commit();
                    //Saving values to editor
                    editor.commit();
                    JSONObject jsonObject = new JSONObject(result);

                    JSONArray result1 = jsonObject.getJSONArray("result");
                    JSONObject collegeData = result1.getJSONObject(0);
                    return collegeData;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
            return null;
        }

        @Override
        protected void onPostExecute(JSONObject json) {
            pDialog.dismiss();
            if(type.equals("get"))

            {
                try {
                    f_name.setText(json.getString(TAG_NAME));
                    M_name.setText(json.getString(TAG_m_name));
                    L_name.setText(json.getString(TAG_l_name));
                    phone_no.setText(json.getString(TAG_no));
                    pass.setText(json.getString(TAG_pass));
                    email.setText(json.getString(TAG_EMAIL));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else
            { if(result.equals(" sign up succsseful !!! hello"))
            {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent logointent= new Intent(_Edit_Account_Admin.this,_log_in_admin.class);
                        _Edit_Account_Admin.this.startActivity(logointent);
                    }
                },SPLASH_TIME_OUT);
                alertDialog.setMessage("changed data successfully");
                alertDialog.show();

            }

            }

        }
    }

}
