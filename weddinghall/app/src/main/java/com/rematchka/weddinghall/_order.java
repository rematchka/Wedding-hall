package com.rematchka.weddinghall;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
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
import java.util.Calendar;

public class _order extends AppCompatActivity {
    AlertDialog alertDialog;
    private boolean loggedIn = false;
    String result;
    String paymentid;
    String usr_name;
    Button proceed;
    Button slot1;
    Button slot2;
    Button date;
    private static int SPLASH_TIME_OUT=2000;
    int myYear,myMonth, myDay;
    static  final int Dialoge_ID=0;
    String date1, slot;
    String slotno;
    Button selectDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__order);
        SharedPreferences sharedPreferences = this.getSharedPreferences(userinfo.SHARED_PREF_NAME, this.MODE_PRIVATE);
        final String email1 = sharedPreferences.getString(userinfo.EMAIL_SHARED_PREF, "Not Available");
        final String payid = sharedPreferences.getString(userinfo.pay_id_pref, "Not Available");
        usr_name=email1;
        paymentid=payid;
        proceed=(Button)findViewById(R.id.order_button_id);
        final Calendar cal = Calendar.getInstance();
        myYear = cal.get(Calendar.YEAR);
        myMonth = cal.get(Calendar.MONTH);
        myDay = cal.get(Calendar.DAY_OF_MONTH);

        showDialogeOnButtonClick();





        proceed.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Log.e("slots",slot);
                Log.e("date selected",date1);
                new JSONParse().execute();




            }
        });
    }
    public void showDialogeOnButtonClick()
    {
        selectDate = (Button)findViewById(R.id.date1);
        selectDate.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        showDialog(Dialoge_ID);
                    }
                }
        );
    }
    @Override
    protected Dialog onCreateDialog(int id)
    {
        if(id == Dialoge_ID)
            return new DatePickerDialog(this, dPickerListner, myYear, myMonth, myDay);
        return null;
    }

    private DatePickerDialog.OnDateSetListener dPickerListner = new DatePickerDialog.OnDateSetListener()
    {
        @Override
        public  void onDateSet(DatePicker view, int year, int month, int day)
        {
            myYear = year;
            myMonth = month + 1;
            myDay = day;

            date1 = myYear + "-" + myMonth + "-" + myDay;
        }
    };


    public void chooseSlot(View view) {
        if (((RadioButton) view).isChecked())
            slot = "" + ((RadioButton) view).getText();
    }
    private class JSONParse extends AsyncTask<String, String, String> {
        private ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            alertDialog=new AlertDialog.Builder(_order.this).create();
            alertDialog.setTitle("Making slots");
            pDialog = new ProgressDialog(_order.this);
            pDialog.setMessage("Getting Data ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

        }


        @Override
        protected String doInBackground(String... args) {


                HTTPHandler sh = new HTTPHandler();
                String login_URL = "http://2f179dfb.ngrok.io/slot_no.php";
                try {
                    SharedPreferences sharedPreferences;
                    sharedPreferences = _order.this.getSharedPreferences(userinfo.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                    SharedPreferences sharedPreferences1;
                    sharedPreferences1 = _order.this.getSharedPreferences(userinfo.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                    //Fetching the boolean value form sharedpreferences
                    loggedIn = sharedPreferences.getBoolean(userinfo.LOGGEDIN_SHARED_PREF, false);
                    URL url = new URL(login_URL);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("slot", "UTF-8") + "=" + URLEncoder.encode(slot, "UTF-8") + "&" + URLEncoder.encode("date1", "UTF-8") + "=" + URLEncoder.encode(date1, "UTF-8")+ "&" + URLEncoder.encode("usr_name", "UTF-8") + "=" + URLEncoder.encode(usr_name, "UTF-8")+ "&" + URLEncoder.encode("paymentid", "UTF-8") + "=" + URLEncoder.encode(paymentid, "UTF-8");
                    Log.e("query ",post_data);
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
                    SharedPreferences sharedPreferences2 = _order.this.getSharedPreferences(userinfo.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                    //Creating editor to store values to shared preferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    //Adding values to editor
                    editor.putBoolean(userinfo.LOGGEDIN_SHARED_PREF, true);
                    editor.putString(userinfo.order_id_shared_pref, result);




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
        protected void onPostExecute(String result) {
            pDialog.dismiss();
            if(!result.equals("already exist"))

            {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent logointent= new Intent(_order.this,_songs_playlist.class);
                        _order.this.startActivity(logointent);
                    }
                },SPLASH_TIME_OUT);
                alertDialog.setMessage("Data saved successfully");
                alertDialog.show();
            }
            else
            {
                alertDialog.setMessage("already exists");
                alertDialog.show();
            }

        }
    }

}
