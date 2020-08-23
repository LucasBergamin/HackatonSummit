package com.example.hacktonsummit.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hacktonsummit.Config.configuracaoFirabase;
import com.example.hacktonsummit.Helper.Base64Custom;
import com.example.hacktonsummit.Model.Usuario;
import com.example.hacktonsummit.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class CadastrarActivity extends AppCompatActivity {

    private EditText campoNomeCompleto, campoAniversario, campoEmail, campoSenha, campoConfirmaSenha;
    private Button btnProximo;
    private FirebaseAuth autentificacao;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        campoNomeCompleto = findViewById(R.id.editNomeCompleto);
        campoAniversario = findViewById(R.id.editAniversario);
        campoEmail = findViewById(R.id.editEmail);
        campoSenha = findViewById(R.id.editSenha);
        campoConfirmaSenha = findViewById(R.id.editConfirmarSenha);
        btnProximo = findViewById(R.id.btnSalvar);

        btnProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = campoNomeCompleto.getText().toString();
                String aniversario = campoAniversario.getText().toString();
                String email = campoEmail.getText().toString();
                String senha = campoSenha.getText().toString();
                String confirmaSenha = campoConfirmaSenha.getText().toString();

                if(!nome.isEmpty()){
                    if(!aniversario.isEmpty()){
                        if(!email.isEmpty()){
                            if(!senha.isEmpty()){
                                if(!confirmaSenha.isEmpty()){
                                    if(senha.equals(confirmaSenha)){

                                        usuario = new Usuario();

                                        usuario.setNome(nome);
                                        usuario.setAniversario(aniversario);
                                        usuario.setEmail(email);
                                        usuario.setSenha(senha);

                                        cadastrarUsuario();

                                    }else{
                                        Toast.makeText(getApplicationContext(), "Uma senha está diferente da outra", Toast.LENGTH_SHORT).show();
                                    }
                                }else{
                                    Toast.makeText(getApplicationContext(), "Confirme a senha!", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(getApplicationContext(), "Preencha a senha!", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(getApplicationContext(), "Preencha o email!", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "Preencha a data de aniversario!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Preencha o nome!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void cadastrarUsuario(){
        autentificacao = configuracaoFirabase.getFirebaseAutentificacao();
        autentificacao.createUserWithEmailAndPassword(
                usuario.getEmail(), usuario.getSenha()
        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    String idUsuario = Base64Custom.codificarBase64(usuario.getEmail());
                    usuario.setIdUsuario(idUsuario);
                    usuario.salvar();
                    finish();
                }else{
                    String exececao = "";
                    try{
                        throw task.getException();
                    }catch(FirebaseAuthWeakPasswordException e){
                        exececao = "Digite uma senha mais forte!";
                    }catch(FirebaseAuthInvalidCredentialsException e){
                        exececao = "Digite um email válido";
                    }catch(FirebaseAuthUserCollisionException e){
                        exececao = "Esta conta já foi cadastrada";
                    }catch(Exception e){
                        exececao = "Erro ao cadastrar usuário: " + e.getMessage();
                        e.printStackTrace();
                    }
                    Toast.makeText(getApplicationContext(), exececao , Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}