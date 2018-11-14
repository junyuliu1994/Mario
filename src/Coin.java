import javafx.scene.image.Image;

public class Coin {
	private final static int SCORE = 100;
	
	private Image image;
	private final int col = 3;
	private final int count = 3;
	private final int offset_x = 948;
    private final int offset_y = 41;

    // to remove the side of coins, have set this values
    //    private final int BLOCK_WIDTH = 38;        // the pixels of one block
    //    private final int BLOCK_HEIGHT = 37;
    public SpriteAnimation animation;
    private int width;
    private int height;
    private double x;
	private double y;
	
	public Coin(Image image, int width, int height, int x, int y) {
	    this.image = image;
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
	}

	void setAnimation(SpriteAnimation newAnimation) { this.animation = newAnimation;}
//	SpriteAnimation getAnimation() {return this.animation;}
	public Image getImage() {
		return image;
	}
	public int getCol() {
		return col;
	}
	public int getCount() {
		return count;
	}
	public int getOffset_x() {
		return offset_x;
	}

	public int getOffset_y() {
		return offset_y;
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
	public double getX() {
		return x;
	}
	public void setX(double d) {
		this.x = d;
	}
	public double getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public static int getScore() {
		return SCORE;
	}
}
