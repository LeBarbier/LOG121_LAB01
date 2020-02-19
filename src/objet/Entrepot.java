package objet;

import java.util.Map;
import java.util.Observable;

/**
 * Classe Entrepot qui permet de stocker des avions prêt à la vente, étend Noeud
 */
public class Entrepot extends Noeud {
    public String typeEntreposage;
    int limiteEntreposage;
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
     * @param _composanteAjouter Composante qui sera entreposée dans l'entrepot
     */
    @Override
    public void ajouterComposanteEnInventaire(Composante _composanteAjouter){
        if ((composanteEntreeInventaire.size() < limiteEntreposage) && (typeEntreposage == _composanteAjouter.nom)){
            if (!composanteEntreeInventaire.containsKey(_composanteAjouter.nom)){
                composanteEntreeInventaire.put(_composanteAjouter.nom, 1);
            } else {
                composanteEntreeInventaire.put(_composanteAjouter.nom, composanteEntreeInventaire.get(_composanteAjouter) + 1);
            }
            limiteEntreposage++;
        }
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
