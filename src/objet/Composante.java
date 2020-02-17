package objet;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Définit les composantes utilisées pour en concevoir d'autre, tel que métal ou ailes
 */
public class Composante {
    public String cheminICone;
    public String nom;

    /**
     * Constructeur d'un composante
     * @param _nom Nom de la composante, l'icone sera déduit par ce nom
     */
    public Composante(String _nom){
        nom = _nom;
        cheminICone = obtenirCheminIconeComposante(_nom);
    }

    /**
     * Obtenir le chemin de l'icône de la composante à partir du nom de celle-ci
     * @param _nomComposante Nom de la composante dont on désire obtenir le chemin de l'icône
     * @return Retourne le chemin absolue de l'icône de la composante désirée
     */
    private static String obtenirCheminIconeComposante(String _nomComposante){
        Path cheminIcone = Paths.get("src/ressources/"+ _nomComposante + ".png");
        return cheminIcone.normalize().toAbsolutePath().toString();
    }
}
