package ie.nr.activities;

import android.graphics.ImageDecoder;
import android.graphics.drawable.AnimatedImageDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.widget.ImageView;

import ie.nr.R;

public class Splash extends Activity{

    private boolean 			mIsBackButtonPressed;
    private static final int 	SPLASH_DURATION = 4000;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);


        ImageView im = findViewById(R.id.imageView);

        try {
            AnimatedImageDrawable netflixAnimation;
            netflixAnimation = (AnimatedImageDrawable) ImageDecoder.decodeDrawable(
                    ImageDecoder.createSource(getResources(), R.drawable.net_splash));
            im.setImageDrawable(netflixAnimation);
            netflixAnimation.start();
        }
        catch(Exception e)
        {

        }

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {

            @Override
            public void run() {

                finish();

                if (!mIsBackButtonPressed) {

                    Intent intent = new Intent(Splash.this, LogIn.class);
                    Splash.this.startActivity(intent);
                }
            }
        }, SPLASH_DURATION);
    }

    @Override
    public void onBackPressed() {

        mIsBackButtonPressed = true;
        super.onBackPressed();
    }
}

