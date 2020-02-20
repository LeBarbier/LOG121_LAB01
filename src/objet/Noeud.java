package objet;

import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * Interface d�finissant le comportement des Noeuds, impl�mente un Observateur
 */
public abstract class Noeud implements Observer {
    public int id;
    public int posX;
    public int posY;
    public String iconeActuelle;
    Map<String, Integer> composanteEntreeInventaire; // ["NomComposante", NombreEnInventaire] -> �tablit le nombre de composantes d'entr�e actuellement dans l'inventaire de l'usine.
    protected String[] listeCheminIcone;
    protected int tempsConstruction;

    /**
     * M�thode qui d�clare, de base, l'ajout d'un item � l'inventaire du noeud
     * @param _composanteAjouter Composante que l'on d�sire ajouter � l'inventaire
     */
    public void ajouterComposanteEnInventaire(Composante _composanteAjouter){
        if (composanteEntreeInventaire.containsKey(_composanteAjouter.nom))
            composanteEntreeInventaire.put(_composanteAjouter.nom, composanteEntreeInventaire.get(_composanteAjouter.nom) + 1);
        else
            composanteEntreeInventaire.put(_composanteAjouter.nom, 1);
    }
}
