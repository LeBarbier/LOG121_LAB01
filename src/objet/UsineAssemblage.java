package objet;

import simulation.PanneauPrincipal;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 *  Classe Usine d'assemblage qui permet d'assembler des pi�ces pour en concevoir de nouvelles, �tend Noeud
 */
public class UsineAssemblage extends Noeud {
    private Composante composanteSortie;
    Map<String, Integer> composanteEntreeNecessaire; // ["NomComposante", NombreNecessaire] -> �tablit le nombre de composantes d'entr�e n�cessaire pour cr�er une autre composante
    private int dureeProduction;

    /**
     * Constructeur d'une usine d'assemblage
     * @param _id Num�ro d'identification de l'usine d'assemblage
     * @param _composanteEntreeNecessaire Liste des composantes n�cessaires � la fabrication de la composante de sortie
     * @param _composanteSortie Composante qui est cr�er dans l'usine de production
     * @param _dureeProduction Temps que prend l'usine d'assemblage pour concevoir la composante de sortie
     * @param _listeCheminIcone Liste des chemins des ic�nes � afficher dans la simulation graphique
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
     * M�thode qui est appel� lorsque l'horloge compl�te un tour et d�signe que l'usine d'assemblage doit �tre mise � jour
     * @param o �l�ment observable qui � �t� mise � jour
     * @param arg Arguments suppl�mentaires pour aider � la mise � jour
     */
    @Override
    public void update(Observable o, Object arg) {
        // Mise � jour de l'icone en fonction de l'�tat de l'usine d'assemblage
        if (tempsConstruction == 0){
            iconeActuelle = listeCheminIcone[0];
        } else if (tempsConstruction <= dureeProduction/3){
            iconeActuelle = listeCheminIcone[1];
        }else if (tempsConstruction >= dureeProduction/3 && tempsConstruction <= 2*(dureeProduction/3)){
            iconeActuelle = listeCheminIcone[2];
        }

        // V�rifier si l'usine poss�de assez de composante pour assembler la composante de sortie.
        if (possedePieceNecessaire()) {
            tempsConstruction++;

            // V�rifier si le nombre de tour de l'horloge est assez grand  pour que l'usine ai assembler une pi�ce
            if (tempsConstruction >= dureeProduction) {
                iconeActuelle = listeCheminIcone[3];
                assemblerPiece();
                tempsConstruction = 0;
            }
        }
    }

    /**
     * Permet d'assembler une pi�ce de l'usine en retirant de l'inventaire les composantes n�cessaires
     */
    private void assemblerPiece(){
        composanteEntreeNecessaire.forEach((nomComposante, nombre) -> {
            composanteEntreeInventaire.put(nomComposante, composanteEntreeInventaire.get(nomComposante) - nombre);
        });
        PanneauPrincipal.mettreComposanteOnWire(composanteSortie, new Point[]{ new Point(posX, posY), new Point() });
    }

    /**
     * Permet de savoir si l'usine d'assemblage poss�de toutes les composantes requise pour pouvoir assembler la composante de sortie
     * @return Retourne un bool�en vrai ou faux, d�pendant si l'usine poss�de toutes les composante n�cessaire pour produire.
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
