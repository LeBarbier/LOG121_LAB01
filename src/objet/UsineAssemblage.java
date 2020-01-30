package objet;

public class UsineAssemblage extends Noeud {
    public String cheminICone;
    private Composante composanteSortie;
    private int dureeProduction;

    public UsineAssemblage(int _id, Composante _composanteSortie, int _dureeProduction, String _cheminIcone, int _posX, int _posY){
        id = _id;
        cheminICone = _cheminIcone;
        composanteSortie = _composanteSortie;
        dureeProduction = _dureeProduction;
        posX = _posX;
        posY = _posY;
    }
}
