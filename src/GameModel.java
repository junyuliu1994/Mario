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

	private Background background = new Background();
	private Image marioImage = new Image("resources/mario.png");
	private Image blocks = new Image("resources/blocks.png");
	private Image marioConvertImage = new Image("resources/mario-ConvertImage.png");

    private Image itemImage = new Image("resources/items.png");
	private Canvas canvasForMario = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
	private GraphicsContext gcForMario = canvasForMario.getGraphicsContext2D();
	private Mario mario = new Mario(marioImage, 4, 0, 195, 80, 40, 40, 100, 400, 1, 0, 96, false, gcForMario);
	private ArrayList<Brick> bricks= new ArrayList<>();
	private ArrayList<Coin> coins= new ArrayList<>();
    private ArrayList<Mushroom> mushrooms = new ArrayList<>();

	private boolean start = false;
	public boolean isStart() {
		return start;
	}
	private boolean paused = false;
	private AnimationTimer at;

	public void setStart(boolean start) {
		this.start = start;
	}

	int levelEnd;

	int canvasDistance;
	public void start() {
		at = new AnimationTimer() {
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

	public boolean getPaused(){
		return paused;
	}

	public void pause(){
		at.stop();
		paused = true;
	}

	public void resume(){
		at.start();
		paused = false;
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

        eatMushroom();
	}

	private void stop() {
		if (mario.isRight() ||  mario.isLeft() || mario.isJump()) {
			return;
		}


		if (mario.getDirection() == 1) {
			mario.setCol(1);
			mario.setCount(0);
            if (mario.getLevel() == 1) {
                mario.setOffset_x(mario.getLv1_offset_x()[0]);
                mario.setOffset_y(mario.getLv1_offset_y());
            }
            else if (mario.getLevel() == 2){
                mario.setOffset_x(mario.getLv1_offset_x()[0]);
                mario.setOffset_y(mario.getLv2_offset_y());
            }

			setChanged();
			notifyObservers();
		}
		else{
			mario.setCol(1);
			mario.setCount(0);
            if (mario.getLevel() == 1) {
                mario.setOffset_x(mario.getLv1_left_offset_x()[0]);
                mario.setOffset_y(mario.getLv1_offset_y());
            }
            else if (mario.getLevel() == 2){
                mario.setOffset_x(mario.getLv1_left_offset_x()[0]);
                mario.setOffset_y(mario.getLv2_left_offset_y());
            }

			setChanged();
			notifyObservers();
		}
	}

	private boolean standOnBlocks() {
	    System.out.println(mario.getLeftF_y());
		for (int i = 0; i < bricks.size(); i++) {
			if (mario.getLeftF_y() == bricks.get(i).getY()) {
				if (mario.getLeftF_x() >= bricks.get(i).getX() && mario.getLeftF_x() <= bricks.get(i).getX() + bricks.get(i).getWidth()) {
					mario.setJumpHeight(0);
					mario.setJump(false);
					return true;
				}

				if (mario.getRightF_x() >= bricks.get(i).getX() && mario.getRightF_x() <= bricks.get(i).getX() + bricks.get(i).getWidth()) {
					mario.setJumpHeight(0);
					mario.setJump(false);


					return true;
				}
			}
		}

		return false;
	}

	private void eatMushroom(){
        for (int i = 0; i < mushrooms.size(); i++){
            if (mushrooms.get(i) != null){
                if (mario.getfCenter_x() >= mushrooms.get(i).getX() && mario.getfCenter_x() <= mushrooms.get(i).getX() + mushrooms.get(i).getWidth()){
                    if (mario.getfCenter_y() >= mushrooms.get(i).getY() && mario.getfCenter_y() <= mushrooms.get(i).getY()+mushrooms.get(i).getHeight()){
                        if (mario.getLevel() == 1) {
                            mario.setOffset_y(mario.getLv2_offset_y());
                            mario.setY(mario.getY() - mario.getHeight());
                            mario.setHeight(mario.getHeight() * 2);
                            mario.setLevel(mario.getLevel() + 1);
                            mario.resetCollisionCor();

                            mushrooms.set(i, null);
                            setChanged();
                            notifyObservers();
                        }
                    }
                }
            }
        }
	}

	//���ƶ�����
	private boolean moveRightStockByBlocks() {
		for (int i = 0; i < bricks.size(); i++) {
			if (mario.getRightH_x() == bricks.get(i).getX()) { //���ִ������������
				if (mario.getRightH_y() >= bricks.get(i).getY() && mario.getRightH_y() <= bricks.get(i).getY() + bricks.get(i).getHeight()) {
					return true;
				}
			}
		}

		for (int i = 0; i < bricks.size(); i++) {
			if (mario.getRightTopC_x() == bricks.get(i).getX()) { //���ִ������������
				if (mario.getRightTopC_y() >= bricks.get(i).getY() && mario.getRightTopC_y() <= bricks.get(i).getY() + bricks.get(i).getHeight()) {
					return true;
				}
			}
		}

		return false;
	}

	//���ƶ�����
	private boolean moveLeftStockByBlocks() {
		for (int i = 0; i < bricks.size(); i++) {
			if (mario.getLeftH_x() == bricks.get(i).getX() + bricks.get(i).getWidth()) { //�������������ұ�
				if (mario.getLeftH_y() >= bricks.get(i).getY() && mario.getLeftH_y() <= bricks.get(i).getY() + bricks.get(i).getHeight()) {
					return true;
				}
			}
		}

		for (int i = 0; i < bricks.size(); i++) {
			if (mario.getLeftTopC_x() == bricks.get(i).getX()) { //���ִ������������
				if (mario.getLeftTopC_y() >= bricks.get(i).getY() && mario.getLeftTopC_y() <= bricks.get(i).getY() + bricks.get(i).getHeight()) {
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
			}

            for (Mushroom mushroom : mushrooms) {
                if (mushroom != null) {
                    mushroom.setX(mushroom.getX() - mario.getSpeed());
                }
            }
		}
	}


	public void move() {
		if (mario.isRight() && !mario.isJump()) {
			if (!moveRightStockByBlocks()) {
				moveRight();
			}
		}
		else if (mario.isLeft() && !mario.isJump()) {
			if (!moveLeftStockByBlocks()) {
				moveLeft();
			}
		}

		if (mario.getJumpHeight() < mario.getJumpMax() && mario.isJump() == true) {
			jump();
		}

		/*if (mario.getJumpHeight() >= mario.getJumpMax()) {
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

	private boolean jumpStockByBrick() {
		for (int i = 0; i < bricks.size(); i++) {
			if (mario.getHead_y() == bricks.get(i).getY() + bricks.get(i).getHeight()) {
				if (mario.getHead_x() >= bricks.get(i).getX() && mario.getHead_x() <= bricks.get(i).getX() + bricks.get(i).getWidth()) {
					return true;
				}

				if (mario.getLeft_tou_x() >= bricks.get(i).getX() && mario.getLeft_tou_x() <= bricks.get(i).getX() + bricks.get(i).getWidth()) {
					return true;
				}

				if (mario.getRight_tou_x() >= bricks.get(i).getX() && mario.getRight_tou_x() <= bricks.get(i).getX() + bricks.get(i).getWidth()) {
					return true;
				}
			}
		}

		for (int i = 0; i < bricks.size(); i++) {
			if (mario.getRightTopC_y() == bricks.get(i).getY()  + bricks.get(i).getHeight()) { //���ִ������������
				if (mario.getRightTopC_x() >= bricks.get(i).getX() && mario.getRightTopC_x() <= bricks.get(i).getX() + bricks.get(i).getWidth()) {
					return true;
				}

				if (mario.getLeftTopC_x() >= bricks.get(i).getX() && mario.getLeftTopC_x() <= bricks.get(i).getX() + bricks.get(i).getWidth()) {
					return true;
				}
			}
		}

		return false;
	}

	private void jump() {
		if (!jumpStockByBrick()) {
			if (mario.isRight()) {
				if (!moveRightStockByBlocks()) {
					moveMarioOrOhters();
				}
			}
			else if (mario.isLeft()) {
				if (!moveLeftStockByBlocks()) {
					mario.setX((int) (mario.getX() + mario.getSpeed()));
				}
			}

			if (mario.getDirection() == 1) {
			    if (mario.getLevel() == 1) {
                    mario.setOffset_x(mario.getLv1_rjump_offset_x());
                    mario.setOffset_y(mario.getLv1_rjump_offset_y());
                }
                else if (mario.getLevel() == 2){
                    mario.setOffset_x(mario.getLv1_rjump_offset_x());
                    mario.setOffset_y(mario.getLv2_rjump_offset_y());
                }
			}
			else {
                if (mario.getLevel() == 1) {
                    mario.setOffset_x(mario.getLv1_ljump_offset_x());
                    mario.setOffset_y(mario.getLv1_ljump_offset_y());
                }
                else if (mario.getLevel() == 2){
                    mario.setOffset_x(mario.getLv1_ljump_offset_x());
                    mario.setOffset_y(mario.getLv2_ljump_offset_y());
                }
			}
			mario.setCol(1);
			mario.setCount(0);
			mario.setY((int) (mario.getY() - 8));

			mario.setJumpHeight(mario.getJumpHeight() + 4);
			setChanged();
			notifyObservers();
		}
		else {
			mario.setJumpHeight(mario.getJumpMax());
			setChanged();
			notifyObservers();
		}
	}

	private void fall() {
		if (mario.isRight()) {
			if (!moveRightStockByBlocks()) {
				moveMarioOrOhters();
			}
		}
		else if (mario.isLeft()) {
			if (!moveLeftStockByBlocks()) {
				mario.setX((int) (mario.getX() + mario.getSpeed()));
			}
		}
		mario.setY((int) (mario.getY() + 4));
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

    public Image getItemImage() {
        return itemImage;
    }

    public void setItemImage(Image itemImage) {
        this.itemImage = itemImage;
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

    public ArrayList<Mushroom> getMushrooms() {
        return mushrooms;
    }

    public void setMushrooms(ArrayList<Mushroom> mushrooms) {
        this.mushrooms = mushrooms;
    }
}