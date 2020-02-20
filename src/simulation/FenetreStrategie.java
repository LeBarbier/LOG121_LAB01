package simulation;

import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * Fen�tre de strat�gie pour la vente
 */
public class FenetreStrategie extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final String TITRE_FENETRE = "S�lectionnez votre strat�gie de vente";
	private static final Dimension DIMENSION = new Dimension(250, 150);
	private PanneauStrategie panneauStrategie = new PanneauStrategie();

	/**
	 * Initialisation de la fen�tre de saisie de type de vente
	 */
	public FenetreStrategie() {
		add(panneauStrategie);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle(TITRE_FENETRE);
		setSize(DIMENSION);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
	}
}
