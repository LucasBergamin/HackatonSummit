package com.example.hacktonsummit.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.hacktonsummit.R;

public class InformacoesMaryActivity extends AppCompatActivity {

    private Button btnNao, btnClaro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacoes_mary);
        btnNao = findViewById(R.id.btnNao);
        btnClaro = findViewById(R.id.btnClaro);

        btnNao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), naoActivity.class));
            }
        });

        btnClaro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), PersonalizarActivity.class));
            }
        });
    }

}