/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ioc.dam.m9.uf3.eac2.b2Servidor;

import java.util.Observable;

/**
 * Objecte observable del patró observer.
 * 
 */

public class MissatgesXat extends Observable{

    private String missatge;
    
    public MissatgesXat(){
    }
    
    public String getMissatge(){
        return missatge;
    }
    
    public void setMissatge(String missatge){
        this.missatge = missatge;
        // Indica que el missatge ha canviat
        this.setChanged();
        // Notifica als observadors que el missatge ha canviat i es passa internament
        // (notifyObservers crida al mètode update del observador)
        this.notifyObservers(this.getMissatge());
    }
}