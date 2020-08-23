package com.example.hacktonsummit.Helper;

import java.text.SimpleDateFormat;

public class DateCustom {

    public static String anoAtual(){
        long data = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        String dataString = simpleDateFormat.format(data);
        return dataString;
    }

    public static String anoEscolhida(String data){
        String retornoData[] = data.split( "/");
        String dia = retornoData[0];
        String mes = retornoData[1];
        String ano = retornoData[2];

        String apenasAno = ano;
        return apenasAno;
    }

}
