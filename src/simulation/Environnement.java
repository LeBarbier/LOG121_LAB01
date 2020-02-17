package simulation;

import javax.swing.SwingWorker;
import java.util.Observer;

public class Environnement extends SwingWorker<Object, String> {
	private boolean actif = true;
	private static final int DELAI = 100;
	public static Horloge horloge;

	public Environnement(){
		horloge = new Horloge();
	}

	@Override
	protected Object doInBackground() throws Exception {

		while(actif) {
			Thread.sleep(DELAI);

			firePropertyChange("TEST", null, "Ceci  est un test");
			horloge.tourPassee();

		}
		return null;
	}

	public void ajouterObservateur(Observer observer){
		horloge.ajouterObservateur(observer);
	}
}