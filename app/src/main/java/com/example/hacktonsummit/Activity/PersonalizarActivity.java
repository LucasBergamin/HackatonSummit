package com.example.hacktonsummit.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hacktonsummit.Config.configuracaoFirabase;
import com.example.hacktonsummit.Helper.Base64Custom;
import com.example.hacktonsummit.Model.Usuario;
import com.example.hacktonsummit.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class PersonalizarActivity extends AppCompatActivity {

    private TextView editNomePessoa;
    private EditText textComprasOnlines;
    private DatabaseReference reference = configuracaoFirabase.getFirabaseDatasabe();
    private FirebaseAuth autentificacao = configuracaoFirabase.getFirebaseAutentificacao();
    private DatabaseReference usuarioRef;
    private ValueEventListener valueEventListenerUsuario;
    private Button btnSalvar;
    private String mensagemPositiva[];
    private String mensagemNegativa[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalizar);
        editNomePessoa = findViewById(R.id.editNomePessoa);
        textComprasOnlines = findViewById(R.id.textCupom);
        btnSalvar = findViewById(R.id.btnSalvar);

        mensagemPositiva = new String[8];
        mensagemNegativa = new String[8];
        positivo();
        negativo();

        recuperarDados();

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifica();
            }
        });
    }

    public void recuperarDados(){
        String emailUsuario = autentificacao.getCurrentUser().getEmail();
        String idUsuario = Base64Custom.codificarBase64(emailUsuario);
        usuarioRef = reference.child("Usuarios").child(idUsuario);

        valueEventListenerUsuario = usuarioRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Usuario usuario = dataSnapshot.getValue(Usuario.class);

                editNomePessoa.setText("Olá, " + usuario.getNome());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void verifica(){
        String compras = textComprasOnlines.getText().toString();
        for (int i = 0;i<mensagemPositiva.length;i++){
            if(compras.equals(mensagemPositiva[i])){
                startActivity(new Intent(getApplicationContext(), PositivoActivity.class));
                finish();
            }
        }
        for (int i = 0;i<mensagemNegativa.length;i++){
            if(compras.equals(mensagemNegativa[i])){
                startActivity(new Intent(getApplicationContext(), NegativoActivity.class));
                finish();
            }
        }

    }

    public void positivo(){
        mensagemPositiva[0] = "amo fazer compras também";
        mensagemPositiva[1] = "Amo fazer compras também";
        mensagemPositiva[2] = "sim";
        mensagemPositiva[3] = "SIM";
        mensagemPositiva[4] = "amo";
        mensagemPositiva[5] = "concerteza";
        mensagemPositiva[6] = "claro";
        mensagemPositiva[7] = "amo também";
    }

    public void negativo(){
        mensagemNegativa[0] = "não gosto";
        mensagemNegativa[1] = "odeio";
        mensagemNegativa[2] = "nao";
        mensagemNegativa[3] = "nunca gostei";
        mensagemNegativa[4] = "tanto faz";
        mensagemNegativa[5] = "concerteza nao";
        mensagemNegativa[6] = "não";
        mensagemNegativa[7] = "prefiro comprar físicamente";
    }
}