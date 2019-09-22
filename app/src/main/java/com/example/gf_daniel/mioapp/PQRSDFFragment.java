package com.example.gf_daniel.mioapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.app.Notification;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

import java.security.spec.ECField;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class PQRSDFFragment extends Fragment {

    String correo;
    String contrasena;

    EditText mensaje;
    Button enviar;

    Session session;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v1=inflater.inflate(R.layout.fragment_pqrsdf, container, false);
        mensaje= (EditText) v1.findViewById(R.id.mensajeCorreo);
        enviar = (Button) v1.findViewById(R.id.enviarCorreo);

        correo="jorge96bolanos@gmail.com";
        contrasena="Asus960M";

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StrictMode.ThreadPolicy policy= new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);

                Properties properties=new Properties();
                properties.put("mail.smtp.host","smtp.googlemail.com");
                properties.put("mail.smtp.socketFactory.port","465");
                properties.put("mail.smtp.socketFactory.class","java.net.ssl.SSLSocketFactory");
                properties.put("mail.smtp.port","465");

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
                        message.setContent(mensaje.getText().toString(),"txt/html; charset=utf-8");

                        Transport.send(message);

                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

        // Inflate the layout for this fragment
        return v1;
    }
}
