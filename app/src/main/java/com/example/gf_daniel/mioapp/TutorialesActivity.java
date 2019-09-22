package com.example.gf_daniel.mioapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class TutorialesActivity extends AppCompatActivity {

    ImageButton tutorial1;
    ImageButton tutorial2;
    ImageButton tutorial3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutoriales);

        tutorial1=(ImageButton) findViewById(R.id.imageButton);
        tutorial2=(ImageButton) findViewById(R.id.imageButton3);
        tutorial3=(ImageButton) findViewById(R.id.imageButton4);

        tutorial1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tutorial1 = new Intent(TutorialesActivity.this,tPlaneTuViajeActivity.class);
                startActivity(tutorial1);
            }
        });

        tutorial2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tutorial2 = new Intent(TutorialesActivity.this,tIngresarEstacionesActivity.class);
                startActivity(tutorial2);
            }
        });

        tutorial3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tutorial3 = new Intent(TutorialesActivity.this,tRecargarTargetaActivity.class);
                startActivity(tutorial3);
            }
        });
    }
}
