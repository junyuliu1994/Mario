import javafx.animation.Animation;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
<<<<<<< HEAD
import javafx.scene.image.ImageView;
=======
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
>>>>>>> branch 'master' of https://github.com/CSC335Fall2018/csc335-teamproject-platformer-p7team-junyu-pengyu-feiran-zhenxiang.git
import javafx.stage.Stage;
import javafx.util.Duration;

<<<<<<< HEAD
public class MarioGameView extends Application {
=======
public class MarioGameView extends Application implements Observer{
	
	
	Image i = new Image("file:marioRight2Lvl0.png", false);
	Image backgroud = new Image("resources/background.png");
	Font font = Font.loadFont(getClass().getResourceAsStream("resources/font.ttf"),13);
>>>>>>> branch 'master' of https://github.com/CSC335Fall2018/csc335-teamproject-platformer-p7team-junyu-pengyu-feiran-zhenxiang.git

<<<<<<< HEAD
    private static final Image IMAGE = new Image("file:320px-The_Horse_in_Motion.jpg");
    private static final int COLUMNS  =   4;
    private static final int COUNT    =  10;
    private static final int OFFSET_X =  10;
    private static final int OFFSET_Y =  6;
    private static final int WIDTH    = 75;
    private static final int HEIGHT   = 50;

    public static void main(String[] args) {
=======

	public static void main(String[] args) {
>>>>>>> branch 'master' of https://github.com/CSC335Fall2018/csc335-teamproject-platformer-p7team-junyu-pengyu-feiran-zhenxiang.git
        launch(args);
    }

<<<<<<< HEAD
    public void start(Stage primaryStage) {
        primaryStage.setTitle("The Horse in Motion");
        
        Canvas canvas = new Canvas(1280, 720);
        
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
=======
    @Override
    public void start(Stage stage) {
    	Canvas canvas = new Canvas(856, 550);
    	GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.drawImage(backgroud, 0,0);

		// load main GUI
		Image title = new Image("resources/title.png");
		gc.drawImage(title,200,50);
		gc.setFill(Color.WHITE);
		gc.setFont(font);
		gc.fillText("\uD83C\uDD2F8102 Ninjigoku",445,285);
    	//gc.drawImage(i, 640, 360);

		font = Font.loadFont(getClass().getResourceAsStream("resources/font.ttf"),20);
		gc.setFont(font);
		gc.fillText("New Game",350, 350);
		gc.fillText("Load Game",348, 380);
		gc.fillText("Setting",350, 410);

    	Pane pane = new Pane();
    	pane.getChildren().add(canvas);
        Scene scene = new Scene(pane);
        scene.setOnKeyPressed(event -> {
        	 if (event.getCode() == KeyCode.RIGHT) {
        		 new AnimationTimer() {
        			 public void handle(long currentNanoTime) {
		        		i = new Image("file:marioRight3Lvl0.png", false);
		        		gc.clearRect(0, 0, 1280, 720); 
		        	    gc.drawImage(i, 640, 360);
		        	            	    
		        	    i = new Image("file:marioRight0Lvl0.png", false);
		        		gc.clearRect(0, 0, 1280, 720); 
		        	    gc.drawImage(i, 640, 360);
		        	    
		        	    i = new Image("file:marioRight1Lvl0.png", false);
		        		gc.clearRect(0, 0, 1280, 720); 
		        	    gc.drawImage(i, 640, 360);
		        	    System.out.println("QWE");
        			 }
        		 }.start();
>>>>>>> branch 'master' of https://github.com/CSC335Fall2018/csc335-teamproject-platformer-p7team-junyu-pengyu-feiran-zhenxiang.git

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