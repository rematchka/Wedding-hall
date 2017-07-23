package com.rematchka.weddinghall;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class _package_option extends AppCompatActivity {
    Button add;
    Button delete;
    Button edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        add=(Button)findViewById(R.id.add_package);
        delete=(Button)findViewById(R.id.delete_package);
        edit=(Button) findViewById(R.id.edit_package);
    }
}
