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
//    GraphicsContext gcForStuff = gameModel.getGCForStuff();
    private Duration coinDuration = Duration.millis(1000);

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


        
        scene.setOnKeyPressed(event -> {
            switch (event.getCode().toString()) {
                case "D":
                    gameController.setStart(true);
                    gameController.getMario().setSpeed(10);
                    gameController.getMario().setRight(true);
                    gameController.getMario().setRightRelease(-1);

                    break;
                case "A":
                    gameController.setStart(true);
                    gameController.getMario().setSpeed(-1);
                    gameController.getMario().setLeft(true);
                    gameController.getMario().setLeftRelease(-1);
                    break;
                case "W":
                    gameController.setStart(true);
                    gameController.getMario().setJump(true);
                    break;
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
    					Brick brick = new Brick(gameController.getBlocks(), 40, 40, j*40, i*40);
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
                    case '2':
                        Coin coin = new Coin(gameModel.getBlocks(),38,37,j*40,i*40);
//                        Coin coin = new Coin( 3, 3, 946, 40, 40,40,j*40,i*40);
//                        System.out.println("file:coin's location:"+j*40+" ,"+i*40);
                        gameController.getCoins().add(coin);
//
//                        gcForStuff.drawImage(coin.getImage(),
//                                coin.getOffset_x(), coin.getOffset_y(),
//                                BLOCK_WIDTH, BLOCK_HEIGHT,
//                                coin.getX(),coin.getY(), BLOCK_WIDTH, BLOCK_HEIGHT
//                        );
                        int end = gameController.getCoins().size() -1;
                        SpriteAnimation coinAnimation2 = new SpriteAnimation(gameController.getCoins().get(end).getImage(),
                                Duration.millis(1000),
                                gameController.getCoins().get(end).getCount(), gameController.getCoins().get(end).getCol(),
                                gameController.getCoins().get(end).getOffset_x(), gameController.getCoins().get(end).getOffset_y(),
                                gameController.getCoins().get(end).getWidth(), gameController.getCoins().get(end).getHeight(),
                                gameController.getCoins().get(end).getX(), gameController.getCoins().get(end).getY(), gcForMario, 1, false);
                        coinAnimation2.setCycleCount(Animation.INDEFINITE);
                        coin.setAnimation(coinAnimation2);
                        System.out.println(gameController.getCoins().get(end).getX() +" and "+ gameController.getCoins().get(0).getY() );
                        coinAnimation2.play();

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

//		System.out.println("redraw coins");
		for (Coin coin : gameController.getCoins()) {
//		    coin.animation.setOffsetX((int)coin.getX());
//            coin.animation.
		    coin.animation.stop();
		    coin.animation = new SpriteAnimation(coin.getImage(), coinDuration, coin.getCount(),
                    coin.getCol(), coin.getOffset_x(), coin.getOffset_y(), coin.getWidth(), coin.getHeight(),
                    coin.getX(), coin.getY(),gcForMario, 1, false);
		    coin.animation.setCycleCount(Animation.INDEFINITE);
		    coin.animation.play();
        }
//        for (Coin coin: gameController.getCoins()) {
//            gcForMario.drawImage(coin.getImage(), coin.getX(),coin.getY(),coin.getWidth(),coin.getHeight());
//        }
	}
}