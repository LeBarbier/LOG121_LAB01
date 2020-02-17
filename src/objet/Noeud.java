package objet;

import java.util.Observable;
import java.util.Observer;

public abstract class Noeud implements Observer {
    public int id;
    public int posX;
    public int posY;
    public String cheminICone;

    int evaluerIndicateur(){
        return 0;
    }
}
