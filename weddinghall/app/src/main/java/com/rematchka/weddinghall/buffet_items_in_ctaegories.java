package com.rematchka.weddinghall;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;

public class buffet_items_in_ctaegories extends AppCompatActivity {
    ArrayList<buffet_items_db> listofsoongs = new ArrayList<buffet_items_db>();
    private static int SPLASH_TIME_OUT=2000;
    AlertDialog alertDialog;
    private boolean loggedIn = false;
    String type;
    String result;
    buffet_item_adapter itemsAdapter;
    Button btn;
    String user_name;
    String ord_id;
    String pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buffet_items_in_ctaegories);
    }
}
