package com.rematchka.weddinghall;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

public class choose_buffet_items extends AppCompatActivity {
    String []categouries;
    ArrayList<buffetcategories> buff_list = new ArrayList<buffetcategories>();
    buffetcatadapter itemsAdapter;

    ArrayAdapter adapter;
    private static int SPLASH_TIME_OUT=2000;
    AlertDialog alertDialog;
    private boolean loggedIn = false;
    String type;
    String result;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_buffet_items);

        new JSONParse().execute();

            itemsAdapter = new buffetcatadapter(this, buff_list);
             itemsAdapter.notifyDataSetChanged();
          btn=(Button)findViewById(R.id.buttonGet_next_item) ;
            ListView listView = (ListView) findViewById(R.id._listView_buffetitem_);
            listView.setAdapter(itemsAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    buffetcategories s = itemsAdapter.getItem(i);
                    if(s.getCategouries().equals("Drinks"))
                    {
                        Intent numbersIntent = new Intent(choose_buffet_items.this, buffet_drinks_items.class);
                        startActivity(numbersIntent);
                    }
                    else if(s.getCategouries().equals("Cakes"))
                    {
                        Intent numbersIntent = new Intent(choose_buffet_items.this, buffet_cake.class);
                        startActivity(numbersIntent);
                    }
                    else if(s.getCategouries().equals("Appetizers"))
                    {
                        Intent numbersIntent = new Intent(choose_buffet_items.this,buffet_appetizers.class);
                        startActivity(numbersIntent);
                    }
                    else {Intent numbersIntent = new Intent(choose_buffet_items.this, buffet_lunch.class);
                        startActivity(numbersIntent);}

                }
            });


            listView.setAdapter(itemsAdapter);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {Intent numbersIntent = new Intent(choose_buffet_items.this, accs_items_order.class);
                startActivity(numbersIntent);


            }
        });




    }
    private class JSONParse extends AsyncTask<String, String, String> {
        private ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            alertDialog=new AlertDialog.Builder(choose_buffet_items.this).create();
            alertDialog.setTitle("Upadting Data");
            pDialog = new ProgressDialog(choose_buffet_items.this);
            pDialog.setMessage("Getting Data ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();


        }


        @Override
        protected String doInBackground(String... args) {



                String login_URL = "http://2f179dfb.ngrok.io/getcat.php";
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
                    //categouries=new String[result1.length()];
                    for (int i = 0; i < result1.length(); i++) {
                        JSONObject c = result1.getJSONObject(i);
                        String category = c.getString("buffCategory");
                        buff_list.add(new buffetcategories(category));


                    }

                    return result;


                }
                catch (MalformedURLException e) {
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
                return result;
            }




        @Override
        protected void onPostExecute(String r) {
               itemsAdapter.notifyDataSetChanged();
               pDialog.dismiss();
            alertDialog.setMessage("hello");
            alertDialog.show();




        }
    }


}
