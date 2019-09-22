package com.example.gf_daniel.mioapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.gf_daniel.mioapp.Fragments.MapaFragment;

public class NoUsersActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_users);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.bringToFront();
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent trayectoIntent = new Intent(NoUsersActivity.this, TrayectoActivity.class);
                startActivity(trayectoIntent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //FragmentManager fragmentManager = getSupportFragmentManager();
        //fragmentManager.beginTransaction().replace(R.id.contenedor_no_users, new Mapa_Fragment()).commit();

        android.app.FragmentManager mfragmentManager = getFragmentManager();
        android.app.FragmentTransaction fragmentTransaction = mfragmentManager.beginTransaction();
        MapaFragment mapaFragment = new MapaFragment();
        fragmentTransaction.replace(R.id.contenedor_no_users,mapaFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentManager fragmentManager = getSupportFragmentManager();

        android.app.FragmentManager mfragmentManager = getFragmentManager();

        if (id == R.id.mapa) {
            android.app.FragmentTransaction fragmentTransaction = mfragmentManager.beginTransaction();
            MapaFragment mapaFragment = new MapaFragment();
            fragmentTransaction.replace(R.id.contenedor_no_users,mapaFragment);
            fragmentTransaction.commit();
        }else if (id == R.id.saldo) {
            //fragmentManager.beginTransaction().replace(R.id.contenedor_no_users, new SaldoFragment()).commit();
            Intent SaldoIntent = new Intent(NoUsersActivity.this, SaldoActivity.class);
            startActivity(SaldoIntent);
        } else if (id == R.id.noticias) {
            //fragmentManager.beginTransaction().replace(R.id.contenedor_no_users, new NoticiasFragment()).commit();
            Intent NoticiasIntent = new Intent(NoUsersActivity.this, NoticiasActivity.class);
            startActivity(NoticiasIntent);
        } else if (id == R.id.pqrsdf) {
            //fragmentManager.beginTransaction().replace(R.id.contenedor_no_users, new PQRSDFFragment()).commit();
            Intent mainIntent = new Intent(NoUsersActivity.this, PQRSActivity.class);
            startActivity(mainIntent);
        } else if (id == R.id.tutoriales) {
            Intent tutorialesIntent = new Intent(NoUsersActivity.this, TutorialesActivity.class);
            startActivity(tutorialesIntent);
        } else if (id == R.id.iniciar_sesion) {
            Intent sesionIntent = new Intent(NoUsersActivity.this, IniciarSesion.class);
            startActivity(sesionIntent);
        } else if (id == R.id.registrarse) {
            Intent registrarseIntent = new Intent(NoUsersActivity.this, Registrarse.class);
            startActivity(registrarseIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
