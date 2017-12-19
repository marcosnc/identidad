package org.identidad.matriculas.utils;


import okhttp3.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static java.lang.String.valueOf;

public class Rutinas {

    public static String sacoCeros(String matricula) {
        String numero = "";
        int ini = 1;
        char matri[] = matricula.toCharArray();
        for(int i = 0; i < matricula.length(); i++)
            if(matri[i] == '-' || matri[i] == '/')
            {
                ini = 1;
                numero = (new StringBuilder(valueOf(numero))).append(matri[i]).toString();
            } else
            if(matri[i] != '0' || ini == 0)
            {
                ini = 0;
                numero = (new StringBuilder(valueOf(numero))).append(matri[i]).toString();
            }

        return numero;
    }

    public static String getPage(OkHttpClient client, int numeroDeEntrada, Date fecha) throws IOException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);

        RequestBody requestBody = new FormBody.Builder()
                .addEncoded("numentrada", valueOf(numeroDeEntrada))
                .addEncoded("dia"       , valueOf(calendar.get(Calendar.DAY_OF_MONTH)))
                .addEncoded("mes"       , valueOf(calendar.get(Calendar.MONTH)+1))
                .addEncoded("ano"       , valueOf(calendar.get(Calendar.YEAR)))
                .addEncoded("enviar"    , "Consultar")
                .build();

        Request request = new Request.Builder()
                .url("http://www.dnrpi.jus.gov.ar/seguitra/index.php")
                .post(requestBody)
                .header("Accept", "*/*")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36")
                .build();


        Response response = client.newCall(request).execute();

        if (!response.isSuccessful()) {
            throw new IOException("Unexpected error: " + response);
        }
        String responseBody = response.body().string();
        response.close();
        return responseBody;
    }

    public static int intValue(String text) {
        try {
            return Integer.parseInt(text);
        } catch (Exception e) {
            return 0;
        }
    }

    private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("dd-MM-YYYY");
    public static String formatDate(Date fecha) {
        return DATE_FORMATTER.format(fecha);
    }
}
