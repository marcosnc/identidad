package org.identidad.matriculas._to_delete;// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   dbGestor.java

import java.sql.*;
import java.util.Properties;

public class DbGestor {

    private Connection con;

    public DbGestor(String dns){
        con = null;
        try {
            Class.forName("org.postgresql.Driver").newInstance();
        } catch(Exception e) {
            System.err.println("Can not load postresql driver: " + e.getMessage());
            e.printStackTrace();
        }
        try {
            con = DriverManager.getConnection((new StringBuilder("jdbc:postgresql://")).append(dns).append(":5432/org.identidad.Identidad").toString(), "postgres", "postgres");
        } catch(SQLException e) {
            System.err.println("Can not connect to database: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean inserTo(String num, String fecha) {
        try {
            String Matri = num;
            Statement r1 = con.createStatement(1004, 1008);
            String strQ = (new StringBuilder("select \"ID_Matricula\" from \"Matriculas\" where \"Matricula\" = '")).append(Matri).append("'").toString();
            ResultSet resultado1 = r1.executeQuery(strQ);
            resultado1.next();
            if(resultado1.getRow() == 0)
            {
                strQ = (new StringBuilder("Insert INTO \"Matriculas\"(\"Matricula\") values('")).append(Matri).append("')").toString();
                r1.executeUpdate(strQ);
                con.commit();
            }
            strQ = (new StringBuilder("Select \"ID_Matricula\" FROM \"Matriculas\"  Where \"Matricula\" ='")).append(Matri).append("'").toString();
            resultado1 = r1.executeQuery(strQ);
            resultado1.next();
            int ID = resultado1.getInt(1);
            strQ = (new StringBuilder("Insert INTO \"MatriFech\"(\"ID_Matricula\",\"Fecha\") values(")).append(ID).append(",'").append(fecha).append("')").toString();
            r1.executeUpdate(strQ);
            con.commit();
            r1.close();
            con.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
