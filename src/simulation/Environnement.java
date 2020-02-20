package simulation;

import javax.swing.SwingWorker;
import java.util.Observer;

/**
 * Objet qui g�re et calcule les "Tours" de l'application
 */
public class Environnement extends SwingWorker<Object, String> {
	private boolean actif = true;
	private static final int DELAI = 100;
	public static Horloge horloge;

	/**
	 * Constructeur de l'objet Environnement
	 */
	public Environnement(){
		horloge = new Horloge();
	}

	/**
	 * M�thode Override qui s'ex�cute en arri�re-plan, tant que l'application roule
	 * @return Retourne nulle car si on sort de la boucle "while", c'est que l'application est ferm�e
	 * @throws Exception
	 */
	@Override
	protected Object doInBackground() throws Exception {

		while(actif) {
			Thread.sleep(DELAI);
			firePropertyChange("TEST", null, "");
			horloge.tourPassee();
		}
		return null;
	}

	/**
	 * M�thode qui permet d'ajouter un observateur qui doit �tre notifier � chaque tour
	 * @param observer
	 */
	public void ajouterObservateur(Observer observer){
		horloge.ajouterObservateur(observer);
	}
}