package simulation;

import objet.TourObservable;

import javax.swing.SwingWorker;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

// La classe environnement est un observable
public class Environnement extends SwingWorker<Object, String> {
	private boolean actif = true;
	public int tourPassee;
	private static final int DELAI = 100;
	private ArrayList<Observer> collectionObservateur;

	public Environnement() {
	}

	@Override
	protected Object doInBackground() throws Exception {
		tourPassee = 0;
		collectionObservateur = new ArrayList<>();

		while(actif) {
			Thread.sleep(DELAI);
			tourPassee++;
			firePropertyChange("TEST", null, "Ceci est un test");
		}
		return null;
	}

	public void ajouterObservateur(Observer o) {
		collectionObservateur.add(o);
	}

	public void supprimerObservateur(Observer o) {
		collectionObservateur.remove(o);
	}

	public void notifierObservateurs() {
		for (Observer observateur : collectionObservateur) {
			// observateur.update(tourPassee);
		}
	}

	// Permet de supprimer tout les observateurs actifs
	public void clearChanged() {
		collectionObservateur.clear();
	}

	public int countObservers() {
		return collectionObservateur.size();
	}
}