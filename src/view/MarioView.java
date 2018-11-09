package view;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Mario;

/**
 * @author Shawn Jin
 */
public class MarioView extends Application {
    private int tickPerFrame = 60;
    private Pane appRoot = new Pane();
    private Scene scene = new Scene(appRoot,1200,620);


    @Override
    public void start(Stage primaryStage) throws Exception {

        AnimationTimer at = new AnimationTimer() {
            @Override
            public void handle(long now) {
                for (int i = 0; i < tickPerFrame; i++) {
                    tick();
                }
            }
            at.start();
        }


        primaryStage.setTitle("SuperMario");
        primaryStage.getIcons().add(new Image("file:images/icon.png"));     // doesn't work
        Image backGroundImage = new Image("file:images/background.png");
        ImageView backGroundIV = new ImageView(backGroundImage);
        Pane mario = new Mario();

        appRoot.getChildren().addAll(backGroundIV, mario);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
