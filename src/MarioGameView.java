import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
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
	
	GraphicsContext gcForMario;
	GraphicsContext gcForStuff; 
	int levelWidth;
	int levelEnd;
	int canvasDistance;
	
	
	boolean jump;
	
	int i = 0;
	int j = 15;

    public static void main(String[] args) {
        launch(args);
    }
    
    private void tick() {
    	if (i < 15 && jump == true) {
    		gameController.getMario().setY((int) (gameController.getMario().getY() - 1));
    		gcForMario.clearRect(0,0,1280,720);
    		gcForMario.drawImage(gameController.getMario().getImage(), // the image to be drawn or null.
            		gameController.getMario().getOffset_x(), // the source rectangle's X coordinate position.
            		gameController.getMario().getOffset_y(), // the source rectangle's Y coordinate position.
            		gameController.getMario().getWidth(), // the source rectangle's width.
            		gameController.getMario().getHeight(), // the source rectangle's height.
            		gameController.getMario().getX(), // the destination rectangle's X coordinate position.
            		gameController.getMario().getY(), // the destination rectangle's Y coordinate position.
            		gameController.getMario().getWidth(), // the destination rectangle's width.
            		gameController.getMario().getWidth()); // the destination rectangle's height. 
    		
    		i++;
    	}
    	
    	if (j > -1 && i == 15 && jump == true){
    		gameController.getMario().setY((int) (gameController.getMario().getY() + 1));
    		gcForMario.clearRect(0,0,1280,720);
    		gcForMario.drawImage(gameController.getMario().getImage(), // the image to be drawn or null.
            		gameController.getMario().getOffset_x(), // the source rectangle's X coordinate position.
            		gameController.getMario().getOffset_y(), // the source rectangle's Y coordinate position.
            		gameController.getMario().getWidth(), // the source rectangle's width.
            		gameController.getMario().getHeight(), // the source rectangle's height.
            		gameController.getMario().getX(), // the destination rectangle's X coordinate position.
            		gameController.getMario().getY(), // the destination rectangle's Y coordinate position.
            		gameController.getMario().getWidth(), // the destination rectangle's width.
            		gameController.getMario().getWidth()); // the destination rectangle's height. 
    		
    		j--;
    	}
    	
    	if (i == 15 && j == 0 && jump == true) {
    		i = 0;
    		j = 15;
    		jump =false;
    	}
	}
    
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Mario Game");
        Canvas canvasForMario = new Canvas(1280, 720);
        Canvas canvasForStuff = new Canvas(1280, 720);

        AnimationTimer at = new AnimationTimer() {
            @Override
            public void handle(long now) {
                    // perform ticksPerFrame ticks
                    for (int i = 0; i < 1; i++) {
                            tick();
                    }
            }
	     };
	     at.start();

        
        gcForMario = canvasForMario.getGraphicsContext2D();
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
        root.getChildren().add(canvasForStuff);
        Scene scene = new Scene(root);

        
        gcForStuff  = canvasForStuff.getGraphicsContext2D();
        
        /*gameController.getCoins().add(new Coin(new Image("file:blocks.png"), 3, 3, 385, 16, 15, 16, 1000, 480));
        coinAnimation = new SpriteAnimation(gameController.getCoins().get(0).getImage(),
                Duration.millis(1000),
                gameController.getCoins().get(0).getCount(), gameController.getCoins().get(0).getCol(),
                gameController.getCoins().get(0).getOffset_x(), gameController.getCoins().get(0).getOffset_y(),
                gameController.getCoins().get(0).getWidth(), gameController.getCoins().get(0).getHeight(), 
                gameController.getCoins().get(0).getX(), gameController.getCoins().get(0).getY(), gcForStuff, 1, false);
        coinAnimation.setCycleCount(Animation.INDEFINITE);
        coinAnimation.play();*/
        
        marioAnimation = new SpriteAnimation(gameController.getMario().getImage(),
                Duration.millis(500),
                gameController.getMario().getCount(), gameController.getMario().getCol(),
                gameController.getMario().getOffset_x(), gameController.getMario().getOffset_y(),
                gameController.getMario().getWidth(), gameController.getMario().getHeight(), 
                gameController.getMario().getX(), gameController.getMario().getY(), gcForMario, 1, true);
        marioAnimation.setCycleCount(Animation.INDEFINITE);
        scene.setOnKeyPressed(event -> {
        	if (event.getCode().toString().equals("D")) {
        		System.out.println(i);
        		gameController.getMario().setSpeed(5);
        		if (gameController.getMario().getX() == 1280/2 && canvasDistance < levelEnd) {
        			canvasDistance+=gameController.getMario().getSpeed();

        			setNewXForStuff(0-gameController.getMario().getSpeed());
        			reDrawStuff();
        		}
        		else {
	        		gameController.getMario().setImage(new Image("file:mario.png"));
	        		gameController.getMario().setCol(4);
	    			gameController.getMario().setCount(4);
	        		gameController.getMario().setOffset_x(80);
	        		gameController.getMario().setOffset_y(32);
	        		gameController.getMario().setX((int) (gameController.getMario().getX() + gameController.getMario().getSpeed()));
	        		System.out.println(gameController.getMario().getY());
	        		
	        		marioAnimation.setImage(gameController.getMario().getImage());
	        		marioAnimation.setCount(gameController.getMario().getCount());
	        		marioAnimation.setColumns(gameController.getMario().getCol());
	        		marioAnimation.setOffsetX(gameController.getMario().getOffset_x());
	        		marioAnimation.setOffsetY(gameController.getMario().getOffset_y());
	        		marioAnimation.setCor_x(gameController.getMario().getX());
	        		marioAnimation.setDirection(1);
	        		marioAnimation.play();
        		}
        	}
        	else if (event.getCode().toString().equals("A")) {
        		gameController.getMario().setSpeed(-5);
        		gameController.getMario().setImage(new Image("file:mario-ConvertImage.png"));
        		gameController.getMario().setCol(4);
    			gameController.getMario().setCount(4);
        		gameController.getMario().setOffset_x(320);
        		gameController.getMario().setOffset_y(32);
        		gameController.getMario().setX((int) (gameController.getMario().getX() + gameController.getMario().getSpeed()));
        		
        		marioAnimation.setImage(gameController.getMario().getImage());
        		marioAnimation.setOffsetX(gameController.getMario().getOffset_x());
        		marioAnimation.setOffsetY(gameController.getMario().getOffset_y());
        		marioAnimation.setCor_x(gameController.getMario().getX());
        		marioAnimation.setDirection(0);
        		marioAnimation.play();
        		        		
        	}
        	else if (event.getCode().toString().equals("W")) {
        		jump = true;
        		if (gameController.getMario().getDirection() == 0) { //left jump

        		}
        		else { //right jump
        			gameController.getMario().setJumpSpeed(15);
        			gameController.getMario().setImage(new Image("file:mario.png"));
        			gameController.getMario().setCol(1);
        			gameController.getMario().setCount(1);
            		gameController.getMario().setOffset_x(160);
            		gameController.getMario().setOffset_y(32);
            		
        		}
        	}
        });
        
        scene.setOnKeyReleased(event -> {
        	if (marioAnimation != null && (event.getCode().toString().equals("D") || event.getCode().toString().equals("A") || event.getCode().toString().equals("W"))) {
        		
        		if (gameController.getMario().getDirection() == 0) {
        			gameController.getMario().setImage(new Image("file:mario-ConvertImage.png"));
        			gameController.getMario().setCol(4);
        			gameController.getMario().setCount(4);
        		}
        		else {
        			gameController.getMario().setImage(new Image("file:mario.png"));
        			gameController.getMario().setCol(4);
        			gameController.getMario().setCount(4);
        			gameController.getMario().setOffset_x(80);
	        		gameController.getMario().setOffset_y(32);
        		}
        		gameController.getMario().setSpeed(0);
        		gameController.getMario().setJumpSpeed(0);
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
        
        initContent(); 
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void setNewXForStuff(double d) {
    	for (int i = 0; i < gameController.getBricks().size(); i++) {
    		gameController.getBricks().get(i).setX(gameController.getBricks().get(i).getX() + d);
    	}
    }
    
    private void reDrawStuff() {
    	gcForStuff.clearRect(0, 0, 1280, 720);
    	for (int i = 0; i < gameController.getBricks().size(); i++) {
    		gcForStuff.drawImage(gameController.getBricks().get(i).getImage(), // the image to be drawn or null.
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
    
    private void initContent() {
    	levelWidth = LevelData.LEVEL1[0].length() * 15;
    	levelEnd = levelWidth - 1280;
    	
    	for (int i = 0; i < LevelData.LEVEL1.length; i++) {
    		String line = LevelData.LEVEL1[i];
    		for (int j = 0; j < line.length(); j++) {
    			switch (line.charAt(j)) {
    				case '0':
    					break;
    				case '1':
    					Brick brick = new Brick(new Image("file:blocks.png"), 0, 0, 15, 15, j*15, i*15);
    					gameController.getBricks().add(brick);
    					int index = gameController.getBricks().size() -1;
    					gcForStuff.drawImage(gameController.getBricks().get(index).getImage(), // the image to be drawn or null.
    							gameController.getBricks().get(index).getOffset_x(), // the source rectangle's X coordinate position.
    							gameController.getBricks().get(index).getOffset_y(), // the source rectangle's Y coordinate position.
    							gameController.getBricks().get(index).getWidth(), // the source rectangle's width.
    							gameController.getBricks().get(index).getHeight(), // the source rectangle's height.
    							gameController.getBricks().get(index).getX(), // the destination rectangle's X coordinate position.
    							gameController.getBricks().get(index).getY(), // the destination rectangle's Y coordinate position.
    							gameController.getBricks().get(index).getWidth(), // the destination rectangle's width.
    							gameController.getBricks().get(index).getHeight()); // the destination rectangle's height. 
    			}
    		}
    	}    	
    }
}