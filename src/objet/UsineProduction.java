package objet;

import java.util.HashMap;
import java.util.Map;

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
}
