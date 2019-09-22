package com.example.gf_daniel.mioapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class IniciarSesion extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private EditText mCorreo;
    private EditText mContrasena;
    private Button mIniciarSesionBtn;

    private ProgressDialog mMensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);

        mAuth = FirebaseAuth.getInstance();

        mMensaje = new ProgressDialog(this);

        mCorreo = (EditText) findViewById(R.id.correo_del_usuario);
        mContrasena = (EditText) findViewById(R.id.contrasena_del_usuario);
        mIniciarSesionBtn = (Button) findViewById(R.id.btn_iniciar_sesion);

        mIniciarSesionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Correo = mCorreo.getText().toString();
                String Contrasena = mContrasena.getText().toString();

                if (!TextUtils.isEmpty(Correo) & !TextUtils.isEmpty(Contrasena)){
                    mMensaje.setTitle(R.string.iniciando_sesion);
                    mMensaje.setMessage("Por favor espera");
                    mMensaje.setCanceledOnTouchOutside(false);
                    mMensaje.show();
                    iniciarSesion(Correo, Contrasena);
                }
            }
        });
    }

    private void iniciarSesion(String Correo, String Contrasena){
        mAuth.signInWithEmailAndPassword(Correo, Contrasena).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    mMensaje.dismiss();
                    Intent mainActivityIntent = new Intent(IniciarSesion.this, MainActivity.class);
                    mainActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);//no me deja que me devuelva a la activiti anterior si me loguie exitosamente
                    startActivity(mainActivityIntent);
                    finish();
                }else{
                    mMensaje.hide();
                    Toast.makeText(IniciarSesion.this,R.string.error, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
