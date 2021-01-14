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
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.*;

/**
 * Classe principal del Client del Xat
 * 
 * */

public class FinestraPrincipal extends JFrame {
    
    private JTextArea missatgeXat;
    private Socket socket;
    
    private int port;
    private String host;
    private String usuari;
    
    public FinestraPrincipal(){
       super("Client Xat");
        // Elements de la finestra
        missatgeXat = new JTextArea();
        missatgeXat.setEnabled(false); // El area de missatges del xat no se debe de poder editar
        missatgeXat.setLineWrap(true); // Las lineas se parten al llegar al ancho del textArea
        missatgeXat.setWrapStyleWord(true); // Las lineas se parten entre palabras (por los espacios blancos)
        JScrollPane scrollMissatgesChat = new JScrollPane(missatgeXat);
        JTextField tfMensaje = new JTextField("");
        JButton btEnviar = new JButton("Enviar");
        
     
        //Col·locació dels components en la finestra
        Container c = this.getContentPane();
        c.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.insets = new Insets(20, 20, 20, 20);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        c.add(scrollMissatgesChat, gbc);
       
        // Restaura els valors per defecte
        gbc.gridwidth = 1;        
        gbc.weighty = 0;
        
        gbc.fill = GridBagConstraints.HORIZONTAL;        
        gbc.insets = new Insets(0, 20, 20, 20);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        c.add(tfMensaje, gbc);
        // Restaura els valors per defecte
        gbc.weightx = 0;
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        c.add(btEnviar, gbc);
        
        this.setBounds(400, 100, 400, 500);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     
        
        // Finestra de configuracion inicial
        FinestraConfiguracio vc = new FinestraConfiguracio(this);
        host = vc.getHost();
        port = vc.getPort();
        usuari = vc.getUsuari();
        
        System.out.println("Connectat " + host + " en el port " + port + " amb el nom d'usuari: " + usuari + ".");
        
        
        //Es crea el socket per connectar amb el Servidor del Xat. Fixeuvos que ja teniu una variable Socket anomenada socket creada
        try {    
            //IMPLEMENTA
            socket = new Socket(host, port);
        } catch (IOException e) {
            System.out.println("Error " + e.getMessage());
        }
        
        // Acció per el boton enviar
        btEnviar.addActionListener(new ConnexioServidor(socket, tfMensaje, usuari));
        
    }
    
    /**
     * Reb los missatges del xat reenviats pel servidor
     */

    public void rebreMissatgesServidor(){
        
        try {
            //Obté el fluxe d'entrada del socket
            DataInputStream dadesEntrada;
            String missatge;            
            dadesEntrada = new DataInputStream(socket.getInputStream());
            
            // Fem un Bucle infinit que reb els missatges del servidor
            boolean online = true;
            while (online) {
                missatge = dadesEntrada.readUTF();
                missatgeXat.append(missatge + System.lineSeparator());
                
            }
        } catch (IOException e) {
            System.out.println("Error " + e.getMessage());
        }
    }

}  

