package com.example.hacktonsummit.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.hacktonsummit.R;

public class NegativoActivity extends AppCompatActivity {

    private Button btnProximo;
    private EditText textNegativo;
    private String mensagemPositiva[];
    private String mensagemNegativa[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_negativo);

        btnProximo = findViewById(R.id.btnSalvar);
        textNegativo = findViewById(R.id.textCupom);

        mensagemPositiva = new String[8];
        mensagemNegativa = new String[8];
        positivo();
        negativo();

        btnProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifica();
            }
        });
    }

    public void verifica(){
        String negativo = textNegativo.getText().toString();
        for (int i = 0;i<mensagemPositiva.length;i++){
            if(negativo.equals(mensagemPositiva[i])){
                startActivity(new Intent(getApplicationContext(), respostaPositivadaNegativaActivity.class));
                finish();
            }
        }
        for (int i = 0;i<mensagemNegativa.length;i++){
            if(negativo.equals(mensagemNegativa[i])){
                startActivity(new Intent(getApplicationContext(), naoActivity.class));
                finish();
            }
        }

    }

    public void positivo(){
        mensagemPositiva[0] = "vamos";
        mensagemPositiva[1] = "sim";
        mensagemPositiva[2] = "SIM";
        mensagemPositiva[3] = "bora";
        mensagemPositiva[4] = "claro";
        mensagemPositiva[5] = "vamos la";
        mensagemPositiva[6] = "me conte";
        mensagemPositiva[7] = "fala";
    }

    public void negativo(){
        mensagemNegativa[0] = "nao quero";
        mensagemNegativa[1] = "NAO";
        mensagemNegativa[2] = "agora nao";
        mensagemNegativa[3] = "depois";
        mensagemNegativa[4] = "mais tarde";
        mensagemNegativa[5] = "nao";
        mensagemNegativa[6] = "não";
        mensagemNegativa[7] = "para de encher o saco";
    }
}