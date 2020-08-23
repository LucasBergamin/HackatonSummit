package com.example.hacktonsummit.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hacktonsummit.Model.Usuario;
import com.example.hacktonsummit.R;

public class IdadeActivity extends AppCompatActivity {

    private EditText textIdade;
    private Button btnSalvar;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idade);

        textIdade = findViewById(R.id.textCupom);
        btnSalvar = findViewById(R.id.btnSalvar);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idade = textIdade.getText().toString();
                if(!idade.isEmpty()){

                    usuario = new Usuario();

                    usuario.setIdade(idade);
                    usuario.salvarPersonalizacao();

                    Toast.makeText(getApplicationContext(), "Nossa que novinho em", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(getApplicationContext(), IncrivelActivity.class));

                }else{
                    Toast.makeText(getApplicationContext(), "preencha o campo.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}