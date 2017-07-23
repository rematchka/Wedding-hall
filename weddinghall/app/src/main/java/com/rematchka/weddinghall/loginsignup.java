package com.rematchka.weddinghall;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class loginsignup extends AppCompatActivity {
   Animation fade_in,fade_out;
    ViewFlipper viewFlipper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginsignup);
        viewFlipper=(ViewFlipper)findViewById(R.id.bckg);
        fade_in= AnimationUtils.loadAnimation(this,android.R.anim.fade_in);
        fade_out= AnimationUtils.loadAnimation(this,android.R.anim.fade_out);
        viewFlipper.setAutoStart(true);
        viewFlipper.setFlipInterval(2000);
        viewFlipper.startFlipping();
        Button button=(Button)findViewById(R.id.sign_UP);
        button.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view)
            {    Toast.makeText(loginsignup.this,"HI !",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(loginsignup.this,Sign_up.class);
                startActivity(intent);
            }

        });

        Button button1=(Button)findViewById(R.id.log_in);
        button1.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view)
            {    Toast.makeText(loginsignup.this," Loging in Please wait !",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(loginsignup.this,Log_in.class);
                startActivity(intent);
            }

        });

    }
}
