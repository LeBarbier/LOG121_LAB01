package simulation;

import java.util.Observable;
import java.util.Observer;

public class Horloge extends Observable {
    private int tourPassee;

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
