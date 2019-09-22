package com.example.gf_daniel.mioapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser==null){
            Intent noUsersIntent = new Intent(MainActivity.this, NoUsersActivity.class);
            startActivity(noUsersIntent);
            finish();
        }else{
            Intent usersIntent = new Intent(MainActivity.this, UsersActivity.class);
            startActivity(usersIntent);
            finish();
        }
    }


    public void proViajesOnClick(MenuItem item) {
        Intent proViajesIntent = new Intent(MainActivity.this,ProgramarViajesActivity.class);
        startActivity(proViajesIntent);
    }

    public void tutorialesOnClick(MenuItem item) {
        Intent tutorialesIntent = new Intent(MainActivity.this,TutorialesActivity.class);
        startActivity(tutorialesIntent);
    }

    public void cerrarSesionOnClick(MenuItem item){
        FirebaseAuth.getInstance().signOut();
    }



}
