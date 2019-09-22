package com.example.gf_daniel.mioapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Notification;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.security.spec.ECField;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class PQRSActivity extends AppCompatActivity {

    String correo;
    String contrasena;

    EditText nombre;
    Button enviar;

    Session session;
    Spinner opciones;
    Spinner opciones2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pqrs);

        opciones =(Spinner)findViewById(R.id.sp01);
        opciones2 =(Spinner)findViewById(R.id.sp02);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.opciones, android.R.layout.simple_spinner_item);
        opciones.setAdapter(adapter);
        ArrayAdapter<CharSequence> adapter2=ArrayAdapter.createFromResource(this, R.array.opciones2, android.R.layout.simple_spinner_item);
        opciones2.setAdapter(adapter2);

        nombre=(EditText)findViewById(R.id.nombre);

        enviar =(Button)findViewById(R.id.enviar);

        correo="appmetrocali@gmail.com";
        contrasena="ARgentina1810";

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StrictMode.ThreadPolicy policy= new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);

                Properties properties=new Properties();
                properties.put("mail.smtp.auth", "true");
                properties.put("mail.smtp.starttls.enable", "true");
                properties.put("mail.smtp.host", "smtp.gmail.com");
                properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
                properties.put("mail.smtp.port", "587");

                try {
                    session=Session.getDefaultInstance(properties, new Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(correo,contrasena);
                        }
                    });


                    if (session!=null){
                        Message message = new MimeMessage(session);
                        message.setFrom(new InternetAddress(correo));
                        message.setSubject("holi");
                        message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("jorge.b16@hotmail.com"));
                        message.setContent(nombre.getText().toString(),"txt/html; charset=utf-8");

                        Transport.send(message);

                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

    }
}
