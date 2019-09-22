package com.example.gf_daniel.mioapp;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

//import com.nispok.snackbar.Snackbar;
import android.support.design.widget.Snackbar;

@SuppressLint("SetJavaScriptEnabled")
public class TrayectoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trayecto);

        WebView myWebView = (WebView) findViewById(R.id.webViewTrayecto);

        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        //myWebView.setWebChromeClient(new WebChromeClient());
        myWebView.setWebViewClient(new WebViewClient());

        myWebView.loadUrl("http://maps.google.com/maps?" + "saddr=" + "&daddr=");


    }
}


