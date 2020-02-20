package objet;

import simulation.PanneauPrincipal;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 *  Classe Usine d'assemblage qui permet d'assembler des pièces pour en concevoir de nouvelles, étend Noeud
 */
public class UsineAssemblage extends Noeud {
    private Composante composanteSortie;
    Map<String, Integer> composanteEntreeNecessaire; // ["NomComposante", NombreNecessaire] -> Établit le nombre de composantes d'entrée nécessaire pour créer une autre composante
    private int dureeProduction;

    /**
     * Constructeur d'une usine d'assemblage
     * @param _id Numéro d'identification de l'usine d'assemblage
     * @param _composanteEntreeNecessaire Liste des composantes nécessaires à la fabrication de la composante de sortie
     * @param _composanteSortie Composante qui est créer dans l'usine de production
     * @param _dureeProduction Temps que prend l'usine d'assemblage pour concevoir la composante de sortie
     * @param _listeCheminIcone Liste des chemins des icônes à afficher dans la simulation graphique
     * @param _posX Position en X dans la simulation graphique
     * @param _posY Position en Y dans la simulation graphique
     */
    public UsineAssemblage(int _id, Map<String, Integer> _composanteEntreeNecessaire, Composante _composanteSortie, int _dureeProduction, String[] _listeCheminIcone, int _posX, int _posY){
        composanteEntreeNecessaire = new HashMap<>();
        composanteEntreeInventaire = new HashMap<>();

        id = _id;
        listeCheminIcone = _listeCheminIcone;
        composanteEntreeNecessaire = _composanteEntreeNecessaire;
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

        // Vérifier si l'usine possède assez de composante pour assembler la composante de sortie.
        if (possedePieceNecessaire()) {
            tempsConstruction++;

            // Vérifier si le nombre de tour de l'horloge est assez grand  pour que l'usine ai assembler une pièce
            if (tempsConstruction >= dureeProduction) {
                iconeActuelle = listeCheminIcone[3];
                assemblerPiece();
                tempsConstruction = 0;
            }
        }
    }

    /**
     * Permet d'assembler une pièce de l'usine en retirant de l'inventaire les composantes nécessaires
     */
    private void assemblerPiece(){
        composanteEntreeNecessaire.forEach((nomComposante, nombre) -> {
            composanteEntreeInventaire.put(nomComposante, composanteEntreeInventaire.get(nomComposante) - nombre);
        });
        PanneauPrincipal.mettreComposanteOnWire(composanteSortie, new Point[]{ new Point(posX, posY), new Point() });
    }

    /**
     * Permet de savoir si l'usine d'assemblage possède toutes les composantes requise pour pouvoir assembler la composante de sortie
     * @return Retourne un booléen vrai ou faux, dépendant si l'usine possède toutes les composante nécessaire pour produire.
     */
    private boolean possedePieceNecessaire(){
        AtomicBoolean valeurRetournee = new AtomicBoolean(false);

        composanteEntreeNecessaire.forEach((nomComposante, nombreNecessaire) -> {
            if (composanteEntreeInventaire.containsKey(nomComposante)) {
                if (composanteEntreeInventaire.get(nomComposante) >= nombreNecessaire)
                {
                    valeurRetournee.set(true);
                } else { valeurRetournee.set(false); }
            } else { valeurRetournee.set(false); }
        });

        return valeurRetournee.get();
    }
}
