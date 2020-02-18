package simulation;

import objet.Composante;
import objet.Model;
import objet.Noeud;

import java.awt.*;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Classe qui d�finit un objet sur le chemin, entre deux noeuds
 */
public class ComposanteEnChemin {
    Composante composante;
    Point positionDepart;
    Point positionArrive;
    Point vitesse;

    /**
     * Constructeur d'un objet ComposanteEnChemin
     * @param _composante Objet Composante qui est en d�placement
     * @param _positionDepart Position de d�part de la composante en d�placement
     * @param _vitesse Vitesse et direction de la composante en d�placement
     */
    public ComposanteEnChemin(Composante _composante, Point _positionDepart, Point _vitesse){
        composante = _composante;
        positionDepart = _positionDepart;
        positionArrive = obtenirPositionArrive();
        vitesse = _vitesse;
    }

    /**
     * M�thode pour obtenir la position d'arriv� de la composante en chemin
     * @return Retourne un point de la position d'arriv�e
     */
    private Point obtenirPositionArrive(){
        Noeud noeudArrive = obtenirNoeudArrive();
        return new Point(noeudArrive.posX, noeudArrive.posY);
    }

    /**
     * M�thode pour obtenir le noeud d'arriver selon la position de d�part et le chemin emprunter
     * @return Retourne le noeud d'arriver de la composante en chemin
     */
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
