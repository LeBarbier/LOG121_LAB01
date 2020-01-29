package objet;

public class UsineAssemblage implements Noeud {
    public String cheminICone;
    private Composante composanteSortie;
    private int dureeProduction;

    public UsineAssemblage(Composante _composanteSortie, int _dureeProduction, String _cheminIcone){
        cheminICone = _cheminIcone;
        composanteSortie = _composanteSortie;
        dureeProduction = _dureeProduction;
    }

    public int evaluerIndicateur() {
        return 0;
    }

    public void update() {

    }
}
