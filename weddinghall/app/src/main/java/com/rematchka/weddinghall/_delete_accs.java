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
import android.view.View;
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

public class _delete_accs extends AppCompatActivity {

    ArrayList<accsitems> listofaccs = new ArrayList<accsitems>();
    private static int SPLASH_TIME_OUT=2000;
    AlertDialog alertDialog;
    private boolean loggedIn = false;
    String type;
    String result;
    accsitemsadapter itemsAdapter;
    Button btn;
    String user_name;
    String ord_id;
    String pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accs_items_order);
        type="get";
        SharedPreferences sharedPreferences = this.getSharedPreferences(userinfo.SHARED_PREF_NAME, this.MODE_PRIVATE);
        final String email1 = sharedPreferences.getString(userinfo.EMAIL_SHARED_PREF, "Not Available");
        final String pass1 = sharedPreferences.getString(userinfo.pass_SHARED_PREF, "Not Available");
        final String ord=sharedPreferences.getString(userinfo.order_id_shared_pref, "Not Available");
        user_name=email1;
        pass=pass1;
        ord_id=ord;
        new JSONParse().execute();
        btn=(Button)findViewById(R.id.buttonGet_accs);
        itemsAdapter = new accsitemsadapter(this, listofaccs);

        ListView listView = (ListView) findViewById(R.id.listView_accs);
        listView.setAdapter(itemsAdapter);

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                type="set";

                new JSONParse().execute(type);

            }
        });




        listView.setAdapter(itemsAdapter);
    }
    private class JSONParse extends AsyncTask<String, String, String> {
        private ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            alertDialog=new AlertDialog.Builder(_delete_accs.this).create();
            alertDialog.setTitle("Deleting Data");
            pDialog = new ProgressDialog(_delete_accs.this);
            pDialog.setMessage("Getting Data ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();


        }


        @Override
        protected String doInBackground(String... args) {


            HTTPHandler sh = new HTTPHandler();
            if(type.equals("get")) {
                String login_URL = "http://2f179dfb.ngrok.io/getaccsitem.php";
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
                        String id = c.getString("acsID");
                        String names = c.getString("acsName");
                        String price = c.getString("acsPrice");


                        listofaccs.add(new accsitems(id, names, price));


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
            else
            {


                //Fetching the boolean value form sharedpreferences

                String login_URL = "http://2f179dfb.ngrok.io/deleteaccs.php";

                try {
                    URL url = new URL(login_URL);



                    for(int i=0;i<itemsAdapter.getCount();i++)
                    { accsitems s=itemsAdapter.getItem(i);
                        Log.e("singername",s.getAcsName());
                        Log.e("songid",s.getAcsID());
                        Log.e("is selected",""+s.isSelected());
                        if(s.isSelected())
                        {   HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                            httpURLConnection.setRequestMethod("POST");
                            httpURLConnection.setDoOutput(true);
                            httpURLConnection.setDoInput(true);
                            OutputStream outputStream = httpURLConnection.getOutputStream();
                            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                            String post_data= URLEncoder.encode("accs_id","UTF-8")+"="+URLEncoder.encode(s.getAcsID(),"UTF-8");
                            bufferedWriter.write(post_data);

                            Log.e("query",post_data);
                            Log.e("orerid",ord_id);

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
                            Log.i("Result",result);
                            httpURLConnection.disconnect();
                        }

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
                }

            }

            return null;


        }

        @Override
        protected void onPostExecute(String r) {
            itemsAdapter.notifyDataSetChanged();
            pDialog.dismiss();

            if(!type.equals("get")) {
                if (result.equals(" deleted accessory successfully"))
                {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent logointent= new Intent(_delete_accs.this,accs_option.class);
                            _delete_accs.this.startActivity(logointent);
                        }
                    },SPLASH_TIME_OUT);
                    alertDialog.setMessage("deleted successfully");
                    alertDialog.show();

                } else {   alertDialog.setMessage("error");
                    alertDialog.show();

                }
            }

        }
    }
}
