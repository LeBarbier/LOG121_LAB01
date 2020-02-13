package simulation;

import objet.Model;
import objet.Noeud;
import org.xml.sax.SAXException;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;

public class PanneauPrincipal extends JPanel {

	private static final long serialVersionUID = 1L;
	// Variables temporaires de la demonstration:
	private Point position = new Point(0,0);
	private Point vitesse = new Point(1,1);
	private int taille = 32;
	String[][] listeImageNoeud;

	public PanneauPrincipal() throws IOException, SAXException, ParserConfigurationException {
		ArrayList<Noeud> listeNoeud = Model.obtenirConfigFichierSimulation();
		listeImageNoeud = new String[listeNoeud.size()][3];

		for (int i = 0; i < listeNoeud.size(); i++) {
			Noeud noeudActuelle = listeNoeud.get(i);
			listeImageNoeud[i] = new String[]{ noeudActuelle.cheminICone, Integer.toString(noeudActuelle.posX), Integer.toString(noeudActuelle.posY) };
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		// Ajouter une icone au noeud désiré.
		// On ajoute à la position le delta x et y de la vitesse
		position.translate(vitesse.x, vitesse.y);
		g.fillRect(position.x, position.y, taille, taille);

		for (String[] infoNoeud : listeImageNoeud) {
			ImageIcon imageIcon = new ImageIcon(infoNoeud[0]);
			imageIcon.paintIcon(this, g, Integer.parseInt(infoNoeud[1]), Integer.parseInt(infoNoeud[2]));
			// g.fillRect(point.x, point.y, taille, taille);
		}
	}

}