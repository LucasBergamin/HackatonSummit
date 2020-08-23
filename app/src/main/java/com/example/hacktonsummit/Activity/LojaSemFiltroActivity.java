package com.example.hacktonsummit.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.hacktonsummit.R;

public class LojaSemFiltroActivity extends AppCompatActivity {

    private TextView textPersonalizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loja_sem_filtro);
        textPersonalizar = findViewById(R.id.textPersonalizar);

        textPersonalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), VoltadoPersonalizacaoActivity.class));
            }
        });
    }


}