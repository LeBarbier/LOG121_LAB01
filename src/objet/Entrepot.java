package objet;

import java.util.Map;

public class Entrepot extends Noeud {
    public String cheminICone;
    int limiteEntreposage;
    Map<Composante, Integer> composanteEntrepose;
    StrategieVente strategieVente; // Va etre initialiser selon either venteAleatoire ou venteIntervalleFixe

    public Entrepot(int _id, int _limiteEntreposage, String _cheminIcone, int _posX, int _posY){
        id = _id;
        cheminICone = _cheminIcone;
        limiteEntreposage = _limiteEntreposage;
        posX = _posX;
        posY = _posY;
    }

    public void ajouterEntreposage(Composante _composante){
        if (composanteEntrepose.size() < limiteEntreposage){
            if (!composanteEntrepose.containsKey(_composante)){
                composanteEntrepose.put(_composante, 1);
            } else {
                composanteEntrepose.put(_composante, composanteEntrepose.get(_composante) + 1);
            }
            limiteEntreposage++;
        }
    }
}
