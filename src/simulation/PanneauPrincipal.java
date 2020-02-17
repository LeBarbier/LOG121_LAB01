package simulation;

import objet.Composante;
import objet.Model;
import objet.Noeud;
import org.xml.sax.SAXException;

import java.awt.*;
import java.awt.geom.Line2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.concurrent.atomic.AtomicReference;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;

public class PanneauPrincipal extends JPanel {

	private static final long serialVersionUID = 1L;
	private int taille = 32;
	String[][] listeImageNoeud;
	HashMap<Integer, Noeud> listeNoeud;
	public static ArrayList<ComposanteGraphique> listeComposanteOnWire; // [ Composante, { pointPosition, pointVitesse } ]
	ArrayList<Line2D> arrayListLigne;
	String[][] listeChemins;

	public PanneauPrincipal() throws IOException, SAXException, ParserConfigurationException {
		listeNoeud = Simulation.getListeNoeudSimulation();
		arrayListLigne = new ArrayList<>();
		listeImageNoeud = new String[listeNoeud.size()][3];
		listeComposanteOnWire = new ArrayList<>();
		listeChemins = Model.obtenirDonneeSimulationChemin();
	}

	@Override
	public void paint(Graphics g) {

		super.paint(g);

		for (String[] stringChemin : listeChemins) {
			// Ajouter 16 pixel à la position en X et Y, car on désire que la ligne soit au centre de l'icône.
			g.drawLine(listeNoeud.get(Integer.parseInt(stringChemin[0])).posX+16,
						listeNoeud.get(Integer.parseInt(stringChemin[0])).posY+16,
						listeNoeud.get(Integer.parseInt(stringChemin[1])).posX+16,
						listeNoeud.get(Integer.parseInt(stringChemin[1])).posY+16);
		}

		listeNoeud.forEach((id, noeud) -> {
			ImageIcon imageIcon = new ImageIcon(noeud.cheminICone);
			imageIcon.paintIcon(this, g, noeud.posX, noeud.posY);
		});

		listeComposanteOnWire.forEach((composanteGraphique -> {
			// Variables temporaires de la demonstration:
			Point position = composanteGraphique.position;  // new Point(0,0);
			Point vitesse = composanteGraphique.vitesse;

			if (vitesse.x == 0 && vitesse.y == 0)
				vitesse = obtenirVitesseTransport(position); // new Point(1,1);


			position.translate(vitesse.x, vitesse.y);
			ImageIcon imageIcon = new ImageIcon(composanteGraphique.composante.cheminICone);
			imageIcon.paintIcon(this, g, position.x, position.y);

			listeComposanteOnWire.set(listeComposanteOnWire.indexOf(composanteGraphique), new ComposanteGraphique(composanteGraphique.composante, position, vitesse));
		}));
	}

	public static void mettreComposanteOnWire(Composante _composante, Point[] _listePositionVitesse){
		if (listeComposanteOnWire != null){
			listeComposanteOnWire.add(new ComposanteGraphique(_composante, _listePositionVitesse[0], _listePositionVitesse[1]));
		}
	}

	private Point obtenirVitesseTransport(Point _positionDepart){
		Point vitesseTransport;
		AtomicReference<Noeud> noeudAtomic = new AtomicReference<>();
		Noeud noeudArrive = null;
		Noeud noeudDepart = null;
		int vitesseDeplacementX = 0;
		int vitesseDeplacementY = 0;

		listeNoeud.forEach((id, noeud) -> {
			if (noeud.posX == _positionDepart.x && noeud.posY == _positionDepart.y){
				noeudAtomic.set(noeud);
			}
		});

		for (String[] chemin : listeChemins) {
			if (Integer.parseInt(chemin[0]) == noeudAtomic.get().id){
				noeudDepart = listeNoeud.get(Integer.parseInt(chemin[0]));
				noeudArrive = listeNoeud.get(Integer.parseInt(chemin[1]));
			}
		}

		// Ici on calcule la vitesse de l'objet
		if (noeudDepart != null && noeudArrive != null) {
			if (noeudDepart.posX == noeudArrive.posX)
				vitesseDeplacementX = 0;
			else if (noeudDepart.posX > noeudArrive.posX)
				vitesseDeplacementX = -1;
			else if (noeudDepart.posX < noeudArrive.posX)
				vitesseDeplacementX = 1;

			if (noeudDepart.posY == noeudArrive.posY)
				vitesseDeplacementY = 0;
			else if (noeudDepart.posY > noeudArrive.posY)
				vitesseDeplacementY = -1;
			else if (noeudDepart.posY < noeudArrive.posY)
				vitesseDeplacementY = 1;

		}
		vitesseTransport = new Point(vitesseDeplacementX, vitesseDeplacementY);

		return vitesseTransport;
	}
}