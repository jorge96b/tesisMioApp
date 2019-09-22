package com.example.gf_daniel.mioapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class SaldoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saldo);

        WebView myWebView = (WebView) this.findViewById(R.id.webViewSaldo);

        // Enable JavaScript WebSettings webSettings =
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Provide a WebViewClient for your WebView
        myWebView.setWebViewClient(new WebViewClient());
        //myWebView.loadUrl("https://twitter.com/metrocali");
        myWebView.loadUrl("https://servicios.siur.com.co/saldo/");
        //myWebView.loadUrl("http://190.216.202.35:8033/saldo/");
    }
}
