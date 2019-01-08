/**
 * Created by Silvan on 08.10.16.
 */
public class Piece implements Forme{

    Cellule[] cellules;
    private Plateau plateau;
    int centre;
    private PieceType pieceType;

    public Piece(Plateau plateau, PieceType pieceType){

        this.plateau = plateau;
        this.cellules = new Cellule[4];
        this.centre = plateau.centreX();
        this.pieceType = pieceType;

    }

    public Piece versLeBas(){

        //tester s'il x a une Piece au desous
        if(!this.plateau.accepter(this, Direction.BAS))
            return this;

        Piece p = new Piece(this.plateau, this.pieceType);

        // augmenter y
        for (int i = 0; i < p.cellules.length; i++)
            p.cellules[i] = new Cellule(cellules[i].y + 1, cellules[i].x, true);

        plateau.retirer(this);
        plateau.ajouter(p);

        return p;

    }

    public Piece versLaGauche(){

        //tester s'il x a une Piece a cote gauche
        if(!this.plateau.accepter(this, Direction.GAUCHE))
            return this;

        Piece p = new Piece(this.plateau, this.pieceType);

        // diminuer x
        for (int i = 0; i < p.cellules.length; i++)
            p.cellules[i] = new Cellule(cellules[i].y, cellules[i].x - 1, true);

        plateau.retirer(this);
        plateau.ajouter(p);

        return p;

    }

    public Piece versLaDroite(){

        //tester s'il x a une Piece a cote droite
        if(!this.plateau.accepter(this, Direction.DROITE))
            return this;

        Piece p = new Piece(this.plateau, this.pieceType);

        // augmenter x
        for (int i = 0; i < p.cellules.length; i++)
            p.cellules[i] = new Cellule(cellules[i].y, cellules[i].x + 1, true);

        plateau.retirer(this);
        plateau.ajouter(p);

        return p;

    }

    public Piece tourner(){

        switch (pieceType){

            case I:
                return tournerI();

            case O:
                return this;

            default:

                Piece p = new Piece(this.plateau, this.pieceType);

                plateau.retirer(this);

                try {

                    p.cellules[0] = tourne(cellules[0], cellules[0]);
                    p.cellules[1] = tourne(cellules[1], cellules[0]);
                    p.cellules[2] = tourne(cellules[2], cellules[0]);
                    p.cellules[3] = tourne(cellules[3], cellules[0]);

                    if (plateau.accepter(p)) {
                        p.plateau.ajouter(p);
                        return p;
                    }
                    else {
                        this.plateau.ajouter(this);
                        return this;
                    }

                }catch (Exception e){this.plateau.ajouter(this); return this;}

        }

    }

    private Piece tournerI() {

        Piece p = new Piece(this.plateau, this.pieceType);

        plateau.retirer(this);

        try {

            if (cellules[0].x - cellules[1].x == 0) {

                p.cellules[0] = new Cellule(cellules[0].y, cellules[0].x, true);
                p.cellules[1] = new Cellule(cellules[1].y + 1, cellules[1].x - 1, true);
                p.cellules[2] = new Cellule(cellules[2].y + 2, cellules[2].x - 2, true);
                p.cellules[3] = new Cellule(cellules[3].y - 1, cellules[3].x + 1, true);

            }
            else {

                p.cellules[0] = new Cellule(cellules[0].y, cellules[0].x, true);
                p.cellules[1] = new Cellule(cellules[1].y - 1, cellules[1].x + 1, true);
                p.cellules[2] = new Cellule(cellules[2].y - 2, cellules[2].x + 2, true);
                p.cellules[3] = new Cellule(cellules[3].y + 1, cellules[3].x - 1, true);

            }

            if (plateau.accepter(p)) {
                p.plateau.ajouter(p);
                return p;
            }

            else {
                this.plateau.ajouter(this);
                return this;
            }

        } catch (Exception e){this.plateau.ajouter(this); return this;}

    }

    private Cellule tourne(Cellule cellule, Cellule pivot){

        //tourne cellule autour centre

        int xDev = cellule.y - pivot.y; //xDeviation
        int yDev = cellule.x - pivot.x; //yDeviation

        if (xDev < 0 && yDev < 0)
            return new Cellule(cellule.y, cellule.x + 2, true);
        if (xDev < 0 && yDev > 0)
            return new Cellule(cellule.y + 2, cellule.x, true);
        if (xDev > 0 && yDev < 0)
            return new Cellule(cellule.y - 2, cellule.x, true);
        if (xDev > 0 && yDev > 0)
            return new Cellule(cellule.y, cellule.x - 2, true);
        if (xDev < 0)
            return new Cellule(cellule.y + 1, cellule.x + 1, true);
        if (xDev > 0)
            return new Cellule(cellule.y - 1, cellule.x - 1, true);
        if (yDev < 0)
            return new Cellule(cellule.y - 1, cellule.x + 1, true);
        if (yDev > 0)
            return new Cellule(cellule.y + 1, cellule.x - 1, true);

        return new Cellule(cellule.y, cellule.x, true);


    }

}

