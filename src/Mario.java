import javafx.animation.Animation;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class Mario {
	private Image image;
    private int col;
    private int count;
    private int offset_x;
    private int offset_y;
    private int width;
    private int height;
    private int x;
	private int y;
	private int direction; //1 right 0 left
	private double speed;
	private double jumpHeight;
	private double jumpMax;
	private boolean jump;
	private SpriteAnimation marioAnimation;
	private boolean right;
	private boolean left;
	
	private int rightRelease;
	public int getRightRelease() {
		return rightRelease;
	}


	public void setRightRelease(int rightRelease) {
		this.rightRelease = rightRelease;
	}


	public int getLeftRelease() {
		return leftRelease;
	}


	public void setLeftRelease(int leftRelease) {
		this.leftRelease = leftRelease;
	}


	private int leftRelease;

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
    }


	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
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
	
	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
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
	
	public double getJumpMax() {
		return jumpMax;
	}


	public void setJumpMax(double jumpMax) {
		this.jumpMax = jumpMax;
	}
	
	public boolean isJump() {
		return jump;
	}


	public void setJump(boolean jump) {
		this.jump = jump;
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
	
}
