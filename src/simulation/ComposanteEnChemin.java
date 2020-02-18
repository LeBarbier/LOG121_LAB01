package simulation;

import objet.Composante;
import objet.Model;
import objet.Noeud;

import java.awt.*;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

public class ComposanteEnChemin {
    Composante composante;
    Point positionDepart;
    Point positionArrive;
    Point vitesse;

    public ComposanteEnChemin(Composante _composante, Point _positionDepart, Point _vitesse){
        composante = _composante;
        positionDepart = _positionDepart;
        positionArrive = obtenirPositionArrive();
        vitesse = _vitesse;
    }

    private Point obtenirPositionArrive(){
        Noeud noeudArrive = obtenirNoeudArrive();


        return new Point(noeudArrive.posX, noeudArrive.posY);
    }

    public Noeud obtenirNoeudArrive(){
        Noeud noeudArrive = null;
        String[][] listeChemins = Model.obtenirDonneeSimulationChemin();
        HashMap<Integer, Noeud> listeNoeud = Simulation.getListeNoeudSimulation();
        AtomicReference<Noeud> noeudAtomic = new AtomicReference<>();
        listeNoeud.forEach((id, noeud) -> {
            if (noeud.posX == positionDepart.x && noeud.posY == positionDepart.y){
                noeudAtomic.set(noeud);
            }
        });
        Noeud noeudTrouver = noeudAtomic.get();

        for (String[] chemin : listeChemins) {
            if (Integer.parseInt(chemin[0]) == noeudTrouver.id){
                noeudArrive = listeNoeud.get(Integer.parseInt(chemin[1]));
            }
        }
        return noeudArrive;
    }
}
