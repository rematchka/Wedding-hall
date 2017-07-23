package com.rematchka.weddinghall;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _add_buffet_item extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
   EditText itemname,itemprice,itemcategoury;
    AlertDialog alertDialog;

    private static int SPLASH_TIME_OUT=2000;
    Map<String, Integer> map = new HashMap<String,Integer>();
    List<String> categories = new ArrayList<String>();
    ArrayAdapter<String> dataAdapter;
    Spinner spinner;
    Button add;
    String result,type,name1,categoury,price1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__add_buffet_item);
         itemname=(EditText)findViewById(R.id.enteredname) ;
        itemprice=(EditText)findViewById(R.id.enteredprice) ;
        add=(Button)findViewById(R.id.add) ;
         type="get";
        new JSONParse().execute();
        add.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                //execute query
                type="set";
                categoury  = spinner.getSelectedItem().toString();
                name1=itemname.getText().toString();
                price1=itemprice.getText().toString();
                new JSONParse().execute();
            }
        });


        spinner = (Spinner) findViewById(R.id.spinner_pkg);
        // Creating adapter for spinner
        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        spinner.setAdapter(dataAdapter);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner

        spinner.setOnItemSelectedListener(this);

    }
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
            alertDialog=new AlertDialog.Builder(_add_buffet_item.this).create();
            alertDialog.setTitle("Adding Data");
            pDialog = new ProgressDialog(_add_buffet_item.this);
            pDialog.setMessage("Getting Data ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();


        }


        @Override
        protected String doInBackground(String... args) {


            HTTPHandler sh = new HTTPHandler();
            if (type.equals("get")) {
                String login_URL = "http://2f179dfb.ngrok.io/getcatego.php";
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

                        String fname;
                        fname = c.getString("buffCategory");

                       // map.put("" + fname + " " + mname + " " + lname, id);
                        categories.add(fname);


                    }

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return result;


            } else {


                //Fetching the boolean value form sharedpreferences

                String login_URL = "http://2f179dfb.ngrok.io/addbuffetitem.php";

                try {
                    URL url = new URL(login_URL);




                    {

                        {   HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                            httpURLConnection.setRequestMethod("POST");
                            httpURLConnection.setDoOutput(true);
                            httpURLConnection.setDoInput(true);
                            OutputStream outputStream = httpURLConnection.getOutputStream();
                            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                            String post_data= URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name1,"UTF-8")+"&"+URLEncoder.encode("price","UTF-8")+"="+URLEncoder.encode(price1,"UTF-8")+"&"+URLEncoder.encode("cat","UTF-8")+"="+URLEncoder.encode(categoury,"UTF-8");
                            bufferedWriter.write(post_data);

                            // Log.e("query",post_data);
                            //Log.e("orerid",ord_id);
                            //Log.e("singername",s.getSingername());
                            //Log.e("songid",s.getSongid());
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
            dataAdapter.notifyDataSetChanged();
            pDialog.dismiss();

            if(!type.equals("get")) {
                if (result.equals("successful"))
                {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent logointent= new Intent(_add_buffet_item.this,buffet_option.class);
                            _add_buffet_item.this.startActivity(logointent);
                        }
                    },SPLASH_TIME_OUT);
                    alertDialog.setMessage("Added successfully");
                    alertDialog.show();

                } else {   alertDialog.setMessage("error");
                    alertDialog.show();

                }
            }

        }
    }
}
