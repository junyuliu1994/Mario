import java.util.ArrayList;
import java.util.Observable;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class GameModel extends Observable{
    // didn't use this info yet
    private final int BLOCK_WIDTH = 40;
    private final int BLOCK_HEIGHT = 40;

    private final int WINDOW_WIDTH = 1000;
    private final int WINDOW_HEIGHT = 480;

    private int FALL_SPEED = 0;
    private final int GRAVITY = 1;

	private Background background = new Background();
	private Image marioImage = new Image("resources/mario.png");
	private Image blocks = new Image("resources/blocks.png");
	private Image marioConvertImage = new Image("resources/mario-ConvertImage.png");
	private Canvas canvasForMario = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
	private GraphicsContext gcForMario = canvasForMario.getGraphicsContext2D();
	private Mario mario = new Mario(marioImage, 4, 0, 195, 80, 40, 40, 100, 400, 1, 0, 100, false, gcForMario);
	private ArrayList<Brick> bricks= new ArrayList<>();
	private ArrayList<Coin> coins= new ArrayList<>();

	private boolean start = false;
	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	int levelEnd;
	
	int canvasDistance;
	public void start() {
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
	}

	private int flashCoinsCount = 0;
	private void tick() {
	    flashCoinsCount++;
        if (flashCoinsCount == 8) {
            flashCoins();
            flashCoinsCount = 0;
        }
        move(); 
        stop();
        if (!standOnBlocks()) {
        	fall();
        }
//             {
//            FALL_SPEED = 0;
//        }
	}
	 
	 private void stop() {
		 if (!((mario.getRightRelease() == 1 ||  mario.getLeftRelease() == 1) && !mario.isJump())) {
			 return;
		 }
		 
		 
		 if (mario.getRightRelease() == 1 && mario.isJump() == false) {
			 mario.setRightRelease(0);
			 
			 mario.setImage(marioImage);
			 mario.setCol(1);
			 mario.setCount(0);
			 mario.setOffset_x(195);
			 mario.setOffset_y(80);
			 
	    	 setChanged();
	         notifyObservers();
		 }
		 else if (mario.getLeftRelease() == 1 && !mario.isJump()) {
			 mario.setLeftRelease(0);
			 
			 mario.setImage(marioConvertImage);
			 mario.setCol(1);
			 mario.setCount(0);
			 mario.setOffset_x(791);
			 mario.setOffset_y(80);
			 
	    	 setChanged();
	         notifyObservers();
		 }
	 }
	 
	 private boolean standOnBlocks() {
		 int leftF_x = mario.getX();
		 int leftF_y = mario.getY() + mario.getHeight();
		 
		 int rightF_x = mario.getX() + mario.getWidth();
		 int rightF_y = mario.getX() + mario.getHeight();
		 
		 for (int i = 0; i < bricks.size(); i++) {
			 if (leftF_y == bricks.get(i).getY()) {
				 if (leftF_x >= bricks.get(i).getX() && leftF_x <= bricks.get(i).getX() + bricks.get(i).getWidth()) {
				   		mario.setJumpHeight(0);
				   		mario.setJump(false);


					 return true;
				 }
				 
				 if (rightF_x >= bricks.get(i).getX() && rightF_x <= bricks.get(i).getX() + bricks.get(i).getWidth()) {
				   		mario.setJumpHeight(0);
				   		mario.setJump(false);


					 return true;
				 }
			 }
		 }
		 
		 return false;
	 }
	 	 
	 //when mario cor_x > 1/2 of 1000, then move other stuff contains background
	 private void moveMarioOrOhters() {
		 if ((mario.getX() < 100 && background.getMoveLength() < 1000) || (background.getMoveLength() == 1000)) {
			 mario.setX((int) (mario.getX() + mario.getSpeed()));
		 }
		 else {
		     background.setOffset_x((int) (background.getOffset_x() + mario.getSpeed()));
	    	 background.setMoveLength((int) (background.getMoveLength() + mario.getSpeed()));

             for (Brick brick : bricks) {
                 brick.setX(brick.getX() - mario.getSpeed());
             }
//             System.out.println("reset coins");
             for (Coin coin : coins) {
                 coin.setX(coin.getX() - mario.getSpeed());
                 gcForMario.drawImage(coin.getImage(), coin.getOffset_x(),coin.getOffset_y(),
                         coin.getWidth(),coin.getHeight(), coin.getX() ,coin.getY(),
                         coin.getWidth(), coin.getHeight());
             }

	    }
	 }
	 
	 public void move() {
		if (mario.isRight() && !mario.isJump()) {
		    moveRight();
	    }
	    else if (mario.isLeft() && !mario.isJump()) {
	    	moveLeft();
	    }
	    
	   	if (mario.getJumpHeight() < mario.getJumpMax() && mario.isJump() == true) {
	    	jump();
	    }
	   
	   	if (mario.getJumpHeight() >= mario.getJumpMax()) {
	   		if (mario.getDirection() == 1) {
	   			mario.setImage(marioImage);
	   			mario.setCol(1);
	    		mario.setCount(0);
	    		mario.setOffset_x(195);
	    		mario.setOffset_y(80);
	    		
	    		setChanged();
	        	notifyObservers();
	    	}
	    	else {
	    		mario.setImage(marioConvertImage);
	    		mario.setCol(1);
	    		mario.setCount(0);
	    		mario.setOffset_x(791);
		       	mario.setOffset_y(80);
		       			       	
		       	setChanged();
		    	notifyObservers();
	    	}
	   		
	   	}
	    
/*<<<<<<< HEAD
	    if (mario.getJumpMax() > -1 && mario.getJumpHeight() == 40 && mario.isJump()){
=======
	    if (mario.getJumpMax() > -1 && mario.getJumpHeight() == 80 && mario.isJump() == true){
>>>>>>> refs/heads/junyu
	    	fall();
	    }
	   	
<<<<<<< HEAD
	    if (mario.getJumpHeight() == 40 && mario.getJumpMax() == 0 && mario.isJump()) { //the finish jumping and stop status
=======
	    if (mario.getJumpHeight() == 80 && mario.getJumpMax() == 0 && mario.isJump() == true) { //the finish jumping and stop status
>>>>>>> refs/heads/junyu
	    	mario.setJumpHeight(0);
	   		mario.setJumpMax(80);
	    	mario.setJump(false);
	    	
	    	if (mario.getDirection() == 1) {
	   			mario.setImage(marioImage);
	   			mario.setCol(1);
	    		mario.setCount(0);
	    		mario.setOffset_x(195);
	    		mario.setOffset_y(80);
	    		
	    		setChanged();
	        	notifyObservers();
	    	}
	    	else {
	    		mario.setImage(marioConvertImage);
	    		mario.setCol(1);
	    		mario.setCount(0);
	    		mario.setOffset_x(791);
		       	mario.setOffset_y(80);
		       			       	
		       	setChanged();
		    	notifyObservers();
	    	}
	    }*/
}
	
	private void moveRight() {
    	mario.setImage(marioImage);
    	mario.setCol(4);
    	if (mario.getCount() < mario.getCol()) {
    		mario.setOffset_x(mario.getLv1_offset_x()[mario.getCount()]);
    		mario.setCount(mario.getCount()+1);
    	}
    	else {
    		mario.setCount(0);
    	}
    	mario.setDirection(1);
    	
    	moveMarioOrOhters();

    	setChanged();
    	notifyObservers();
    }
    
    private void moveLeft() {
    	mario.setImage(marioConvertImage);
    	mario.setCol(4);
    	if (mario.getCount() < mario.getCol()) {
    		mario.setOffset_x(mario.getLv1_left_offset_x()[mario.getCount()]);
    		mario.setCount(mario.getCount()+1);
    	}
    	else {
    		mario.setCount(0);
    	}
    	mario.setDirection(0);
    	mario.setX((int) (mario.getX() + mario.getSpeed()));
		
		setChanged();
		notifyObservers();
    }
    private void flashCoins(){
        for (Coin coin : coins) {
            if (coin.getCount() < coin.getCol()) {
                coin.setOffset_x(coin.initial_offset_x + (coin.getWidth()*coin.getCount()));
                coin.setCount(coin.getCount() + 1);
            } else {
                coin.setCount(0);
            }
        }
        setChanged();
        notifyObservers();
    }
    
    private void jump() {
    	if (mario.isRight()) {
    		moveMarioOrOhters();
		}
		else if (mario.isLeft()) {
    		mario.setX((int) (mario.getX() + mario.getSpeed()));
		}
		
    	if (mario.getDirection() == 1) {
    		mario.setImage(marioImage);
    		mario.setOffset_x(395);
    		mario.setOffset_y(78);
    	}
    	else {
    		mario.setImage(marioConvertImage);
    		mario.setOffset_x(591);
    		mario.setOffset_y(78);
    	}
    	mario.setCol(1);
		mario.setCount(0);
		mario.setY((int) (mario.getY() - 7));
		
		mario.setJumpHeight(mario.getJumpHeight() + 4);
		setChanged();
		notifyObservers();
    }
    
    private void fall() {

    	if (mario.isRight()) {
    		moveMarioOrOhters();
		}
		else if (mario.isLeft()) {
    		mario.setX((int) (mario.getX() + mario.getSpeed()));
		}
//    	FALL_SPEED += GRAVITY;
    	mario.setY((mario.getY() + 4));
		setChanged();
		notifyObservers();
    }
    
    public Image getMarioImage() {
		return marioImage;
	}

	public void setMarioImage(Image marioImage) {
		this.marioImage = marioImage;
	}
	
	public Image getMarioConvertImage() {
		return marioConvertImage;
	}

	public void setMarioConvertImage(Image marioConvertImage) {
		this.marioConvertImage = marioConvertImage;
	}
	
	public Image getBlocks() {
		return blocks;
	}

	public void setBlocks(Image blocks) {
		this.blocks = blocks;
	}
    
    public Background getBackground() {
		return background;
	}
	
	public Mario getMario() {
		return mario;
	}

	public void setMario(Mario mario) {
		this.mario = mario;
	}
	
	public ArrayList<Brick> getBricks() {
		return bricks;
	}
	
	public ArrayList<Coin> getCoins() {
		return coins;
	}
	public Canvas getCanvasForMario() {
		return canvasForMario;
	}

	public void setCanvasForMario(Canvas canvasForMario) {
		this.canvasForMario = canvasForMario;
	}

	public GraphicsContext getGcForMario() {
		return gcForMario;
	}

	public void setGcForMario(GraphicsContext gcForMario) {
		this.gcForMario = gcForMario;
	}
}
