package be.thomasmore.androidproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Lars Gijbels.
 *
 */

public class SplashScreen extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_splashscreen);
        super.onCreate(savedInstanceState);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i;
                i = new Intent(SplashScreen.this, Startpagina.class);
                SplashScreen.this.startActivity(i);
                SplashScreen.this.finish();

            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}


