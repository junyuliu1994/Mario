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
	private int [] lv1_offset_x = {195, 195+40, 195+40*2, 195+40*3};//right
	private int [] lv1_left_offset_x = {791, 791-40, 791-40*2, 791-40*3};//left

	private int leftF_x;
	private int leftF_y;

	private int rightF_x;
	private int rightF_y;

	private int leftH_x;
	private int leftH_y;

	private int rightH_x;
	private int rightH_y;

	private int head_x;
	private int head_y;

	private int leftTopC_x;
	private int leftTopC_y;

	private int rightTopC_x;
	private int rightTopC_y;

	private int left_tou_x;
	private int left_tou_y;

	private int right_tou_x;
	private int right_tou_y;

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

		resetCollisionCor();
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

	private void resetCollisionCor() {
		leftF_x = this.x;;
		leftF_y = this.y + height;

		rightF_x = this.x + width;
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

}
