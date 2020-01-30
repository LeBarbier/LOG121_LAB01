package objet;

import java.util.HashMap;
import java.util.Map;

public class UsineProduction extends Noeud{
    public String cheminICone;
    Map<Composante, Integer> composanteEntree;
    Composante composanteSortie;
    int dureeProduction;

    public UsineProduction(int _id, Composante[] _composantesEntree, Composante _composanteSortie, int _dureeProduction, String _cheminIcone, int _posX, int _posY){
        composanteEntree = new HashMap<>();

        id = _id;
        cheminICone = _cheminIcone;
        for (Composante composante : _composantesEntree) {
            composanteEntree.put(composante, 0);
        }
        composanteSortie = _composanteSortie;
        dureeProduction = _dureeProduction;
        posX = _posX;
        posY = _posY;
    }
}
