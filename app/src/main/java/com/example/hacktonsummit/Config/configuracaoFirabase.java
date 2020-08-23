package com.example.hacktonsummit.Config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class configuracaoFirabase {
    private static FirebaseAuth autentificacao;
    private static DatabaseReference firebase;

    public static FirebaseAuth getFirebaseAutentificacao(){
        if( autentificacao == null){
            autentificacao = FirebaseAuth.getInstance();
        }
        return autentificacao;
    }

    public static DatabaseReference getFirabaseDatasabe(){
        if( firebase == null){
            firebase = FirebaseDatabase.getInstance().getReference();
        }
        return firebase;
    }
}
