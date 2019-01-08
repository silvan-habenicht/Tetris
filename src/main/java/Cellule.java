import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * Created by Silvan on 08.10.16.
 */
public class Cellule extends Pane{

    int y;
    int x;
    boolean estOccupe;
    private ImageView image = new ImageView();
    private Image gris = new Image("grey.png");
    private Image vide = new Image("black.jpg");

    public Cellule(int y, int x, boolean estOccupe){

        this.y = y;
        this.x = x;
        this.estOccupe = estOccupe;

        if(estOccupe)
            image.setImage(gris);
        else
            image.setImage(vide);

        image.setImage(vide);
        image.setFitHeight(Main.taille);
        image.setFitWidth(Main.taille);
        getChildren().add(image);

        setTranslateX(y * Main.taille);
        setTranslateY(x * Main.taille);

    }

    void retirer(){
        //faire this invisible
        this.estOccupe = false;
        image.setImage(vide);

    }

    void ajouter(){
        //faire this visible
        this.estOccupe = true;
        image.setImage(gris);

    }

}

