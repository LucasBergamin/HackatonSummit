package com.example.hacktonsummit.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.hacktonsummit.R;

public class respostaPositivadaNegativaActivity extends AppCompatActivity {

    private Button btnVoltarPersonalizar, btnIrEmbora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resposta_positivada_negativa);

        btnVoltarPersonalizar = findViewById(R.id.btnSalvar);
        btnIrEmbora = findViewById(R.id.btnIrEmbora);

        btnVoltarPersonalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), VoltadoPersonalizacaoActivity.class));
            }
        });

        btnIrEmbora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), naoActivity.class));
            }
        });

    }
}