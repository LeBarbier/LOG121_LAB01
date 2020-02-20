package simulation;

import objet.Model;
import objet.Noeud;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;

public class Simulation {
	public static HashMap<Integer, Noeud> listeNoeudSimulation;

	/**
	 * Cette classe représente l'application dans son ensemble.
	 */
	public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
		listeNoeudSimulation = Model.obtenirConfigFichierSimulation();
		Environnement environnement = new Environnement();
		FenetrePrincipale fenetre = new FenetrePrincipale();

		listeNoeudSimulation.forEach((id, noeud) ->{
			environnement.ajouterObservateur(noeud);
		});

		environnement.addPropertyChangeListener(fenetre);
		environnement.execute();
	}

	public static HashMap<Integer, Noeud> getListeNoeudSimulation(){ return listeNoeudSimulation;}
}
