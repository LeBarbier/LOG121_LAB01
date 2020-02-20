package objet;

import simulation.Environnement;

/**
 * Stratégie de vente à intervalle fixe
 */
public class venteIntervalleFixe implements StrategieVente {
    /**
     * Méthode qui permet de savoir si on as vendu un avion, par intervalle fixe
     * @return Retourne vrai si on a vendu un avion, faux sinon
     */
    public boolean vendreAvion() {
        // À chaque 3 tours, on fait une vente
        if (Environnement.horloge.getTourPassee() % 3 == 0)
            return true;
        else
            return false;
    }
}
