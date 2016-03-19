package com.kemikalreaktion.genie.core;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;

import com.kemikalreaktion.genie.R;

public class ActivitySplash extends Activity{
    Handler timeoutHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT < 16) {
            // Hide notification bar for Android 4.0 and lower
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        else {
            // Hide notification bar for Android 4.1 and higher
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        }

        setContentView(R.layout.activity_splash);

        timeoutHandler = new Handler();
        timeoutHandler.postDelayed(passGo, 2000);
    }

    @Override
    protected void onStart() {
        super.onStart();
        GenieManager.getInstance().loadData();
    }

    @Override
    protected void onStop() {
        super.onStop();
        timeoutHandler.removeCallbacks(passGo);
        finish();
    }

    private Runnable passGo = new Runnable() {
        @Override
        public void run() {
            startActivity(new Intent(getApplicationContext(), ActivityMain.class));
        }
    };
}
