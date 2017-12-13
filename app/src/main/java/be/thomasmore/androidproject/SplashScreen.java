package be.thomasmore.androidproject;

/**
 * Created by larsg on 4/12/2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import static java.lang.Thread.sleep;

public class SplashScreen extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_splash);
        super.onCreate(savedInstanceState);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                // naar startscherm
                Intent i;
                i = new Intent(SplashScreen.this, Startpagina.class);
                SplashScreen.this.startActivity(i);
                SplashScreen.this.finish();

            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}


