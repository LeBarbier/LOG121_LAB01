package objet;

/**
 * Strat�gie de vente al�atoire
 */
public class venteAleatoire implements StrategieVente {
    /**
     * M�thode qui permet de savoir si on as vendu un avion, de fa�on al�atoire
     * @return Retourne vrai si on a vendu un avion, faux sinon
     */
    public boolean vendreAvion() {
        int nombreAleatoire = (0 - 10) + 1;
        nombreAleatoire = (int)(Math.random() * nombreAleatoire);

        // Avec un nombre al�atoire, si on a 1 on fait une vente (donc 1 chance sur 10)
        if (nombreAleatoire == 1){
            return true;
        }
        return false;
    }
}
