package objet;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

// Usine-matière dans le fichier de configuration
public class UsineProduction extends Noeud{
    Composante composanteSortie;
    int dureeProduction;

    public UsineProduction(int _id, Composante _composanteSortie, int _dureeProduction, String _cheminIcone, int _posX, int _posY){
        id = _id;
        cheminICone = _cheminIcone;
        composanteSortie = _composanteSortie;
        dureeProduction = _dureeProduction;
        posX = _posX;
        posY = _posY;
    }

    public void update(int _tourPassee) {
        // Si vrai, c'est que le nombre de tour est divisible par la durée de production, donc un tour est passée
        if ((_tourPassee % dureeProduction) == 0) {
        }
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
