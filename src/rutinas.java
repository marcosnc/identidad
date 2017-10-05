// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   rutinas.java


public class rutinas
{

    public rutinas()
    {
    }

    public static String getNameByNum(int i)
    {
        String mesName = "";
        switch(i)
        {
        case 1: // '\001'
            mesName = "Enero";
            break;

        case 2: // '\002'
            mesName = "Febrero";
            break;

        case 3: // '\003'
            mesName = "Marzo";
            break;

        case 4: // '\004'
            mesName = "Abril";
            break;

        case 5: // '\005'
            mesName = "Mayo";
            break;

        case 6: // '\006'
            mesName = "Junio";
            break;

        case 7: // '\007'
            mesName = "Julio";
            break;

        case 8: // '\b'
            mesName = "Agosto";
            break;

        case 9: // '\t'
            mesName = "Septiembre";
            break;

        case 10: // '\n'
            mesName = "Octubre";
            break;

        case 11: // '\013'
            mesName = "Noviembre";
            break;

        case 12: // '\f'
            mesName = "Diciembre";
            break;
        }
        return mesName;
    }

    public static String getNumByMes(String mes2)
    {
        System.out.print(mes2);
        if(mes2.compareTo("Enero") == 0)
            return "01";
        if(mes2.compareTo("Febrero") == 0)
            return "02";
        if(mes2.compareTo("Marzo") == 0)
            return "03";
        if(mes2.compareTo("Abril") == 0)
            return "04";
        if(mes2.compareTo("Mayo") == 0)
            return "05";
        if(mes2.compareTo("Junio") == 0)
            return "06";
        if(mes2.compareTo("Julio") == 0)
            return "07";
        if(mes2.compareTo("Agosto") == 0)
            return "08";
        if(mes2.compareTo("Septiembre") == 0)
            return "09";
        if(mes2.compareTo("Octubre") == 0)
            return "10";
        if(mes2.compareTo("Noviembre") == 0)
            return "11";
        if(mes2.compareTo("Diciembre") == 0)
            return "12";
        else
            return "";
    }

    public static String sacoCeros(String matricula)
    {
        String numero = "";
        int ini = 1;
        char matri[] = matricula.toCharArray();
        for(int i = 0; i < matricula.length(); i++)
            if(matri[i] == '-' || matri[i] == '/')
            {
                ini = 1;
                numero = (new StringBuilder(String.valueOf(numero))).append(matri[i]).toString();
            } else
            if(matri[i] != '0' || ini == 0)
            {
                ini = 0;
                numero = (new StringBuilder(String.valueOf(numero))).append(matri[i]).toString();
            }

        return numero;
    }
}
