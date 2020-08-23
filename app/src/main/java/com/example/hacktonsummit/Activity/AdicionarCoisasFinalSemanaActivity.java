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

public class AdicionarCoisasFinalSemanaActivity extends AppCompatActivity {

    private EditText textGostaFinalSemana;
    private Button btnSalvar;
    private Dados dados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_coisas_final_semana);

        textGostaFinalSemana = findViewById(R.id.textGostaFinalSemana);
        btnSalvar = findViewById(R.id.btnSalvar);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String gostaFinalSemana = textGostaFinalSemana.getText().toString();
                if(!gostaFinalSemana.isEmpty()){

                    dados = new Dados();

                    dados.setFinalDeSemana(gostaFinalSemana);
                    dados.salvar();

                    Toast.makeText(getApplicationContext(), "preenchido com sucesso :)", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(getApplicationContext(), MeuPerfilActivity.class));
                    finish();

                }else{
                    Toast.makeText(getApplicationContext(), "preencha o campo.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}