package com.kemikalreaktion.genie;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.kemikalreaktion.genie.Constants.AUTH_REQUEST_URL;
import static com.kemikalreaktion.genie.Constants.LOGOUT_URL;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void logout(View view) {
        /*Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(LOGOUT_URL));
        startActivity(browserIntent);*/

        logoutTask.execute();
    }

    AsyncTask<Void, Void, Void> logoutTask = new AsyncTask<Void, Void, Void>() {
        @Override
        protected Void doInBackground(Void[] objects) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(LOGOUT_URL)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                String stringResponse = response.body().string();
                Log.d(TAG, "response=" + stringResponse);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Intent intent = new Intent(MainActivity.this, ActivityLogin.class);
            startActivity(intent);
            MainActivity.this.finish();
        }
    };
}
