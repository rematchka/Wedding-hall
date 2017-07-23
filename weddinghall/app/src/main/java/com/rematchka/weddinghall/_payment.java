package com.rematchka.weddinghall;

import android.app.Activity;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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
import java.util.ArrayList;
import java.util.List;

public class _payment  extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    AlertDialog alertDialog;
    private boolean loggedIn = false;
    String result;
    String paymentid;
    Spinner spinner;
    String text;
    String usr_name;
    Button proceed;

    private static int SPLASH_TIME_OUT=2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__payment);
        // Spinner element
         spinner = (Spinner) findViewById(R.id.spinner);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);
        proceed=(Button)findViewById(R.id.proceed_slot) ;
        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Visa");
        categories.add("Cash");
        categories.add("Master");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        SharedPreferences sharedPreferences = this.getSharedPreferences(userinfo.SHARED_PREF_NAME, this.MODE_PRIVATE);
        final String email1 = sharedPreferences.getString(userinfo.EMAIL_SHARED_PREF, "Not Available");
        usr_name=email1;

        proceed.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                new JSONParse().execute();

                text = spinner.getSelectedItem().toString();
                Log.e("text selected",text);

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
    private class JSONParse extends AsyncTask<String, String, String> {
        private ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            alertDialog=new AlertDialog.Builder(_payment.this).create();
            alertDialog.setTitle("Getting payment type");
            pDialog = new ProgressDialog(_payment.this);
            pDialog.setMessage("Setting Data ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

        }


        @Override
        protected String doInBackground(String... args) {


                HTTPHandler sh = new HTTPHandler();
                String login_URL = "http://2f179dfb.ngrok.io/payment.php";
                try {
                    SharedPreferences sharedPreferences;
                    sharedPreferences = _payment.this.getSharedPreferences(userinfo.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                    SharedPreferences sharedPreferences1;
                    sharedPreferences1 =_payment.this.getSharedPreferences(userinfo.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                    //Fetching the boolean value form sharedpreferences
                    loggedIn = sharedPreferences.getBoolean(userinfo.LOGGEDIN_SHARED_PREF, false);

                    URL url = new URL(login_URL);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));


                    String post_data =  URLEncoder.encode("text", "UTF-8") + "=" + URLEncoder.encode(text, "UTF-8");
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
                    paymentid=result;
                    SharedPreferences sharedPreferences2 = _payment.this.getSharedPreferences(userinfo.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                    //Creating editor to store values to shared preferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    //Adding values to editor
                    editor.putBoolean(userinfo.LOGGEDIN_SHARED_PREF, true);
                    editor.putString(userinfo.pay_id_pref, paymentid);




                    //Saving values to editor
                    editor.commit();
                    Log.e("RESULT", result);
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
        protected void onPostExecute(String r) {

            pDialog.dismiss();

            if(r.equals("5")||r.equals("3")||r.equals("4")) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent logointent= new Intent(_payment.this,_order.class);
                        _payment.this.startActivity(logointent);
                    }
                },SPLASH_TIME_OUT);
                alertDialog.setMessage("fetched");
                alertDialog.show();
            }

        }
    }

}

