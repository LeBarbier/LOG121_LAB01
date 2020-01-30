package simulation;

import objet.Composante;
import objet.Model;
import objet.Noeud;
import org.xml.sax.SAXException;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;

public class PanneauPrincipal extends JPanel {

	private static final long serialVersionUID = 1L;
	// Variables temporaires de la demonstration:
	private Point position = new Point(0,0);
	private Point vitesse = new Point(1,1);
	private int taille = 32;
	ArrayList<Point> listePoint;

	public PanneauPrincipal() throws IOException, SAXException, ParserConfigurationException {
		ArrayList<Noeud> listeNoeud = Model.obtenirListeNoeud();

		listePoint = new ArrayList<>();
		int iterateur = 0;

		for (Noeud noeud : listeNoeud) {
			listePoint.add(new Point(5+iterateur, 5+iterateur));
			iterateur += 10;
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		// Ajouter une icone au noeud désiré.
		// ImageIcon imageIcon = new ImageIcon();
		// On ajoute à la position le delta x et y de la vitesse
		position.translate(vitesse.x, vitesse.y);
		g.fillRect(position.x, position.y, taille, taille);

		for (Point point : listePoint) {
			g.fillRect(point.x, point.y, taille, taille);
		}
	}

}