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

    /**
     * Ajout d'un observateur à la liste
     * @param _o Observateur qu'on désire ajouter à la liste
     */
    public void ajouterObservateur(Observer _o){
        addObserver(_o);
    }

    /**
     * Permet d'obtenir le nombre de tour qui son passée depuis le début de l'application
     * @return Retourne le nombre de tour passée
     */
    public int getTourPassee(){
        return tourPassee;
    }

    /**
     * Savoir si la vente est setter sur vente aléatoire
     * @return Retourne vrai si la vente est aléatoire, faux sinon
     */
    public static boolean isVenteAleatoire() {
        return venteAleatoire;
    }

    /**
     * Modifier la valeur de venteAleatoire
     * @param venteAleatoire Valeur que l'on désire donner à venteAléatoire
     */
    public static void setVenteAleatoire(boolean venteAleatoire) {
        Horloge.venteAleatoire = venteAleatoire;
    }
}
