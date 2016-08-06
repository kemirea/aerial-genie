package com.kemikalreaktion.genie.core;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
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

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onStart() {
        super.onStart();
        new LoadDataTask().execute();
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    private class LoadDataTask extends AsyncTask<Void, Integer, Boolean> {
        @Override
        protected void onPostExecute(Boolean result) {
            //startActivity(new Intent(getApplicationContext(), ActivityMain.class));
            startActivity(new Intent(getApplicationContext(), ActivityTest.class));
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            GenieManager.getInstance().loadData();
            try {
                Thread.sleep(2000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            return true;
        }

        protected void onProgressUpdate(Integer... progress) {
        }

    };
}
