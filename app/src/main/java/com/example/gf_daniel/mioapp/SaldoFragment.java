package com.example.gf_daniel.mioapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class SaldoFragment extends Fragment {

    public WebView myWebView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_saldo, container, false);
        myWebView = (WebView) v.findViewById(R.id.webViewSaldo);
        myWebView.loadUrl("https://servicios.siur.com.co/saldo/");
        //myWebView.loadUrl("http://190.216.202.35:8033/saldo/");

        // Enable Javascript
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        myWebView.setWebViewClient(new WebViewClient());

        return v;
    }
}
