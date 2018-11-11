import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
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
	private GameModel gameModel= new GameModel();
	private GameController gameController = new GameController(gameModel);
	SpriteAnimation marioAnimation = null;
	SpriteAnimation coinAnimation = null;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Mario Game");
        
        Canvas canvasForMario = new Canvas(1280, 720);
        Canvas canvasForCoin = new Canvas(1280, 720);
        
        GraphicsContext gcForMario = canvasForMario.getGraphicsContext2D();
        gcForMario.drawImage(gameController.getMario().getImage(), // the image to be drawn or null.
        		gameController.getMario().getOffset_x(), // the source rectangle's X coordinate position.
        		gameController.getMario().getOffset_y(), // the source rectangle's Y coordinate position.
        		gameController.getMario().getWidth(), // the source rectangle's width.
        		gameController.getMario().getHeight(), // the source rectangle's height.
        		gameController.getMario().getX(), // the destination rectangle's X coordinate position.
        		gameController.getMario().getY(), // the destination rectangle's Y coordinate position.
        		gameController.getMario().getWidth(), // the destination rectangle's width.
        		gameController.getMario().getWidth()); // the destination rectangle's height. 
        
        Group root = new Group(); 
        root.getChildren().add(canvasForMario);
        root.getChildren().add(canvasForCoin);
        Scene scene = new Scene(root);
        
        
        gameController.getCoins().add(new Coin(new Image("file:blocks.png"), 3, 3, 385, 16, 15, 16, 1000, 480));
        GraphicsContext gcForCoin = canvasForCoin.getGraphicsContext2D();
        coinAnimation = new SpriteAnimation(gameController.getCoins().get(0).getImage(),
                Duration.millis(1000),
                gameController.getCoins().get(0).getCount(), gameController.getCoins().get(0).getCol(),
                gameController.getCoins().get(0).getOffset_x(), gameController.getCoins().get(0).getOffset_y(),
                gameController.getCoins().get(0).getWidth(), gameController.getCoins().get(0).getHeight(), 
                gameController.getCoins().get(0).getX(), gameController.getCoins().get(0).getY(), gcForCoin, 1);
        coinAnimation.setCycleCount(Animation.INDEFINITE);
        coinAnimation.play();
        
        marioAnimation = new SpriteAnimation(
        		gameController.getMario().getImage(),
                Duration.millis(500),
                gameController.getMario().getCount(), gameModel.getMario().getCol(),
                gameController.getMario().getOffset_x(), gameModel.getMario().getOffset_y(),
                gameController.getMario().getWidth(), gameModel.getMario().getHeight(), 
                gameController.getMario().getX(), gameModel.getMario().getY(), gcForMario, 1
        );
        marioAnimation.setCycleCount(Animation.INDEFINITE);
        scene.setOnKeyPressed(event -> {
        	if (event.getCode().toString().equals("D")) {
        		gameController.getMario().setImage(new Image("file:mario.png"));
        		gameController.getMario().setOffset_x(80);
        		gameController.getMario().setOffset_y(32);
        		gameController.getMario().setX((int) (gameController.getMario().getX() + Mario.SPEED));
        		
        		marioAnimation.setImage(gameController.getMario().getImage());
        		marioAnimation.setOffsetX(gameController.getMario().getOffset_x());
        		marioAnimation.setOffsetY(gameController.getMario().getOffset_y());
        		marioAnimation.setCor_x(gameController.getMario().getX());
        		marioAnimation.setDirection(1);
        		marioAnimation.play();
        		
        	}
        	else if (event.getCode().toString().equals("A")) {
        		gameController.getMario().setImage(new Image("file:mario-ConvertImage.png"));
        		gameController.getMario().setOffset_x(320);
        		gameController.getMario().setOffset_y(32);
        		gameController.getMario().setX((int) (gameController.getMario().getX() - Mario.SPEED));
        		
        		marioAnimation.setImage(gameController.getMario().getImage());
        		marioAnimation.setOffsetX(gameController.getMario().getOffset_x());
        		marioAnimation.setOffsetY(gameController.getMario().getOffset_y());
        		marioAnimation.setCor_x(gameController.getMario().getX());
        		marioAnimation.setDirection(0);
        		marioAnimation.play();
        		        		
        	}
        });
        
        scene.setOnKeyReleased(event -> {
        	if (marioAnimation != null) {
        		gcForMario.clearRect(0,0,1280,720);
        		gcForMario.drawImage(gameModel.getMario().getImage(), // the image to be drawn or null.
        				gameController.getMario().getOffset_x(), // the source rectangle's X coordinate position.
        				gameController.getMario().getOffset_y(), // the source rectangle's Y coordinate position.
        				gameController.getMario().getWidth(), // the source rectangle's width.
        				gameController.getMario().getHeight(), // the source rectangle's height.
        				gameController.getMario().getX(), // the destination rectangle's X coordinate position.
        				gameController.getMario().getY(), // the destination rectangle's Y coordinate position.
        				gameController.getMario().getWidth(), // the destination rectangle's width.
        				gameController.getMario().getWidth()); // the destination rectangle's height. 
        		
        		marioAnimation.stop();
        	}
        });
        
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}