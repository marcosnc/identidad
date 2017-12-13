package org.identidad.matriculas.forms;

import org.identidad.matriculas.Configuration;
import org.identidad.matriculas.LogTracker;
import org.identidad.matriculas.data.Store;
import org.identidad.matriculas.process.GestorDeConsultas;
import org.identidad.matriculas.utils.Rutinas;
import org.jdatepicker.JDateComponentFactory;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class FormConsultas implements LogTracker {
    private JPanel panelMain;
    private JTextField txtTramiteDesde;
    private JTextField txtTramiteHasta;
    private JTextField txtIncremento;
    private JButton btnAdd;
    private JDatePickerImpl datePickerFecha;
    private JButton btnStart;
    private JButton btnStop;
    private JTextArea txtAreaLog;
    private JButton btnSubstract;
    private JLabel lblTitle;
    private JLabel lblDesde;
    private JLabel lblHasta;
    private JLabel lblFecha;
    private JLabel lblProgress;
    private JPanel panelTop;
    private JPanel panelBottom;

    public FormConsultas(Configuration configuration, Store store) {

        btnAdd.addActionListener(e -> txtTramiteHasta.setText( String.valueOf(Rutinas.intValue(txtTramiteHasta.getText()) + Rutinas.intValue(txtIncremento.getText()))));

        btnSubstract.addActionListener(e -> {
            int newValue = Rutinas.intValue(txtTramiteHasta.getText()) - Rutinas.intValue(txtIncremento.getText());
            if (newValue >= Rutinas.intValue(txtTramiteDesde.getText())) {
                txtTramiteHasta.setText( String.valueOf(newValue));
            }
        });

        final FormConsultas formConsultas = this;
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int  tramiteInicial = parseTramite(txtTramiteDesde.getText());
                int  tramiteFinal   = parseTramite(txtTramiteHasta.getText());
                Date fecha          = ((Calendar) datePickerFecha.getModel().getValue()).getTime();

                GestorDeConsultas consultas = new GestorDeConsultas(tramiteInicial, tramiteFinal, fecha, formConsultas, configuration, store);
                consultas.start();
            }
        });
    }

    private void createUIComponents() {

        // Create DatePicker
        datePickerFecha = (JDatePickerImpl) new JDateComponentFactory().createJDatePicker();

    }

    public JPanel getPanelMain() {
        return panelMain;
    }

    @Override
    public void addToLog(String message) {
        synchronized (txtAreaLog) {
            txtAreaLog.setText(txtAreaLog.getText() + message + "\n");
        }
    }

    private int parseTramite(String text) {
        return Integer.parseInt(text.replaceAll(",", "").replaceAll("\\.", ""));
    }

}
