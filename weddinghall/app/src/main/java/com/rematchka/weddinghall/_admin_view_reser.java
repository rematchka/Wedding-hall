package com.rematchka.weddinghall;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;

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

public class _admin_view_reser extends AppCompatActivity {

    ArrayList<reservationdate> listofsoongs = new ArrayList<reservationdate>();
    private static int SPLASH_TIME_OUT=2000;
    AlertDialog alertDialog;
    private boolean loggedIn = false;
    String type;
    String result;
    reservationdateAdapter itemsAdapter;
    Button btn;
    String user_name;
    String ord_id;
    String pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__user_view_reservation);
        type="get";
        SharedPreferences sharedPreferences = this.getSharedPreferences(userinfo.SHARED_PREF_NAME, this.MODE_PRIVATE);
        final String email1 = sharedPreferences.getString(userinfo.EMAIL_SHARED_PREF, "Not Available");
        final String pass1 = sharedPreferences.getString(userinfo.pass_SHARED_PREF, "Not Available");
        final String ord=sharedPreferences.getString(userinfo.order_id_shared_pref, "Not Available");
        user_name=email1;

        new JSONParse().execute();
        itemsAdapter = new reservationdateAdapter(this, listofsoongs);
        ListView listView = (ListView) findViewById(R.id.listView9);
        listView.setAdapter(itemsAdapter);





    }
    private class JSONParse extends AsyncTask<String, String, String> {
        private ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            alertDialog=new AlertDialog.Builder(_admin_view_reser.this).create();
            alertDialog.setTitle("Upadting Data");
            pDialog = new ProgressDialog(_admin_view_reser.this);
            pDialog.setMessage("Getting Data ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();


        }


        @Override
        protected String doInBackground(String... args) {


            HTTPHandler sh = new HTTPHandler();
            if (type.equals("get")) {
                String login_URL = "http://2f179dfb.ngrok.io/get_all_orders.php";
                try {

                    //Fetching the boolean value form sharedpreferences


                    URL url = new URL(login_URL);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);

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
                    Log.e("RESULT", result);
                    JSONObject jsonObject = new JSONObject(result);

                    JSONArray result1 = jsonObject.getJSONArray("result");
                    for (int i = 0; i < result1.length(); i++) {
                        JSONObject c = result1.getJSONObject(i);
                        String id = c.getString("slotNum");
                        String names = c.getString("ordDate");

                        listofsoongs.add(new reservationdate(names, id));


                    }

                    return result;
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
            return null;

        }

        @Override
        protected void onPostExecute(String r) {
            itemsAdapter.notifyDataSetChanged();
            pDialog.dismiss();

            if(!type.equals("get")) {
                if (result.equals("successful"))
                {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent logointent= new Intent(_admin_view_reser.this,choose_photographer.class);
                            _admin_view_reser.this.startActivity(logointent);
                        }
                    },SPLASH_TIME_OUT);
                    alertDialog.setMessage("added successfully");
                    alertDialog.show();

                } else {   alertDialog.setMessage("error");
                    alertDialog.show();

                }
            }

        }
    }
}
