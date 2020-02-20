package simulation;

import java.util.Observable;
import java.util.Observer;

/**
 * Objet Horloge qui étend la classe Observable pour le patron observateur
 */
public class Horloge extends Observable {
    private int tourPassee;
    private static boolean venteAleatoire = false;

    /**
     * Constructeur d'un objet horloge
     *      On initialise le nombre de tour passé à -1 pour que l'application commence son nombre de tour à 0.
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

    public int getTourPassee(){
        return tourPassee;
    }

    public static boolean isVenteAleatoire() {
        return venteAleatoire;
    }

    public static void setVenteAleatoire(boolean venteAleatoire) {
        Horloge.venteAleatoire = venteAleatoire;
    }
}
