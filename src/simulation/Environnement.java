package simulation;

import javax.swing.SwingWorker;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Environnement extends SwingWorker<Object, String> implements Observateur {
	private boolean actif = true;
	private static final int DELAI = 100;
	private ArrayList<Observer> collectionObservateur;
	
	@Override
	protected Object doInBackground() throws Exception {
		while(actif) {
			Thread.sleep(DELAI);
			/**
			 * C'est ici que vous aurez à faire la gestion de la notion de tour.
			 */
			firePropertyChange("TEST", null, "Ceci est un test");
		}
		return null;
	}

	@Override
	public void addObserver(Observer o) {
		collectionObservateur.add(o);
	}

	@Override
	public void deleteObserver(Observer o) {
		collectionObservateur.remove(o);
	}

	@Override
	public void notifyObservers() {

	}

	@Override
	public void notifyObservers(Object arg) {

	}

	@Override // Permet de supprimer tout les observateurs actifs
	public void deleteObservers() {
		collectionObservateur.clear();
	}

	@Override
	public void setChanged() {

	}

	@Override
	public void clearChanged() {

	}

	@Override
	public boolean hasChanged() {
		return false;
	}

	@Override
	public int countObservers() {
		return collectionObservateur.size();
	}
}