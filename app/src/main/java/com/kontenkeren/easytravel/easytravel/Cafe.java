package com.kontenkeren.easytravel.easytravel;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MotionEvent;

/**
 * Created by Djoko Priyono on 14/02/2016. djoko@maxmobilemedia.com
 */
public class Cafe extends Activity {

    protected boolean _active = true;
    protected int _splashTime = 2500;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.cafe);

        // thread for displaying the SplashScreen
        Thread splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while(_active && (waited < _splashTime)) {
                        sleep(200);
                        if(_active) {
                            waited += 100;
                        }
                    }
                } catch(InterruptedException e) {
                    // do nothing
                } finally {
                    finish();
                    Intent newIntent=new Intent(Cafe.this, MainActivity.class);
                    startActivityForResult(newIntent,0);
                }
            }
        };
        splashTread.start();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            _active = false;
        }
        return true;
    }
}
