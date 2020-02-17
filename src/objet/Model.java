package objet;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class Model {
    static DocumentBuilderFactory dbf;
    static DocumentBuilder db;
    static Document doc;
    static String[][] matriceDonneeSimulation;

    public static HashMap<Integer, Noeud> obtenirConfigFichierSimulation() throws IOException, SAXException, ParserConfigurationException {
        HashMap<Integer, Noeud> listeNoeudSimulation = new HashMap<>();
        initialiserDocumentReader();
        matriceDonneeSimulation = obtenirDonneeSimulationUsine();

        // Parcourir chacune des usines dans la balise simulation
        //  C'est cette balise qui détermine quelle composante sont dans la simulation
        //  et donc le nombre de chacun des noeuds que l'on doit générer.
        for (int i = 0; i < matriceDonneeSimulation.length; i++) {
            if (matriceDonneeSimulation[i][0] != null){
                Noeud noeud = obtenirDonneeMetadata(matriceDonneeSimulation[i]);
                listeNoeudSimulation.put(noeud.id, noeud);
            }
        }

         return listeNoeudSimulation;
    }

    public static void initialiserDocumentReader() throws ParserConfigurationException, IOException, SAXException {
        // Accès à la racine du document de configuration
        dbf = DocumentBuilderFactory.newInstance();
        db = dbf.newDocumentBuilder();
        doc = db.parse(new File("src/" + (Paths.get("ressources/configuration.xml"))));
        doc.getDocumentElement().normalize();
    }

    public static Noeud obtenirDonneeMetadata(String[] _tableDonneeSimulation) {
        //Récupération de l'ensemble des éléments nommées "USINE" du fichier de configuration
        NodeList listeNodeUsine = doc.getDocumentElement().getElementsByTagName("metadonnees").item(0).getChildNodes();
        String typeUsineRecherchee = _tableDonneeSimulation[0];
        String cheminIcone = "";
        String typeEntreposage = "";
        int limiteEntreposage = 0;
        int intervalProduction = 0;

        try {
            // Parcourir listeNodeUsine pour parcourir toutes les nodes <usine />
            for (int i = 0; i < listeNodeUsine.getLength(); i++) {
                Node nodeActuelle = listeNodeUsine.item(i); // Une balise USINE
                NodeList enfantsNodeActuelle = nodeActuelle.getChildNodes();

                if (nodeActuelle.getAttributes() != null){
                    String typeUsineActuelle = nodeActuelle.getAttributes().getNamedItem("type").getNodeValue();

                    if (typeUsineRecherchee.equalsIgnoreCase("entrepot") || typeUsineRecherchee.equalsIgnoreCase("usine-matiere")) {
                        if (typeUsineActuelle.equalsIgnoreCase(typeUsineRecherchee) && typeUsineActuelle.equalsIgnoreCase("entrepot")) {
                            for (int j = 0; j < enfantsNodeActuelle.getLength(); j++) {
                                Node enfantActuelle = enfantsNodeActuelle.item(j);

                                if (enfantActuelle.getNodeName().equalsIgnoreCase("icones")){
                                    cheminIcone = enfantActuelle.getChildNodes().item(1).getAttributes().getNamedItem("path").getNodeValue();

                                } else if (enfantActuelle.getNodeName().equalsIgnoreCase("entree")){
                                    limiteEntreposage = Integer.parseInt(enfantActuelle.getAttributes().getNamedItem("capacite").getNodeValue());
                                    typeEntreposage = enfantActuelle.getAttributes().getNamedItem("type").getNodeValue();
                                }
                            }
                            return new Entrepot(Integer.parseInt(_tableDonneeSimulation[1]),
                                    typeEntreposage,
                                    limiteEntreposage,
                                    cheminIcone,
                                    Integer.parseInt(_tableDonneeSimulation[2]),
                                    Integer.parseInt(_tableDonneeSimulation[3]));

                        } else if (typeUsineActuelle.equalsIgnoreCase(typeUsineRecherchee) && typeUsineActuelle.equalsIgnoreCase("usine-matiere")) {
                            Composante composanteSortie = new Composante("");

                            for (int j = 0; j < enfantsNodeActuelle.getLength(); j++) {
                                Node enfantActuelle = enfantsNodeActuelle.item(j);

                                if (enfantActuelle.getNodeName().equalsIgnoreCase("icones")){
                                    cheminIcone = enfantActuelle.getChildNodes().item(1).getAttributes().getNamedItem("path").getNodeValue();

                                } else if (enfantActuelle.getNodeName().equalsIgnoreCase("sortie")){
                                    
                                    composanteSortie = new Composante(enfantActuelle.getAttributes().getNamedItem("type").getNodeValue());


                                } else if (enfantActuelle.getNodeName().equalsIgnoreCase("interval-production")){
                                    intervalProduction = Integer.parseInt(enfantActuelle.getFirstChild().getNodeValue());
                                }
                            }
                            return new UsineProduction(Integer.parseInt(_tableDonneeSimulation[1]),
                                    composanteSortie,
                                    intervalProduction,
                                    cheminIcone,
                                    Integer.parseInt(_tableDonneeSimulation[2]),
                                    Integer.parseInt(_tableDonneeSimulation[3]));
                        }
                    } else { // Ici on traite tout les autres cas d'usine (Comme usine-assemblage)
                        if (typeUsineActuelle.equalsIgnoreCase(typeUsineRecherchee)) {
                            Map<Composante, Integer> mapComposanteEntree = new HashMap<>();
                            Composante composanteSortie = new Composante("");

                            for (int j = 0; j < enfantsNodeActuelle.getLength(); j++) {
                                Node enfantActuelle = enfantsNodeActuelle.item(j);

                                if (enfantActuelle.getNodeName().equalsIgnoreCase("icones")){
                                    cheminIcone = enfantActuelle.getChildNodes().item(1).getAttributes().getNamedItem("path").getNodeValue();

                                } else if (enfantActuelle.getNodeName().equalsIgnoreCase("entree")){
                                    int quantite = Integer.parseInt(enfantActuelle.getAttributes().getNamedItem("quantite").getNodeValue());
                                    String typeEntree = enfantActuelle.getAttributes().getNamedItem("type").getNodeValue();
                                    mapComposanteEntree.put(new Composante(typeEntree), quantite);

                                } else if (enfantActuelle.getNodeName().equalsIgnoreCase("sortie")){
                                    composanteSortie = new Composante(enfantActuelle.getAttributes().getNamedItem("type").getNodeValue());

                                } else if (enfantActuelle.getNodeName().equalsIgnoreCase("interval-production")){
                                    intervalProduction = Integer.parseInt(enfantActuelle.getFirstChild().getNodeValue());
                                }
                            }
                            return new UsineAssemblage(Integer.parseInt(_tableDonneeSimulation[1]),
                                    mapComposanteEntree,
                                    composanteSortie,
                                    intervalProduction,
                                    cheminIcone,
                                    Integer.parseInt(_tableDonneeSimulation[2]),
                                    Integer.parseInt(_tableDonneeSimulation[3]));
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String[][] obtenirDonneeSimulationUsine(){
        //Récupération de la node "SIMULATION"
        NodeList listeNodeUsineSimulation = doc.getElementsByTagName("simulation").item(0).getChildNodes();
        String[][] matriceDonneeSimulation = new String[listeNodeUsineSimulation.getLength()][4];

        for (int i = 0; i < listeNodeUsineSimulation.getLength(); i++) {
            if (listeNodeUsineSimulation.item(i).getAttributes() != null
                && listeNodeUsineSimulation.item(i).getNodeName() == "usine"){
                String[] listeDonneeSimulation = new String[4]; // [type][id][posX][posY]

                listeDonneeSimulation[0] = listeNodeUsineSimulation.item(i).getAttributes().getNamedItem("type").getNodeValue();
                listeDonneeSimulation[1] = listeNodeUsineSimulation.item(i).getAttributes().getNamedItem("id").getNodeValue();
                listeDonneeSimulation[2] = listeNodeUsineSimulation.item(i).getAttributes().getNamedItem("x").getNodeValue();
                listeDonneeSimulation[3] = listeNodeUsineSimulation.item(i).getAttributes().getNamedItem("y").getNodeValue();

                matriceDonneeSimulation[i] = listeDonneeSimulation;
            }
        }

        return matriceDonneeSimulation;
    }

    public static String[][] obtenirDonneeSimulationChemin(){
        int iterateurCheminNonNulle = 0;
        //Récupération de la liste de node "CHEMIN"
        NodeList listeNodeSimulationChemin = doc.getElementsByTagName("chemins").item(0).getChildNodes();
        // Initialisation de la valeur à retourner
        String[][] listeChemins = new String[doc.getElementsByTagName("chemin").getLength()][2]; //[{ "1.DE", "1.VERS" }, { "2.DE", "2.VERS" }]

        for (int i = 0; i < listeNodeSimulationChemin.getLength(); i++) {
            if (listeNodeSimulationChemin.item(i).getAttributes() != null
                    && listeNodeSimulationChemin.item(i).getNodeName() == "chemin"){
                String[] listeDonneeSimulation = new String[2]; // [de][vers]

                listeDonneeSimulation[0] = listeNodeSimulationChemin.item(i).getAttributes().getNamedItem("de").getNodeValue();
                listeDonneeSimulation[1] = listeNodeSimulationChemin.item(i).getAttributes().getNamedItem("vers").getNodeValue();

                listeChemins[iterateurCheminNonNulle] = listeDonneeSimulation;
                iterateurCheminNonNulle++;
            }
        }

        return listeChemins;
    }

}
