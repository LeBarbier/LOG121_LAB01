package objet;

import simulation.Environnement;
import simulation.Horloge;
import simulation.PanneauStrategie;

import java.util.HashMap;
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
     * @param _listeCheminIcone Liste des chemins des icônes à afficher dans la simulation graphique
     * @param _posX Position en X dans la simulation graphique
     * @param _posY Position en Y dans la simulation graphique
     */
    public Entrepot(int _id, String _typeEntreposage, int _limiteEntreposage, String[] _listeCheminIcone, int _posX, int _posY){
        composanteEntreeInventaire = new HashMap<>();

        id = _id;
        listeCheminIcone = _listeCheminIcone;
        typeEntreposage = _typeEntreposage;
        limiteEntreposage = _limiteEntreposage;
        posX = _posX;
        posY = _posY;
        iconeActuelle = _listeCheminIcone[0];
    }

    /**
     * Permet d'ajouter une composante dans l'entrepot
     * @param _composanteAjouter Composante qui sera entreposée dans l'entrepot
     */
    @Override
    public void ajouterComposanteEnInventaire(Composante _composanteAjouter){
        // Mise à jour de l'icone en fonction de l'état de l'usine d'assemblage
        if (composanteEntreeInventaire.size() == 0){
            iconeActuelle = listeCheminIcone[0];
        } else if (composanteEntreeInventaire.size() <= limiteEntreposage/3){
            iconeActuelle = listeCheminIcone[1];
        }else if (composanteEntreeInventaire.size() >= limiteEntreposage/3 && tempsConstruction <= 2*(limiteEntreposage/3)){
            iconeActuelle = listeCheminIcone[2];
        }

        if ((composanteEntreeInventaire.size() < limiteEntreposage) && (typeEntreposage.equalsIgnoreCase(_composanteAjouter.nom))){
            if (!composanteEntreeInventaire.containsKey(_composanteAjouter.nom)){
                composanteEntreeInventaire.put(_composanteAjouter.nom, 1);
            } else {
                composanteEntreeInventaire.put(_composanteAjouter.nom, composanteEntreeInventaire.get(_composanteAjouter.nom) + 1);
            }
            limiteEntreposage++;
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        StrategieVente strategieVente;
        boolean venteEffectuee;

        if (composanteEntreeInventaire.isEmpty() == false) {
            if (composanteEntreeInventaire.get(typeEntreposage) > 0
                    && composanteEntreeInventaire.get(typeEntreposage) != null){

                if (Horloge.isVenteAleatoire()){
                    strategieVente = new venteAleatoire();
                } else {
                    strategieVente = new venteIntervalleFixe();
                }

                venteEffectuee = strategieVente.vendreAvion();

                if (venteEffectuee){
                    composanteEntreeInventaire.put(typeEntreposage, composanteEntreeInventaire.get(typeEntreposage) - 1);
                }
            }
        }
    }
}
