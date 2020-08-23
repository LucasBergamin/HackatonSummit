package com.example.hacktonsummit.Model;

import com.example.hacktonsummit.Config.configuracaoFirabase;
import com.example.hacktonsummit.Helper.Base64Custom;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

public class Usuario {
    private String nome;
    private String aniversario;
    private String email;
    private String senha;
    private String confirmarSenha;
    private String idUsuario;
    public String coisasGostaComprar;
    public String idade;

    public Usuario() {

    }
    @Exclude
    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAniversario() {
        return aniversario;
    }

    public void setAniversario(String aniversario) {
        this.aniversario = aniversario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Exclude
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Exclude
    public String getConfirmarSenha() {
        return confirmarSenha;
    }

    public void setConfirmarSenha(String confirmarSenha) {
        this.confirmarSenha = confirmarSenha;
    }

    public void salvar(){
        DatabaseReference reference = configuracaoFirabase.getFirabaseDatasabe();
        reference.child("Usuarios")
                .child(this.idUsuario)
                .setValue(this);
    }

    public String getCoisasGostaComprar() {
        return coisasGostaComprar;
    }

    public void setCoisasGostaComprar(String coisasGostaComprar) {
        this.coisasGostaComprar = coisasGostaComprar;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public void salvarPersonalizacao(){
        FirebaseAuth auth = configuracaoFirabase.getFirebaseAutentificacao();
        String idUsuario = Base64Custom.codificarBase64(auth.getCurrentUser().getEmail());
        DatabaseReference reference = configuracaoFirabase.getFirabaseDatasabe();

        reference.child("Personalizacao")
                .child(idUsuario)
                .push()
                .setValue(this);

    }
}
