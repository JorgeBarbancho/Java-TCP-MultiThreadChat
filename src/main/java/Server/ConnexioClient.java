/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

/**
 * 
 * Aquesta classe gestiona l'enviament de dades entre el servidor i el client que s'està atenenen
 * 
 */


public class ConnexioClient extends Thread implements Observer{
    
    private Socket socket; 
    private MissatgesXat missatges;
    private DataInputStream dadesEntrada;
    private DataOutputStream sortidaDades;
    
    public ConnexioClient (Socket socket, MissatgesXat missatges){
        this.socket = socket;
        this.missatges = missatges;
        //creem els fluxes d'entrada i sortida, podeu utilitzar les variables dadesEntrada i sortidaDades
        try {            
            dadesEntrada = new DataInputStream(socket.getInputStream());
            sortidaDades = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }        
    }
    
    @Override
    public void run(){
        String missatgeRebut;
        boolean online = true;
        // S'apunta a la lista d'observadors dels missatges
        missatges.addObserver(this);
        
        while (online) {
            try {
                // Llegeix un missatge enviat per un client
                missatgeRebut = dadesEntrada.readUTF();
                // Fica el missatge rebut en missatges perquè es notifiqui 
                // als seus observadors que hi ha un nou missatge.
                missatges.setMissatge(missatgeRebut);
            } catch (IOException ex) {
                System.out.println("Client amb la IP " + socket.getInetAddress().getHostName() + " desconnectat.");
                online = false; 
                // Si s'ha produït un error al rebre dades del client es tanca la connexió amb ell.
                try {
                    dadesEntrada.close();
                    sortidaDades.close();
                } catch (IOException ex2) {
                    System.out.println("Error al tancar els stream d'entrada i salida :" + ex2.getMessage());
                }
            }
        }   
    }
    
    @Override
    public void update(Observable o, Object arg) {
        try {
            // Envia el missatge al client
            sortidaDades.writeUTF(arg.toString());
        } catch (IOException ex) {
            System.out.println("Error al enviar missatge al client (" + ex.getMessage() + ").");
        }
    }
} 
