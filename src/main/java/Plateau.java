import javafx.scene.layout.GridPane;

/**
 * Created by Silvan on 08.10.16.
 */
public class Plateau extends GridPane {

    private Cellule[][] grille;
    private int xTaille;
    private int yTaille;

    Plateau(int xTaille, int yTaille){

        this.grille = new Cellule[xTaille][yTaille];
        this.xTaille = xTaille;
        this.yTaille = yTaille;

        //remplir le Plateau avec Cellules vides
        for (int i = 0; i < xTaille; i++)
            for (int j = 0; j < yTaille; j++) {
                this.getChildren().add(grille[i][j] = new Cellule(i, j, false));
            }

        this.setPrefSize(xTaille * Main.taille, yTaille * Main.taille);

    }

    int centreX(){

        //rends le centre horizontal du Plateau

        return xTaille/2;

    }

    public boolean accepter(Piece p){

        // teste si this peut recevoir p

        for(Cellule c : p.cellules)
            if(grille[c.x][c.y].estOccupe)
                return false;
        return true;
    }

    boolean accepter(Piece p, Direction direction){

        // teste si this peut recevoir p apres deplacement

        try {
            switch (direction) {

                case BAS:
                    //pour chaque Cellule dans ce Piece
                    for (Cellule c : p.cellules) {
                        //teste si la Cellule suivante (x+1) est deja occupe dans this
                        if (grille[c.x][c.y + 1].estOccupe) {
                            boolean valide = true;
                            for (Cellule ct : p.cellules) {
                                //teste si la Cellule occupe n'est pas de this
                                if (c.x == ct.x && c.y + 1 == ct.y)
                                    valide = false;
                            }
                            //return false s'il x a une Cellule occupe dessous
                            if (valide)
                                return false;
                        }
                    }
                    break;

                case GAUCHE:
                    for (Cellule c : p.cellules) {
                        if (grille[c.x - 1][c.y].estOccupe) {
                            boolean valide = true;
                            for (Cellule ct : p.cellules) {
                                if (c.y == ct.y && c.x - 1 == ct.x)
                                    valide = false;
                            }
                            if (valide)
                                return false;
                        }
                    }
                    break;

                case DROITE:
                    for (Cellule c : p.cellules) {
                        if (grille[c.x + 1][c.y].estOccupe) {
                            boolean valide = true;
                            for (Cellule ct : p.cellules) {
                                if (c.y == ct.y && c.x + 1 == ct.x)
                                    valide = false;
                            }
                            if (valide)
                                return false;
                        }
                    }
                    break;

                default:
                    return false;
            }
        }catch (Exception e){return false;} //en cas de deplacement au dehors du Plateau

        //sinon this peut recevoir p
        return true;

    }

    void retirer(Piece p){

        for(Cellule c : p.cellules)
            this.grille[c.x][c.y].retirer();

    }

    public void ajouter(Piece p){

        //copier les Cellules d'un Piece dans this

        for(Cellule c : p.cellules)
            this.grille[c.x][c.y].ajouter();

    }

    void testeEnlevement(){

        //teste si une ligne est complet, si oui, la retirer

        for(int j = yTaille-1; j >= 0; j--) {
            boolean plein = true;
            for (int i = xTaille - 1; i >= 0; i--)
                if(!grille[i][j].estOccupe)
                    plein = false;

            if(plein) {
                for (int i = 0; i < xTaille; i++)
                    grille[i][j].retirer();
                for(int k = j; k > 0; k--)
                    for (int i = 0; i < xTaille; i++) {
                        if(grille[i][k - 1].estOccupe)
                            grille[i][k].ajouter();
                        else
                            grille[i][k].retirer();

                    }
                j++;
            }
        }
    }

}

