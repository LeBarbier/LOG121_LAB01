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
			// Ajouter 16 pixel � la position en X et Y, car on d�sire que la ligne soit au centre de l'ic�ne.
			g.drawLine(listeNoeud.get(Integer.parseInt(stringChemin[0])).posX+16,
						listeNoeud.get(Integer.parseInt(stringChemin[0])).posY+16,
						listeNoeud.get(Integer.parseInt(stringChemin[1])).posX+16,
						listeNoeud.get(Integer.parseInt(stringChemin[1])).posY+16);
		}

		listeNoeud.forEach((id, noeud) -> {
			ImageIcon imageIcon = new ImageIcon(noeud.cheminICone);
			imageIcon.paintIcon(this, g, noeud.posX, noeud.posY);
		});

		listeComposanteOnWire.forEach((composanteEnChemin -> {
			// Variables temporaires de la demonstration:
			Point positionDepart = composanteEnChemin.positionDepart;  // new Point(0,0);
			Point vitesse = composanteEnChemin.vitesse;

			if (vitesse.x == 0 && vitesse.y == 0)
				vitesse = obtenirVitesseTransport(composanteEnChemin); // new Point(1,1);

			// V�rifier si la composante � atteint sa destination
			if (isComposanteAtDestination(composanteEnChemin)){
				listeComposanteOnWireARetirer.add(composanteEnChemin);
				return;
			}

			positionDepart.translate(vitesse.x, vitesse.y);
			ImageIcon imageIcon = new ImageIcon(composanteEnChemin.composante.cheminICone);
			imageIcon.paintIcon(this, g, positionDepart.x, positionDepart.y);

			composanteEnChemin.positionDepart = positionDepart;

			listeComposanteOnWire.set(listeComposanteOnWire.indexOf(composanteEnChemin), composanteEnChemin);
		}));

		// Ici on retirer les composantes arriver � destination
		listeComposanteOnWireARetirer.forEach(composanteEnChemin -> {
			retirerComposanteOnWire(composanteEnChemin);
		});
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
		if (_composanteEnChemin.positionDepart != null && _composanteEnChemin.positionArrive != null) {
			if (_composanteEnChemin.positionDepart.x == _composanteEnChemin.positionArrive.x)
				vitesseDeplacementX = 0;
			else if (_composanteEnChemin.positionDepart.x > _composanteEnChemin.positionArrive.x)
				vitesseDeplacementX = -1;
			else if (_composanteEnChemin.positionDepart.x < _composanteEnChemin.positionArrive.x)
				vitesseDeplacementX = 1;

			if (_composanteEnChemin.positionDepart.y == _composanteEnChemin.positionArrive.y)
				vitesseDeplacementY = 0;
			else if (_composanteEnChemin.positionDepart.y > _composanteEnChemin.positionArrive.y)
				vitesseDeplacementY = -1;
			else if (_composanteEnChemin.positionDepart.y < _composanteEnChemin.positionArrive.y)
				vitesseDeplacementY = 1;

		}

		return new Point(vitesseDeplacementX, vitesseDeplacementY);
	}

	private static boolean isComposanteAtDestination(ComposanteEnChemin _composanteEnChemin){
		if (_composanteEnChemin.positionDepart.x == _composanteEnChemin.positionArrive.x
				&& _composanteEnChemin.positionDepart.y == _composanteEnChemin.positionArrive.y){
			return true;
		}
		return  false;
	}

	private void retirerComposanteOnWire(ComposanteEnChemin _composanteEnChemin){
		if (listeComposanteOnWire != null){
			Noeud noeudArrive = listeNoeud.get(_composanteEnChemin.obtenirNoeudArrive().id);

			noeudArrive.ajouterComposanteEnInventaire(new Composante(_composanteEnChemin.composante.nom));

			listeComposanteOnWire.remove(_composanteEnChemin);
		}
	}
}