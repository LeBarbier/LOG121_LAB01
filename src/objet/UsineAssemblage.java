package objet;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

public class UsineAssemblage extends Noeud {
    private Composante composanteSortie;
    Map<Composante, Integer> composanteEntreeNecessaire; // ["NomComposante", NombreNecessaire] -> �tablit le nombre de composantes d'entr�e n�cessaire pour cr�er une autre composante
    Map<Composante, Integer> composanteEntreeInventaire; // ["NomComposante", NombreEnInventaire] -> �tablit le nombre de composantes d'entr�e actuellement dans l'inventaire de l'usine.
    private int dureeProduction;

    public UsineAssemblage(int _id, Map<Composante, Integer> _composanteEntreeNecessaire, Composante _composanteSortie, int _dureeProduction, String _cheminIcone, int _posX, int _posY){
        composanteEntreeNecessaire = new HashMap<>();

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

    }

    // TODO: Ajouter une m�thode d'ajout ou de retrait de composantes dans l'inventaire.
}
