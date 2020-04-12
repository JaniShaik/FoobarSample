package com.foobar.gireesam.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.foobar.gireesam.R;
import com.foobar.gireesam.utils.Utils;

public class SplashActivity extends BaseActivity {

    // Context
    private Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Utils.changeStatusBarColor(getResources().getColor(R.color.white), this);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer.
             */
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(mContext, LoginActivity.class);
                startActivity(i);
                finish();

            }
        }, 3000);

    }

    @Override
    protected int containerId() {
        return 0;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_splash;
    }
}
