package objet;

import java.util.HashMap;
import java.util.Map;

public class UsineProduction implements Noeud{
    public String cheminICone;
    Map<Composante, Integer> composanteEntree;
    Composante composanteSortie;
    int dureeProduction;

    public UsineProduction(Composante[] _composantesEntree, Composante _composanteSortie, int _dureeProduction, String _cheminIcone){
        composanteEntree = new HashMap<>();
        cheminICone = _cheminIcone;
        for (Composante composante : _composantesEntree) {
            composanteEntree.put(composante, 0);
        }
        composanteSortie = _composanteSortie;
        dureeProduction = _dureeProduction;
    }

    public int evaluerIndicateur(){
        return 0;
    }
    public void update(){

    }
}
