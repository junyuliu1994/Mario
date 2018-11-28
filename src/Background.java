import javafx.scene.image.Image;

import java.io.Serializable;

public class Background implements Serializable {
	private static Image image = new Image("resources/background_version3.png");
	private int moveLength;
	private int width = 2000;
	private int height = 480;
	private int offset_x = 0;
	private int offset_y = 70;
	static final long serialVersionUID = 1;
	
	private int x = 0;
	private int y = 0;
	
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
	
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	public int getMoveLength() {
		return moveLength;
	}
	public void setMoveLength(int moveLength) {
		this.moveLength = moveLength;
	}
}
