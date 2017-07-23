package com.rematchka.weddinghall;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Config;
import android.util.Log;
import android.widget.Toast;

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

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by extra on 20/12/2016.
 */
public class sendtochecklogin extends AsyncTask<String,Void,String> {
   Context context;
    private static int SPLASH_TIME_OUT=1000;
    String usr;
    String pass;
    private boolean loggedIn = false;

    AlertDialog alertDialog;
    sendtochecklogin(Context ctx)
    {
      context=ctx;
    }
    @Override
    protected String doInBackground(String... voids)
    {  String type=voids[0];

        SharedPreferences sharedPreferences;
        sharedPreferences = context.getSharedPreferences(userinfo.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences sharedPreferences1;
        sharedPreferences1 = context.getSharedPreferences(userinfo.SHARED_PREF_NAME, Context.MODE_PRIVATE);

        //Fetching the boolean value form sharedpreferences
        loggedIn = sharedPreferences.getBoolean(userinfo.LOGGEDIN_SHARED_PREF, false);
        //String login_URL="http://df5a458c.ngrok.io/login.php";
        if(type.equals("login"))
        {   String login_URL="http://2f179dfb.ngrok.io/login.php";
            try {
                String usr_nm=voids[1];
                String usr_pass=voids[2];
                usr=usr_nm;
                pass=usr_pass;
                URL url=new URL(login_URL);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data= URLEncoder.encode("usr_nm","UTF-8")+"="+URLEncoder.encode(usr_nm,"UTF-8")+"&"+URLEncoder.encode("usr_pass","UTF-8")+"="+URLEncoder.encode(usr_pass,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line=bufferedReader.readLine())!=null)
                {
                    result+=line;
                }
                SharedPreferences sharedPreferences2 = context.getSharedPreferences(userinfo.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                //Creating editor to store values to shared preferences
                SharedPreferences.Editor editor = sharedPreferences.edit();

                //Adding values to editor
                editor.putBoolean(userinfo.LOGGEDIN_SHARED_PREF, true);
                editor.putString(userinfo.EMAIL_SHARED_PREF, usr);


                SharedPreferences sharedPreferences3 = context.getSharedPreferences(userinfo.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                //Creating editor to store values to shared preferences
                SharedPreferences.Editor editor1 = sharedPreferences.edit();

                //Adding values to editor
                editor1.putBoolean(userinfo.LOGGEDIN_SHARED_PREF, true);
                editor1.putString(userinfo.pass_SHARED_PREF, pass);

                editor1.commit();
                //Saving values to editor
                editor.commit();
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(type.equals("signup"))
        {   String signup_URL="http://2f179dfb.ngrok.io/signup.php";
            String usr_f_name=voids[1];
            String usr_l_name=voids[3];
            String usr_m_name=voids[2];
            String usr_pass=voids[6];
            String usr_check_pass=voids[7];
            String usr_email1=voids[5];
            String usr_no=voids[4];
            usr=usr_email1;
            usr=usr_pass;
            if(!(usr_check_pass.equals(usr_pass))) {

                return "Passwords doesn't match";
            }
            else
            {

                try {
                    URL url=new URL(signup_URL);
                    HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream=httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                    String post_data= URLEncoder.encode("usr_f_name","UTF-8")+"="+URLEncoder.encode(usr_f_name,"UTF-8")+"&"+URLEncoder.encode("usr_l_name","UTF-8")+"="+URLEncoder.encode(usr_l_name,"UTF-8")+"&"+ URLEncoder.encode("usr_m_name","UTF-8")+"="+URLEncoder.encode(usr_m_name,"UTF-8")+"&"+ URLEncoder.encode("usr_pass","UTF-8")+"="+URLEncoder.encode(usr_pass,"UTF-8")+"&"+ URLEncoder.encode("usr_no","UTF-8")+"="+URLEncoder.encode(usr_no,"UTF-8")+"&"+ URLEncoder.encode("usr_email1","UTF-8")+"="+URLEncoder.encode(usr_email1,"UTF-8");
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream=httpURLConnection.getInputStream();
                    BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                    String result="";
                    String line="";
                    while((line=bufferedReader.readLine())!=null)
                    {
                        result+=line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    SharedPreferences sharedPreferences2 = context.getSharedPreferences(userinfo.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                    //Creating editor to store values to shared preferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    //Adding values to editor
                    editor.putBoolean(userinfo.LOGGEDIN_SHARED_PREF, true);
                    editor.putString(userinfo.EMAIL_SHARED_PREF, usr);


                    SharedPreferences sharedPreferences3 = context.getSharedPreferences(userinfo.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                    //Creating editor to store values to shared preferences
                    SharedPreferences.Editor editor1 = sharedPreferences.edit();

                    //Adding values to editor
                    editor1.putBoolean(userinfo.LOGGEDIN_SHARED_PREF, true);
                    editor1.putString(userinfo.pass_SHARED_PREF, pass);

                    editor1.commit();
                    //Saving values to editor
                    editor.commit();
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
        }
      return null;
    }



    @Override
    protected void onPreExecute() {
       alertDialog=new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Status");
    }

    @Override
    protected void onPostExecute(String result) {
        Log.e("RESULT", result);
        alertDialog.setMessage(result);
        alertDialog.show();
        if(result.equals("user"))
        { new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            Intent logointent= new Intent(context,_log_in_menu_user.class);
            context.startActivity(logointent);
            }
        },SPLASH_TIME_OUT);
            alertDialog.setMessage("Successfully logged in");
            alertDialog.show();
        }
        else if(result.equals("Admin"))
        {   new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent logointent= new Intent(context,_log_in_admin.class);
                context.startActivity(logointent);
            }
        },SPLASH_TIME_OUT);
            alertDialog.setMessage("logged in as an admin");
            alertDialog.show();
        }
        else { alertDialog.setMessage("check your user name or password");
            alertDialog.show();}


    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    String getEmail()
    {
        return usr;
    }
    String getPass()
    {
        return pass;
    }
}
