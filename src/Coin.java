import javafx.scene.image.Image;

public class Coin {
	private final static int SCORE = 100;
	
	private Image image;
	private int col;
	private int count;
	private int offset_x;
    private int offset_y;
    private int width;
    private int height;
    private double x;
	private double y;
	
	public Coin(Image image, int col, int count, int offset_x, int offset_y, int width, int height, int x, int y) {
		this.image = image;
		this.col = col;
		this.count = count;
		this.offset_x = offset_x;
		this.offset_y = offset_y;
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
	}
	
	
	public Image getImage() {
		return image;
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
	public void setImage(Image image) {
		this.image = image;
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
