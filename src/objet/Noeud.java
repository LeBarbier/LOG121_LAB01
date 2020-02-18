package objet;

import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * Interface définissant le comportement des Noeuds, implémente un Observateur
 */
public abstract class Noeud implements Observer {
    public int id;
    public int posX;
    public int posY;
    public String cheminICone;
    Map<Composante, Integer> composanteEntreeInventaire; // ["NomComposante", NombreEnInventaire] -> Établit le nombre de composantes d'entrée actuellement dans l'inventaire de l'usine.

    int evaluerIndicateur(){
        return 0;
    }
    public void ajouterComposanteEnInventaire(Composante _composanteAjouter){
        composanteEntreeInventaire.put(_composanteAjouter, 1);
        System.out.println(composanteEntreeInventaire.toString());
    }
}
