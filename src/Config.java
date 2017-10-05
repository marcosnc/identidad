// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Config.java

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Properties;
import javax.swing.*;

public class Config extends JDialog
{

    public Config(int x, int y, int w, int h)
    {
        jContentPane = null;
        properties = new Properties();
        jTextField = null;
        jLabel = null;
        jTextField1 = null;
        jLabel1 = null;
        jTextField2 = null;
        jLabel2 = null;
        jButton2 = null;
        
        jLabel20 = null;
        jLabel21 = null;
        jTextField20 = null;
        jTextField21 = null;
        
        initialize(x, y, w, h);
    }

    private void initialize(int x, int y, int w, int h)
    {
        setSize(270, 212);
        setResizable(false);
        int my = 0;
        int mx = Integer.valueOf((x + w / 2) - getWidth() / 2).intValue();
        my = Integer.valueOf((y + h / 2) - getHeight() / 2).intValue();
        setLocation(mx, my);
        setContentPane(getJContentPane());
        setTitle("Configuracion");
        try
        {
            properties.load(new FileInputStream("identidad.properties"));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        jTextField.setText(properties.getProperty("dns"));
        jTextField1.setText(properties.getProperty("logo"));
        jTextField2.setText(properties.getProperty("host"));

        jTextField20.setText(properties.getProperty("proxyHost"));
        jTextField21.setText(properties.getProperty("proxyPort"));
    }

    private JPanel getJContentPane()
    {
        if(jContentPane == null)
        {
            jLabel2 = new JLabel();
            jLabel2.setBounds(new Rectangle(8, 119, 127, 13));
            jLabel2.setText("Host");
            jLabel1 = new JLabel();
            jLabel1.setBounds(new Rectangle(8, 80, 126, 14));
            jLabel1.setText("Imagen");
            jLabel = new JLabel();
            jLabel.setBounds(new Rectangle(8, 6, 130, 13));
            jLabel.setText("DNS");
            jContentPane = new JPanel();
            jContentPane.setLayout(null);
            jContentPane.add(getJTextField(), null);
            jContentPane.add(jLabel, null);
            jContentPane.add(getJTextField1(), null);
            jContentPane.add(jLabel1, null);
            jContentPane.add(getJTextField2(), null);
            jContentPane.add(jLabel2, null);
            jContentPane.add(getJButton2(), null);

            jLabel20 = new JLabel();
            jLabel20.setBounds(new Rectangle(8, 42, 130, 13));
            jLabel20.setText("Proxy Host");
            jContentPane.add(jLabel20, null);

            jLabel21 = new JLabel();
            jLabel21.setBounds(new Rectangle(8, 60, 130, 13));
            jLabel21.setText("Proxy Port");
            jContentPane.add(jLabel21, null);

            jContentPane.add(getJTextField20(), null);
            jContentPane.add(getJTextField21(), null);
            
        }
        return jContentPane;
    }

    private JTextField getJTextField()
    {
        if(jTextField == null)
        {
            jTextField = new JTextField();
            jTextField.setBounds(new Rectangle(8, 20, 228, 18));
            jTextField.setEditable(true);
        }
        return jTextField;
    }

    private JTextField getJTextField1()
    {
        if(jTextField1 == null)
        {
            jTextField1 = new JTextField();
            jTextField1.setBounds(new Rectangle(8, 94, 228, 18));
            jTextField1.setEditable(false);
        }
        return jTextField1;
    }

    private JTextField getJTextField2()
    {
        if(jTextField2 == null)
        {
            jTextField2 = new JTextField();
            jTextField2.setBounds(new Rectangle(8, 132, 228, 18));
            jTextField2.setEditable(true);
        }
        return jTextField2;
    }

    private JTextField getJTextField20()
    {
        if(jTextField20 == null)
        {
        	jTextField20 = new JTextField();
        	jTextField20.setBounds(new Rectangle(80, 40, 156, 18));
        	jTextField20.setEditable(true);
        }
        return jTextField20;
    }

    private JTextField getJTextField21()
    {
        if(jTextField21 == null)
        {
        	jTextField21 = new JTextField();
        	jTextField21.setBounds(new Rectangle(80, 58, 156, 18));
        	jTextField21.setEditable(true);
        }
        return jTextField21;
    }
    
    private JButton getJButton2()
    {
        if(jButton2 == null)
        {
            jButton2 = new JButton();
            jButton2.setBounds(new Rectangle(177, 155, 82, 22));
            jButton2.setText("Guardar");
            jButton2.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    properties.setProperty("dns", jTextField.getText());
                    properties.setProperty("logo", jTextField1.getText());
                    properties.setProperty("host", jTextField2.getText());

                    properties.setProperty("proxyHost", jTextField20.getText());
                    properties.setProperty("proxyPort", jTextField21.getText());
                    try
                    {
                        properties.store(new FileOutputStream("identidad.properties"), null);
                    }
                    catch(IOException ioexception) { }
                    Identidad.refresh();
                    dispose();
                }

                final Config this$0;

            
            {
                this$0 = Config.this;
            }
            }
);
        }
        return jButton2;
    }

    private static final long serialVersionUID = 1L;
    private JPanel jContentPane;
    private Properties properties;
    private JTextField jTextField;
    private JLabel jLabel;
    private JTextField jTextField1;
    private JLabel jLabel1;
    private JTextField jTextField2;
    private JLabel jLabel2;
    private JButton jButton2;


    private JLabel jLabel20;
    private JLabel jLabel21;
    private JTextField jTextField20;
    private JTextField jTextField21;


}
