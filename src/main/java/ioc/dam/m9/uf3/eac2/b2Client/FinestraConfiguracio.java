/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package ioc.dam.m9.uf3.eac2.b2Client;


import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * 
 * Configuració de la finestra pel xat
 * 
 */

public class FinestraConfiguracio extends JDialog{
    
    private JTextField tfUsuari;
    private JTextField tfHost;
    private JTextField tfPort;
    
    /**
     * Constructor de la finestra de configuracion inicial
     * 
     * @param pare Finestra pare
     */

    public FinestraConfiguracio(JFrame pare) {
        super(pare, "Configuració inicial", true);
        
        JLabel lbUsuari = new JLabel("Usuari:");
        JLabel lbHost = new JLabel("Host:");
        JLabel lbPort = new JLabel("Port:");
        
        tfUsuari = new JTextField();
        tfHost = new JTextField("localhost");
        tfPort = new JTextField("7777");
        
        JButton btAcceptar = new JButton("Acceptar");
        btAcceptar.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        
        Container c = this.getContentPane();
        c.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.insets = new Insets(20, 20, 0, 20);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        c.add(lbUsuari, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        c.add(lbHost, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        c.add(lbPort, gbc);
        
        gbc.ipadx = 100;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        c.add(tfUsuari, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        c.add(tfHost, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        c.add(tfPort, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 20, 20, 20);
        c.add(btAcceptar, gbc);
        
        this.pack(); // Li dona a la  finestra el màxim tamany possible
        this.setLocation(450, 200); // Posició de la finestra
        this.setResizable(false); // Evita que es pugui modificar la finestra
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE); // Deshabilita el botó de tancament de la finestra 
        this.setVisible(true);
    }
    
    
    public String getUsuari(){
        return this.tfUsuari.getText();
    }
    
    public String getHost(){
        return this.tfHost.getText();
    }
    
    public int getPort(){
        return Integer.parseInt(this.tfPort.getText());
    }

}
