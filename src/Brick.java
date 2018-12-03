/**
 * It is a brick class of mario game
 * @author Junyu Liu， Pengyu Yang, Zhengxiang Jin, Feiran Yange
 *
 */

import javafx.scene.image.Image;

import java.io.Serializable;

public class Brick implements Serializable {
	private static Image image;
	private int offset_x = 0;
	private int offset_y = 0;
	private int width;
	private int height;
	private double x;
	private double y;
	static final long serialVersionUID = 1;

	public Brick(Image image, int width, int height, double x, double y) {
		this.image = image;
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
	}

	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
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
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}

	public void setOffset_x(int offset_x) {
		this.offset_x = offset_x;
	}

	public void setOffset_y(int offset_y) {
		this.offset_y = offset_y;
	}
}