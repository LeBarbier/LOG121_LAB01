package objet;

import simulation.PanneauPrincipal;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

/**
 * Classe Usine de production qui permet d'assemnbler des pièces pour en concevoir de nouvelles, étend Noeud
 *      (Correspond à une Usine matière dans le fichier de configuration XML)
 */
public class UsineProduction extends Noeud{
    Composante composanteSortie;
    int dureeProduction;

    /**
     * Constructeur d'une usine de production
     * @param _id Numéro d'identification de l'usine de production
     * @param _composanteSortie Composante qui est créer dans l'usine de production
     * @param _dureeProduction Temps que prend l'usine d'assemblage pour concevoir la composante de sortie
     * @param _cheminIcone Chemin de l'icône à afficher dans la simulation graphique
     * @param _posX Position en X dans la simulation graphique
     * @param _posY Position en Y dans la simulation graphique
     */
    public UsineProduction(int _id, Composante _composanteSortie, int _dureeProduction, String _cheminIcone, int _posX, int _posY){
        composanteEntreeInventaire = null;
        id = _id;
        cheminICone = _cheminIcone;
        composanteSortie = _composanteSortie;
        dureeProduction = _dureeProduction;
        posX = _posX;
        posY = _posY;
    }

    /**
     * Méthode qui est appelé lorsque l'horloge complète un tour et désigne que l'usine d'assemblage doit être mise à jour
     * @param o Élément observable qui à été mise à jour
     * @param arg Arguments supplémentaires pour aider à la mise à jour
     */
    @Override
    public void update(Observable o, Object arg) {
        // Si vrai, c'est que le nombre de tour est divisible par la durée de production, donc un tour est passée
        if (((Integer.parseInt(arg.toString()) % dureeProduction) == 0) && (Integer.parseInt(arg.toString()) != 0)) {
            assemblerPiece();
        }
    }

    /**
     * Permet d'assembler une pièce de l'usine en retirant de l'inventaire les composantes nécessaires
     */
    private void assemblerPiece(){
        PanneauPrincipal.mettreComposanteOnWire(composanteSortie, new Point[]{ new Point(posX, posY), new Point() });
    }

    @Override
    public void ajouterComposanteEnInventaire(Composante _composanteAjouter){
    }
}
