package org.identidad.matriculas._to_delete;

import okhttp3.*;
import org.identidad.matriculas.utils.Rutinas;

import java.io.BufferedReader;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {



        OkHttpClient client = new OkHttpClient();

        int numero = 814281;
        String dia = "2";
        String mes = "Noviembre";
        int anio = 2017;

        RequestBody requestBody = new FormBody.Builder()
                .addEncoded("numentrada",  String.valueOf(numero))
                .addEncoded("dia",  dia)
                .addEncoded("mes",  "11")
                .addEncoded("anio",  String.valueOf(anio))
                .addEncoded("enviar",  "Consultar")
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
            throw new IOException("Unexpected code " + response);
        }

        BufferedReader reader = new BufferedReader(response.body().charStream());
        reader.lines()
                .filter(line -> line.contains(String.valueOf(numero)))
                .forEach(System.out::println);

//        String responseBody = response.body().string();
//        System.out.println(responseBody);


//        OkHttpClient client = new OkHttpClient();
//
////        String proxyHost = "webproxy.wlb2.nam.nsroot.nt";
////        int proxyPort = 8080;
////        Proxy proxy = new Proxy(Proxy.Type.HTTP, InetSocketAddress.createUnresolved(proxyHost, proxyPort));
////        OkHttpClient client = new OkHttpClient.Builder().proxy(proxy).build();
//
//
//        RequestBody formBody = new FormBody.Builder()
//                .add("numentrada", String.valueOf(numero))
//                .add("dia", dia)
//                .add("mes", Rutinas.getNumByMes(mes))
//                .add("anio", String.valueOf(anio))
//                .add("enviar", "Consultar").build();
//        Request request = new Request.Builder().url("http://www.dnrpi.jus.gov.ar/seguitra/index.php").post(formBody).build();
//        Response response = client.newCall(request).execute();


    }
}
