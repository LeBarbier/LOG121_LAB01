package objet;

import simulation.Environnement;

/**
 * Strat�gie de vente � intervalle fixe
 */
public class venteIntervalleFixe implements StrategieVente {
    /**
     * M�thode qui permet de savoir si on as vendu un avion, par intervalle fixe
     * @return Retourne vrai si on a vendu un avion, faux sinon
     */
    public boolean vendreAvion() {
        // � chaque 3 tours, on fait une vente
        if (Environnement.horloge.getTourPassee() % 3 == 0)
            return true;
        else
            return false;
    }
}
