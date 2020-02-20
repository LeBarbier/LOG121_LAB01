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
 * Classe gérant le panneau d'affichage du choix de la stratégie de vente.
 */
public class PanneauStrategie extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	* Constructeur du panneau stratégie
	*/
	public PanneauStrategie() {
		ButtonGroup groupeBoutons = new ButtonGroup();
		JRadioButton strategieAleatoire = new JRadioButton("Vente aléatoire");
		JRadioButton strategieIntervalle = new JRadioButton("Vente à intervalle fixe");
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

			// Fermer la fenêtre du composant
			SwingUtilities.getWindowAncestor((Component) e.getSource()).dispose();
		});

		JButton boutonAnnuler = new JButton("Annuler");
		boutonAnnuler.addActionListener((ActionEvent e) -> {
			// Fermer la fenêtre du composant
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
	 * Retourne le bouton sélectionné dans un groupe de boutons.
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
