package Server;

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
