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

public class _edit_packg extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    AlertDialog alertDialog;
    private static int SPLASH_TIME_OUT=2000;
    private boolean loggedIn = false;
    String result;
    String paymentid;
    Spinner spinner;
    Button add;
    String name;
    String type;
    Map<String, Integer> map = new HashMap<String,Integer>();
    List<String> categories = new ArrayList<String>();
    ArrayAdapter<String> dataAdapter;
    EditText pricc;
    EditText noo;
    String text;
    String id1;
    String no;
    String price1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__edit_packg);


        pricc=(EditText)findViewById(R.id.price);
        noo=(EditText)findViewById(R.id.noofphotos);
        // Spinner click listener
        ;
        add=(Button)findViewById(R.id.add_pckg) ;
        // Spinner Drop down elements

        type="get";
        new JSONParse().execute();
        add.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                //execute query
                type="set";
                text  = spinner.getSelectedItem().toString();
                no=noo.getText().toString();
                price1=pricc.getText().toString();
                id1=map.get(text).toString();
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
            alertDialog=new AlertDialog.Builder(_edit_packg.this).create();
            alertDialog.setTitle("Upadting Data");
            pDialog = new ProgressDialog(_edit_packg.this);
            pDialog.setMessage("Getting Data ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();


        }


        @Override
        protected String doInBackground(String... args) {


            HTTPHandler sh = new HTTPHandler();
            if (type.equals("get")) {


                String login_URL = "http://2f179dfb.ngrok.io/getpack.php";
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
                        String pkgid = c.getString("pkgID");
                        String photoid = c.getString("photographerID");
                        String price = c.getString("pkgPrice");
                        String noofpic = c.getString("numOfPics");
                        String loginURL = "http://2f179dfb.ngrok.io/getphotog.php";
                        URL url1 = new URL(loginURL);
                        HttpURLConnection httpURLConnection1 = (HttpURLConnection) url1.openConnection();
                        httpURLConnection1.setRequestMethod("POST");
                        httpURLConnection1.setDoOutput(true);
                        httpURLConnection1.setDoInput(true);
                        OutputStream outputStream1 = httpURLConnection1.getOutputStream();
                        BufferedWriter bufferedWriter1 = new BufferedWriter(new OutputStreamWriter(outputStream1, "UTF-8"));

                        String post_data1 = URLEncoder.encode("photoid", "UTF-8") + "=" + URLEncoder.encode(photoid, "UTF-8");
                        bufferedWriter1.write(post_data1);
                        bufferedWriter1.flush();
                        bufferedWriter1.close();
                        outputStream1.close();
                        InputStream inputStream1 = httpURLConnection1.getInputStream();
                        BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(inputStream1, "iso-8859-1"));
                        result = "";
                        String line1 = "";
                        while ((line1 = bufferedReader1.readLine()) != null) {
                            result += line1;
                        }
                        Log.e("REsult", result);
                        bufferedReader1.close();
                        inputStream1.close();
                        JSONObject jsonObject1 = new JSONObject(result);

                        JSONArray result11 = jsonObject1.getJSONArray("result");
                        JSONObject collegeData1 = result11.getJSONObject(0);
                        String fname, lname, wage, mname, pphoneno;
                        fname = collegeData1.getString("phFName");
                        lname = (collegeData1.getString("phLName"));
                        wage = (collegeData1.getString("Wages"));
                        mname = (collegeData1.getString("phMinit"));
                        pphoneno = (collegeData1.getString("phPhoneNum"));
                        int id = Integer.parseInt(pkgid);
                        map.put(" Name " + fname + " " + mname + " " + lname + " pkg price" + price + "no of photos " + noofpic, id);
                        categories.add(" Name " + fname + " " + mname + " " + lname + " pkg price" + price + "no of photos " + noofpic);


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


                else {


                //Fetching the boolean value form sharedpreferences

                String login_URL = "http://2f179dfb.ngrok.io/update_pckg.php";

                try {
                    URL url = new URL(login_URL);




                    {



                        {   HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                            httpURLConnection.setRequestMethod("POST");
                            httpURLConnection.setDoOutput(true);
                            httpURLConnection.setDoInput(true);
                            OutputStream outputStream = httpURLConnection.getOutputStream();
                            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                            String post_data= URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode(id1,"UTF-8")+"&"+URLEncoder.encode("noofpic","UTF-8")+"="+URLEncoder.encode(no,"UTF-8")+"&"+URLEncoder.encode("price","UTF-8")+"="+URLEncoder.encode(price1,"UTF-8");
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
                            Intent logointent= new Intent(_edit_packg.this,pckg_option.class);
                            _edit_packg.this.startActivity(logointent);
                        }
                    },SPLASH_TIME_OUT);
                    alertDialog.setMessage("updated successfully");
                    alertDialog.show();

                } else {   alertDialog.setMessage("error");
                    alertDialog.show();

                }
            }

        }
    }
}
