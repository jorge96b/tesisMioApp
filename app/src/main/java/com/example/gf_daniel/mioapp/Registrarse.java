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

public class Registrarse extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private ProgressDialog mMensaje;

    private EditText mNombre;
    private EditText mCorreo;
    private EditText mContrasena;
    private Button mRegistrarseBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        mAuth = FirebaseAuth.getInstance();

        mMensaje = new ProgressDialog(this);

        mNombre = (EditText) findViewById(R.id.nombre_de_usuario);
        mCorreo = (EditText) findViewById(R.id.correo_del_usuario);
        mContrasena = (EditText) findViewById(R.id.contrasena_del_usuario);
        mRegistrarseBtn = (Button) findViewById(R.id.btn_registrarse);

        mRegistrarseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Nombre = mNombre.getText().toString();
                String Correo = mCorreo.getText().toString();
                String Contrasena = mContrasena.getText().toString();

                if (!TextUtils.isEmpty(Nombre) & !TextUtils.isEmpty(Correo) & !TextUtils.isEmpty(Contrasena)){

                    mMensaje.setTitle(R.string.registrando);
                    mMensaje.setMessage("Por favor espera");
                    mMensaje.setCanceledOnTouchOutside(false);
                    mMensaje.show();

                    registrarUsuario(Nombre, Correo, Contrasena);
                }

            }
        });
    }

    private void registrarUsuario(String nombre, String correo, String contrasena){
        mAuth.createUserWithEmailAndPassword(correo, contrasena).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    mMensaje.dismiss();

                    Intent mainActivityIntent = new Intent(Registrarse.this, MainActivity.class);
                    mainActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);//no me deja que me devuelva a la activity anterior si me registre exitosamente
                    startActivity(mainActivityIntent);
                    finish();

                }else{
                    mMensaje.hide();
                    Toast.makeText(Registrarse.this,R.string.error, Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
