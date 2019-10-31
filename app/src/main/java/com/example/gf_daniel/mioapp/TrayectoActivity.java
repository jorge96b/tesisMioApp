package com.example.gf_daniel.mioapp;
import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import android.webkit.WebSettings;

import android.webkit.WebViewClient;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class TrayectoActivity extends AppCompatActivity  {

    public static final String TAG = "MainActivity";

    private static final int RECORD_REQUEST_CODE = 101;
    @BindView(R.id.status)
    TextView status;
    @BindView(R.id.textMessage)
    TextView textMessage;


    String url = "https://www.google.com/maps/dir/?api=1&origin=Pontificia+Universidad+Javeriana+Cali&destination=00002+Cali+Colombia&travelmode=transit";
    String[] dir = {
            "carrera",
            "calle",
            "avenida",
            "diagonal",
            "transversal","estacion","estación"
    };

    String tem= "0001.*";
    String direccion;
    final Context context = this;
    private List<String> stringList;
    private SpeechAPI speechAPI;
    private VoiceRecorder mVoiceRecorder;
    private ProgressDialog progressDialog;
    private final VoiceRecorder.Callback mVoiceCallback = new VoiceRecorder.Callback() {

        @Override
        public void onVoiceStart() {
            if (speechAPI != null) {
                speechAPI.startRecognizing(mVoiceRecorder.getSampleRate());
            }
        }

        @Override
        public void onVoice(byte[] data, int size) {
            if (speechAPI != null) {
                speechAPI.recognize(data, size);
            }
        }

        @Override
        public void onVoiceEnd() {
            if (speechAPI != null) {
                speechAPI.finishRecognizing();
            }
        }

    };
    private ArrayAdapter adapter;
    private final SpeechAPI.Listener mSpeechServiceListener =
            new SpeechAPI.Listener() {
                @Override
                public void onSpeechRecognized(final String text, final boolean isFinal, final Float stability, final Float confidence) {
                    if (isFinal) {
                        mVoiceRecorder.dismiss();
                    }
                    if (textMessage != null && !TextUtils.isEmpty(text)) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (isFinal) {
                                    textMessage.setText(null);
                                    stringList.add(0,text);
                                    adapter.notifyDataSetChanged();
                                    mVoiceRecorder.stop();
                                    mVoiceRecorder.dismiss();
                                    System.out.println("error" + " "+(1-confidence));
                                    String[] temText = text.split(" ");
                                    if(temText.length<=2){
                                        direccion=text;
                                    }else{
                                        direccion= text;
                                        int bandera = 0;
                                        while (((direccion.split(" a ")).length>1) || (direccion.split(" al ").length>1 )){
                                            for (String s : dir) {
                                                String replace = tem.replaceAll("0001", s);
                                                if (direccion.matches(replace)) {
                                                    bandera=1;
                                                    break;
                                                }
                                            }
                                            if(bandera==1){
                                                break;
                                            }else if((direccion.split(" a ")).length>1) {
                                                String[] subText = direccion.split(" a ",2);
                                                if(subText[1].matches("la.*")){
                                                    direccion=subText[1];
                                                    String[] subText1 = direccion.split("la",2);
                                                    direccion=subText1[1];
                                                }else{
                                                    direccion = subText[1];
                                                }
                                            }else if((direccion.split(" al ")).length>1) {
                                                String[] subText = direccion.split(" al ",2);
                                                direccion = subText[1];
                                            }
                                        }
                                    }
                                    System.out.println("dirreccion"+direccion);
                                    progressDialog.dismiss();
                                    if(confidence > 0.7){
                                        WebView myWebView = (WebView) findViewById(R.id.webViewTrayecto);
                                        WebSettings webSettings = myWebView.getSettings();
                                        webSettings.setJavaScriptEnabled(true);
                                        myWebView.setWebViewClient(new WebViewClient());
                                        String newUrl = url.replaceAll("00002", direccion);
                                        myWebView.loadUrl(newUrl);
                                    }else if(confidence > 0.6 && confidence<=0.7){
                                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                                        // set title
                                        alertDialogBuilder.setTitle("Quieres ir a "+ direccion);

                                        // set dialog message
                                        alertDialogBuilder
                                                .setMessage("teniendo en cuenta que el % de error es  "+ (1-confidence));
                                        // create alert dialog
                                        AlertDialog alertDialog = alertDialogBuilder.create();

                                        // show it
                                        alertDialog.show();
                                    }else{
                                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                                        // set title
                                        alertDialogBuilder.setTitle("Intentalo de nuevo");
                                                // set dialog message
                                        alertDialogBuilder
                                                .setMessage("");
                                        // create alert dialog
                                        AlertDialog alertDialog = alertDialogBuilder.create();

                                        // show it
                                        alertDialog.show();
                                    }
                                } else {
                                    textMessage.setText(text);
                                }
                            }
                        });
                    }
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        progressDialog= new ProgressDialog(this);
        progressDialog.setMessage("Escuchando");
        progressDialog.show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trayecto);
        ButterKnife.bind(this);
        speechAPI = new SpeechAPI(TrayectoActivity.this);
        stringList = new ArrayList<>();
        adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, stringList);
    }

    @Override
    protected void onStop() {
        stopVoiceRecorder();

        // Stop Cloud Speech API
        speechAPI.removeListener(mSpeechServiceListener);
        speechAPI.destroy();
        speechAPI = null;

        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (isGrantedPermission(Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
            startVoiceRecorder();
        } else {
            makeRequest(Manifest.permission.RECORD_AUDIO);
        }
        speechAPI.addListener(mSpeechServiceListener);
    }

    private int isGrantedPermission(String permission) {
        return ContextCompat.checkSelfPermission(this, permission);
    }

    private void makeRequest(String permission) {
        ActivityCompat.requestPermissions(this, new String[]{permission}, RECORD_REQUEST_CODE);
    }

    private void startVoiceRecorder() {
        if (mVoiceRecorder != null) {
            mVoiceRecorder.stop();
        }
        mVoiceRecorder = new VoiceRecorder(mVoiceCallback);
        mVoiceRecorder.start();
    }

    private void stopVoiceRecorder() {
        if (mVoiceRecorder != null) {
            mVoiceRecorder.stop();
            mVoiceRecorder = null;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == RECORD_REQUEST_CODE) {
            if (grantResults.length == 0 && grantResults[0] == PackageManager.PERMISSION_DENIED
                    && grantResults[0] == PackageManager.PERMISSION_DENIED) {
                finish();
            } else {
                startVoiceRecorder();
            }
        }
    }

}
