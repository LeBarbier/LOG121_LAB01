package objet;

import javax.print.DocPrintJob;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Document;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Model {
    static DocumentBuilderFactory dbf;
    static DocumentBuilder db;
    static Document doc;
    static String[][] matriceDonneeSimulation;

    public static ArrayList<Noeud> obtenirConfigFichierSimulation() throws IOException, SAXException, ParserConfigurationException {
        ArrayList<Noeud> listeNoeudSimulation = new ArrayList<>();
        initialiserDocumentReader();
        matriceDonneeSimulation = obtenirDonneeSimulation();

        for (int i = 0; i < matriceDonneeSimulation.length; i++) {
            listeNoeudSimulation.add(obtenirDonneeMetadata(matriceDonneeSimulation[i]));
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
        //Récupération de l'ensemble des éléments nommées "USINE"
        NodeList listeNodeUsine = doc.getElementsByTagName("usine");
        String cheminIcone = "";
        String composanteSortie = "";
        int limiteEntreposage = 0;
        int dureeProduction = 0;
        int intervalProduction = 0;
        String typeUsine = _tableDonneeSimulation[0];


        try {
            // Parcourir listeNodeUsine pour parcourir toutes les nodes <usine />
            for (int i = 0; i < listeNodeUsine.getLength(); i++) {
                Node nodeActuelle = listeNodeUsine.item(i);
                if (i == 1)
                    cheminIcone = nodeActuelle.getFirstChild().getNextSibling().getFirstChild().getNextSibling().getAttributes().getNamedItem("path").getNodeValue();
                // listeCheminIcone.item(1).getAttributes().getNamedItem("path").getNodeValue();
                composanteSortie = nodeActuelle.getAttributes().getNamedItem("type").getNodeValue();

                if (nodeActuelle.getAttributes().getNamedItem("type").getNodeValue().equals("entrepot")) {
                    limiteEntreposage = Integer.parseInt(nodeActuelle.getFirstChild().getNodeValue());
                    composanteSortie = ""; // Un entrepot n'a aucune composante de sortie;
                } else if (nodeActuelle.getAttributes().getNamedItem("type").getNodeValue().equals("usine-assemblage")) {

                } else {
                    intervalProduction = Integer.parseInt(listeNodeUsine.item(1).getChildNodes().item(i).getFirstChild().getNodeValue());
                }
            }
            // Vérifier si l'élément avec un tag Usine trouvé est un Entrepot
            if (typeUsine == "entrepot"){
                return new Entrepot(Integer.parseInt(_tableDonneeSimulation[1]), limiteEntreposage,
                    cheminIcone, Integer.parseInt(_tableDonneeSimulation[2]), Integer.parseInt(_tableDonneeSimulation[3]));
            } else if (_tableDonneeSimulation[0].equals("usine-assemblage")){
                    return new UsineAssemblage(Integer.parseInt(_tableDonneeSimulation[1]),
                            new Composante(composanteSortie), dureeProduction, cheminIcone,
                            Integer.parseInt(_tableDonneeSimulation[2]), Integer.parseInt(_tableDonneeSimulation[3]));
            } else {
                // Si ce n'est ni un Entrepot(), ni une UsineAssemblage(), alors c'est forcement une UsineProduction()
                NodeList listeInformationsUsine = listeNodeUsine.item(1).getChildNodes();
                Composante[] listeComposanteEntree = new Composante[0];
                int iterateurComposanteEntree = 0;

                for (int j = 0; j < listeInformationsUsine.getLength(); j++) {
                    Node noeudInformationUsine = listeInformationsUsine.item(j);
                    switch (listeInformationsUsine.item(j).getNodeName()){
                        case ("entree"):
                            // TODO: On doit préciser une quantité nécessaire à la composante d'entrée
                            listeComposanteEntree[iterateurComposanteEntree] = new Composante(listeInformationsUsine.item(j).getAttributes().getNamedItem("type").getNodeValue());
                            iterateurComposanteEntree++;
                            break;
                    }
                }
                return new UsineProduction(Integer.parseInt(_tableDonneeSimulation[1]),
                        listeComposanteEntree,
                        new Composante(composanteSortie),
                        intervalProduction,
                        cheminIcone,
                        Integer.parseInt(_tableDonneeSimulation[2]),
                        Integer.parseInt(_tableDonneeSimulation[3]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String[][] obtenirDonneeSimulation(){
        String[] listeDonneeSimulation = new String[4]; // [type][id][posX][posY]
        //Récupération de la node "SIMULATION"
        NodeList listeNodeUsineSimulation = doc.getElementsByTagName("simulation").item(0).getChildNodes();
        String[][] matriceDonneeSimulation = new String[listeNodeUsineSimulation.getLength()][4];

        for (int i = 0; i < listeNodeUsineSimulation.getLength(); i++) {
            if (listeNodeUsineSimulation.item(i).getAttributes() != null
                && listeNodeUsineSimulation.item(i).getNodeName() == "usine"){
                NamedNodeMap nodeSelectMapAttr = listeNodeUsineSimulation.item(i).getAttributes();

                listeDonneeSimulation[0] = nodeSelectMapAttr.getNamedItem("type").getNodeValue();
                listeDonneeSimulation[1] = nodeSelectMapAttr.getNamedItem("id").getNodeValue();
                listeDonneeSimulation[2] = nodeSelectMapAttr.getNamedItem("x").getNodeValue();
                listeDonneeSimulation[3] = nodeSelectMapAttr.getNamedItem("y").getNodeValue();

                matriceDonneeSimulation[i] = listeDonneeSimulation;
            }
        }

        return matriceDonneeSimulation;
    }
}
