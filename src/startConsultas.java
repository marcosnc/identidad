// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   startConsultas.java

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Time;
import java.util.Properties;
import java.util.Vector;

public class startConsultas extends Thread
{

    startConsultas(int n, int nf, int d, String m, int a)
    {
        Socks = new Vector();
        ultEnt = 0;
        properties = new Properties();
        try
        {
            properties.load(new FileInputStream("identidad.properties"));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        dia = d;
        num = n;
        anio = a;
        mes = m;
        numf = nf;
    }

    public void run()
    {
        boolean a = true;
        ultEnt = num;
        cant = numf - num;
        int i = 0;
        for(int o = 0; a && o <= cant;)
        {
            int numero = num + o;
            Time tiempo = new Time(System.currentTimeMillis());
            String dia1 = String.valueOf(dia);
            if(dia1.length() == 1)
                dia1 = (new StringBuilder("0")).append(dia1).toString();
            Socks.add(new newSocket(properties.getProperty("host"), num + o, dia1, mes, anio,
            		properties.getProperty("proxyHost"), properties.getProperty("proxyPort")));
            ((newSocket)Socks.elementAt(i)).run();
            String result = ((newSocket)Socks.elementAt(i)).getResult();
            if(result == "")
                Identidad.jTextPane.setText((new StringBuilder(String.valueOf(Identidad.jTextPane.getText()))).append("[").append(tiempo.toString()).append("] Tiempo de respuesta agotado\n").toString());
            if(result.compareTo("No disponible") == 0)
            {
                Identidad.jTextPane.setText((new StringBuilder(String.valueOf(Identidad.jTextPane.getText()))).append("[").append(tiempo.toString()).append("] ").append(result).append("\n").toString());
                Identidad.jTextPane.setText((new StringBuilder(String.valueOf(Identidad.jTextPane.getText()))).append("[").append(tiempo.toString()).append("] Reintentando").append("\n").toString());
                o--;
            } else
            if(result != "")
                Identidad.jTextPane.setText((new StringBuilder(String.valueOf(Identidad.jTextPane.getText()))).append("[").append(tiempo.toString()).append("] ").append(result).append("\n").toString());
            o++;
            i++;
            ultEnt = numero;
        }

    }

    Vector Socks;
    int dia;
    int num;
    int anio;
    int numf;
    int cant;
    String mes;
    int ultEnt;
    private Properties properties;
}
