package simulation;

import objet.Composante;
import objet.Model;
import objet.Noeud;

import java.awt.*;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Classe qui définit un objet sur le chemin, entre deux noeuds
 */
public class ComposanteEnChemin {
    Composante composante;
    Noeud noeudDepart;
    Noeud noeudArrive;
    Point positionActuelle;
    Point vitesse;

    /**
     * Constructeur d'un objet ComposanteEnChemin
     * @param _composante Objet Composante qui est en déplacement
     * @param _positionDepart Position de départ de la composante en déplacement
     * @param _vitesse Vitesse et direction de la composante en déplacement
     */
    public ComposanteEnChemin(Composante _composante, Point _positionDepart, Point _vitesse){
        initNoeudDepartArrive(_positionDepart);
        composante = _composante;
        vitesse = _vitesse;
        positionActuelle = _positionDepart;
    }

    private void initNoeudDepartArrive(Point _posDepart){
        HashMap<Integer, Noeud> listeNoeud = Simulation.getListeNoeudSimulation();
        AtomicReference<Noeud> noeudAtomic = new AtomicReference<>();
        String[][] listeChemins = Model.obtenirDonneeSimulationChemin();

        listeNoeud.forEach((id, noeud) -> {
            if (noeud.posX == _posDepart.x && noeud.posY == _posDepart.y){
                noeudAtomic.set(noeud);
            }
        });
        noeudDepart = noeudAtomic.get();

        for (String[] chemin : listeChemins) {
            if (Integer.parseInt(chemin[0]) == noeudDepart.id){
                noeudArrive = listeNoeud.get(Integer.parseInt(chemin[1]));
            }
        }
    }
}
