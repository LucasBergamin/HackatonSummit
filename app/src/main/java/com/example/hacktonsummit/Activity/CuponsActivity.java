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

public class CuponsActivity extends AppCompatActivity {

    private TextView editNomePessoa;
    private EditText textCupom;
    private Button btnSalvar;
    private DatabaseReference reference = configuracaoFirabase.getFirabaseDatasabe();
    private FirebaseAuth autentificacao = configuracaoFirabase.getFirebaseAutentificacao();
    private DatabaseReference usuarioRef;
    private ValueEventListener valueEventListenerUsuario;
    private String mensagemPositiva[];
    private String mensagemNegativa[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cupons);

        editNomePessoa = findViewById(R.id.editNomePessoa);
        textCupom = findViewById(R.id.textCupom);
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

                editNomePessoa.setText("Agora me fala, " + usuario.getNome() + " Você quer receber cupons de desconto também?");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void verifica(){
        String cupom = textCupom.getText().toString();
        for (int i = 0;i<mensagemPositiva.length;i++){
            if(cupom.equals(mensagemPositiva[i])){
                startActivity(new Intent(getApplicationContext(), CupomRespostaPositivaActivity.class));
                finish();
            }
        }
        for (int i = 0;i<mensagemNegativa.length;i++){
            if(cupom.equals(mensagemNegativa[i])){
                startActivity(new Intent(getApplicationContext(), CupomRespostaNegativaActivity.class));
                finish();
            }
        }

    }

    public void positivo(){
        mensagemPositiva[0] = "quero";
        mensagemPositiva[1] = "quero sim";
        mensagemPositiva[2] = "sim";
        mensagemPositiva[3] = "SIM";
        mensagemPositiva[4] = "muito";
        mensagemPositiva[5] = "se possivel sil";
        mensagemPositiva[6] = "claro";
        mensagemPositiva[7] = "com certeza";
    }

    public void negativo(){
        mensagemNegativa[0] = "não";
        mensagemNegativa[1] = "nao quero";
        mensagemNegativa[2] = "nao";
        mensagemNegativa[3] = "agora nao";
        mensagemNegativa[4] = "tanto faz";
        mensagemNegativa[5] = "concerteza nao";
        mensagemNegativa[6] = "depois";
        mensagemNegativa[7] = "nao estou afim";
    }
}