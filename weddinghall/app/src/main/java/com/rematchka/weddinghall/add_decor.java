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
import android.widget.Button;
import android.widget.TextView;

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

public class add_decor extends AppCompatActivity {
   Button btn;
    TextView name;
    TextView price;
    String name1;
    String price1;
    private static int SPLASH_TIME_OUT=2000;
    AlertDialog alertDialog;
    String result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_decor);
        btn=(Button)findViewById(R.id.add_decor_item);
        name=(TextView)findViewById(R.id.entered_name_decor);
        price=(TextView)findViewById(R.id.entered_price_decor);
        btn.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                //execute query
                name1=name.getText().toString();
                price1=price.getText().toString();
                new JSONParse().execute();
            }
        });
    }

    private class JSONParse extends AsyncTask<String, String, String> {
        private ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            alertDialog = new AlertDialog.Builder(add_decor.this).create();
            alertDialog.setTitle("Inserting Data");



        }


        @Override
        protected String doInBackground(String... args) {

            HTTPHandler sh = new HTTPHandler();
            String login_URL = "http://2f179dfb.ngrok.io/adddecor.php";
            try {


                URL url = new URL(login_URL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("name1", "UTF-8") + "=" + URLEncoder.encode(name1, "UTF-8") + "&" + URLEncoder.encode("price1", "UTF-8") + "=" + URLEncoder.encode(price1, "UTF-8");
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

            if(resultl.equals(" add new decor item successfully"))
            {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent logointent= new Intent(add_decor.this,decor_option.class);
                        add_decor.this.startActivity(logointent);
                    }
                },SPLASH_TIME_OUT);
                alertDialog.setMessage("Added data successfully");
                alertDialog.show();
            }


        }
    }

}
