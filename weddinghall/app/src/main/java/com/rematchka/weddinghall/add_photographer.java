package com.rematchka.weddinghall;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
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

public class add_photographer extends AppCompatActivity {
   Button add;
    EditText wages,fname,lname,mname,phoneno;
    String f_name,l_name,m_name,_wages,phone_no;
    String result;
    private static int SPLASH_TIME_OUT=2000;
    AlertDialog alertDialog;
    private boolean loggedIn = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_photographer);
        add=(Button)findViewById(R.id.add_photographer_s);
        wages=(EditText)findViewById(R.id.wages);
        fname=(EditText)findViewById(R.id.f_name_photog) ;
        lname=(EditText)findViewById(R.id.l_name_photog) ;
        mname=(EditText)findViewById(R.id.m_ini_photog) ;
        phoneno=(EditText)findViewById(R.id.phone_no_photog) ;
        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                f_name=fname.getText().toString();
                l_name=lname.getText().toString();
                m_name=mname.getText().toString();
                phone_no=phoneno.getText().toString();
                _wages=wages.getText().toString();
                new JSONParse().execute();


            }
        });
    }



    private class JSONParse extends AsyncTask<String, String, String> {
        private ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            alertDialog = new AlertDialog.Builder(add_photographer.this).create();
            alertDialog.setTitle("Upadting Data");



        }


        @Override
        protected String doInBackground(String... args) {

            HTTPHandler sh = new HTTPHandler();
            String login_URL = "http://2f179dfb.ngrok.io/addphotog.php";
            try {


                URL url = new URL(login_URL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("f_name", "UTF-8") + "=" + URLEncoder.encode(f_name, "UTF-8") + "&" + URLEncoder.encode("l_name", "UTF-8") + "=" + URLEncoder.encode(l_name, "UTF-8")+ "&" + URLEncoder.encode("m_name", "UTF-8") + "=" + URLEncoder.encode(m_name, "UTF-8")+ "&" + URLEncoder.encode("_wages", "UTF-8") + "=" + URLEncoder.encode(_wages, "UTF-8")+ "&" + URLEncoder.encode("phone_no", "UTF-8") + "=" + URLEncoder.encode(phone_no, "UTF-8");
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

                //Adding values to editor
               Log.e("REsult",result);
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
            return null;

        }




        @Override
        protected void onPostExecute(String resultl) {

            if(resultl.equals(" add new photogrpher successfully"))
            {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent logointent= new Intent(add_photographer.this,photographer_option.class);
                        add_photographer.this.startActivity(logointent);
                    }
                },SPLASH_TIME_OUT);
                alertDialog.setMessage("Added data successfully");
                alertDialog.show();
            }


        }
    }
}
