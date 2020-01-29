package simulation;

import objet.Noeud;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class PanneauPrincipal extends JPanel {

	private static final long serialVersionUID = 1L;
	// Variables temporaires de la demonstration:
	private Point position = new Point(0,0);
	private Point vitesse = new Point(1,1);
	private int taille = 32;
	AffichageNoeud affichageNoeud;
	ArrayList<Point> listePoint;

	public PanneauPrincipal(){
		affichageNoeud = new AffichageNoeud();
		listePoint = new ArrayList<>();
		int iterateur = 0;

		for (Noeud noeud : affichageNoeud.listeNoeud) {
			listePoint.add(new Point(5+iterateur, 5+iterateur));
			iterateur += 10;
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		ImageIcon imageIcon = new ImageIcon();
		// On ajoute à la position le delta x et y de la vitesse
		position.translate(vitesse.x, vitesse.y);
		g.fillRect(position.x, position.y, taille, taille);

		for (Point point : listePoint) {
			g.fillRect(point.x, point.y, taille, taille);
		}
	}

}