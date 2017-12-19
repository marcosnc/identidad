package org.identidad.matriculas;

import org.identidad.matriculas.data.Store;
import org.identidad.matriculas.data.TextFileStore;
import org.identidad.matriculas.forms.FormConsultas;

import javax.swing.*;
import java.io.IOException;

public class Main {

    public static void main(String args[]) throws IOException {
        Configuration configuration = new Configuration();
        Store store = new TextFileStore(configuration.getFileStore());

        JFrame jFrame = new JFrame("Identidad - Gestión de Consulta de Matrículas");
        jFrame.setContentPane(new FormConsultas(configuration, store).getPanelMain());
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
    }

}
