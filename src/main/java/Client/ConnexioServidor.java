/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JTextField;

/**
 * 
 * Aquesta classe gestiona l'enviament de dades entre el client i el servidor
 * 
 * 
 */

public class ConnexioServidor implements ActionListener {
    
    private Socket socket; 
    private JTextField textFieldMissatge;
    private String usuari;
    private DataOutputStream sortidaDades;
    
    public ConnexioServidor(Socket socket, JTextField textFieldMissatge, String usuari) {
        this.socket = socket;
        this.textFieldMissatge = textFieldMissatge;
        this.usuari = usuari;
        try {
            this.sortidaDades = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            System.out.println("Error al crear el stream de sortida : " + ex.getMessage());
        } catch (NullPointerException ex) {
            System.out.println("El socket no s`ha creat correctament. ");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            sortidaDades.writeUTF(usuari + ": " + textFieldMissatge.getText() );
            textFieldMissatge.setText("");
        } catch (IOException ex) {
            System.out.println("Error a l'intentar enviar un missatge: " + ex.getMessage());
        }
    }
}
