package simulation;

import java.util.Observable;
import java.util.Observer;

/**
 * Objet Horloge qui �tend la classe Observable pour le patron observateur
 */
public class Horloge extends Observable {
    private int tourPassee;
    private static boolean venteAleatoire = false;

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

    /**
     * Ajout d'un observateur � la liste
     * @param _o Observateur qu'on d�sire ajouter � la liste
     */
    public void ajouterObservateur(Observer _o){
        addObserver(_o);
    }

    /**
     * Permet d'obtenir le nombre de tour qui son pass�e depuis le d�but de l'application
     * @return Retourne le nombre de tour pass�e
     */
    public int getTourPassee(){
        return tourPassee;
    }

    /**
     * Savoir si la vente est setter sur vente al�atoire
     * @return Retourne vrai si la vente est al�atoire, faux sinon
     */
    public static boolean isVenteAleatoire() {
        return venteAleatoire;
    }

    /**
     * Modifier la valeur de venteAleatoire
     * @param venteAleatoire Valeur que l'on d�sire donner � venteAl�atoire
     */
    public static void setVenteAleatoire(boolean venteAleatoire) {
        Horloge.venteAleatoire = venteAleatoire;
    }
}
