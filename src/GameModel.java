import java.sql.BatchUpdateException;
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

	private Image wxzImage = new Image("resources/wxz.png");

	private Image wxzConvertImage = new Image("resources/wxz-convert.png");

    private Image itemImage = new Image("resources/items.png");
	private Canvas canvasForMario = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
	private GraphicsContext gcForMario = canvasForMario.getGraphicsContext2D();
	private Mario mario = new Mario(marioImage, 4, 0, 195, 80, 40, 40, 100, 400, 1, 0, 192, false, gcForMario);
	private ArrayList<Brick> bricks= new ArrayList<>();
	private ArrayList<Coin> coins= new ArrayList<>();
    private ArrayList<Mushroom> mushrooms = new ArrayList<>();
	private ArrayList<Bullet> bullets = new ArrayList<>();
	private Brick standBrick;
	private boolean start = false;
	private int score = 0;

	private boolean moveBackground = false;

	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

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
			flashQuestionBrick();
			flashCoinsCount = 0;
		}

		move();
		bulletMove();
		resetBulletSpeed();
		stop();

		fall();

        eatMushroom();

		if (mario.getLife() > 0){
			if (jumpToHoleDeath()) {
				mario.setLevel(1);
				mario.setDirection(1);
				mario.setImage(marioImage);
				mario.setOffset_x(mario.getLv1_offset_x()[0]);
				mario.setOffset_y(mario.getLv1_offset_y());
				mario.setHeight(mario.getInitialHeight());

				mario.setLife(mario.getLife() - 1);
				mario.setX((int)standBrick.getX());
				mario.setY((int)standBrick.getY()-mario.getHeight());
			}
		}
		else{
			//gameover
		}
		setChanged();
		notifyObservers();
	}

	private void resetBulletSpeed(){
		if (mario.isRight() && moveBackground) {
			for (Bullet bullet : bullets) {
				if (bullet == null) {
					continue;
				}
				if (bullet.getSpeed() > 0) {
					bullet.setSpeed(4);
				}
			}
		}
		else{
			for (Bullet bullet : bullets) {
				if (bullet == null) {
					continue;
				}
				if (bullet.getSpeed() > 0) {
					bullet.setSpeed(6);
				}
			}
		}
	}

	private void bulletMove(){
		for (int i = 0; i < bullets.size(); i++){
			if (bullets.get(i) != null){
				if (bullets.get(i).getSpeed() > 0) {
					for (int j = 0; j < bullets.get(i).getSpeed(); j++) {
						if (!bulletCollision(bullets.get(i))) {
							bullets.get(i).setX(bullets.get(i).getX() + 1);
						}
						else{
							bullets.set(i, null);
							break;
						}
					}
				}
				else{
					for (int j = 0; j > bullets.get(i).getSpeed(); j--) {
						if (!bulletCollision(bullets.get(i))) {
							bullets.get(i).setX(bullets.get(i).getX() - 1);
						}
						else{
							bullets.set(i, null);
							break;
						}
					}
				}
			}
		}
	}

	private boolean jumpToHoleDeath(){
		if (mario.getY() > 440){
			return true;
		}
		return false;
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
            else{
            	mario.setOffset_x(mario.getLv3_offset_x());
            	mario.setOffset_y(mario.getLv3_offset_y());
			}

			
			
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
            else{
				mario.setOffset_x(mario.getLv3_loffset_x());
				mario.setOffset_y(mario.getLv3_offset_y());
			}

			
			
		}
	}

	private boolean bulletCollision(Bullet bullet){
		for (Brick brick : bricks){
			if (brick == null){
				continue;
			}

			if (bullet.getX() + bullet.getWidth() == brick.getX()){
				if (bullet.getY() + bullet.getHeight()/2 >= brick.getY() && bullet.getY() + bullet.getHeight()/2 <= brick.getY() + brick.getHeight()){
					return true;
				}
			}

			if (bullet.getX() == brick.getX() + brick.getWidth()){
				if (bullet.getY() + bullet.getHeight()/2 >= brick.getY() && bullet.getY() + bullet.getHeight()/2 <= brick.getY() + brick.getHeight()){
					return true;
				}
			}
		}
		return false;
	}

	private boolean standOnBlocks() {
		for (int i = 0; i < bricks.size(); i++) {
		    if (bricks.get(i) == null){
		        continue;
            }
			if (mario.getLeftF_y() == bricks.get(i).getY()) {
				if (mario.getLeftF_x() >= bricks.get(i).getX() && mario.getLeftF_x() <= bricks.get(i).getX() + bricks.get(i).getWidth()) {
					mario.setJumpHeight(0);
					mario.setJump(false);
					standBrick = bricks.get(i);
					return true;
				}

				if (mario.getRightF_x() >= bricks.get(i).getX() && mario.getRightF_x() <= bricks.get(i).getX() + bricks.get(i).getWidth()) {
					mario.setJumpHeight(0);
					mario.setJump(false);
					standBrick = bricks.get(i);
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
						score += 1000;
                        if (mario.getLevel() == 1) {
                            mario.setOffset_y(mario.getLv2_offset_y());
                            mario.setY(mario.getY() - mario.getHeight());
                            mario.setHeight(mario.getHeight() * 2);
                            mario.setLevel(mario.getLevel() + 1);
                            mario.resetCollisionCor();

                            mushrooms.set(i, null);
                        }
                        else if (mario.getLevel() == 2){

							if (mario.getDirection() == 1){
                        		mario.setImage(wxzImage);
                        		mario.setOffset_x(mario.getLv3_offset_x());
                        		mario.setOffset_y(mario.getLv3_offset_y());
                        		mario.setY(mario.getY() + mario.getHeight()/2);
                        		mario.setHeight(mario.getHeight()/2);
                        		mario.setLevel(mario.getLevel() + 1);
                        		mario.resetCollisionCor();

								mushrooms.set(i, null);
							}
							else{
								mario.setImage(wxzConvertImage);
								mario.setOffset_x(mario.getLv3_loffset_x());
								mario.setOffset_y(mario.getLv3_offset_y());
								mario.setY(mario.getY() + mario.getHeight()/2);
								mario.setHeight(mario.getHeight()/2);
								mario.setLevel(mario.getLevel() + 1);
								mario.resetCollisionCor();

								mushrooms.set(i, null);
							}
						}
                    }
                }
            }
        }
	}

	//���ƶ�����
	private boolean moveRightStockByBlocks() {
		for (int i = 0; i < bricks.size(); i++) {
		    if (bricks.get(i) == null){
		        continue;
            }
			if (mario.getRightH_x() == bricks.get(i).getX()) { //���ִ������������
				if (mario.getRightH_y() >= bricks.get(i).getY() && mario.getRightH_y() <= bricks.get(i).getY() + bricks.get(i).getHeight()) {
					return true;
				}
			}
		}

		for (int i = 0; i < bricks.size(); i++) {
            if (bricks.get(i) == null){
                continue;
            }
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
            if (bricks.get(i) == null){
                continue;
            }
			if (mario.getLeftH_x() == bricks.get(i).getX() + bricks.get(i).getWidth()) { //�������������ұ�
				if (mario.getLeftH_y() >= bricks.get(i).getY() && mario.getLeftH_y() <= bricks.get(i).getY() + bricks.get(i).getHeight()) {
					return true;
				}
			}
		}

		for (int i = 0; i < bricks.size(); i++) {
            if (bricks.get(i) == null){
                continue;
            }
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
		if ((mario.getX() < 200 && background.getMoveLength() < 1000) || (background.getMoveLength() == 1000)) {
			for (int i = 0; i < mario.getSpeed(); i++) {
				if (!moveRightStockByBlocks()) {
					mario.setX((int) (mario.getX() + 1));
				}
			}
			moveBackground = false;
		}
		else {
			for (int i = 0; i < mario.getSpeed(); i++) {
				if (!moveRightStockByBlocks()) {
					background.setOffset_x((int) (background.getOffset_x() + 1));
					background.setMoveLength((int) (background.getMoveLength() + 1));
				}
			}
			moveBackground = true;

			for (Brick brick : bricks) {
                if (brick == null){
                    continue;
                }

                for (int i = 0; i < mario.getSpeed(); i++) {
                	if (!moveRightStockByBlocks()) {
						brick.setX(brick.getX() - 1);
					}
				}
			}
//             System.out.println("reset coins");
			for (Coin coin : coins) {
				if (coin == null){
					continue;
				}
				for (int i = 0; i < mario.getSpeed(); i++) {
					if (!moveRightStockByBlocks()) {
						coin.setX(coin.getX() - 1);
					}
				}
			}

            for (Mushroom mushroom : mushrooms) {
                if (mushroom != null) {
					for (int i = 0; i < mario.getSpeed(); i++) {
						if (!moveRightStockByBlocks()) {
							mushroom.setX(mushroom.getX() - 1);
						}
					}
                }
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

		/*if (mario.getJumpHeight() >= mario.getJumpMax()) {
			if (mario.getDirection() == 1) {
				mario.setImage(marioImage);
				mario.setCol(1);
				mario.setCount(0);
				mario.setOffset_x(195);
				mario.setOffset_y(80);

				
				
			}
			else {
				mario.setImage(marioConvertImage);
				mario.setCol(1);
				mario.setCount(0);
				mario.setOffset_x(791);
				mario.setOffset_y(80);

				
				
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

	    		
	        	
	    	}
	    	else {
	    		mario.setImage(marioConvertImage);
	    		mario.setCol(1);
	    		mario.setCount(0);
	    		mario.setOffset_x(791);
		       	mario.setOffset_y(80);

		       	
		    	
	    	}
	    }*/
	}

	private void moveRight() {
		if (mario.getLevel() < 3) {
			mario.setImage(marioImage);
			mario.setCol(4);
			if (mario.getCount() < mario.getCol()) {
				mario.setOffset_x(mario.getLv1_offset_x()[mario.getCount()]);
				mario.setCount(mario.getCount() + 1);
			} else {
				mario.setCount(0);
			}
		}
		else{
			mario.setImage(wxzImage);
			mario.setOffset_x(mario.getLv3_offset_x());
			mario.setOffset_y(mario.getLv3_offset_y());
			mario.setCol(1);
			mario.setCount(0);
		}
		mario.setDirection(1);

		moveMarioOrOhters();
	}

	private void moveLeft() {
		if (mario.getLevel() < 3) {
			mario.setImage(marioConvertImage);
			mario.setCol(4);
			if (mario.getCount() < mario.getCol()) {
				mario.setOffset_x(mario.getLv1_left_offset_x()[mario.getCount()]);
				mario.setCount(mario.getCount() + 1);
			} else {
				mario.setCount(0);
			}
		}
		else{
			mario.setImage(wxzConvertImage);
			mario.setOffset_x(mario.getLv3_loffset_x());
			mario.setOffset_y(mario.getLv3_offset_y());
			mario.setCol(1);
			mario.setCount(0);
		}
		mario.setDirection(0);
		for (int i = 0; i > mario.getSpeed(); i--) {
			if (!moveLeftStockByBlocks()) {
				mario.setX((int) (mario.getX() - 1));
			}
		}
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
		
		
	}

	private void flashQuestionBrick(){
		for (Brick brick : bricks) {
			if (brick != null && brick instanceof QuestionBrick){
				if (((QuestionBrick) brick).getCount() < ((QuestionBrick) brick).getCol()){
					brick.setOffset_x(((QuestionBrick) brick).getS_offset_x()[((QuestionBrick) brick).getCount()]);
					((QuestionBrick) brick).setCount(((QuestionBrick) brick).getCount()+1);
				}
				else{
					((QuestionBrick) brick).setCount(0);
				}
			}
		}
	}

	private void jumpCollisionWithBrick(Brick brick, int i){
	    if (brick instanceof Wall){
	        if (mario.getLevel() > 1) {
	            bricks.set(i, null);
            }
        }
        else if (brick instanceof QuestionBrick){
            if (((QuestionBrick) brick).getContains() > 0) {
                Mushroom mushroom = new Mushroom(itemImage, 40, 40, brick.getX(), brick.getY() - 40);
                mushrooms.add(mushroom);
                ((QuestionBrick) brick).setContains(0);
                brick.setOffset_x(((QuestionBrick) brick).getS_offset_x()[3]);
                ((QuestionBrick) brick).setCol(-1);
            }
        }
    }

	private boolean jumpStockByBrick() {
		for (int i = 0; i < bricks.size(); i++) {
		    if (bricks.get(i) == null){
		        continue;
            }
			if (mario.getHead_y() == bricks.get(i).getY() + bricks.get(i).getHeight()) {
				if (mario.getHead_x() >= bricks.get(i).getX() && mario.getHead_x() <= bricks.get(i).getX() + bricks.get(i).getWidth()) {
                    jumpCollisionWithBrick(bricks.get(i), i);
					return true;
				}

				if (mario.getLeft_tou_x() >= bricks.get(i).getX() && mario.getLeft_tou_x() <= bricks.get(i).getX() + bricks.get(i).getWidth()) {
                    jumpCollisionWithBrick(bricks.get(i), i);
                    return true;
				}

				if (mario.getRight_tou_x() >= bricks.get(i).getX() && mario.getRight_tou_x() <= bricks.get(i).getX() + bricks.get(i).getWidth()) {
                    jumpCollisionWithBrick(bricks.get(i), i);
                    return true;
				}
			}
		}

		for (int i = 0; i < bricks.size(); i++) {
            if (bricks.get(i) == null){
                continue;
            }
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
		if (mario.isRight()) {
			moveMarioOrOhters();
		} else if (mario.isLeft()) {
			for (int i = 0; i > mario.getSpeed(); i-- ) {
				if (!moveLeftStockByBlocks()) {
					mario.setX((int) (mario.getX() - 1));
				}
			}
		}

		if (mario.getDirection() == 1) {
			if (mario.getLevel() == 1) {
				mario.setOffset_x(mario.getLv1_rjump_offset_x());
				mario.setOffset_y(mario.getLv1_rjump_offset_y());
			} else if (mario.getLevel() == 2) {
				mario.setOffset_x(mario.getLv1_rjump_offset_x());
				mario.setOffset_y(mario.getLv2_rjump_offset_y());
			} else {
				mario.setOffset_x(mario.getLv3_offset_x());
				mario.setOffset_y(mario.getLv3_offset_y());
			}
		} else {
			if (mario.getLevel() == 1) {
				mario.setOffset_x(mario.getLv1_ljump_offset_x());
				mario.setOffset_y(mario.getLv1_ljump_offset_y());
			} else if (mario.getLevel() == 2) {
				mario.setOffset_x(mario.getLv1_ljump_offset_x());
				mario.setOffset_y(mario.getLv2_ljump_offset_y());
			} else {
				mario.setOffset_x(mario.getLv3_loffset_x());
				mario.setOffset_y(mario.getLv3_offset_y());
			}
		}
		mario.setCol(1);
		mario.setCount(0);

		for (int i = 0; i < 8; i++) {
			if (!jumpStockByBrick()) {
				mario.setY((int) (mario.getY() - 1));
			} else {
				mario.setJumpHeight(mario.getJumpMax());
			}
		}
		mario.setJumpHeight(mario.getJumpHeight() + 4);
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

		for (int i = 0; i < 4; i++) {
			if (!standOnBlocks()) {
				mario.setY((int) (mario.getY() + 1));
			}
		}
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

	public Image getWxzImage() {
		return wxzImage;
	}

	public void setWxzImage(Image wxzImage) {
		this.wxzImage = wxzImage;
	}

	public Image getWxzConvertImage() {
		return wxzConvertImage;
	}

	public void setWxzConvertImage(Image wxzConvertImage) {
		this.wxzConvertImage = wxzConvertImage;
	}

	public ArrayList<Bullet> getBullets() {
		return bullets;
	}

	public void setBullets(ArrayList<Bullet> bullets) {
		this.bullets = bullets;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}