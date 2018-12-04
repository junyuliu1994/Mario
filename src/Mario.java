/**
 * It is a mario class of mario game
 * @author Junyu Liu， Pengyu Yang, Zhengxiang Jin, Feiran Yange
 *
 */

import javafx.animation.Animation;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.util.Duration;

import java.io.Serializable;

public class Mario implements Serializable {
	private static Image image;
	private int col;
	private int count;
	private int offset_x;
	private int offset_y;
	private int width;
	private int height;
	private int x;
	private int y;
	private int direction; //1 right 0 left
	private final int flagstaff_offset_x_small = 515;
	private final int flagstaff_offset_y_small = 81;
	private int SCORE = 0;
	private int COINS = 0;
	private double speed;
	private double jumpHeight;
	private double jumpMax;
	private boolean jump;
	private boolean fall = false;
	private SpriteAnimation marioAnimation;
	private boolean right;
	private boolean left;
	private int [] lv1_offset_x = {195, 195+40, 195+40*2, 195+40*3};//right
	private int [] lv1_left_offset_x = {791, 791-40, 791-40*2, 791-40*3};//left
	static final long serialVersionUID = 1;
	
	private int lv1_offset_y = 80;
	private int lv1_left_offset_y = 80;

	private int lv2_offset_y = 0;
	private int lv2_left_offset_y = 0;

	private int lv1_rjump_offset_x = 395;
	private int lv1_rjump_offset_y = 80;

	private int lv1_ljump_offset_x = 591;
	private int lv1_ljump_offset_y = 78;

	private int lv2_rjump_offset_y = 0;

	private int lv2_ljump_offset_y = 0;

	private int lv3_offset_x = 0;

	private int lv3_offset_y = 0;

	private int lv3_loffset_x = 43;

	private int level = 1;
	private int life = 3;

	private int leftF_x;//leftfeet
	private int leftF_y;

	private int rightF_x;//rightfeet
	private int rightF_y;
    
	
	private int leftH_x;//lefthand
	private int leftH_y;

	private int rightH_x;//right hand 
	private int rightH_y;

	private int head_x;
	private int head_y;//center head

	private int leftTopC_x;//left shoulder
	private int leftTopC_y;

	private int rightTopC_x;//right shoulder
	private int rightTopC_y;

	private int left_tou_x;//left head
	private int left_tou_y;

	private int right_tou_x;//right head
	private int right_tou_y;
	
	private boolean invincible = false;


	public int getInitialHeight() {
		return initialHeight;
	}

	private final int initialHeight = 38;

	public int[] getSize(){
		int[] array = new int[2];
		array[0] = width;
		array[1] = height;
		return array;
	}

	public void setSize(int[] array){
		width = array[0];
		height = array[1];
		if(level == 2) {
			y = 360;
		}
	}


	public int getfCenter_y() {
		return fCenter_y;
	}

	public void setfCenter_y(int fCenter_y) {
		this.fCenter_y = fCenter_y;
	}

	private int fCenter_x ;
	private int fCenter_y;

	public int[] getLv1_left_offset_x() {
		return lv1_left_offset_x;
	}


	public void setLv1_left_offset_x(int[] lv1_left_offset_x) {
		this.lv1_left_offset_x = lv1_left_offset_x;
	}


	public int[] getLv1_offset_x() {
		return lv1_offset_x;
	}


	public void setLv1_offset_x(int[] lv1_offset_x) {
		this.lv1_offset_x = lv1_offset_x;
	}

	public Mario(Image image, int col, int count, int offset_x, int offset_y, int width, int height, int x, int y, int direction, int jumpHeight, int jumpMax, boolean jump, GraphicsContext gc) {
		this.image = image;
		this.col  =   col;
		this.count    =  count;
		this.offset_x =  offset_x;
		this.offset_y =  offset_y;
		this.width    = width;
		this.height   = height;
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.jumpHeight = jumpHeight;
		this.jumpMax = jumpMax;
		this.jump = jump;

		marioAnimation = new SpriteAnimation(this.getImage(),
				Duration.millis(500),
				this.getCount(), this.getCol(),
				this.getOffset_x(), this.getOffset_y(),
				this.getWidth(), this.getHeight(),
				this.getX(), this.getY(), gc, 1, true);
		marioAnimation.setCycleCount(Animation.INDEFINITE);

		resetCollisionCor();
	}


	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getSCORE() { return this.SCORE; }

	public void setSCORE(int Score) { this.SCORE = Score; }

	public int getCOINS() { return this.COINS; }

	public void setCOINS(int x) { this.COINS=x; }

	public int getOffset_x() {
		return offset_x;
	}

	public void setOffset_x(int offset_x) {
		this.offset_x = offset_x;
	}

	public int getOffset_y() {
		return offset_y;
	}

	public void setOffset_y(int offset_y) {
		this.offset_y = offset_y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getfCenter_x() {
		return fCenter_x;
	}

	public void setfCenter_x(int fCenter_x) {
		this.fCenter_x = fCenter_x;
	}

	public void resetCollisionCor() {
		leftF_x = this.x + width/10;
		leftF_y = this.y + height;

		rightF_x = this.x + width*9/10;
		rightF_y = this.y + height;

		leftH_x = this.x;
		leftH_y = this.y + height/2;

		rightH_x = this.x + width;
		rightH_y = this.y + height/2;

		head_x = this.x + width/2;
		head_y = this.y;

		leftTopC_x = this.x + width/4;
		leftTopC_y = this.y + height/4;

		rightTopC_x = this.x + width*3/4;
		rightTopC_y = this.y + height/4;

		left_tou_x = this.x + width/5;
		left_tou_y = this.y;

		right_tou_x = this.x + width*4/5;
		right_tou_y = this.y;

		fCenter_x = this.x + width/2;
		fCenter_y = this.y + height;

	}

	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
		resetCollisionCor();
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
		resetCollisionCor();
	}

	public int getDirection() {
		return direction;
	}


	public void setDirection(int direction) {
		this.direction = direction;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getJumpHeight() {
		return jumpHeight;
	}


	public void setJumpHeight(double jumpHeight) {
		this.jumpHeight = jumpHeight;
	}
	
	public void setInvincible(boolean value) {
		this.invincible = value;
	}
	public boolean getInvincibleStatus() {
		return invincible;
	}

	public double getJumpMax() {
		return jumpMax;
	}


	public void setJumpMax(double jumpMax) {
		this.jumpMax = jumpMax;
	}

	public boolean isJump() {
		return jump;
	}
	
	public boolean isFall() {
		return fall;
	}


	public void setJump(boolean jump) {
		this.jump = jump;
	}
	
	public void setFall(boolean fall) {
		this.fall = fall;
	}

	public SpriteAnimation getMarioAnimation() {
		return marioAnimation;
	}


	public void setMarioAnimation(SpriteAnimation marioAnimation) {
		this.marioAnimation = marioAnimation;
	}


	public boolean isRight() {
		return right;
	}


	public void setRight(boolean right) {
		this.right = right;
	}


	public boolean isLeft() {
		return left;
	}


	public void setLeft(boolean left) {
		this.left = left;
	}

	public int getLeftF_x() {
		return leftF_x;
	}


	public void setLeftF_x(int leftF_x) {
		this.leftF_x = leftF_x;
	}


	public int getLeftF_y() {
		return leftF_y;
	}


	public void setLeftF_y(int leftF_y) {
		this.leftF_y = leftF_y;
	}


	public int getRightF_x() {
		return rightF_x;
	}


	public void setRightF_x(int rightF_x) {
		this.rightF_x = rightF_x;
	}


	public int getRightF_y() {
		return rightF_y;
	}


	public void setRightF_y(int rightF_y) {
		this.rightF_y = rightF_y;
	}


	public int getLeftH_x() {
		return leftH_x;
	}


	public void setLeftH_x(int leftH_x) {
		this.leftH_x = leftH_x;
	}


	public int getLeftH_y() {
		return leftH_y;
	}


	public void setLeftH_y(int leftH_y) {
		this.leftH_y = leftH_y;
	}


	public int getRightH_x() {
		return rightH_x;
	}


	public void setRightH_x(int rightH_x) {
		this.rightH_x = rightH_x;
	}


	public int getRightH_y() {
		return rightH_y;
	}


	public void setRightH_y(int rightH_y) {
		this.rightH_y = rightH_y;
	}


	public int getHead_x() {
		return head_x;
	}


	public void setHead_x(int head_x) {
		this.head_x = head_x;
	}


	public int getHead_y() {
		return head_y;
	}


	public void setHead_y(int head_y) {
		this.head_y = head_y;
	}

	public int getLeftTopC_x() {
		return leftTopC_x;
	}


	public void setLeftTopC_x(int leftTopC_x) {
		this.leftTopC_x = leftTopC_x;
	}


	public int getLeftTopC_y() {
		return leftTopC_y;
	}


	public void setLeftTopC_y(int leftTopC_y) {
		this.leftTopC_y = leftTopC_y;
	}


	public int getRightTopC_x() {
		return rightTopC_x;
	}


	public void setRightTopC_x(int rightTopC_x) {
		this.rightTopC_x = rightTopC_x;
	}


	public int getRightTopC_y() {
		return rightTopC_y;
	}


	public void setRightTopC_y(int rightTopC_y) {
		this.rightTopC_y = rightTopC_y;
	}

	public int getLeft_tou_x() {
		return left_tou_x;
	}


	public void setLeft_tou_x(int left_tou_x) {
		this.left_tou_x = left_tou_x;
	}


	public int getLeft_tou_y() {
		return left_tou_y;
	}


	public void setLeft_tou_y(int left_tou_y) {
		this.left_tou_y = left_tou_y;
	}


	public int getRight_tou_x() {
		return right_tou_x;
	}


	public void setRight_tou_x(int right_tou_x) {
		this.right_tou_x = right_tou_x;
	}


	public int getRight_tou_y() {
		return right_tou_y;
	}


	public void setRight_tou_y(int right_tou_y) {
		this.right_tou_y = right_tou_y;
	}

	public int getLv1_offset_y() {
		return lv1_offset_y;
	}

	public void setLv1_offset_y(int lv1_offset_y) {
		this.lv1_offset_y = lv1_offset_y;
	}

	public int getLv1_left_offset_y() {
		return lv1_left_offset_y;
	}

	public void setLv1_left_offset_y(int lv1_left_offset_y) {
		this.lv1_left_offset_y = lv1_left_offset_y;
	}

	public int getLv2_offset_y() {
		return lv2_offset_y;
	}

	public void setLv2_offset_y(int lv2_offset_y) {
		this.lv2_offset_y = lv2_offset_y;
	}

	public int getLv2_left_offset_y() {
		return lv2_left_offset_y;
	}

	public void setLv2_left_offset_y(int lv2_left_offset_y) {
		this.lv2_left_offset_y = lv2_left_offset_y;
	}

	public int getLv1_rjump_offset_x() {
		return lv1_rjump_offset_x;
	}

	public void setLv1_rjump_offset_x(int lv1_rjump_offset_x) {
		this.lv1_rjump_offset_x = lv1_rjump_offset_x;
	}

	public int getLv1_rjump_offset_y() {
		return lv1_rjump_offset_y;
	}

	public void setLv1_rjump_offset_y(int lv1_rjump_offset_y) {
		this.lv1_rjump_offset_y = lv1_rjump_offset_y;
	}

	public int getLv1_ljump_offset_x() {
		return lv1_ljump_offset_x;
	}

	public void setLv1_ljump_offset_x(int lv1_ljump_offset_x) {
		this.lv1_ljump_offset_x = lv1_ljump_offset_x;
	}

	public int getLv1_ljump_offset_y() {
		return lv1_ljump_offset_y;
	}

	public void setLv1_ljump_offset_y(int lv1_ljump_offset_y) {
		this.lv1_ljump_offset_y = lv1_ljump_offset_y;
	}

	public int getLv2_rjump_offset_y() {
		return lv2_rjump_offset_y;
	}

	public void setLv2_rjump_offset_y(int lv2_rjump_offset_y) {
		this.lv2_rjump_offset_y = lv2_rjump_offset_y;
	}

	public int getLv2_ljump_offset_y() {
		return lv2_ljump_offset_y;
	}

	public void setLv2_ljump_offset_y(int lv2_ljump_offset_y) {
		this.lv2_ljump_offset_y = lv2_ljump_offset_y;
	}

	public int getLv3_offset_x() {
		return lv3_offset_x;
	}

	public void setLv3_offset_x(int lv3_offset_x) {
		this.lv3_offset_x = lv3_offset_x;
	}

	public int getLv3_offset_y() {
		return lv3_offset_y;
	}

	public void setLv3_offset_y(int lv3_offset_y) {
		this.lv3_offset_y = lv3_offset_y;
	}

	public int getLv3_loffset_x() {
		return lv3_loffset_x;
	}

	public void setLv3_loffset_x(int lv3_loffset_x) {
		this.lv3_loffset_x = lv3_loffset_x;
	}

	public int getFlagstaff_offset_x_small() {
		return flagstaff_offset_x_small;
	}

	public int getFlagstaff_offset_y_small() {
		return flagstaff_offset_y_small;
	}


}
