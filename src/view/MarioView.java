package view;

import controller.SpriteAnimation;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Mario;

/**
 * @author Shawn Jin
 */
public class MarioView extends Application {
    private int tickPerFrame = 60;
    private Pane appRoot = new Pane();
    ;


    @Override
    public void start(Stage primaryStage) throws Exception {

//        AnimationTimer at = new AnimationTimer() {
//            @Override
//            public void handle(long now) {
//                for (int i = 0; i < tickPerFrame; i++) {
////                    tick();
//                    // this method is going recalculates those things and changes the state of the model.
//                }
//            }
//
//        };
//        at.start();


        primaryStage.setTitle("SuperMario");
//        primaryStage.getIcons().add(new Image("file:images/icon.png"));     // doesn't work
//        Image backGroundImage = new Image("file:images/background.png", false);
        Image mario = new Image("file:images/mario.png");
//        ImageView backGroundIV = new ImageView(backGroundImage);
        Rectangle2D viewPort = new Rectangle2D(300,300,200,200);
        ImageView marioIV = new ImageView(mario);
//        Rectangle2D
//        backGroundIV.backGroundIV
        marioIV.setViewport(viewPort);
//        backGroundIV.
//        Pane mario = new Mario();
        int count = 4, colums = 4;
        Animation animation = new SpriteAnimation(
                marioIV,
                Duration.millis(1000),
                count, colums,
                0, 0,
                16,32
        );
        animation.setCycleCount(100);
        animation.play();
//        appRoot.getChildren().addAll(backGroundIV, mario);

        Scene scene = new Scene(new Group(marioIV));
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
