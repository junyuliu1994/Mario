import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GameModel extends Observable implements Serializable {
	// didn't use this info yet
	private final int BLOCK_WIDTH = 40;
	private final int BLOCK_HEIGHT = 40;

	private static final int WINDOW_WIDTH = 1000;
	private static final int WINDOW_HEIGHT = 480;
    static final long serialVersionUID = 1;

    private Background background = new Background();
	private static Image marioImage = new Image("resources/mario.png");
	private static Image blocks = new Image("resources/blocks.png");
	private static Image marioConvertImage = new Image("resources/mario-ConvertImage.png");
	private static Image fireWorkImage = new Image("resources/effects.png");
	private static Image wxzImage = new Image("resources/wxz.png");
	private static Image wxzConvertImage = new Image("resources/wxz-convert.png");
    private static Image itemImage = new Image("resources/items.png");
	private static Canvas canvasForMario = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
	private static GraphicsContext gcForMario = canvasForMario.getGraphicsContext2D();
	private Mario mario = new Mario(marioImage, 4, 0, 195, 80, 40, 40, 100, 400, 1, 0, 192, false, gcForMario);
    private Flagstaff flagstaff = new Flagstaff();
	private ArrayList<Brick> bricks= new ArrayList<>();
	private ArrayList<Coin> coins= new ArrayList<>();
    private ArrayList<Mushroom> mushrooms = new ArrayList<>();
    private ArrayList<Monster> monsters = new ArrayList<>();
    private ArrayList<Bullet> bullets = new ArrayList<>();
	private ArrayList<Firework> fireworks = new ArrayList<>();
	private ArrayList<BlackCircle> blackCircles = new ArrayList<>();
	private ArrayList<Information> informations = new ArrayList<>();

	private final int monsterFrameRate = 4;
	private int monsterClockCount = 0;
	private int flashCoinsCount = 0;

    private int stopMarioCountChangeColor = 0;

    private int disappearMarioCount = 0;
	private int invincibleCount = 0;
	private static AnimationTimer at;

    private boolean OUT_OF_CONTROL = false;
	private int level = 0;

    private Brick standBrick;

	private boolean start = false;
	private boolean paused = false;
	private int score = 0;
	private int i;

	private boolean moveBackground = false;
	public void setBackground(Image image){
		background.setImage(image);
	}

	public boolean isStart() {
		return start;
	}

	public boolean won(){
			return stopMarioCountChangeColor == 6;
	}

	public int getMarioLevel(){
		return mario.getLevel();
	}

	public void setMarioLevel(int level){
		mario.setLevel(level);
	}
	public void setLevel(int level){
		this.level = level;
	}

	public void setMario(Mario mario){
		this.mario = mario;
	}

	public int getLevel(){
		return level;
	}


	public void setStart(boolean start) {
		this.start = start;
	}

	/**
	 * call animation timer to start draw image on window,
	 * 60 frame pre second
	 */
	public void start() {
		at = new AnimationTimer() {
			@Override
			public void handle(long now) {
				// perform ticksPerFrame ticks
				for (int i = 0; i < 1; i++) {
					tick();
				}
				//System.out.print(123);
			}


		};
		at.start();
	}


	/**
	 * restore gc and canvas to class
	 * @param gc GraphicsContext
	 * @param canvas Canvas
	 */
	public void restore(GraphicsContext gc, Canvas canvas){
		canvasForMario = canvas;
		gcForMario = gc;
		mario.getMarioAnimation().restore(gc);

	}

	/**
	 * get the pause status
	 * @return whether the game has been pasused or not
	 */
	public boolean getPaused(){
		return paused;
	}

	/**
	 * pause the game
	 */
	public void pause(){
		at.stop();
		paused = true;
	}

	/**
	 * resume the game
	 */
	public void resume(){
		at.start();
		paused = false;
	}

	public void tick() {
        flashCoinsCount++;

		monsterClockCount++;
		invincibleCount++;
		if(level == -1){
			i++;
			if(touchFlag()){
				if(i%3 != 0){
					return;
				}
			}
			else if(i%10 != 0) {
				return;
			}
		}

		if(invincibleCount >= 128) {
			invicibleEnd();
			//System.out.println("invincible end");
			invincibleCount = 0;
		}else {
			invincibleFrame();
		}


		if (flashCoinsCount == 8) {
			flashCoins();
			flashQuestionBrick();
			flashFireworks();
			flashCoinsCount = 0;
		}


		if (monsterClockCount == monsterFrameRate) {
			 monsterMove();
	         monsterClockCount = 0;
	    }

		monsterMarioCollision();

		if (!OUT_OF_CONTROL) {
			move();
			stop();

		} else {	// play the animation after Mario touch the flagstaff
			// 690 is the position of gate
			if(mario.getX() <= 690) {
				mario.setX(mario.getX()+2);
				moveRight();
			} else {

				if (stopMarioCountChangeColor == 3) {
					disappearMarioCount--;
				} else {
					disappearMarioCount++;
				}

				if (stopMarioCountChangeColor == 0 && disappearMarioCount==10) {
					// hide mario
					stopMarioCountChangeColor++;
					mario.setOffset_y(mario.getOffset_y()+2130);
					disappearMarioCount=0;
				}
				if (stopMarioCountChangeColor == 1 && disappearMarioCount==5) {
					// disappear mario
					mario.setOffset_y(0);
					mario.setOffset_x(0);
					stopMarioCountChangeColor++;
					disappearMarioCount = 0;
				}
				if (stopMarioCountChangeColor == 2 && disappearMarioCount == 10) {
					// add fireworks
					fireworks.add(new Firework(fireWorkImage, 660, 200, 0));
					fireworks.add(new Firework(fireWorkImage, 680, 230, 3));
					fireworks.add(new Firework(fireWorkImage, 720, 220, 4));
					fireworks.add(new Firework(fireWorkImage, 740, 200, 0));
					disappearMarioCount=40;
					stopMarioCountChangeColor++;
				}
				// add circles
				if (stopMarioCountChangeColor == 3) {
					int x = -disappearMarioCount*20+590;
					int y = -disappearMarioCount*20+230;
					int diameter = 250+(disappearMarioCount*40);
					blackCircles.add(new BlackCircle(x,y,diameter,false));
				}
				if (stopMarioCountChangeColor == 3 && disappearMarioCount == -1) {
					// to stop draw circle
					stopMarioCountChangeColor++;
					disappearMarioCount = 0;
				}
				if (stopMarioCountChangeColor == 4 && disappearMarioCount == 10) {
					int x = -disappearMarioCount*20+590;
					int y = -disappearMarioCount*20+230;
					int diameter = 250+(disappearMarioCount*40);
					blackCircles.add(new BlackCircle(x,y,diameter,true));
					stopMarioCountChangeColor++;
                }
				if (stopMarioCountChangeColor == 5) {
//                    System.out.println("filling text");
					informations.add(new Information("You won!", Color.WHITE, 410, 380,
							Font.loadFont(getClass().getResourceAsStream("resources/font.ttf"),20)));
					stopMarioCountChangeColor++;
					setChanged();
					notifyObservers();
				}
            }
		}

		// after touching the flag, no more interaction

		if (touchFlag()) {
			OUT_OF_CONTROL = true;
			if (!standOnBlocks()) {
				// set image of holding flagstaff
				mario.setOffset_x(mario.getFlagstaff_offset_x_small());
				if (mario.getLevel() == 1) {
					mario.setOffset_y(mario.getFlagstaff_offset_y_small());
				} else {
					mario.setOffset_y(mario.getFlagstaff_offset_y_small() - mario.getHeight());
				}

				int fallingSpeed = 4;
				mario.setY(mario.getY() + fallingSpeed);
				// set location of image
				mario.setX(flagstaff.getX() - mario.getWidth());

			} else {
				// stand on ground

				// set 710 is the gate's location
			}
		}

		if (touchCoin()) {
			// TODO: play the score animation
		}

		removeDeadMonster();
		fall();
		eatMushroom();
		koopaMonsterCollision();
		move();

		bulletMove();
		resetBulletSpeed();

		stop();
		
		removeDeadMonster();
       

		fall();

        eatMushroom();

		

		// check mario's life
		if (mario.getLife() > 0) {

			if (jumpToHoleDeath()) {
				mario.setLevel(1);
				mario.setDirection(1);
				mario.setImage(marioImage);
				mario.setOffset_x(mario.getLv1_offset_x()[0]);
				mario.setOffset_y(mario.getLv1_offset_y());
				mario.setHeight(mario.getInitialHeight());

				mario.setLife(mario.getLife() - 1);
				mario.setX((int) standBrick.getX());
				mario.setY((int) standBrick.getY() - mario.getHeight());
			}
		} else {
			//gameover
		}
		setChanged();
		notifyObservers();
	}


	public void resetBulletSpeed(){
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

	public void bulletMove(){
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

	public boolean jumpToHoleDeath(){
		if (mario.getY() > 440){
			return true;
		}
		return false;
	}

	public boolean stop() {
		if (mario.isRight() ||  mario.isLeft() || mario.isJump()) {
			return false;
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

			return true;

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


			return true;

		}
	}

	public boolean bulletCollision(Bullet bullet){
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
                    bullets.remove(bullet);
                    return true;
				}
			}
		}

		for (int i = 0; i < monsters.size(); i++){
			if (monsters.get(i) == null){
				continue;
			}
			if (bullet.getX() + bullet.getWidth() == monsters.get(i).getX()){
				if (bullet.getY() + bullet.getHeight()/2 >= monsters.get(i).getY() && bullet.getY() + bullet.getHeight()/2 <= monsters.get(i).getY() + monsters.get(i).getHeight()){
				    System.out.println(monsters.size());
				    monsters.set(i, null);
                    System.out.println(monsters.size());
                    bullets.remove(bullet);
				    score+=100;
				    monsters.get(i).isDead = true;
					return true;
				}
			}
		}
		return false;
	}

	public boolean standOnBlocks() {
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

	public boolean eatMushroom(){
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
                            return true;
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
								return true;
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
								return true;
							}
						}
                    }
                }
            }
        }
        return false;
	}

	//���ƶ�����
	public boolean moveRightStockByBlocks() {
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
			if (mario.getRightTopC_x() == bricks.get(i).getX()) { 
				if (mario.getRightTopC_y() >= bricks.get(i).getY() && mario.getRightTopC_y() <= bricks.get(i).getY() + bricks.get(i).getHeight()) {
					return true;
				}
			}
		}

		return false;
	}

	//���ƶ�����
	public boolean moveLeftStockByBlocks() {
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
	public void moveMarioOrOhters() {
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

            for(Monster monster: monsters) {
            	monster.setX(monster.getX() - mario.getSpeed());
     		}
			flagstaff.moveFlag(mario.getSpeed());
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

	public void moveRight() {
		if (mario.getLevel() < 3) {
			mario.setImage(marioImage);
			mario.setCol(4);
			if (mario.getCount() < mario.getCol()) {
				mario.setOffset_x(mario.getLv1_offset_x()[mario.getCount()]);
				mario.setCount(mario.getCount() + 1);
			} else {
				mario.setCount(0);
			}
		} else{
			mario.setImage(wxzImage);
			mario.setOffset_x(mario.getLv3_offset_x());
			mario.setOffset_y(mario.getLv3_offset_y());
			mario.setCol(1);
			mario.setCount(0);
		}
		mario.setDirection(1);

		moveMarioOrOhters();
	}

	public void moveLeft() {
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

		mario.setX((int) (mario.getX() + mario.getSpeed()));
	}

    /**
     * monsterMove
     * This is method controls goomba's movement
     * monster will intinally move to left if it collides with brick or sees cliff
     * It turns around.
     *
     * @return void
     *
     */
	 public void monsterMove(){
	    	for(Monster monster: monsters) {
	    		if(monster != null) {
	    			
	    			//refresh Monster' move Animation  	
	    			if (monster.getCount() < 2) {
	        			monster.setOffset_x(monster.getIniOffsetX() + (monster.getWidth()*monster.getCount()));
	        			monster.setCount(monster.getCount() + 1);
	                } else {
	                	monster.setCount(0);
	                }
	    			
	        		//Monster's actual Movement
	        		if(isLeftCollsion(monster) || isLeftCliff(monster)){
	        			if(monster instanceof Koopa) {
	        				((Koopa) monster).koopaMoveRight();
	        			}
	        			monster.setSpeed(5);
	        		}
	        		
	        		if(isRightCollison(monster) || isRightCliff(monster)) {
	        			if(monster instanceof Koopa) {
	        				((Koopa) monster).koopaMoveLeft();
	        				if(((Koopa) monster).getShell()) {
	        					monster.isDead = true;
	        				}
	        			}
	        			
	        			monster.setSpeed(-5);
	        		}
	        		
	        		if(monster instanceof Koopa) {
	        			if(((Koopa) monster).getShell()) {
	  
	        				monster.setSpeed(0);
	        			}
	        			
	        			if(((Koopa) monster).isRolling()) {
	        				monster.setSpeed(25);
	        			}
	        			
	        		}
	        		
	        		if(monster instanceof PiranhaPlant) {
	        			monster.setSpeed(0);
	        		}
	        		
	        	
	        		//move monsters
	        		monster.setX(monster.getX() + monster.getSpeed());

	    		}
	    		
	    	}
	    }

		/**
	     * This is helper method to determine if that Enemy objects collides with brick
	     * Checking the left side collision
	     * @param Monster Object
	     * @return boolean value
	     */
	 public boolean isLeftCollsion(Monster monster){
	    	//check if monster collide with brick
	    	for (int i = 0; i < bricks.size(); i++) {
	    		if(bricks.get(i) == null) {
	    			continue;
	    		}
	    		if(monster.getLeftX() <= bricks.get(i).getX()+ bricks.get(i).getWidth() && monster.getLeftX() > bricks.get(i).getX()) {
	    			if (monster.getLeftY() >= bricks.get(i).getY() && monster.getLeftY() <= bricks.get(i).getY() + bricks.get(i).getHeight()) 
	    			return true;
	    		}
	    	}
			return false;
			}
	    

    /**
     * This is helper method to determine if that Monster objects' left is cliff
     * @param monster Object
     * @return boolean value
     */

	public boolean isLeftCliff(Monster monster) {
    	for (int i = 0; i < bricks.size(); i++) {
    		if(bricks.get(i) == null) {
    			continue;
    		}
    		if(monster.getDownLeftX() <= bricks.get(i).getX() + bricks.get(i).getWidth() && monster.getDownLeftX() >= bricks.get(i).getX()) {
    			if(monster.getDownLeftY() == bricks.get(i).getY()) {
    				return false;
    			}
    		}
    	}
		return true;
    }

    /**
     * This is helper method to determine if that monster objects collides with brick
     * Checking the right side collision
     * @param monster Object
     * @return boolean value
     */
	public boolean isRightCollison(Monster monster) {
    	for (int i = 0; i < bricks.size(); i++) {
    		if(bricks.get(i) == null) {
    			continue;
    		}

    		if(monster.getRightX() >= bricks.get(i).getX() && monster.getRightX() <= bricks.get(i).getX() +  bricks.get(i).getWidth()) {
    			if (monster.getRightY() >= bricks.get(i).getY() && monster.getRightY() <= bricks.get(i).getY() + bricks.get(i).getHeight()) 

    			return true;
    		}
	    }

		return false;

    }

    /**
     * This is helper method to determine if that Monster objects' right is cliff
     * @param monster Object
     * @return boolean value
     */
	public boolean isRightCliff(Monster monster) {
    	for (int i = 0; i < bricks.size(); i++) {
    		if(bricks.get(i) == null) {
    			continue;
    		}
    		if(monster.getDownRightX() <= bricks.get(i).getX() + bricks.get(i).getWidth() && monster.getDownRightX() >= bricks.get(i).getX()) {
    			if(monster.getDownLeftY() == bricks.get(i).getY()) {
    				return false;
    			}
    		}
    	}
		return true;
    }

    /**
     * check if this monster is step on by mario
     * @param monster - Monster objects need to be check
     * @return boolean value
     */
   public boolean stepOnByMario(Monster monster) {
    	if(mario.isFall()) {
    		if (mario.getLeftF_y() == monster.getY()) {
				 if (mario.getLeftF_x() >= monster.getX() && mario.getLeftF_x() <=monster.getX() + monster.getWidth() && mario.isJump()) {
					 return true;
				 }
				 if (mario.getRightF_x() >= monster.getX() && mario.getRightF_x() <= monster.getX() + monster.getWidth() && mario.isJump()) {  
					 return true;
				 }
			 }
    	}
    	return false;
    }
   
    /**
     * This method check if monster is collide by Mario
     */
   /**
    * This method check if monster is collide by Mario
    */
   public boolean isMarioCollideMonster(Monster monster) {
   	
   	
   	//check all possible collision point
   	if(mario.getInvincibleStatus() == false) {
   		if(mario.getLevel() == 1 || mario.getLevel() == 3) {
       		if(mario.getRightH_x() >= monster.getLeftX() && mario.getRightH_x() < monster.getLeftX() + monster.getWidth() ) {
       			if(mario.getRightH_y() >  monster.getUpLeftY() && mario.getRightH_y() < monster.getUpLeftY() + monster.getWidth()) {
           			mario.setInvincible(true);
           			return true;
           		}
           	}
           	
           	if(mario.getLeftH_x() <= monster.getRightX() && mario.getLeftH_x() >= monster.getX()) {
           		if(mario.getLeftH_y()  >  monster.getUpRightY() && mario.getLeftH_y() < monster.getUpRightY() + monster.getWidth()) {
           			mario.setInvincible(true);
           			return true;
           		}
           	}
       	}
       	
       	if(mario.getLevel()==2) {
       		if(mario.getRightH_x() >= monster.getLeftX() && mario.getRightH_x() < monster.getLeftX() + monster.getWidth() ) {
       			if(mario.getRightH_y() + 20 >  monster.getUpLeftY() && mario.getRightH_y() + 20 < monster.getUpLeftY() + monster.getWidth()) {
           			mario.setInvincible(true);
           			return true;
           		}
           	}
    
           	if(mario.getLeftH_x() <= monster.getRightX() && mario.getLeftH_x() >= monster.getX()) {
           		if(mario.getLeftH_y() + 20 >  monster.getUpRightY() && mario.getLeftH_y() + 20< monster.getUpRightY() + monster.getWidth()) {
           			mario.setInvincible(true);
           			return true;
           		}
           	}
       		
       	}
   	}
   	
   	
   	return false;
   	}


  
    /**
     * This method checks if all collisions between mario and monsters
     */
    public void monsterMarioCollision() {
    	 for(Iterator<Monster> iterator = monsters.iterator(); iterator.hasNext();) {
         	Monster temp = iterator.next();
         	if(isMarioCollideMonster(temp)) {
         		//check if monster is koopaShell
            	if(temp instanceof Koopa) {
            		if(((Koopa) temp).getShell()) {
            			return;
            		}
            	}
            	
            	//Mario will level down or dead.
         		if (mario.getLevel() == 1){
         			mario.setLife(mario.getLife() - 1);
                    //mario is dead 
         			System.out.println("mario dead");

				}else if(mario.getLevel() == 2) {
        		    mario.setLevel(1);
                   if (mario.getDirection() == 1) {
                       mario.setCol(1);
                       mario.setCount(0);
                       mario.setOffset_x(mario.getLv1_offset_x()[0]);
                       mario.setOffset_y(mario.getLv1_offset_y());
                       mario.setHeight(mario.getInitialHeight());
                       mario.resetCollisionCor();
                       for (int i = 0; i < mario.getInitialHeight(); i++) {
                           System.out.println(mario.getY() + ", " + mario.getHeight());
                           if (!standOnBlocks()) {
                               mario.setY(mario.getY() + 1);
                           }
                       }
                   }
                   else{
                       mario.setCol(1);
                       mario.setCount(0);
                       mario.setOffset_x(mario.getLv1_left_offset_x()[0]);
                       mario.setOffset_y(mario.getLv1_offset_y());
                       mario.setHeight(mario.getInitialHeight());
                       mario.resetCollisionCor();
                       for (int i = 0; i < mario.getInitialHeight(); i++) {
                           if (!standOnBlocks()) {
                               mario.setY(mario.getY() + 1);
                           }
                       }
                   }
					
					//back to level 1
					System.out.println("mario become lv1");
				}else {
                   mario.setLevel(2);
                   if (mario.getDirection() == 1){
                       mario.setImage(marioImage);
                       mario.setCol(1);
                       mario.setCount(0);
                       mario.setOffset_x(mario.getLv1_offset_x()[0]);
                       mario.setOffset_y(mario.getLv2_offset_y());
                       mario.setHeight(mario.getInitialHeight() * 2);
                       mario.resetCollisionCor();
                       for (int i = 0; i < mario.getInitialHeight(); i++) {
                           if (!standOnBlocks()) {
                               mario.setY(mario.getY() - 1);
                           }
                       }
                   }
                   else{
                       mario.setImage(marioConvertImage);
                       mario.setCol(1);
                       mario.setCount(0);
                       mario.setOffset_x(mario.getLv1_left_offset_x()[0]);
                       mario.setOffset_y(mario.getLv2_offset_y());
                       mario.setHeight(mario.getInitialHeight() * 2);
                       mario.resetCollisionCor();
                       for (int i = 0; i < mario.getInitialHeight(); i++) {
                           if (!standOnBlocks()) {
                               mario.setY(mario.getY() - 1);
                           }
                       }
                   }
                   //back to level 2
					System.out.println("mario become lv2");
					
				}

        		
        	}
        	
         	if(stepOnByMario(temp)) {
         		if(temp instanceof Koopa) {
         			if(!((Koopa) temp).getShell()) {
         				((Koopa) temp).setShell();
         				temp.setOffset_x(400);
        			    temp.setInitiall_offsetX(400);
        			    temp.setOffset_y(40);
        			    temp.setHeight(40);
        			    temp.setY(temp.getY()/9*10);
         			}else {
         				((Koopa) temp).setRolling();
         			}
         			
      
         		}
         		if(temp instanceof Goomba) {
         			temp.isDead = true;
         		}
         		
         	}
        }
   }



    private void koopaMonsterCollision() {
    	for(Iterator<Monster> iterator = monsters.iterator(); iterator.hasNext();) {
    		Monster temp = iterator.next();
    		if(!(temp instanceof Koopa) ) {
    			continue;
    		}else {
    			if(((Koopa) temp).getShell() && ((Koopa) temp).isRolling()) {
    				
    				for(Iterator<Monster> iterator2 = monsters.iterator(); iterator2.hasNext();) {
            			Monster temp2 = iterator2.next();
            			if(temp.getRightX() >= temp2.getLeftX() && temp.getRightX() < temp2.getLeftX() + temp2.getWidth() ) {
                			if(temp.getRightY() >  temp2.getUpLeftY() && temp.getRightY() < temp2.getUpLeftY() + temp2.getWidth()) {
                				temp2.isDead = true;
                			
                    		}
                    	}
            		}
    			}
    			
    		}
    	}
    	
    }
    
    private void removeDeadMonster() {

    	for(Iterator<Monster> iterator = monsters.iterator(); iterator.hasNext();) {
    		Monster temp = iterator.next();
         	if(temp.isDead) {
         		iterator.remove();

         	}
         }

		for (int i = 0; i > mario.getSpeed(); i--) {
			if (!moveLeftStockByBlocks()) {
				mario.setX((int) (mario.getX() - 1));
			}
		}

	}
	private void flashCoins(){
		for (Coin coin : coins) {
			if (coin == null) continue;
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

	private void flashFireworks() {
		for (Firework firework : fireworks) {
			if (firework == null) continue;
			if (firework.getCount() < firework.getCol()) {
				firework.setOffsetX(firework.getInitial_offsetX() + (firework.getWidth() * firework.getCount()));
				firework.setCount(firework.getCount() + 1);
			} else {
				firework.setCount(0);
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

	public boolean jumpStockByBrick() {
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

	private void invicibleEnd() {
		mario.setInvincible(false);
	}

	private void invincibleFrame() {

		return;
	}

	public boolean touchCoin() {
//        System.out.println("getting check the coin");
		for (int i = 0; i < coins.size(); i++) {
			if (coins.get(i) == null) continue;
			// when jump
			if (mario.getHead_y() == coins.get(i).getY()+coins.get(i).getHeight()) {
				if (mario.getHead_x() >= coins.get(i).getX() && mario.getHead_x() <= coins.get(i).getX() + coins.get(i).getWidth()) {
//                    jumpCollisionWithBrick(coin, i);
					mario.setCount(mario.getCount() + coins.get(i).getScore());
					coins.set(i, null);
					return true;
				}

				if (mario.getLeft_tou_x() >= coins.get(i).getX() && mario.getLeft_tou_x() <= coins.get(i).getX() + coins.get(i).getWidth()) {
//                    jumpCollisionWithBrick(bricks.get(i), i);
					mario.setCount(mario.getCount() + coins.get(i).getScore());
					coins.set(i, null);
					return true;
				}

				if (mario.getRight_tou_x() >= coins.get(i).getX() && mario.getRight_tou_x() <= coins.get(i).getX() + coins.get(i).getWidth()) {
//                    jumpCollisionWithBrick(bricks.get(i), i);
					mario.setCount(mario.getCount() + coins.get(i).getScore());
					coins.set(i, null);
					return true;
				}
			}
			if (mario.getRightTopC_y() == coins.get(i).getY()  + coins.get(i).getHeight()) {
				if (mario.getRightTopC_x() >= coins.get(i).getX() && mario.getRightTopC_x() <= coins.get(i).getX() + coins.get(i).getWidth()) {
					mario.setCount(mario.getCount() + coins.get(i).getScore());
					coins.set(i, null);
					return true;
				}

				if (mario.getLeftTopC_x() >= coins.get(i).getX() && mario.getLeftTopC_x() <= coins.get(i).getX() + coins.get(i).getWidth()) {
					mario.setCount(mario.getCount() + coins.get(i).getScore());
					coins.set(i, null);
					return true;
				}
			}
			// while moving
			if ((mario.getY() <= coins.get(i).getHeight()+coins.get(i).getY() && mario.getY() >= coins.get(i).getY()) ||
					(mario.getY()+mario.getHeight() >= coins.get(i).getY() && mario.getY()+mario.getHeight() <= coins.get(i).getY()+BLOCK_HEIGHT)) {
				if ((mario.getX() <= coins.get(i).getX()+coins.get(i).getWidth() && mario.getX() >= coins.get(i).getX()) ||
						(mario.getX()+mario.getWidth() >= coins.get(i).getX() && mario.getX()+mario.getWidth() <= coins.get(i).getX()+coins.get(i).getWidth())) {
					mario.setCount(mario.getCount() + coins.get(i).getScore());
					coins.set(i, null);
					return true;
				}
			}


		}

		return false;
	}

	public boolean touchFlag() {
		return (mario.getX()+mario.getWidth() >= flagstaff.getX());
	}

	public void jump() {
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
				return;
			}
		}
		mario.setJumpHeight(mario.getJumpHeight() + 4);
	}


	public void fall() {
		mario.setFall(true);

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


	public Image getMarioConvertImage() {
		return marioConvertImage;
	}

	public Image getBlocks() {
		return blocks;
	}


	public Background getBackground() {
		return background;
	}

	public Mario getMario() {
		return mario;
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

	public GraphicsContext getGcForMario() {
		return gcForMario;
	}

    public ArrayList<Mushroom> getMushrooms() {
        return mushrooms;
    }


	public ArrayList<Monster> getMonsters() {
		return monsters;
	}
	public Image getWxzImage() {
		return wxzImage;
	}


	public Image getWxzConvertImage() {
		return wxzConvertImage;
	}


	public ArrayList<Bullet> getBullets() {
		return bullets;
	}


	public ArrayList<BlackCircle> getBlackCircles() { return this.blackCircles; }

	public ArrayList<Firework> getFireworks() { return this.fireworks; }

	public ArrayList<Information> getInformations() { return this.informations; }

	public boolean isOUT_OF_CONTROL() {return this.OUT_OF_CONTROL;}

	public boolean isMoveBackground() {
		return moveBackground;
	}

	public void setMoveBackground(boolean moveBackground) {
		this.moveBackground = moveBackground;
	}

    public void setOUT_OF_CONTROL(boolean OUT_OF_CONTROL) {
        this.OUT_OF_CONTROL = OUT_OF_CONTROL;
    }

    public void setStandBrick(Brick standBrick) {
        this.standBrick = standBrick;
    }


    public void setStopMarioCountChangeColor(int stopMarioCountChangeColor) {
        this.stopMarioCountChangeColor = stopMarioCountChangeColor;
    }

    public int getDisappearMarioCount() {
        return disappearMarioCount;
    }

    public void setDisappearMarioCount(int disappearMarioCount) {
        this.disappearMarioCount = disappearMarioCount;
    }

    public Flagstaff getFlagstaff() {
        return flagstaff;
    }

}