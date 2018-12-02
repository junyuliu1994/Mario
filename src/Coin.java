import javafx.scene.image.Image;

import java.io.Serializable;

public class Coin implements Serializable {
	private final int SCORE = 100;

	private static Image image;
	private  int col = 3;
	private  int count = 3;
	final int initial_offset_x = 948;
	private int offset_x = 948;
	private int offset_y = 41;

	// to remove the side of coins, have set this values
	private final int BLOCK_WIDTH = 38;        // the pixels of one block
	private final int BLOCK_HEIGHT = 37;

	private int width;
	private int height;
	private double x;
	private double y;

	public Coin(Image image, int x, int y) {
		this.image = image;
		this.width = 38;
		this.height = 37;
		this.x = x;
		this.y = y;
	}

	public Image getImage() {
		return image;
	}
	public int getCol() {
		return col;
	}
	void setCount(int newCount) {this.count = newCount;}
	public int getCount() {
		return count;
	}
	public void setOffset_x(int x) {this.offset_x = x;}
	public int getOffset_x() {
		return offset_x;
	}
	void setoffset_y(int y) {this.offset_y = y;}
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
	public int getScore() {
		return SCORE;
	}
}