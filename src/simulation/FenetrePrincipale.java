package simulation;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;

/**
 * Affichage de la page principale de l'application
 * */
public class FenetrePrincipale extends JFrame implements PropertyChangeListener {

	private static final long serialVersionUID = 1L;
	private static final String TITRE_FENETRE = "Laboratoire 1 : LOG121 - Simulation de chaîne de production";
	private static final Dimension DIMENSION = new Dimension(700, 700);

	/**
	 *
	 */
	public FenetrePrincipale() {
		PanneauPrincipal panneauPrincipal = new PanneauPrincipal();
		MenuFenetre menuFenetre = new MenuFenetre();
		add(panneauPrincipal);
		add(menuFenetre, BorderLayout.NORTH);

		setDefaultCloseOperation(EXIT_ON_CLOSE); // Faire en sorte que le X de la fenêtre ferme la fenêtre
		setTitle(TITRE_FENETRE);
		setSize(DIMENSION);

		setVisible(true); // Rendre la fenêtre visible
		setLocationRelativeTo(null); // Mettre la fenêtre au centre de l'écran
		setResizable(false); // Empêcher la redimension de la fenêtre
	}

	/**
	 *
	 * @param evt Évènement qui a entrainé le changement de la propriété
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("TEST")) {
			repaint();
			System.out.println(evt.getNewValue());
		}
	}
}
