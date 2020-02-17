package objet;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Composante {
    public String cheminICone;
    public String nom;

    public Composante(String _nom){
        nom = _nom;
        cheminICone = obtenirCheminIconeComposante(_nom);
    }

    private static String obtenirCheminIconeComposante(String _nomComposante){
        Path cheminIcone = Paths.get("src/ressources/"+ _nomComposante + ".png");
        return cheminIcone.normalize().toAbsolutePath().toString();
    }
}
