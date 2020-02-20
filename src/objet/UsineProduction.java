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
     * @param _listeCheminIcone Liste des chemins des icônes à afficher dans la simulation graphique
     * @param _posX Position en X dans la simulation graphique
     * @param _posY Position en Y dans la simulation graphique
     */
    public UsineProduction(int _id, Composante _composanteSortie, int _dureeProduction, String[] _listeCheminIcone, int _posX, int _posY){
        composanteEntreeInventaire = null;
        id = _id;
        listeCheminIcone = _listeCheminIcone;
        composanteSortie = _composanteSortie;
        dureeProduction = _dureeProduction;
        posX = _posX;
        posY = _posY;
        iconeActuelle = _listeCheminIcone[0];
    }

    /**
     * Méthode qui est appelé lorsque l'horloge complète un tour et désigne que l'usine d'assemblage doit être mise à jour
     * @param o Élément observable qui à été mise à jour
     * @param arg Arguments supplémentaires pour aider à la mise à jour
     */
    @Override
    public void update(Observable o, Object arg) {
        // Mise à jour de l'icone en fonction de l'état de l'usine d'assemblage
        if (tempsConstruction == 0){
            iconeActuelle = listeCheminIcone[0];
        } else if (tempsConstruction <= dureeProduction/3){
            iconeActuelle = listeCheminIcone[1];
        }else if (tempsConstruction >= dureeProduction/3 && tempsConstruction <= 2*(dureeProduction/3)){
            iconeActuelle = listeCheminIcone[2];
        }

        tempsConstruction++;

        // Si vrai, c'est que le nombre de tour est divisible par la durée de production, donc un tour est passée
        if (tempsConstruction >= dureeProduction) {
            iconeActuelle = listeCheminIcone[3];
            assemblerPiece();
            tempsConstruction = 0;
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
