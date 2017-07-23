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

public class delete_package extends AppCompatActivity {

    ArrayList<photographer> photograp_list = new ArrayList<photographer>();
    private static int SPLASH_TIME_OUT=2000;
    AlertDialog alertDialog;
    private boolean loggedIn = false;
    String type;
    String result;
    photographerAdapter itemsAdapter;
    Button btn;
    String user_name;
    String photog_id;
    String ordid;
    String pckgid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    setContentView(R.layout.choose_photographer);
    type="get";
    SharedPreferences sharedPreferences = this.getSharedPreferences(userinfo.SHARED_PREF_NAME, this.MODE_PRIVATE);
    final String email1 = sharedPreferences.getString(userinfo.EMAIL_SHARED_PREF, "Not Available");
    final String oordid=sharedPreferences.getString(userinfo.order_id_shared_pref, "Not Available");
    user_name=email1;
    ordid=oordid;
    new JSONParse().execute();
    btn=(Button)findViewById(R.id.buttonGet_photog);
    itemsAdapter = new photographerAdapter(this, photograp_list);

    ListView listView = (ListView) findViewById(R.id.listView_photog);
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
        alertDialog=new AlertDialog.Builder(delete_package.this).create();
        alertDialog.setTitle("Deleting Data");
        pDialog = new ProgressDialog(delete_package.this);
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
                    String noofpic=c.getString("numOfPics");
                    String loginURL = "http://2f179dfb.ngrok.io/getphotog.php";
                    URL url1 = new URL(loginURL);
                    HttpURLConnection httpURLConnection1 = (HttpURLConnection) url1.openConnection();
                    httpURLConnection1.setRequestMethod("POST");
                    httpURLConnection1.setDoOutput(true);
                    httpURLConnection1.setDoInput(true);
                    OutputStream outputStream1 = httpURLConnection1.getOutputStream();
                    BufferedWriter bufferedWriter1 = new BufferedWriter(new OutputStreamWriter(outputStream1, "UTF-8"));

                    String post_data1= URLEncoder.encode("photoid","UTF-8")+"="+URLEncoder.encode(photoid,"UTF-8");
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
                    Log.e("REsult",result);
                    bufferedReader1.close();
                    inputStream1.close();
                    JSONObject jsonObject1 = new JSONObject(result);

                    JSONArray result11 = jsonObject1.getJSONArray("result");
                    JSONObject collegeData1 = result11.getJSONObject(0);
                    String fname,lname,wage,mname,pphoneno;
                    fname=collegeData1.getString("phFName");
                    lname=(collegeData1.getString("phLName"));
                    wage=(collegeData1.getString("Wages"));
                    mname=(collegeData1.getString("phMinit"));
                    pphoneno=(collegeData1.getString("phPhoneNum"));


                    photograp_list.add(new photographer(fname, mname, lname,wage,photoid,pkgid,noofpic,price));


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
        } else {


            //Fetching the boolean value form sharedpreferences

            String login_URL = "http://2f179dfb.ngrok.io/delete_photograg.php";

            try {
                URL url = new URL(login_URL);



                for(int i=0;i<itemsAdapter.getCount();i++)
                { photographer s=itemsAdapter.getItem(i);
                    Log.e("photographer name ",s.getFname());
                    Log.e("pckg_id",s.getPckg_id());
                    Log.e("is selected",""+s.isSelected());
                    if(s.isSelected())
                    {   HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                        httpURLConnection.setRequestMethod("POST");
                        httpURLConnection.setDoOutput(true);
                        httpURLConnection.setDoInput(true);
                        OutputStream outputStream = httpURLConnection.getOutputStream();
                        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                        String post_data= URLEncoder.encode("photog_id","UTF-8")+"="+URLEncoder.encode(s.getPckg_id(),"UTF-8");
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
        itemsAdapter.notifyDataSetChanged();
        pDialog.dismiss();

        if(!type.equals("get")) {
            if (result.equals(" deleted photogrpher successfully"))
            {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent logointent= new Intent(delete_package.this,pckg_option.class);
                        delete_package.this.startActivity(logointent);
                    }
                },SPLASH_TIME_OUT);
                alertDialog.setMessage("Deleted successfully");
                alertDialog.show();

            } else {   alertDialog.setMessage("error");
                alertDialog.show();

            }
        }

    }
}
}
