package com.rematchka.weddinghall;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import  android.view.animation.Animation;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class logo extends AppCompatActivity {
    private static int SPLASH_TIME_OUT=8000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ImageView imageView;


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
      /**  BitmapFactory.Options options=new BitmapFactory.Options();
        options.inJustDecodeBounds=true;
        BitmapFactory.decodeResource(getResources(),R.id.logo,options);
        int imageheight=options.outHeight;
        int imagewidth=options.outWidth;
        String imagetype=options.outMimeType;

        imageView.setImageBitmap(decodeSampledBitmapFromResources(getResources(),R.id.logo,100,100));*/

       /* imageView=(ImageView)findViewById(R.id.logo);
        File photos= new File("C:/Users/extra/Downloads/ud843-QuakeReport-starting-point/weddinghall/app/src/main/res/drawable/love_te.jpg");
        Bitmap bitmap = decodeFile(photos);
        bitmap = Bitmap.createScaledBitmap(bitmap,150, 150, true);*/
     //   imageView.setImageBitmap(bitmap);


        imageView=(ImageView)findViewById(R.id.logo);



        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.logoanimation);
        imageView.setAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener(){
            @Override
            public void onAnimationStart (Animation animation)
            {

            }
            @Override
            public void onAnimationEnd(Animation animation)
            {
                Toast.makeText(logo.this,"Starting app in few seconds",Toast.LENGTH_LONG).show();
               new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent logointent= new Intent(logo.this,loginsignup.class);
                        startActivity(logointent);
                        finish();


                    }
                },SPLASH_TIME_OUT);

            }
            @Override
            public void onAnimationRepeat(Animation animation)
            {

            }

        });
        /**   new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent logointent= new Intent(logo.this,loginsignup.class);
                startActivity(logointent);
                finish();


            }
        },SPLASH_TIME_OUT);*/
    }
  /**  public static int calculateInSampleSize(BitmapFactory.Options options,int reqwidth,int reqheight)
    {
        final int height=options.outHeight;
        final int width=options.outWidth;
        int insamplesize=1;
        if(height>reqheight||width>reqwidth)
        {
            final int halfheight=height/2;
            final int halfwidth=width/2;
            while((halfheight/insamplesize)>=reqheight&&(halfwidth/insamplesize)>=reqwidth)
            {
                insamplesize*=2;
            }
        }
        return insamplesize;
    }

    public static Bitmap decodeSampledBitmapFromResources(Resources res , int resid,int reqwidth,int reqheight)
    {
        final BitmapFactory.Options options=new BitmapFactory.Options();
        options.inJustDecodeBounds=true;
        BitmapFactory.decodeResource(res,resid,options);
        options.inSampleSize=calculateInSampleSize(options,reqwidth,reqheight);
        options.inJustDecodeBounds=false;
        return BitmapFactory.decodeResource(res,resid,options);
    }


    private Bitmap decodeFile(File f){
        try {
            //decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(f),null,o);
            //Find the correct scale value. It should be the power of 2.
            final int REQUIRED_SIZE=70;
            int width_tmp=o.outWidth, height_tmp=o.outHeight;
            int scale=1;
            while(true){
                if(width_tmp/2<REQUIRED_SIZE || height_tmp/2<REQUIRED_SIZE)
                    break;
                width_tmp/=2;
                height_tmp/=2;
                scale++;
            }

            //decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize=scale;
            return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
        } catch (FileNotFoundException e) {}
        return null;
    }*/
}
