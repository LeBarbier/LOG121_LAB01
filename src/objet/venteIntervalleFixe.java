package objet;

import simulation.Environnement;

public class venteIntervalleFixe implements StrategieVente {
    public boolean vendreAvion() {
        // À chaque 3 tours, on fait une vente
        if (Environnement.horloge.getTourPassee() % 3 == 0)
            return true;
        else
            return false;
    }
}
