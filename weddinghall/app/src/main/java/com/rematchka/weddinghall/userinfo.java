package com.rematchka.weddinghall;

/**
 * Created by extra on 22/12/2016.
 */
public class userinfo {


   public static final String email="myemail";
   public static final String pass="mypass";

    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_Payment = "password";

        //Keys for Sharedpreferences
    //This would be the name of our shared preferences
    public static final String SHARED_PREF_NAME = "myloginapp";

    //This would be used to store the email of current logged in user
    public static final String EMAIL_SHARED_PREF = "email";
    public static final String pass_SHARED_PREF = "pass";
    public static final  String pay_id_pref="";
    public static final String order_id_shared_pref="";
    public static final String photographer_id_shared_pref="";
    public static final String accs_id_shared_pref="";
    public static final String buffet_id_shared_pref="";
    public static final String decor_id_shared_pref="";
    //We will use this to store the boolean in sharedpreference to track user is loggedin or not
    public static final String LOGGEDIN_SHARED_PREF = "loggedin";
}
