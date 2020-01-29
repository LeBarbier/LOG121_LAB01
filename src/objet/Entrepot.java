package objet;

import java.util.Map;

public class Entrepot implements Noeud {
    public String cheminICone;
    int limiteEntreposage;
    Map<Composante, Integer> composanteEntrepose;
    StrategieVente strategieVente; // Va etre initialiser selon either venteAleatoire ou venteIntervalleFixe

    public Entrepot(int _limiteEntreposage, String _cheminIcone){
        cheminICone = _cheminIcone;
        limiteEntreposage = _limiteEntreposage;
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

    public int evaluerIndicateur() {
        return 0;
    }

    public void update() {

    }
}
