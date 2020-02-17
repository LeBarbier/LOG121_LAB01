package simulation;

import objet.Composante;

import java.awt.*;

public class ComposanteGraphique {
    String[] chemin; // Tableau à deux valeurs -> { DE, VERS }
    Composante composante;
    Point position;
    Point vitesse;

    public ComposanteGraphique(Composante _composante, Point _position, Point _vitesse){
        composante = _composante;
        position = _position;
        vitesse = _vitesse;
        chemin = new String[]{ "", ""};
    }

    public ComposanteGraphique(Composante _composante, Point _position, Point _vitesse, String[] _chemin){
        composante = _composante;
        position = _position;
        vitesse = _vitesse;
        chemin = _chemin;
    }
}
