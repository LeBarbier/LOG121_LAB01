package objet;

import simulation.PanneauPrincipal;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.concurrent.atomic.AtomicBoolean;

public class UsineAssemblage extends Noeud {
    private Composante composanteSortie;
    Map<Composante, Integer> composanteEntreeNecessaire; // ["NomComposante", NombreNecessaire] -> �tablit le nombre de composantes d'entr�e n�cessaire pour cr�er une autre composante
    Map<Composante, Integer> composanteEntreeInventaire; // ["NomComposante", NombreEnInventaire] -> �tablit le nombre de composantes d'entr�e actuellement dans l'inventaire de l'usine.
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
        // V�rifier si l'usine poss�de assez de composante pour assembler la composante de sortie.
        if (possedePieceNecessaire()) {
            // V�rifier si le nombre de tour de l'horloge est assez grand  pour que l'usine ai assembler une pi�ce
            if (((Integer.parseInt(arg.toString()) % dureeProduction) == 0) && (Integer.parseInt(arg.toString()) != 0)) {
                assemblerPiece();
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

    // TODO: Ajouter une m�thode d'ajout ou de retrait de composantes dans l'inventaire.
}
