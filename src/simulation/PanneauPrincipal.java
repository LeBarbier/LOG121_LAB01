package simulation;

import objet.*;
import org.xml.sax.SAXException;

import java.awt.*;
import java.awt.geom.Line2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;

public class PanneauPrincipal extends JPanel {

	private static final long serialVersionUID = 1L;
	private int taille = 32;
	String[][] listeImageNoeud;
	HashMap<Integer, Noeud> listeNoeud;
	public static ArrayList<ComposanteEnChemin> listeComposanteOnWire; // [ Composante, { pointPosition, pointVitesse } ]
	public static ArrayList<ComposanteEnChemin> listeComposanteOnWireARetirer; // [ Composante, { pointPosition, pointVitesse } ]
	ArrayList<Line2D> arrayListLigne;
	String[][] listeChemins;

	public PanneauPrincipal() throws IOException, SAXException, ParserConfigurationException {
		listeNoeud = Simulation.getListeNoeudSimulation();
		arrayListLigne = new ArrayList<>();
		listeImageNoeud = new String[listeNoeud.size()][3];
		listeComposanteOnWire = new ArrayList<>();
		listeComposanteOnWireARetirer = new ArrayList<>();
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
			ImageIcon imageIcon = new ImageIcon(noeud.iconeActuelle);
			imageIcon.paintIcon(this, g, noeud.posX, noeud.posY);
		});

		listeComposanteOnWire.forEach((composanteEnChemin -> {
			// Variables temporaires de la demonstration:
			Point posActuelleModifiee = new Point(composanteEnChemin.positionActuelle.x, composanteEnChemin.positionActuelle.y);  // new Point(0,0);
			Point vitesse = composanteEnChemin.vitesse;

			if (vitesse.x == 0 && vitesse.y == 0)
				vitesse = obtenirVitesseTransport(composanteEnChemin); // new Point(1,1);

			// Vérifier si la composante à atteint sa destination
			if (isComposanteAtDestination(composanteEnChemin)){
				listeComposanteOnWireARetirer.add(composanteEnChemin);
				return;
			}

			posActuelleModifiee.translate(vitesse.x, vitesse.y);
			ImageIcon imageIcon = new ImageIcon(composanteEnChemin.composante.cheminICone);
			imageIcon.paintIcon(this, g, posActuelleModifiee.x, posActuelleModifiee.y);

			composanteEnChemin.positionActuelle = posActuelleModifiee;

			listeComposanteOnWire.set(listeComposanteOnWire.indexOf(composanteEnChemin), composanteEnChemin);
		}));

		// Ici on retirer les composantes arriver à destination
		listeComposanteOnWireARetirer.forEach(composanteEnChemin -> {
			retirerComposanteOnWire(composanteEnChemin);
		});
		listeComposanteOnWireARetirer.clear();
	}

	public static void mettreComposanteOnWire(Composante _composante, Point[] _listePositionVitesse){
		if (listeComposanteOnWire != null){
			listeComposanteOnWire.add(new ComposanteEnChemin(_composante, _listePositionVitesse[0], _listePositionVitesse[1]));
		}
	}

	private static Point obtenirVitesseTransport(ComposanteEnChemin _composanteEnChemin){
		int vitesseDeplacementX = 0;
		int vitesseDeplacementY = 0;

		// Ici on calcule la vitesse de l'objet
		if (_composanteEnChemin.positionActuelle != null && _composanteEnChemin.noeudArrive != null) {
			if (_composanteEnChemin.positionActuelle.x == _composanteEnChemin.noeudArrive.posX)
				vitesseDeplacementX = 0;
			else if (_composanteEnChemin.positionActuelle.x > _composanteEnChemin.noeudArrive.posX)
				vitesseDeplacementX = -1;
			else if (_composanteEnChemin.positionActuelle.x < _composanteEnChemin.noeudArrive.posX)
				vitesseDeplacementX = 1;

			if (_composanteEnChemin.positionActuelle.y == _composanteEnChemin.noeudArrive.posY)
				vitesseDeplacementY = 0;
			else if (_composanteEnChemin.positionActuelle.y > _composanteEnChemin.noeudArrive.posY)
				vitesseDeplacementY = -1;
			else if (_composanteEnChemin.positionActuelle.y < _composanteEnChemin.noeudArrive.posY)
				vitesseDeplacementY = 1;

		}

		return new Point(vitesseDeplacementX, vitesseDeplacementY);
	}

	private static boolean isComposanteAtDestination(ComposanteEnChemin _composanteEnChemin){
		if (_composanteEnChemin.positionActuelle.x == _composanteEnChemin.noeudArrive.posX
				&& _composanteEnChemin.positionActuelle.y == _composanteEnChemin.noeudArrive.posY){
			return true;
		}
		return  false;
	}

	private void retirerComposanteOnWire(ComposanteEnChemin _composanteEnChemin){
		if (listeComposanteOnWire != null){
			Noeud noeudArrive = listeNoeud.get(_composanteEnChemin.noeudArrive.id);

			noeudArrive.ajouterComposanteEnInventaire(new Composante(_composanteEnChemin.composante.nom));

			listeComposanteOnWire.remove(_composanteEnChemin);
		}
	}
}