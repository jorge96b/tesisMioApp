package com.example.gf_daniel.mioapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class NoticiasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias);

        WebView myWebView = (WebView) this.findViewById(R.id.webViewNoticias);

        // Enable JavaScript WebSettings webSettings =
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Provide a WebViewClient for your WebView
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.loadUrl("https://twitter.com/metrocali");
    }
}
