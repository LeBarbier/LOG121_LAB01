package simulation;

import org.xml.sax.SAXException;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Affichage de la page principale de l'application
 * */
public class FenetrePrincipale extends JFrame implements PropertyChangeListener {

	private static final long serialVersionUID = 1L;
	private static final String TITRE_FENETRE = "Laboratoire 1 : LOG121 - Simulation de cha�ne de production";
	private static final Dimension DIMENSION = new Dimension(700, 700);

	/**
	 *
	 */
	public FenetrePrincipale() throws ParserConfigurationException, SAXException, IOException {
		PanneauPrincipal panneauPrincipal = new PanneauPrincipal();
		MenuFenetre menuFenetre = new MenuFenetre();
		add(panneauPrincipal);
		add(menuFenetre, BorderLayout.NORTH);

		setDefaultCloseOperation(EXIT_ON_CLOSE); // Faire en sorte que le X de la fen�tre ferme la fen�tre
		setTitle(TITRE_FENETRE);
		setSize(DIMENSION);

		setVisible(true); // Rendre la fen�tre visible
		setLocationRelativeTo(null); // Mettre la fen�tre au centre de l'�cran
		setResizable(false); // Emp�cher la redimension de la fen�tre
	}

	/**
	 *
	 * @param evt �v�nement qui a entrain� le changement de la propri�t�
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("TEST")) {
			repaint();
		}
	}
}
