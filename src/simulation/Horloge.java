package simulation;

import java.util.Observable;
import java.util.Observer;

/**
 * Objet Horloge qui �tend la classe Observable pour le patron observateur
 */
public class Horloge extends Observable {
    private int tourPassee;

    /**
     * Constructeur d'un objet horloge
     *      On initialise le nombre de tour pass� � -1 pour que l'application commence son nombre de tour � 0.
     */
    public Horloge(){
        tourPassee = -1;
    }

    public void tourPassee(){
        this.tourPassee++;
        setChanged();
        notifyObservers(tourPassee);
    }

    public void ajouterObservateur(Observer _o){
        addObserver(_o);
    }
}
