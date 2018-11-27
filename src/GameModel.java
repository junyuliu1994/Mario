import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
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
    private Image fireWorkImage = new Image("resources/effects.png");
    private Image itemImage = new Image("resources/items.png");
	private Canvas canvasForMario = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
	private GraphicsContext gcForMario = canvasForMario.getGraphicsContext2D();
	private Mario mario = new Mario(marioImage, 4, 0, 195, 80, 40, 40, 100, 400, 1, 0, 128, false, gcForMario);
	private Flagstaff flagstaff = new Flagstaff();
	private ArrayList<Brick> bricks= new ArrayList<>();
	private ArrayList<Coin> coins= new ArrayList<>();
    private ArrayList<Mushroom> mushrooms = new ArrayList<>();
    private ArrayList<Goomba> goombas = new ArrayList<>();
    private ArrayList<Firework> fireworks = new ArrayList<>();
    private ArrayList<BlackCircle> blackCircles = new ArrayList<>();
    private ArrayList<Information> informations = new ArrayList<>();
	private final int monsterFrameRate = 4;
	private int monsterClockCount = 0;
	private int goombaMovePattern = 0;
	private Brick standBrick;
	public boolean OUT_OF_CONTROL = false;
	

	
	private boolean start = false;
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
	private int stopMarioCountChangeColor = 0;
	private int dispaearMarioCount = 0;

	private void tick() {

	    // flash coins and question brick
		flashCoinsCount++;
		monsterDead();
		if (flashCoinsCount == 8) {
			flashCoins();
			flashQuestionBrick();
			flashFireworks();
			flashCoinsCount = 0;
		}

		// move monster
		monsterClockCount++;
		if (monsterClockCount == monsterFrameRate) {
			 goombaMove();
	         monsterClockCount = 0;
	    }

	    if (!OUT_OF_CONTROL) {
	        // move mario
            move();
            stop();
        } else {
	        // 700 is the position of gate
	        if(mario.getX() <= 690) {
	            mario.setX(mario.getX()+(int)mario.getSpeed());
	            moveRight();
            } else {

	            if (stopMarioCountChangeColor == 3) {
	                dispaearMarioCount--;
                } else {
	                dispaearMarioCount++;
                }

	            stop();

                if (stopMarioCountChangeColor == 0 && dispaearMarioCount==10) {
                    // hide mario
                    stopMarioCountChangeColor++;
                    mario.setOffset_y(mario.getOffset_y()+2130);
                    dispaearMarioCount=0;
                }
                if (stopMarioCountChangeColor == 1 && dispaearMarioCount==5) {
                    // disappear mario
                    mario.setOffset_y(0);
                    mario.setOffset_x(0);
                    stopMarioCountChangeColor++;
                    dispaearMarioCount = 0;
                }
                if (stopMarioCountChangeColor == 2 && dispaearMarioCount == 10) {
                    // add fireworks
                    fireworks.add(new Firework(fireWorkImage, 660, 200, 0));
                    fireworks.add(new Firework(fireWorkImage, 680, 230, 3));
                    fireworks.add(new Firework(fireWorkImage, 720, 220, 4));
                    fireworks.add(new Firework(fireWorkImage, 740, 200, 0));
                    dispaearMarioCount=40;
                    stopMarioCountChangeColor++;
                }
                // add circles
                if (stopMarioCountChangeColor == 3) {
                    int x = -dispaearMarioCount*20+590;
                    int y = -dispaearMarioCount*20+230;
                    int diameter = 250+(dispaearMarioCount*40);
                    blackCircles.add(new BlackCircle(x,y,diameter,false));
                }
                if (stopMarioCountChangeColor == 3 && dispaearMarioCount == -1) {
                    // to stop draw circle
                    stopMarioCountChangeColor++;
                    dispaearMarioCount = 0;
                }
                if (stopMarioCountChangeColor == 4 && dispaearMarioCount == 10) {
                    int x = -dispaearMarioCount*20+590;
                    int y = -dispaearMarioCount*20+230;
                    int diameter = 250+(dispaearMarioCount*40);
                    blackCircles.add(new BlackCircle(x,y,diameter,true));
                    stopMarioCountChangeColor++;
                }

                if (stopMarioCountChangeColor == 5) {
//                    System.out.println("filling text");
                    informations.add(new Information("You won!", Color.WHITE, 410, 380,
                            Font.loadFont(getClass().getResourceAsStream("resources/font.ttf"),20)));
                    stopMarioCountChangeColor++;
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
            if (!standOnBlocks()) {
                fall();
            }

            eatMushroom();

            if (touchCoin()) {
                // TODO: play the score animation

            }

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

                    System.out.println(mario.getX() + ", " + mario.getY());


                }
            } else {
                //gameover
            }

		setChanged();
		notifyObservers();
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

			
			
		}
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
                        if (mario.getLevel() == 1) {
                            mario.setOffset_y(mario.getLv2_offset_y());
                            mario.setY(mario.getY() - mario.getHeight());
                            mario.setHeight(mario.getHeight() * 2);
                            mario.setLevel(mario.getLevel() + 1);
                            mario.resetCollisionCor();

                            mushrooms.set(i, null);
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
	    // don't need to move stuffs
		if ((mario.getX() < 100 && background.getMoveLength() < 1000) || (background.getMoveLength() == 1000)) {
			mario.setX((int) (mario.getX() + mario.getSpeed()));
		}
		else {
		    // move all stuffs except mario
			background.setOffset_x((int) (background.getOffset_x() + mario.getSpeed()));
			background.setMoveLength((int) (background.getMoveLength() + mario.getSpeed()));

			for (Brick brick : bricks) {
                if (brick == null){
                    continue;
                }
				brick.setX(brick.getX() - mario.getSpeed());
			}
//             System.out.println("reset coins");
			for (Coin coin : coins) {
				if (coin == null) continue;
				coin.setX(coin.getX() - mario.getSpeed());
			}

            for (Mushroom mushroom : mushrooms) {
                if (mushroom != null) {
                    mushroom.setX(mushroom.getX() - mario.getSpeed());
                }
            }
            
            for(Goomba goomba: goombas) {
            	goomba.setX(goomba.getX() - mario.getSpeed());
     			gcForMario.drawImage( goomba.getImage(), goomba.getOffset_x(), goomba.getOffset_y(),
     					goomba.getWidth(), goomba.getHeight(),
     					goomba.getX(), goomba.getY(), goomba.getWidth(), goomba.getHeight());
     		}
     		flagstaff.moveFlag(mario.getSpeed());
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

		
		
	}
	
	
	//====These are method of monsters' movement
    /**
     * goombaMove
     * This is method controls goomba's movement
     * Goomba will intinally move to left if it collides with brick or sees cliff
     * It turns around.
     * 
     * @param
     * @return void
     * 
     */
    private void goombaMove(){
    	for(Goomba goomba: goombas) {
    		if(goomba.isDead) {
    			
        			goomba.setCount(2);
        			goomba.setOffset_x(goomba.getIniOffsetX() + (goomba.getWidth()*goomba.getCount()));
        		
        			
        		
    		}else {
    			
    			//refresh Goombas' move Animation  	
    			if (goomba.getCount() < 2) {
        			goomba.setOffset_x(goomba.getIniOffsetX() + (goomba.getWidth()*goomba.getCount()));
        			goomba.setCount(goomba.getCount() + 1);
                } else {
                	goomba.setCount(0);
                }
    			
    			
        		//goomba's actual Movement
        		if(isLeftCollsion(goomba) || isLeftCliff(goomba)){
        			goomba.setSpeed(5);
        		}
        		
        		if(isRightCollison(goomba) || isRightCliff(goomba)) {
        			goomba.setSpeed(-5);
        		}
        		goomba.setX(goomba.getX() + goomba.getSpeed());
    			
    		}
    		
    	
    		
		
	        
    	}
    }
 
    
	/**
     * This is helper method to determine if that Enemy objects collides with brick
     * Checking the left side collision
     * @param
     * @return boolean value
     */
    private boolean isLeftCollsion(Goomba goomba){
 
    	//check if monster collide with brick
    	for (int i = 0; i < bricks.size(); i++) {
    		if(bricks.get(i) == null) {
    			continue;
    		}
    		if(goomba.leftX == bricks.get(i).getX()+ bricks.get(i).getWidth()) {
    			if (goomba.leftY >= bricks.get(i).getY() && goomba.leftY <= bricks.get(i).getY() + bricks.get(i).getHeight()) 
    			return true;
    		}
    	}
		return false;
    }
    
    /**
     * This is helper method to determine if that Monster objects' left is cliff
     * @param
     * @return boolean value
     */
    
    private boolean isLeftCliff(Goomba goomba) {
    	for (int i = 0; i < bricks.size(); i++) {
    		if(bricks.get(i) == null) {
    			continue;
    		}
    		if(goomba.downLeftX <= bricks.get(i).getX() + bricks.get(i).getWidth() && goomba.downLeftX >= bricks.get(i).getX()) {
    			if(goomba.downLeftY == bricks.get(i).getY()) {
    				return false;
    			}
    		}
    		
    	}
		return true;
    }
    
    /**
     * This is helper method to determine if that <onster objects collides with brick
     * Checking the right side collision
     * @param
     * @return boolean value
     */
    private boolean isRightCollison(Goomba goomba) {
    	for (int i = 0; i < bricks.size(); i++) {
    		if(bricks.get(i) == null) {
    			continue;
    		}
    		if(goomba.rightX == bricks.get(i).getX()) {
    			if (goomba.rightY >= bricks.get(i).getY() && goomba.rightY <= bricks.get(i).getY() + bricks.get(i).getHeight()) 
    			return true;
    		}
	    }
    	
		return false;
    	
    }
    
    /**
     * This is helper method to determine if that Monster objects' right is cliff
     * @param
     * @return boolean value
     */
    private boolean isRightCliff(Goomba goomba) {
    	for (int i = 0; i < bricks.size(); i++) {
    		if(bricks.get(i) == null) {
    			continue;
    		}
    		if(goomba.downRightX <= bricks.get(i).getX() + bricks.get(i).getWidth() && goomba.downRightX >= bricks.get(i).getX()) {
    			if(goomba.downLeftY == bricks.get(i).getY()) {
    				return false;
    			}
    		}
    	}
		return true;
    }
    
    /**
     * check if this monster is step on by mario
     * @param goomba - Monster objects need to be check
     * @return boolean value
     */
    private boolean stepOnByMario(Goomba goomba) {
    		if (mario.getLeftF_y() == goomba.getY()) {
				 if (mario.getLeftF_x() >= goomba.getX() && mario.getLeftF_x() <=goomba.getX() + goomba.getWidth() && mario.isJump()) {
					 return true;
				 }
				 if (mario.getRightF_x() >= goomba.getX() && mario.getRightF_x() <= goomba.getX() + goomba.getWidth() && mario.isJump()) {  
					 return true;
				 }
			 }
    	return false;
    }
    
    
    private void monsterDead() {
    	 for(Iterator<Goomba> iterator = goombas.iterator(); iterator.hasNext();) {
         	Goomba temp = iterator.next();
         	if(stepOnByMario(temp)) {
         		temp.isDead = true;
         	}
         }
    }
    
    private void removeDeadMonster() {
    	for(Iterator<Goomba> iterator = goombas.iterator(); iterator.hasNext();) {
         	Goomba temp = iterator.next();
         	if(temp.isDead) {
         		iterator.remove();
         	
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
        for (Firework firework: fireworks) {
            if (firework == null) continue;
            if (firework.getCount() < firework.getCol()) {
                firework.setOffsetX(firework.getInitial_offsetX() + (firework.getWidth()*firework.getCount()));
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

	private boolean touchCoin() {
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

    private boolean touchFlag() {
        return (mario.getX()+mario.getWidth() >= flagstaff.getX());
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
			
			
		}
		else {
			mario.setJumpHeight(mario.getJumpMax());
			
			
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

	public ArrayList<Goomba> getGoombas() {
		return goombas;
	}

	public ArrayList<BlackCircle> getBlackCircles() { return this.blackCircles; }

	public ArrayList<Firework> getFireworks() { return this.fireworks; }

	public ArrayList<Information> getInformations() { return this.informations; }

}