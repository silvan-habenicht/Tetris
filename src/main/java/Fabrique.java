/**
 * Created by Silvan on 08.10.16.
 */
class Fabrique {

    private Plateau plateau;

    Fabrique(Plateau plateau){

        this.plateau = plateau;

    }

    Piece creerO(){return new PieceO(plateau);}

    Piece creerL(){return new PieceL(plateau);}

    Piece creerJ(){return new PieceJ(plateau);}

    Piece creerI(){return new PieceI(plateau);}

    Piece creerT(){return new PieceT(plateau);}

    Piece creerS(){return new PieceS(plateau);}

    Piece creerZ(){return new PieceZ(plateau);}

    Piece creerAleatoire(){

        int i = (int)(Math.random()*7); //entier alleatoire entre 0 et 6

        switch (i){

            case 0:
                return creerO();
            case 1:
                return creerL();
            case 2:
                return creerJ();
            case 3:
                return creerI();
            case 4:
                return creerT();
            case 5:
                return creerS();
            case 6:
                return creerZ();
            default:
                return creerT();

        }


    }

}

