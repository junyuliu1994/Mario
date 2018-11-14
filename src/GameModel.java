import java.util.ArrayList;
import java.util.Observable;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class GameModel extends Observable{
	private Image background = new Image("resources/background_version3.png");
	private Image marioImage = new Image("resources/mario.png");
	private Image blocks = new Image("resources/blocks.png");
	private Image marioConvertImage = new Image("resources/mario-ConvertImage.png");
	private Canvas canvasForMario = new Canvas(1000, 520);
	private GraphicsContext gcForMario = canvasForMario.getGraphicsContext2D();
	private Mario mario = new Mario(marioImage, 4, 4, 196, 80, 40, 40, 0, 440, 1, 0, 40, false, gcForMario);
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
	
	 private void tick() {
		 move(); 
		 stop();
	}
	 
	 private void stop() {
		 if (!((mario.getRightRelease() == 1 ||  mario.getLeftRelease() == 1) && mario.isJump()== false )) {
			 return;
		 }
		 
		 if (mario.getRightRelease() == 1 && mario.isJump() == false) {
			 mario.setRightRelease(0);
			 
			 mario.setImage(marioImage);
			 mario.setCol(1);
			 mario.setCount(1);
			 mario.setOffset_x(195);
			 mario.setOffset_y(80);
			 
			 mario.getMarioAnimation().setImage(mario.getImage());
		     mario.getMarioAnimation().setCount(mario.getCount());
		     mario.getMarioAnimation().setColumns(mario.getCol());
		     mario.getMarioAnimation().setOffsetX(mario.getOffset_x());
		     mario.getMarioAnimation().setOffsetY(mario.getOffset_y());
		     mario.getMarioAnimation().setDirection(1);
	    	 setChanged();
	         notifyObservers();
		 }
		 else if (mario.getLeftRelease() == 1 && mario.isJump() == false) {
			 mario.setLeftRelease(0);
			 
			 mario.setImage(marioConvertImage);
			 mario.setCol(1);
			 mario.setCount(1);
			 mario.setOffset_x(791);
			 mario.setOffset_y(80);
			 
			 mario.getMarioAnimation().setImage(mario.getImage());
		     mario.getMarioAnimation().setCount(mario.getCount());
		     mario.getMarioAnimation().setColumns(mario.getCol());
		     mario.getMarioAnimation().setOffsetX(mario.getOffset_x());
		     mario.getMarioAnimation().setOffsetY(mario.getOffset_y());
		     mario.getMarioAnimation().setDirection(0);
	    	 setChanged();
	         notifyObservers();
		 }
	 }
	 
	 public void move() {
		if (mario.isRight() && mario.isJump() == false) {
		    	moveRight();
	    }
	    else if (mario.isLeft() && mario.isJump() == false) {
	    	moveLeft();
	    }
	    
	   	if (mario.getJumpHeight() < 40 && mario.isJump() == true) {
	    	jump();
	    }
	    
	    if (mario.getJumpMax() > -1 && mario.getJumpHeight() == 40 && mario.isJump() == true){
	    	fall();
	    }
	   	
	    if (mario.getJumpHeight() == 40 && mario.getJumpMax() == 0 && mario.isJump() == true) { //the finish jumping and stop status
	    	mario.setJumpHeight(0);
	   		mario.setJumpMax(40);
	    	mario.setJump(false);
	    	
	    	if (mario.getDirection() == 1) {
	   			mario.setImage(marioImage);
	   			mario.setCol(1);
	    		mario.setCount(1);
	    		mario.setOffset_x(195);
	    		mario.setOffset_y(80);
	    		
	    		mario.getMarioAnimation().setImage(mario.getImage());
	        	mario.getMarioAnimation().setCount(mario.getCount());
	        	mario.getMarioAnimation().setColumns(mario.getCol());
	        	mario.getMarioAnimation().setOffsetX(mario.getOffset_x());
	        	mario.getMarioAnimation().setOffsetY(mario.getOffset_y());
	        	mario.getMarioAnimation().setCor_x(mario.getX());
	        	mario.getMarioAnimation().setDirection(1);
	    		setChanged();
	        	notifyObservers();
	    	}
	    	else {
	    		mario.setImage(marioConvertImage);
	    		mario.setCol(1);
	    		mario.setCount(1);
	    		mario.setOffset_x(791);
		       	mario.setOffset_y(80);
		       	
		       	mario.getMarioAnimation().setImage(mario.getImage());
		    	mario.getMarioAnimation().setCount(mario.getCount());
		    	mario.getMarioAnimation().setColumns(mario.getCol());
		    	mario.getMarioAnimation().setOffsetX(mario.getOffset_x());
		    	mario.getMarioAnimation().setOffsetY(mario.getOffset_y());
		    	mario.getMarioAnimation().setCor_x(mario.getX());
		    	mario.getMarioAnimation().setDirection(0);
		       	setChanged();
		    	notifyObservers();
	    	}
	    }
}
	
	private void moveRight() {
    	mario.setImage(marioImage);
    	mario.setCol(4);
		mario.setCount(4);
    	mario.setOffset_x(195);
    	mario.setOffset_y(80);
    	mario.setDirection(1);
    	mario.setX((int) (mario.getX() + mario.getSpeed()));
    	
    	mario.getMarioAnimation().setImage(mario.getImage());
    	mario.getMarioAnimation().setCount(mario.getCount());
    	mario.getMarioAnimation().setColumns(mario.getCol());
    	mario.getMarioAnimation().setOffsetX(mario.getOffset_x());
    	mario.getMarioAnimation().setOffsetY(mario.getOffset_y());
    	mario.getMarioAnimation().setCor_x(mario.getX());
    	mario.getMarioAnimation().setDirection(1);
    	setChanged();
    	notifyObservers();
    }
    
    private void moveLeft() {
    	mario.setImage(marioConvertImage);
		mario.setCol(4);
		mario.setCount(4);
		mario.setOffset_x(791);
		mario.setOffset_y(80);
		mario.setDirection(0);
		mario.setX((int) (mario.getX() + mario.getSpeed()));
		
		mario.getMarioAnimation().setImage(mario.getImage());
    	mario.getMarioAnimation().setCount(mario.getCount());
    	mario.getMarioAnimation().setColumns(mario.getCol());
    	mario.getMarioAnimation().setOffsetX(mario.getOffset_x());
    	mario.getMarioAnimation().setOffsetY(mario.getOffset_y());
    	mario.getMarioAnimation().setCor_x(mario.getX());
		mario.getMarioAnimation().setDirection(0);
		setChanged();
		notifyObservers();
    }
    
    private void jump() {
    	if (mario.isRight() == true) {
    		mario.setX((int) (mario.getX() + mario.getSpeed()));
		}
		else if (mario.isLeft() == true) {
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
		mario.setCount(1);
		mario.setY((int) (mario.getY() - 4));
		mario.getMarioAnimation().setImage(mario.getImage());
		mario.getMarioAnimation().setOffsetX(mario.getOffset_x());
		mario.getMarioAnimation().setOffsetY(mario.getOffset_y());
		mario.getMarioAnimation().setColumns(mario.getCol());
		mario.getMarioAnimation().setCount(mario.getCount());
		mario.getMarioAnimation().setCor_x(mario.getX());
		mario.getMarioAnimation().setCor_y(mario.getY());
		mario.setJumpHeight(mario.getJumpHeight() + 4);
		setChanged();
		notifyObservers();
    }
    
    private void fall() {
    	if (mario.isRight() == true) {
    		mario.setX((int) (mario.getX() + mario.getSpeed()));
		}
		else if (mario.isLeft() == true) {
    		mario.setX((int) (mario.getX() + mario.getSpeed()));
		}
    	
    	mario.setY((int) (mario.getY() + 4));
    	mario.getMarioAnimation().setCor_x(mario.getX());
		mario.getMarioAnimation().setCor_y(mario.getY());		
		mario.setJumpMax(mario.getJumpMax() - 4);
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
    
    public Image getBackground() {
		return background;
	}

	public void setBackground(Image background) {
		this.background = background;
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
