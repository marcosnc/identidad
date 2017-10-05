// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Identidad.java

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;

public class Identidad extends JFrame
{

    public Identidad()
    {
        jContentPane = null;
        jButton = null;
        dia = null;
        mes = null;
        anio = null;
        jScrollPane = null;
        jLabel = null;
        jLabel1 = null;
        jTextField = null;
        consultas = null;
        jJMenuBar = null;
        jMenu = null;
        Configuracion = null;
        jMenuItem = null;
        jMenuItem1 = null;
        jMenu1 = null;
        jMenuItem2 = null;
        jTextField1 = null;
        jLabel3 = null;
        initialize();
    }

    private void initialize()
    {
        setResizable(false);
        setContentPane(getJContentPane());
        setTitle("Identidad - Gestor de Consultas");
        setSize(512, 328);
        setLocation(300, 225);
        setJMenuBar(getJJMenuBar());
        setDefaultCloseOperation(2);
        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }

            final Identidad this$0;

            
            {
                this$0 = Identidad.this;
            }
        }
);
        iniCombos();
        Properties properties = new Properties();
        try
        {
            properties.load(new FileInputStream("identidad.properties"));
        }
        catch(IOException e)
        {
            Config conf = new Config(getX(), getY(), getWidth(), getHeight());
            conf.setVisible(true);
            jLabel2.setIcon(new ImageIcon(properties.getProperty("logo")));
            jTextField.setText(properties.getProperty("tramite"));
        }
        jLabel2.setIcon(new ImageIcon(properties.getProperty("logo")));
        jTextField.setText(properties.getProperty("tramite"));
    }

    private JPanel getJContentPane()
    {
        if(jContentPane == null)
        {
            jLabel3 = new JLabel();
            jLabel3.setBounds(new Rectangle(105, 17, 90, 22));
            jLabel3.setText("Tramite final");
            jLabel2 = new JLabel();
            jLabel2.setPreferredSize(new Dimension(200, 120));
            jLabel2.setVerticalAlignment(0);
            jLabel2.setHorizontalAlignment(0);
            jLabel2.setBounds(new Rectangle(280, 10, 220, 130));
            jLabel2.setText("");
            jLabel1 = new JLabel();
            jLabel1.setBounds(new Rectangle(8, 17, 90, 22));
            jLabel1.setText("Tramite inicial");
            jLabel = new JLabel();
            jLabel.setBounds(new Rectangle(7, 96, 160, 20));
            jLabel.setText("Fecha");
            jContentPane = new JPanel();
            jContentPane.setLayout(null);
            jContentPane.add(getJScrollPane(), null);
            jContentPane.add(getJButton(), null);
            jContentPane.add(getDia(), null);
            jContentPane.add(getJComboBox(), null);
            jContentPane.add(getAnio(), null);
            jContentPane.add(jLabel, null);
            jContentPane.add(jLabel1, null);
            jContentPane.add(getJTextField(), null);
            jContentPane.add(jLabel2, null);
            jContentPane.add(getJTextField1(), null);
            jContentPane.add(jLabel3, null);
        }
        return jContentPane;
    }

    private JButton getJButton()
    {
        if(jButton == null)
        {
            jButton = new JButton();
            jButton.setBounds(new Rectangle(410, 244, 80, 23));
            jButton.setText("Comenzar");
            jButton.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    Pattern p = Pattern.compile("[0-9]{1,9}");
                    Matcher m = p.matcher(jTextField.getText());
                    if(!m.find())
                        return;
                    m = p.matcher(jTextField1.getText());
                    if(!m.find())
                        return;
                    if(jButton.getText() == "Comenzar")
                    {
                        jButton.setText("Terminar");
                        jScrollPane.setVisible(true);
                        Identidad.jTextPane.setVisible(true);
                        Identidad.jTextPane.setText("");
                        comenzar();
                    } else
                    {
                        jButton.setText("Comenzar");
                        jScrollPane.setVisible(false);
                        Identidad.jTextPane.setVisible(false);
                        terminar();
                    }
                }

                final Identidad this$0;

            
            {
                this$0 = Identidad.this;
            }
            }
);
        }
        return jButton;
    }

    private JTextPane getJTextPane()
    {
        if(jTextPane == null)
        {
            jTextPane = new JTextPane();
            jTextPane.setName("resultado");
            jTextPane.setToolTipText("Resultados de las consultas");
            jTextPane.setEditable(false);
            jTextPane.setVisible(false);
        }
        return jTextPane;
    }

    private JComboBox getDia()
    {
        if(dia == null)
        {
            dia = new JComboBox();
            dia.setBounds(new Rectangle(8, 120, 50, 24));
            dia.setEditable(false);
        }
        return dia;
    }

    private JComboBox getJComboBox()
    {
        if(mes == null)
        {
            mes = new JComboBox();
            mes.setLocation(new Point(62, 120));
            mes.setSize(new Dimension(98, 24));
        }
        return mes;
    }

    private JComboBox getAnio()
    {
        if(anio == null)
        {
            anio = new JComboBox();
            anio.setBounds(new Rectangle(166, 120, 60, 24));
        }
        return anio;
    }

    private JScrollPane getJScrollPane()
    {
        if(jScrollPane == null)
        {
            jScrollPane = new JScrollPane();
            jScrollPane.setBounds(new Rectangle(5, 5, 497, 232));
            jScrollPane.setVerticalScrollBarPolicy(20);
            jScrollPane.setHorizontalScrollBarPolicy(30);
            jScrollPane.setViewportView(getJTextPane());
            jScrollPane.setVisible(false);
        }
        return jScrollPane;
    }

    private JTextField getJTextField()
    {
        if(jTextField == null)
        {
            jTextField = new JTextField();
            jTextField.setBounds(new Rectangle(8, 41, 85, 21));
            jTextField.setBackground(Color.white);
            jTextField.addKeyListener(new KeyAdapter() {

                public void keyReleased(KeyEvent e)
                {
                    Pattern p = Pattern.compile("[0-9]{1,9}");
                    Matcher m = p.matcher(jTextField.getText());
                    if(m.find())
                        jTextField.setText(m.group());
                    else
                        jTextField.setText("");
                }

                final Identidad this$0;

            
            {
                this$0 = Identidad.this;
            }
            }
);
        }
        return jTextField;
    }

    private void terminar()
    {
        try
        {
            properties.load(new FileInputStream("identidad.properties"));
            properties.setProperty("tramite", String.valueOf(consultas.ultEnt));
            properties.setProperty("dns", properties.getProperty("dns"));
            properties.setProperty("host", properties.getProperty("host"));
            properties.setProperty("logo", properties.getProperty("logo"));
            try
            {
                properties.store(new FileOutputStream("identidad.properties"), null);
            }
            catch(IOException ioexception) { }
            jTextField.setText(properties.getProperty("tramite"));
        }
        catch(IOException e)
        {
            Config conf = new Config(getX(), getY(), getWidth(), getHeight());
            conf.setModal(true);
            conf.setVisible(true);
        }
    }

    private void comenzar()
    {
        int num = Integer.valueOf(jTextField.getText()).intValue();
        int numf = Integer.valueOf(jTextField1.getText()).intValue();
        int d = Integer.valueOf(dia.getSelectedItem().toString()).intValue();
        int a = Integer.valueOf(anio.getSelectedItem().toString()).intValue();
        String m = mes.getSelectedItem().toString();
        consultas = new startConsultas(num, numf, d, m, a);
        consultas.start();
    }

    private void iniCombos()
    {
        Date hola = new Date(System.currentTimeMillis());
        String fecha[] = hola.toString().split("-");
        for(int i = 1; i < 32; i++)
        {
            String a;
            if(i < 10)
                a = (new StringBuilder("0")).append(i).toString();
            else
                a = String.valueOf(i);
            dia.addItem(new String(a));
        }

        for(int i = 1; i < 151; i++)
            anio.addItem(new Integer(1900 + i));

        for(int i = 1; i < 13; i++)
            mes.addItem(new String(rutinas.getNameByNum(i)));

        dia.setSelectedItem(new String(fecha[2]));
        anio.setSelectedItem(new Integer(fecha[0]));
        mes.setSelectedItem(new String(rutinas.getNameByNum(Integer.valueOf(fecha[1]).intValue())));
    }

    private JMenuBar getJJMenuBar()
    {
        if(jJMenuBar == null)
        {
            jJMenuBar = new JMenuBar();
            jJMenuBar.add(getJMenu());
            jJMenuBar.add(getConfiguracion());
            jJMenuBar.add(getJMenu1());
        }
        return jJMenuBar;
    }

    private JMenu getJMenu()
    {
        if(jMenu == null)
        {
            jMenu = new JMenu();
            jMenu.setText("File");
            jMenu.add(getJMenuItem());
        }
        return jMenu;
    }

    private JMenu getConfiguracion()
    {
        if(Configuracion == null)
        {
            Configuracion = new JMenu();
            Configuracion.setText("Tools");
            Configuracion.add(getJMenuItem1());
        }
        return Configuracion;
    }

    private JMenuItem getJMenuItem()
    {
        if(jMenuItem == null)
        {
            jMenuItem = new JMenuItem();
            jMenuItem.setText("Exit");
            jMenuItem.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    System.exit(0);
                }

                final Identidad this$0;

            
            {
                this$0 = Identidad.this;
            }
            }
);
        }
        return jMenuItem;
    }

    private JMenuItem getJMenuItem1()
    {
        if(jMenuItem1 == null)
        {
            jMenuItem1 = new JMenuItem();
            jMenuItem1.setText("Config");
            jMenuItem1.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    Config conf = new Config(getX(), getY(), getWidth(), getHeight());
                    conf.setModal(true);
                    conf.setVisible(true);
                }

                final Identidad this$0;

            
            {
                this$0 = Identidad.this;
            }
            }
);
        }
        return jMenuItem1;
    }

    private JMenu getJMenu1()
    {
        if(jMenu1 == null)
        {
            jMenu1 = new JMenu();
            jMenu1.setText("help");
            jMenu1.add(getJMenuItem2());
        }
        return jMenu1;
    }

    private JMenuItem getJMenuItem2()
    {
        if(jMenuItem2 == null)
        {
            jMenuItem2 = new JMenuItem();
            jMenuItem2.setText("About");
            jMenuItem2.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    About d = new About(getX(), getY(), getWidth(), getHeight());
                    d.setModal(true);
                    d.setVisible(true);
                }

                final Identidad this$0;

            
            {
                this$0 = Identidad.this;
            }
            }
);
        }
        return jMenuItem2;
    }

    private JTextField getJTextField1()
    {
        if(jTextField1 == null)
        {
            jTextField1 = new JTextField();
            jTextField1.setBounds(new Rectangle(105, 41, 85, 21));
            jTextField1.addKeyListener(new KeyAdapter() {

                public void keyReleased(KeyEvent e)
                {
                    Pattern p = Pattern.compile("[0-9]{1,9}");
                    Matcher m = p.matcher(jTextField1.getText());
                    if(m.find())
                        jTextField1.setText(m.group());
                    else
                        jTextField1.setText("");
                }

                final Identidad this$0;

            
            {
                this$0 = Identidad.this;
            }
            }
);
        }
        return jTextField1;
    }

    public static void main(String args[])
    {
        Identidad a = new Identidad();
        a.setVisible(true);
    }

    static void refresh()
    {
        try
        {
            properties.load(new FileInputStream("identidad.properties"));
        }
        catch(IOException ioexception) { }
        jLabel2.setIcon(new ImageIcon(properties.getProperty("logo")));
        jLabel2.repaint();
    }

    private static final long serialVersionUID = 1L;
    private JPanel jContentPane;
    private JButton jButton;
    static JTextPane jTextPane = null;
    private JComboBox dia;
    private JComboBox mes;
    private JComboBox anio;
    private JScrollPane jScrollPane;
    private JLabel jLabel;
    private JLabel jLabel1;
    private JTextField jTextField;
    private startConsultas consultas;
    static JLabel jLabel2 = null;
    static Properties properties = new Properties();
    private JMenuBar jJMenuBar;
    private JMenu jMenu;
    private JMenu Configuracion;
    private JMenuItem jMenuItem;
    private JMenuItem jMenuItem1;
    private JMenu jMenu1;
    private JMenuItem jMenuItem2;
    private JTextField jTextField1;
    private JLabel jLabel3;







}
