import java.util.Observable;
import java.util.Observer;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MarioGameView extends Application implements Observer{
	private GameModel gameModel= new GameModel();
	private GameController gameController = new GameController(gameModel);
//	SpriteAnimation coinAnimation = null;
	Canvas canvasForMario = gameModel.getCanvasForMario();
	GraphicsContext gcForMario = gameModel.getGcForMario();	

    public static void main(String[] args) {
        launch(args);
    }
    
    
    
    
    public void start(Stage primaryStage) {
    	gameModel.start();
    	gameModel.addObserver(this);
        primaryStage.setTitle("Mario Game");
        
        
        
        Group root = new Group(); 
        root.getChildren().add(canvasForMario);
        Scene scene = new Scene(root);

        
        
        
        /*gameController.getCoins().add(new Coin(new Image("file:blocks.png"), 3, 3, 385, 16, 15, 16, 1000, 480));
        coinAnimation = new SpriteAnimation(gameController.getCoins().get(0).getImage(),
                Duration.millis(1000),
                gameController.getCoins().get(0).getCount(), gameController.getCoins().get(0).getCol(),
                gameController.getCoins().get(0).getOffset_x(), gameController.getCoins().get(0).getOffset_y(),
                gameController.getCoins().get(0).getWidth(), gameController.getCoins().get(0).getHeight(), 
                gameController.getCoins().get(0).getX(), gameController.getCoins().get(0).getY(), gcForStuff, 1, false);
        coinAnimation.setCycleCount(Animation.INDEFINITE);
        coinAnimation.play();*/
        
        
        scene.setOnKeyPressed(event -> {
        	if (event.getCode().toString().equals("D")) {
        		gameController.setStart(true);
        		gameController.getMario().setSpeed(2);
        		gameController.getMario().setRight(true);
        		gameController.getMario().setRightRelease(-1);
        		
        	}
        	else if (event.getCode().toString().equals("A")) {
        		gameController.setStart(true);
        		gameController.getMario().setSpeed(-2);
        		gameController.getMario().setLeft(true);
        		gameController.getMario().setLeftRelease(-1);
        	}
        	else if (event.getCode().toString().equals("W")) {
        		gameController.setStart(true);
        		gameController.getMario().setJump(true);
        	}
        });
        
        scene.setOnKeyReleased(event -> {
        	if (gameController.getMario().getMarioAnimation() != null) {
        		if (event.getCode().toString().equals("D")) {
        			gameController.getMario().setRight(false);
	        		gameController.getMario().setSpeed(0);
	        		gameController.getMario().setRightRelease(1);
        		}
        		
        		if (event.getCode().toString().equals("A")) {
        			gameController.getMario().setLeft(false);
	        		gameController.getMario().setSpeed(0);
	        		gameController.getMario().setLeftRelease(1);
        		}
        	}
        });
        
        initContent(); 
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    
    
    private void initContent() {
    	 gcForMario.drawImage(gameController.getBackground().getImage(), // the image to be drawn or null.
    			 gameController.getBackground().getOffset_x(), // the source rectangle's X coordinate position.
    			 gameController.getBackground().getOffset_y(), // the source rectangle's Y coordinate position.
    			 gameController.getBackground().getWidth(), // the source rectangle's width.
    			 gameController.getBackground().getHeight(), // the source rectangle's height.
    			 gameController.getBackground().getX(), // the destination rectangle's X coordinate position.
    			 gameController.getBackground().getY(), // the destination rectangle's Y coordinate position.
    			 gameController.getBackground().getWidth(), // the destination rectangle's width.
    			 gameController.getBackground().getHeight()); // the destination rectangle's height. 
    	 
    	
    	 gcForMario.drawImage(gameController.getMario().getImage(), // the image to be drawn or null.
         		gameController.getMario().getOffset_x(), // the source rectangle's X coordinate position.
         		gameController.getMario().getOffset_y(), // the source rectangle's Y coordinate position.
         		gameController.getMario().getWidth(), // the source rectangle's width.
         		gameController.getMario().getHeight(), // the source rectangle's height.
         		gameController.getMario().getX(), // the destination rectangle's X coordinate position.
         		gameController.getMario().getY(), // the destination rectangle's Y coordinate position.
         		gameController.getMario().getWidth(), // the destination rectangle's width.
         		gameController.getMario().getWidth()); // the destination rectangle's height. 
    	     	    	 
    	for (int i = 0; i < LevelData.LEVEL1.length; i++) {
    		String line = LevelData.LEVEL1[i];
    		for (int j = 0; j < line.length(); j++) {
    			switch (line.charAt(j)) {
    				case '0':
    					break;
    				case '1':
    					Brick brick = new Brick(gameController.getBlocks(), 0, 0, 40, 40, j*40, i*40);
    					gameController.getBricks().add(brick);
    					int index = gameController.getBricks().size() -1;
    					gcForMario.drawImage(gameController.getBricks().get(index).getImage(), // the image to be drawn or null.
    							gameController.getBricks().get(index).getOffset_x(), // the source rectangle's X coordinate position.
    							gameController.getBricks().get(index).getOffset_y(), // the source rectangle's Y coordinate position.
    							gameController.getBricks().get(index).getWidth(), // the source rectangle's width.
    							gameController.getBricks().get(index).getHeight(), // the source rectangle's height.
    							gameController.getBricks().get(index).getX(), // the destination rectangle's X coordinate position.
    							gameController.getBricks().get(index).getY(), // the destination rectangle's Y coordinate position.
    							gameController.getBricks().get(index).getWidth(), // the destination rectangle's width.
    							gameController.getBricks().get(index).getHeight()); // the destination rectangle's height. 
    					break;
    			}
    		}
    	}  
    }

	@Override
	public void update(Observable o, Object arg1) {
		GameModel model = (GameModel)o;
		gcForMario.clearRect(0, 0, 1000, 480);
		reDrawExceptionMario();
		reDrawMario();
	}
	
	private void reDrawMario() {
		gcForMario.drawImage(gameController.getMario().getImage(), // the image to be drawn or null.
				gameController.getMario().getOffset_x(), // the source rectangle's X coordinate position.
				gameController.getMario().getOffset_y(), // the source rectangle's Y coordinate position.
				gameController.getMario().getWidth(), // the source rectangle's width.
				gameController.getMario().getHeight(), // the source rectangle's height.
				gameController.getMario().getX(), // the destination rectangle's X coordinate position.
				gameController.getMario().getY(), // the destination rectangle's Y coordinate position.
				gameController.getMario().getWidth(), // the destination rectangle's width.
				gameController.getMario().getHeight()); // the destination rectangle's height. 
	}
	
	private void reDrawExceptionMario() {
		gcForMario.drawImage(gameController.getBackground().getImage(), // the image to be drawn or null.
   			 gameController.getBackground().getOffset_x(), // the source rectangle's X coordinate position.
   			 gameController.getBackground().getOffset_y(), // the source rectangle's Y coordinate position.
   			 gameController.getBackground().getWidth(), // the source rectangle's width.
   			 gameController.getBackground().getHeight(), // the source rectangle's height.
   			 gameController.getBackground().getX(), // the destination rectangle's X coordinate position.
   			 gameController.getBackground().getY(), // the destination rectangle's Y coordinate position.
   			 gameController.getBackground().getWidth(), // the destination rectangle's width.
   			 gameController.getBackground().getHeight()); // the destination rectangle's height. 
		
		
		for (int i = 0; i < gameController.getBricks().size(); i++) {
			gcForMario.drawImage(gameController.getBricks().get(i).getImage(), // the image to be drawn or null.
					gameController.getBricks().get(i).getOffset_x(), // the source rectangle's X coordinate position.
					gameController.getBricks().get(i).getOffset_y(), // the source rectangle's Y coordinate position.
					gameController.getBricks().get(i).getWidth(), // the source rectangle's width.
					gameController.getBricks().get(i).getHeight(), // the source rectangle's height.
					gameController.getBricks().get(i).getX(), // the destination rectangle's X coordinate position.
					gameController.getBricks().get(i).getY(), // the destination rectangle's Y coordinate position.
					gameController.getBricks().get(i).getWidth(), // the destination rectangle's width.
					gameController.getBricks().get(i).getHeight()); // the destination rectangle's height. 
		}
	}
}