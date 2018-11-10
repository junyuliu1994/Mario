import javafx.animation.Animation;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MarioGameView extends Application {

    private static final Image IMAGE = new Image("file:320px-The_Horse_in_Motion.jpg");
    private static final int COLUMNS  =   4;
    private static final int COUNT    =  10;
    private static final int OFFSET_X =  10;
    private static final int OFFSET_Y =  6;
    private static final int WIDTH    = 75;
    private static final int HEIGHT   = 50;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        primaryStage.setTitle("The Horse in Motion");
        
        Canvas canvas = new Canvas(1280, 720);
        
        GraphicsContext gc = canvas.getGraphicsContext2D();
        

        final ImageView imageView = new ImageView(IMAGE);
        gc.drawImage(imageView.getImage(), // the image to be drawn or null.
        		OFFSET_X, // the source rectangle's X coordinate position.
        		OFFSET_Y, // the source rectangle's Y coordinate position.
        		WIDTH, // the source rectangle's width.
        		HEIGHT, // the source rectangle's height.
        		0, // the destination rectangle's X coordinate position.
        		0, // the destination rectangle's Y coordinate position.
        		WIDTH, // the destination rectangle's width.
        		HEIGHT); // the destination rectangle's height. 
        
        Group root = new Group();
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);
        

        final Animation animation = new SpriteAnimation(
                imageView,
                Duration.millis(1000),
                COUNT, COLUMNS,
                OFFSET_X, OFFSET_Y,
                WIDTH, HEIGHT, gc
        );
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}