package com.example.hacktonsummit.Model;

import com.example.hacktonsummit.Config.configuracaoFirabase;
import com.example.hacktonsummit.Helper.Base64Custom;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class Dados {
    public String coisasGostaComprar;
    public String idade;
    public String finalDeSemana;

    public Dados() {
    }

    public String getFinalDeSemana() {
        return finalDeSemana;
    }

    public void setFinalDeSemana(String finalDeSemana) {
        this.finalDeSemana = finalDeSemana;
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

    public void salvar(){
        FirebaseAuth auth = configuracaoFirabase.getFirebaseAutentificacao();
        String idUsuario = Base64Custom.codificarBase64(auth.getCurrentUser().getEmail());
        DatabaseReference reference = configuracaoFirabase.getFirabaseDatasabe();

        reference.child("Personalizacao")
                .child(idUsuario)
                .push()
                .setValue(this);

    }
}
