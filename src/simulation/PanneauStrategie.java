package simulation;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

/**
 * Classe g�rant le panneau d'affichage du choix de la strat�gie de vente.
 */
public class PanneauStrategie extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	* Constructeur du panneau strat�gie
	*/
	public PanneauStrategie() {
		ButtonGroup groupeBoutons = new ButtonGroup();
		JRadioButton strategieAleatoire = new JRadioButton("Vente al�atoire");
		JRadioButton strategieIntervalle = new JRadioButton("Vente � intervalle fixe");
		JButton boutonConfirmer = new JButton("Confirmer");

		if (Horloge.isVenteAleatoire()){
			strategieAleatoire.setSelected(true);
			strategieIntervalle.setSelected(false);
		} else {
			strategieAleatoire.setSelected(false);
			strategieIntervalle.setSelected(true);
		}

		boutonConfirmer.addActionListener((ActionEvent e) -> {
			if (strategieAleatoire.isSelected())
				Horloge.setVenteAleatoire(true);
			else if (strategieIntervalle.isSelected())
				Horloge.setVenteAleatoire(false);

			// Fermer la fen�tre du composant
			SwingUtilities.getWindowAncestor((Component) e.getSource()).dispose();
		});

		JButton boutonAnnuler = new JButton("Annuler");
		boutonAnnuler.addActionListener((ActionEvent e) -> {
			// Fermer la fen�tre du composant
			SwingUtilities.getWindowAncestor((Component) e.getSource()).dispose();
		});

		groupeBoutons.add(strategieAleatoire);
		groupeBoutons.add(strategieIntervalle);
		add(strategieAleatoire);
		add(strategieIntervalle);
		add(boutonConfirmer);
		add(boutonAnnuler);
	}

	/**
	 * Retourne le bouton s�lectionn� dans un groupe de boutons.
	 * @param groupeBoutons
	 * @return
	 */
	public String getSelectedButtonText(ButtonGroup groupeBoutons) {
		for (Enumeration<AbstractButton> boutons = groupeBoutons.getElements(); boutons.hasMoreElements();) {
			AbstractButton bouton = boutons.nextElement();
			if (bouton.isSelected()) {
				return bouton.getText();
			}
		}

		return null;
	}

}
