// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   About.java

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class About extends JDialog
{

    public About(int x, int y, int w, int h)
    {
        jContentPane = null;
        jTextPane = null;
        jButton = null;
        initialize(x, y, w, h);
    }

    private void initialize(int x, int y, int w, int h)
    {
        setSize(241, 144);
        setResizable(false);
        setContentPane(getJContentPane());
        setTitle("About");
        int my = 0;
        int mx = Integer.valueOf((x + w / 2) - getWidth() / 2).intValue();
        my = Integer.valueOf((y + h / 2) - getHeight() / 2).intValue();
        setLocation(mx, my);
    }

    private JPanel getJContentPane()
    {
        if(jContentPane == null)
        {
            jContentPane = new JPanel();
            jContentPane.setLayout(null);
            jContentPane.add(getJTextPane(), null);
            jContentPane.add(getJButton(), null);
        }
        return jContentPane;
    }

    private JTextPane getJTextPane()
    {
        if(jTextPane == null)
        {
            jTextPane = new JTextPane();
            jTextPane.setBounds(new Rectangle(6, 4, 225, 76));
            jTextPane.setBackground(new Color(237, 237, 237));
            jTextPane.setEditable(false);
            jTextPane.setEnabled(true);
            jTextPane.setText("Identidad Gestor de consultas, es un programa dise\361ado para generar consultas de una forma automatica y eficaz.-");
        }
        return jTextPane;
    }

    private JButton getJButton()
    {
        if(jButton == null)
        {
            jButton = new JButton();
            jButton.setBounds(new Rectangle(77, 85, 78, 22));
            jButton.setText("Cerrar");
            jButton.addActionListener(new ActionListener() {

                final About this$0;
                {
                    this$0 = About.this;
                }
                public void actionPerformed(ActionEvent e)
                {
                    dispose();
                }

            }
);
        }
        return jButton;
    }

    private static final long serialVersionUID = 0x5ef824affd3ded2L;
    private JPanel jContentPane;
    private JTextPane jTextPane;
    private JButton jButton;
}
