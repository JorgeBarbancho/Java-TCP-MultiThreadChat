/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package ioc.dam.m9.uf3.eac2.b2Servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;



public class ServidorXat {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
          
        int port = 7777;
        int maxConnexions = 10; // Màxim de connexions a la vegada
        ServerSocket servidor; 
        Socket socket = null;
        MissatgesXat missatges = new MissatgesXat();
        boolean end = false;
       
        // Es crea el serverSocket            
        try {
            
            servidor = new ServerSocket(port, maxConnexions);
            System.out.println("Servidor a l'espera de connexions");
        
	    // Bucle infinit per esperar connexions
	    while (!end) {
	       	socket = servidor.accept();	       
                ConnexioClient cc = new ConnexioClient(socket, missatges);
                cc.start();
                System.out.println("Connectat client amb la IP " + socket.getInetAddress().getHostName());
	    }	            
	    //tanquem els streams            
            if (socket!=null)socket.close();
            servidor.close();
            
            
        } catch (IOException e) {			
            System.out.println("Error " + e.getMessage());
        }    
    }
}