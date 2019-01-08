/**
 * Created by Silvan on 19.10.16.
 */
class PieceT extends Piece {

    PieceT(Plateau plateau){

        super(plateau, PieceType.T);
        cellules[0] = new Cellule(1, centre,   true);
        cellules[1] = new Cellule(0, centre,   true);
        cellules[2] = new Cellule(1, centre -1, true);
        cellules[3] = new Cellule(1, centre +1, true);
        if(plateau.accepter(this))
            plateau.ajouter(this);

    }

}

