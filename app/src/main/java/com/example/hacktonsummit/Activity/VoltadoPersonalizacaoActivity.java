package com.example.hacktonsummit.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.hacktonsummit.R;

public class VoltadoPersonalizacaoActivity extends AppCompatActivity {

    private Button btnBora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voltado_personalizacao);
        btnBora = findViewById(R.id.btnBora);

        btnBora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), PersonalizarActivity.class));
            }
        });
    }
}