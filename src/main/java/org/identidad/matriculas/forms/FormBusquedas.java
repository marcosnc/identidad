package org.identidad.matriculas.forms;

import org.identidad.matriculas.data.Store;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FormBusquedas {
    private JLabel lblFilter;
    private JTextField txtFilter;
    private JButton btnFilter;
    private JTextArea textAreaResults;
    private JPanel panelMain;
    private JCheckBox chkRemoverRepetidos;

    private final Store store;

    public FormBusquedas(Store store) {
        this.store = store;

        btnFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> lines = store.query(txtFilter.getText());
                StringBuilder text = new StringBuilder();
                Set<String> added = new HashSet<>();
                for(String line : lines) {
                    if (!added.contains(line)) {
                        text.append(line).append(System.lineSeparator());
                    }
                    if (chkRemoverRepetidos.isSelected()) {
                        added.add(line);
                    }
                }
                textAreaResults.setText(text.toString());
            }
        });
    }


    public JPanel getPannelMain() {
        return panelMain;
    }
}
