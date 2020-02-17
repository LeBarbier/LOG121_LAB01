package objet;

import simulation.PanneauPrincipal;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.concurrent.atomic.AtomicBoolean;

public class UsineAssemblage extends Noeud {
    private Composante composanteSortie;
    Map<Composante, Integer> composanteEntreeNecessaire; // ["NomComposante", NombreNecessaire] -> Établit le nombre de composantes d'entrée nécessaire pour créer une autre composante
    Map<Composante, Integer> composanteEntreeInventaire; // ["NomComposante", NombreEnInventaire] -> Établit le nombre de composantes d'entrée actuellement dans l'inventaire de l'usine.
    private int dureeProduction;

    public UsineAssemblage(int _id, Map<Composante, Integer> _composanteEntreeNecessaire, Composante _composanteSortie, int _dureeProduction, String _cheminIcone, int _posX, int _posY){
        composanteEntreeNecessaire = new HashMap<>();
        composanteEntreeInventaire = new HashMap<>();

        id = _id;
        cheminICone = _cheminIcone;
        composanteEntreeNecessaire = _composanteEntreeNecessaire;
        composanteSortie = _composanteSortie;
        dureeProduction = _dureeProduction;
        posX = _posX;
        posY = _posY;
    }

    @Override
    public void update(Observable o, Object arg) {
        // Vérifier si l'usine possède assez de composante pour assembler la composante de sortie.
        if (possedePieceNecessaire()) {
            // Vérifier si le nombre de tour de l'horloge est assez grand  pour que l'usine ai assembler une pièce
            if (((Integer.parseInt(arg.toString()) % dureeProduction) == 0) && (Integer.parseInt(arg.toString()) != 0)) {
                assemblerPiece();
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

        composanteEntreeInventaire.forEach((nomComposante, nombreEnInventaire) -> {
            if (composanteEntreeNecessaire.containsKey(nomComposante)) {
                if (composanteEntreeNecessaire.get(nomComposante) == nombreEnInventaire)
                {
                    valeurRetournee.set(true);
                } else { valeurRetournee.set(false); }
            } else { valeurRetournee.set(false); }
        });

        return valeurRetournee.get();
    }

    // TODO: Ajouter une méthode d'ajout ou de retrait de composantes dans l'inventaire.
}
