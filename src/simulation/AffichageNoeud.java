package simulation;

import objet.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class AffichageNoeud {
    ArrayList<Noeud> listeNoeud;

    public AffichageNoeud(){
        listeNoeud = new ArrayList<>();
        creationToutNoeuds();
    }

    private void creationToutNoeuds(){
        Composante[] composantes = { new Composante("Ailes"), new Composante("Fuselage") };

        listeNoeud.add(
                new UsineAssemblage(
                        new Composante("Avion"),
                        10,
                        Paths.get("src/ressources/UA100%.png").toString()));

        listeNoeud.add(
                new UsineProduction(composantes,
                        new Composante("Avion"),
                        100,
                        Paths.get("src/ressources/UA100%.png").toString()));

        listeNoeud.add(
                new Entrepot(2,
                        Paths.get("src/ressources/UA100%.png").toString()));
    }
}
