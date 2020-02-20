package objet;

/**
 * Stratégie de vente aléatoire
 */
public class venteAleatoire implements StrategieVente {
    /**
     * Méthode qui permet de savoir si on as vendu un avion, de façon aléatoire
     * @return Retourne vrai si on a vendu un avion, faux sinon
     */
    public boolean vendreAvion() {
        int nombreAleatoire = (0 - 10) + 1;
        nombreAleatoire = (int)(Math.random() * nombreAleatoire);

        // Avec un nombre aléatoire, si on a 1 on fait une vente (donc 1 chance sur 10)
        if (nombreAleatoire == 1){
            return true;
        }
        return false;
    }
}
