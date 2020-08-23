package com.example.hacktonsummit.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hacktonsummit.Model.Dados;
import com.example.hacktonsummit.R;

public class CupomRespostaPositivaActivity extends AppCompatActivity {

    private EditText textCupom;
    private Button btnSalvar;
    private Dados dados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cupom_resposta_positiva);
        textCupom = findViewById(R.id.textCupom);
        btnSalvar = findViewById(R.id.btnSalvar);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String gostaFazer = textCupom.getText().toString();
                if(!gostaFazer.isEmpty()){

                    dados = new Dados();

                    dados.setFinalDeSemana(gostaFazer);
                    dados.salvar();

                    Toast.makeText(getApplicationContext(), "preenchido com sucesso :)", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(getApplicationContext(), ComemorandoActivity.class));
                    finish();


                }else{
                    Toast.makeText(getApplicationContext(), "preencha o campo.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}