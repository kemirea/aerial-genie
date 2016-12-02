package com.kemikalreaktion.genie;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.util.HashMap;

import static com.kemikalreaktion.genie.Constants.AUTH_REQUEST_URL;

/**
 * ActivityLogin
 * TODO: add class header comment
 */

public class ActivityLogin extends AppCompatActivity {
    private static final String TAG = "ActivityLogin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        WebView webView = (WebView) findViewById(R.id.webview);
        webView.loadUrl(AUTH_REQUEST_URL);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String request) {
                Log.d(TAG, "shouldOverrideUrlLoading request=" + request);
                Uri uri = Uri.parse(request);

                if (uri.getAuthority().equals("localhost")) {
                    if (uri.getFragment() != null) {
                        // login success
                        String token = getAccessToken(uri.getFragment());

                        Toast.makeText(ActivityLogin.this, "Success!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ActivityLogin.this, MainActivity.class);
                        startActivity(intent);
                    }
                    else if (uri.getQuery() != null) {
                        // login failed
                        Toast.makeText(ActivityLogin.this, "Login failed", Toast.LENGTH_SHORT).show();
                    }
                    ActivityLogin.this.finish();
                    return true;
                }
                else {
                    return false;
                }
            }
        });
    }

    private String getAccessToken(String fragment) {
        if (fragment == null || fragment.length() == 0) {
            return null;
        }

        String[] splitString = fragment.split("=");
        if (splitString.length == 2) {
            return splitString[1];
        }
        else {
            return null;
        }
    }

    public void onClick(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(AUTH_REQUEST_URL));
        startActivity(browserIntent);
    }
}
