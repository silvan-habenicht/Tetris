import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Created by Silvan on 19.10.16.
 */

public class Main extends Application {

    static final int taille = 20; //pixels pour chaque Cellule
    private final int largeur = 10; //nombre des colonnes
    private final int hauteur = 20; //nombre des lignes

    private Plateau plateau = new Plateau(largeur, hauteur);
    private Fabrique fabrique = new Fabrique(plateau);
    private Piece actuel = fabrique.creerAleatoire();

    private boolean run = true; //mettre false pour fermer thread
    private int attendre = 1000; //vitesse au début

    private MediaPlayer mediaPlayer //charger musique de la fiche resources
            = new MediaPlayer(new Media(this.getClass()
            .getClassLoader().getResource("tetris.mp3").toExternalForm()));

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Scene scene = new Scene(plateau);

        primaryStage.setTitle("TETRIS");

        Thread thread = new Thread(() -> {

            passerMusique();

            while (run) {

                if (!plateau.accepter(actuel, Direction.BAS)) { //si actuel est par terre
                    plateau.testeEnlevement();                  //chercher nouveau lignes complets
                    actuel = fabrique.creerAleatoire();         //ajouter un nouveau Piece aleatoire
                }

                try {
                    Thread.sleep(attendre); //commence a 1000 ms
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                Platform.runLater(() -> {
                    actuel = actuel.versLeBas(); //mettre le Piece actuel continuant vers le bas
                });

                if(attendre > 200)
                    attendre -= 2; //augmenter la vitesse avec chaque repetition
                if(attendre == 202){
                    mediaPlayer.stop();
                    mediaPlayer = new MediaPlayer(
                            new Media(this.getClass().getClassLoader().getResource("bach.mp3").toExternalForm()));
                    passerMusique();
                }

            }

        });
        thread.start();


        //Commandes par clavier

        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.UP) {
                actuel = actuel.tourner();
            } else if (e.getCode() == KeyCode.LEFT) {
                actuel = actuel.versLaGauche();
            } else if (e.getCode() == KeyCode.RIGHT) {
                actuel = actuel.versLaDroite();
            } else if (e.getCode() == KeyCode.DOWN) {
                actuel = actuel.versLeBas();
            }

        });

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        primaryStage.setOnCloseRequest(we -> run = false); //fermer tread a la fin

    }

    private void passerMusique(){

        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));
        mediaPlayer.play();
        mediaPlayer.setVolume(0.5);


    }

}

