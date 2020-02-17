package objet;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * D�finit les composantes utilis�es pour en concevoir d'autre, tel que m�tal ou ailes
 */
public class Composante {
    public String cheminICone;
    public String nom;

    /**
     * Constructeur d'un composante
     * @param _nom Nom de la composante, l'icone sera d�duit par ce nom
     */
    public Composante(String _nom){
        nom = _nom;
        cheminICone = obtenirCheminIconeComposante(_nom);
    }

    /**
     * Obtenir le chemin de l'ic�ne de la composante � partir du nom de celle-ci
     * @param _nomComposante Nom de la composante dont on d�sire obtenir le chemin de l'ic�ne
     * @return Retourne le chemin absolue de l'ic�ne de la composante d�sir�e
     */
    private static String obtenirCheminIconeComposante(String _nomComposante){
        Path cheminIcone = Paths.get("src/ressources/"+ _nomComposante + ".png");
        return cheminIcone.normalize().toAbsolutePath().toString();
    }
}
