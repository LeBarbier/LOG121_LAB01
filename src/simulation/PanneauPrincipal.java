package simulation;

import objet.Model;
import objet.Noeud;
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
	// Variables temporaires de la demonstration:
	private Point position = new Point(0,0);
	private Point vitesse = new Point(1,1);
	private int taille = 32;
	String[][] listeImageNoeud;
	HashMap<Integer, Noeud> listeNoeud;
	ArrayList<Line2D> arrayListLigne;

	public PanneauPrincipal() throws IOException, SAXException, ParserConfigurationException {
		listeNoeud = Model.obtenirConfigFichierSimulation();
		arrayListLigne = new ArrayList<>();
		listeImageNoeud = new String[listeNoeud.size()][3];
	}

	@Override
	public void paint(Graphics g) {
		String[][] listeChemins = Model.obtenirDonneeSimulationChemin();

		super.paint(g);
		// Ajouter une icone au noeud désiré.
		// On ajoute à la position le delta x et y de la vitesse
		position.translate(vitesse.x, vitesse.y);
		g.fillRect(position.x, position.y, taille, taille);

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
	}
}