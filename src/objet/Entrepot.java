package objet;

import java.util.Map;
import java.util.Observable;

/**
 * Classe Entrepot qui permet de stocker des avions prêt à la vente, étend Noeud
 */
public class Entrepot extends Noeud {
    public String typeEntreposage;
    int limiteEntreposage;
    Map<Composante, Integer> composanteEntrepose;
    StrategieVente strategieVente; // Va etre initialiser selon either venteAleatoire ou venteIntervalleFixe

    /**
     * Constructeur d'un Entrepot
     * @param _id Numéro d'identification de l'entrepot
     * @param _typeEntreposage Chaîne définissant le type d'entreposage de l'entrepot, tel que Avion
     * @param _limiteEntreposage Limite d'espace dans l'entrepot
     * @param _cheminIcone Chemin de l'icône à afficher dans la simulation graphique
     * @param _posX Position en X dans la simulation graphique
     * @param _posY Position en Y dans la simulation graphique
     */
    public Entrepot(int _id, String _typeEntreposage, int _limiteEntreposage, String _cheminIcone, int _posX, int _posY){
        id = _id;
        cheminICone = _cheminIcone;
        typeEntreposage = _typeEntreposage;
        limiteEntreposage = _limiteEntreposage;
        posX = _posX;
        posY = _posY;
    }

    /**
     * Permet d'ajouter une composante dans l'entrepot
     * @param _composante Composante qui sera entreposée dans l'entrepot
     */
    public void ajouterEntreposage(Composante _composante){
        if ((composanteEntrepose.size() < limiteEntreposage) && (typeEntreposage == _composante.nom)){
            if (!composanteEntrepose.containsKey(_composante)){
                composanteEntrepose.put(_composante, 1);
            } else {
                composanteEntrepose.put(_composante, composanteEntrepose.get(_composante) + 1);
            }
            limiteEntreposage++;
        }
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
