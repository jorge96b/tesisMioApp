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
import android.content.Intent;
//import com.nispok.snackbar.Snackbar;
import android.speech.RecognizerIntent;
import android.support.design.widget.Snackbar;
import java.util.Locale;
import android.widget.Toast;

@SuppressLint("SetJavaScriptEnabled")
public class TrayectoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 10);
        } else {
            Toast.makeText(this, "Your Device Don't Support Speech Input", Toast.LENGTH_SHORT).show();
        }
        //setContentView(R.layout.activity_trayecto);

       // WebView myWebView = (WebView) findViewById(R.id.webViewTrayecto);

       // WebSettings webSettings = myWebView.getSettings();
        //webSettings.setJavaScriptEnabled(true);

        //myWebView.setWebChromeClient(new WebChromeClient());
       // myWebView.setWebViewClient(new WebViewClient());

        //myWebView.loadUrl("http://maps.google.com/maps?" + "saddr=" + "&daddr=");


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 10) {

            if (resultCode == RESULT_OK && data != null) {
                setContentView(R.layout.activity_trayecto);
                WebView myWebView = (WebView) findViewById(R.id.webViewTrayecto);

                WebSettings webSettings = myWebView.getSettings();
                webSettings.setJavaScriptEnabled(true);
                myWebView.setWebViewClient(new WebViewClient());

                myWebView.loadUrl("https://www.google.com/maps/dir/?api=1&origin=Pontificia+Universidad+Javeriana+Cali&destination=Cosmocentro+Cali+Colombia&travelmode=transit");
            }

        }
    }
}


