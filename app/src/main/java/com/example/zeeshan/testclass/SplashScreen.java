package com.example.zeeshan.testclass;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.felipecsl.gifimageview.library.GifImageView;

import java.io.InputStream;

public class SplashScreen extends AppCompatActivity {
   // Handler handler;
   // private GifImageView gifImageView;
   private final int SPLASH_DISPLAY_LENGTH = 3500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

      //  gifImageView=findViewById(R.id.spiralgif);
      // gifImageView.setBackgroundResource(R.drawable.spiral);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent=new Intent(SplashScreen.this,Login.class);
                startActivity(mainIntent);
                finish();
            }
        },SPLASH_DISPLAY_LENGTH);




    //    handler=new Handler();
     //   handler.postDelayed(new Runnable() {
       //     @Override
       //     public void run() {
       //         Intent intent=new Intent(SplashScreen.this,Login.class);
        //        startActivity(intent);
          //      finish();
        //    }
       // },7000);

    }
}
