package com.example.hacktonsummit.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hacktonsummit.Config.configuracaoFirabase;
import com.example.hacktonsummit.Helper.Base64Custom;
import com.example.hacktonsummit.Helper.DateCustom;
import com.example.hacktonsummit.Model.Dados;
import com.example.hacktonsummit.Model.Usuario;
import com.example.hacktonsummit.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class MeuPerfilActivity extends AppCompatActivity {

    private TextView campoNome, campoAniversario;
    private DatabaseReference reference = configuracaoFirabase.getFirabaseDatasabe();
    private FirebaseAuth autentificacao = configuracaoFirabase.getFirebaseAutentificacao();
    private DatabaseReference usuarioRef;
    private ValueEventListener valueEventListenerUsuario;
    private ImageView imageQrCode;
    private Dados dados;


    private String nome, aniversario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meu_perfil);

        campoNome = findViewById(R.id.editNomeRecuperado);
        campoAniversario = findViewById(R.id.editAniversarioRecuperado);
        imageQrCode = findViewById(R.id.imageQrCode);

        recuperarDados();

        gerarQrCode();

    }

    public void recuperarDados(){
        String emailUsuario = autentificacao.getCurrentUser().getEmail();
        String idUsuario = Base64Custom.codificarBase64(emailUsuario);
        usuarioRef = reference.child("Usuarios").child(idUsuario);

        valueEventListenerUsuario = usuarioRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Usuario usuario = dataSnapshot.getValue(Usuario.class);

                nome = usuario.getNome();
                aniversario = usuario.getAniversario();

                campoNome.setText(nome);
                campoAniversario.setText(aniversario);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void gerarQrCode(){
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try{
            BitMatrix bitMatrix = multiFormatWriter.encode(nome + aniversario, BarcodeFormat.QR_CODE, 400, 400);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            imageQrCode.setImageBitmap(bitmap);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void adicionarCoisasComprar(View view){
        startActivity(new Intent(getApplicationContext(), AdicionarCoisasComprarActivity.class));
    }

    public void adicionarFinalSemana(View view){
        startActivity(new Intent(getApplicationContext(), AdicionarCoisasFinalSemanaActivity.class));
    }
}