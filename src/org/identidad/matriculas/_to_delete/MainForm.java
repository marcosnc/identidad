package org.identidad.matriculas._to_delete;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   org.identidad.Identidad.java

import org.identidad.matriculas.forms.About;
import org.identidad.matriculas.forms.Config;
import org.identidad.matriculas.process.GestorDeConsultas;
import org.identidad.matriculas.Main;
import org.identidad.matriculas.utils.Rutinas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.text.NumberFormat;

public class MainForm extends JFrame {

    private static final long serialVersionUID = 1L;
    private final Main matriculas;
    private JPanel jContentPane;
    private JButton buttonStartEnd;
    static JTextPane txtResultados = null;
    private JComboBox comboDia;
    private JComboBox comboMes;
    private JComboBox comboAnio;
    private JScrollPane scrollResultados;
    private JTextField txtTramiteInicial;
    private GestorDeConsultas consultas;
    private JMenuBar jJMenuBar;
    private JMenu jMenu;
    private JMenu Configuracion;
    private JMenuItem jMenuItem;
    private JMenuItem jMenuItem1;
    private JMenu jMenu1;
    private JMenuItem jMenuItem2;
    private JTextField txtTramiteFinal;

    private JLabel lblFecha;
    private JLabel lblTramiteInicial;
    private JLabel lblTramiteFinal;

    public MainForm(Main matriculas) {
        this.matriculas = matriculas;
        initialize();
    }

    private void initialize() {
        setResizable(false);
        setContentPane(getJContentPane());
        setTitle("Identidad - Consultas de Matrículas");
        setSize(512, 328);
        setLocation(300, 225);
        setJMenuBar(getJJMenuBar());
        setDefaultCloseOperation(2);
        addWindowListener(new WindowAdapter() {
                              public void windowClosing(WindowEvent e) {
                                  System.exit(0);
                              }
                          }
        );
        iniCombos();

//        if (matriculas.getProperties().isEmpty()) {
//            Config conf = new Config(getX(), getY(), getWidth(), getHeight(), matriculas);
//            conf.setVisible(true);
//        } else {
//            txtTramiteInicial.setText(matriculas.getProperties().getProperty("tramite"));
//        }
    }

    private JPanel getJContentPane() {
        if(jContentPane == null) {
            jContentPane = new JPanel();
            jContentPane.setLayout(null);

            lblFecha = new JLabel();
            lblFecha.setBounds(new Rectangle(7, 96, 160, 20));
            lblFecha.setText("Fecha");
            jContentPane.add(lblFecha, null);

            lblTramiteInicial = new JLabel();
            lblTramiteInicial.setBounds(new Rectangle(8, 17, 90, 22));
            lblTramiteInicial.setText("Tramite inicial");
            jContentPane.add(lblTramiteInicial, null);

            txtTramiteInicial = new JFormattedTextField(NumberFormat.getNumberInstance());
            txtTramiteInicial.setBounds(new Rectangle(8, 41, 85, 21));
            txtTramiteInicial.setBackground(Color.white);
            jContentPane.add(txtTramiteInicial, null);

            lblTramiteFinal = new JLabel();
            lblTramiteFinal.setBounds(new Rectangle(105, 17, 90, 22));
            lblTramiteFinal.setText("Tramite final");
            jContentPane.add(lblTramiteFinal, null);

            txtTramiteFinal = new JFormattedTextField(NumberFormat.getNumberInstance());
            txtTramiteFinal.setBounds(new Rectangle(105, 41, 85, 21));
            jContentPane.add(txtTramiteFinal, null);

            comboDia = new JComboBox();
            comboDia.setBounds(new Rectangle(8, 120, 70, 24));
            comboDia.setEditable(false);
            jContentPane.add(comboDia, null);

            comboMes = new JComboBox();
            comboMes.setLocation(new Point(72, 120));
            comboMes.setSize(new Dimension(120, 24));
            jContentPane.add(comboMes, null);

            comboAnio = new JComboBox();
            comboAnio.setBounds(new Rectangle(186, 120, 90, 24));
            jContentPane.add(comboAnio, null);

            txtResultados = new JTextPane();
            txtResultados.setName("Resultados");
            txtResultados.setToolTipText("Resultados de las consultas");
            txtResultados.setEditable(false);
            txtResultados.setVisible(false);

            scrollResultados = new JScrollPane();
            scrollResultados.setBounds(new Rectangle(5, 5, 497, 232));
            scrollResultados.setVerticalScrollBarPolicy(20);
            scrollResultados.setHorizontalScrollBarPolicy(30);
            scrollResultados.setViewportView(txtResultados);
            scrollResultados.setVisible(false);
            jContentPane.add(scrollResultados, null);

            buttonStartEnd = new JButton();
            buttonStartEnd.setBounds(new Rectangle(350, 244, 120, 23));
            buttonStartEnd.setText("Comenzar");
            buttonStartEnd.addActionListener(e -> {
                if(buttonStartEnd.getText() == "Comenzar") {
                    buttonStartEnd.setText("Terminar");
                    scrollResultados.setVisible(true);
                    MainForm.txtResultados.setVisible(true);
                    MainForm.txtResultados.setText("");
                    comenzar();
                } else {
                    buttonStartEnd.setText("Comenzar");
                    scrollResultados.setVisible(false);
                    MainForm.txtResultados.setVisible(false);
                    terminar();
                }
            });
            jContentPane.add(buttonStartEnd, null);
        }
        return jContentPane;
    }


    private void terminar() {
//        matriculas.saveProperties();
    }

    private int parseTramite(String text) {
        return Integer.parseInt(text.replaceAll(",", "").replaceAll("\\.", ""));
    }

    private void comenzar() {
        int tramiteInicial = parseTramite(txtTramiteInicial.getText());
        int tramiteFinal   = parseTramite(txtTramiteFinal.getText());
        int dia            = Integer.valueOf(this.comboDia.getSelectedItem().toString()).intValue();
        int anio           = Integer.valueOf(this.comboAnio.getSelectedItem().toString()).intValue();
        String mes         = this.comboMes.getSelectedItem().toString();

//        consultas = new GestorDeConsultas(tramiteInicial, tramiteFinal, dia, mes, anio, matriculas);
//        consultas.start();
    }

    private void iniCombos() {
        Date hola = new Date(System.currentTimeMillis());
        String fecha[] = hola.toString().split("-");
        for(int i = 1; i < 32; i++) {
            String a;
            if(i < 10)
                a = (new StringBuilder("0")).append(i).toString();
            else
                a = String.valueOf(i);
            comboDia.addItem(new String(a));
        }

        for(int i = 1; i < 151; i++) {
            comboAnio.addItem(new Integer(1900 + i));
        }

        for(int i = 1; i < 13; i++) {
//            comboMes.addItem(new String(Rutinas.getNameByNum(i)));
        }

        comboDia.setSelectedItem(new String(fecha[2]));
        comboAnio.setSelectedItem(new Integer(fecha[0]));
//        comboMes.setSelectedItem(new String(Rutinas.getNameByNum(Integer.valueOf(fecha[1]).intValue())));
    }

    private JMenuBar getJJMenuBar() {
        if(jJMenuBar == null) {
            jJMenuBar = new JMenuBar();
            jJMenuBar.add(getJMenu());
            jJMenuBar.add(getConfiguracion());
            jJMenuBar.add(getJMenu1());
        }
        return jJMenuBar;
    }

    private JMenu getJMenu() {
        if(jMenu == null) {
            jMenu = new JMenu();
            jMenu.setText("File");
            jMenu.add(getJMenuItem());
        }
        return jMenu;
    }

    private JMenu getConfiguracion() {
        if(Configuracion == null) {
            Configuracion = new JMenu();
            Configuracion.setText("Tools");
            Configuracion.add(getJMenuItem1());
        }
        return Configuracion;
    }

    private JMenuItem getJMenuItem() {
        if(jMenuItem == null) {
            jMenuItem = new JMenuItem();
            jMenuItem.setText("Exit");
            jMenuItem.addActionListener(new ActionListener() {
                                            public void actionPerformed(ActionEvent e) {
                                                System.exit(0);
                                            }
                                        }
            );
        }
        return jMenuItem;
    }

    private JMenuItem getJMenuItem1() {
        if(jMenuItem1 == null) {
            jMenuItem1 = new JMenuItem();
            jMenuItem1.setText("Config");
            jMenuItem1.addActionListener(new ActionListener() {
                                             public void actionPerformed(ActionEvent e) {
                                                 Config conf = new Config(getX(), getY(), getWidth(), getHeight(), matriculas);
                                                 conf.setModal(true);
                                                 conf.setVisible(true);
                                             }
                                         }
            );
        }
        return jMenuItem1;
    }

    private JMenu getJMenu1() {
        if(jMenu1 == null) {
            jMenu1 = new JMenu();
            jMenu1.setText("help");
            jMenu1.add(getJMenuItem2());
        }
        return jMenu1;
    }

    private JMenuItem getJMenuItem2() {
        if(jMenuItem2 == null) {
            jMenuItem2 = new JMenuItem();
            jMenuItem2.setText("About");
            jMenuItem2.addActionListener(new ActionListener() {
                                             public void actionPerformed(ActionEvent e) {
                                                 About d = new About(getX(), getY(), getWidth(), getHeight());
                                                 d.setModal(true);
                                                 d.setVisible(true);
                                             }
                                         }
            );
        }
        return jMenuItem2;
    }

}
