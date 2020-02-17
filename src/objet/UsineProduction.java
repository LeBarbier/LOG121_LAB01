package objet;

import simulation.PanneauPrincipal;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

// Usine-mati�re dans le fichier de configuration
public class UsineProduction extends Noeud{
    Composante composanteSortie;
    int dureeProduction;

    public UsineProduction(int _id, Composante _composanteSortie, int _dureeProduction, String _cheminIcone, int _posX, int _posY){
        id = _id;
        cheminICone = _cheminIcone;
        composanteSortie = _composanteSortie;
        dureeProduction = _dureeProduction;
        posX = _posX;
        posY = _posY;
    }

    @Override
    public void update(Observable o, Object arg) {
        // Si vrai, c'est que le nombre de tour est divisible par la dur�e de production, donc un tour est pass�e
        if (((Integer.parseInt(arg.toString()) % dureeProduction) == 0) && (Integer.parseInt(arg.toString()) != 0)) {
            assemblerPiece();
        }
    }

    /**
     * Permet d'assembler une pi�ce de l'usine en retirant de l'inventaire les composantes n�cessaires
     */
    private void assemblerPiece(){
        PanneauPrincipal.mettreComposanteOnWire(composanteSortie, new Point[]{ new Point(posX, posY), new Point() });
    }
}
