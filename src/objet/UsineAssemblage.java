package objet;

public class UsineAssemblage implements Noeud {
    private Composante composanteSortie;
    private int dureeProduction;

    public UsineAssemblage(Composante _composanteSortie, int _dureeProduction){
        composanteSortie = _composanteSortie;
        dureeProduction = _dureeProduction;
    }

    public int evaluerIndicateur() {
        return 0;
    }

    public void update() {

    }
}
