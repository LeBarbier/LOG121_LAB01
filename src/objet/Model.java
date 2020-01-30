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
import java.nio.file.Paths;
import java.util.ArrayList;

public class Model {
    public static ArrayList<Noeud> obtenirListeNoeud() throws ParserConfigurationException, IOException, SAXException {
        ArrayList<Noeud> listeNoeudRetourne = new ArrayList<>();

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new File("src/" + (Paths.get("ressources/configuration.xml"))));
        // Accès à la racine du document
        doc.getDocumentElement().normalize();

        //Récupération de l'ensemble des éléments nommées "USINE"
        NodeList listeNodeUsine = doc.getElementsByTagName("usine");

        try {
            // Parcourir listeNodeUsine pour parcourir toutes les nodes <usine />
            for (int i = 0; i < listeNodeUsine.getLength(); i++) {
                Node nodeUsine = listeNodeUsine.item(i);

                // Valeur du type de l'usine -> attribuer au nom
                String stringTypeUsine = nodeUsine.getAttributes().getNamedItem("type").getNodeValue();

                // Vérifier si l'élément avec un tag Usine trouvé est un Entrepot
                if (stringTypeUsine == "entrepot"){
                    listeNoeudRetourne.add(
                          new Entrepot(Integer.parseInt(nodeUsine.getFirstChild().getNodeValue()),
                                        nodeUsine.getAttributes().getNamedItem("path").getNodeValue()));
                } else if (stringTypeUsine == "usine-assemblage"){
                    NodeList listeCheminIcone = nodeUsine.getChildNodes();
                    listeNoeudRetourne.add(
                            new UsineAssemblage(new Composante(nodeUsine.getAttributes().getNamedItem("type").getNodeValue()),
                                                Integer.parseInt(nodeUsine.getFirstChild().getNodeValue()),
                                                listeCheminIcone.item(1).getAttributes().getNamedItem("path").getNodeValue()));
                } else {
                    // Si ce n'est ni un entrepot, ni une UsineAssemblage(), alors c'est forcement une UsineProduction()
                    NodeList listeInformationsUsine = nodeUsine.getChildNodes();
                    Composante[] listeComposanteEntree = new Composante[0];
                    Composante composanteSortie = null;
                    int iterateurComposanteEntree = 0;
                    int intervalProduction = 0;
                    String cheminIcone = "";

                    for (int j = 0; j < listeInformationsUsine.getLength(); j++) {
                        Node noeudInformationUsine = listeInformationsUsine.item(j);
                        switch (listeInformationsUsine.item(j).getNodeName()){
                            case ("icones"):
                                NodeList listeCheminIcone = listeInformationsUsine.item(j).getChildNodes();
                                cheminIcone = listeCheminIcone.item(1).getAttributes().getNamedItem("path").getNodeValue();
                                break;
                            case ("entree"):
                                // TODO: On doit préciser une quantité nécessaire à la composante d'entrée
                                listeComposanteEntree[iterateurComposanteEntree] = new Composante(listeInformationsUsine.item(j).getAttributes().getNamedItem("type").getNodeValue());
                                iterateurComposanteEntree++;
                                break;
                            case("sortie"):
                                composanteSortie = new Composante(listeInformationsUsine.item(j).getAttributes().getNamedItem("type").getNodeValue());
                                break;
                            case ("interval-production"):
                                intervalProduction = Integer.parseInt(listeInformationsUsine.item(j).getFirstChild().getNodeValue());
                                break;
                        }
                    }

                    listeNoeudRetourne.add(new UsineProduction(listeComposanteEntree,
                                        composanteSortie,
                                        intervalProduction,
                                        cheminIcone));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listeNoeudRetourne;
    }
}
