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

	/**
	 * brick contstrucotr 
	 * @param Imgae image - image of brick 
	 * @param int width - width of brick
	 * @param int height - height of brick
	 * @param int x - x coordinate of brick
	 * @param int y - y coordinate of brick
	 */
	public Brick(Image image, int width, int height, double x, double y) {
		this.image = image;
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
	}

	/**
	 * @return mgae image - image of brick 
	 */
	public Image getImage() {
		return image;
	}
	/**
	 * @param igae image - image of brick 
	 */
	public void setImage(Image image) {
		this.image = image;
	}
	/**
	 * @return int  offset x - offset x of brick 
	 */
	public int getOffset_x() {
		return offset_x;
	}

	/**
	 * @return int  offset y - offset y of brick 
	 */
	public int getOffset_y() {
		return offset_y;
	}

	/**
	 * @return int  width - width of brick 
	 */
	public int getWidth() {
		return width;
	}
	

	/**
	 * @param int width - width  of brick 
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	
	
	/**
	 * @return int height - gheight of brick 
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * @param int height - gheight of brick 
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	/**
	 * @return
	 */
	public double getX() {
		return x;
	}
	/**
	 * @param double x - x coordiante of brick
	 */
	public void setX(double x) {
		this.x = x;
	}
	/**
	 * @return double y - y coordiante of brick
	 */
	public double getY() {
		return y;
	}
	/**
	 * @param double x - x coordiante of brick
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * @param int offset_x - offset_x of brick
	 */
	public void setOffset_x(int offset_x) {
		this.offset_x = offset_x;
	}

	/**
	 * @param int offset_y - offset_y  of brick
	 */
	public void setOffset_y(int offset_y) {
		this.offset_y = offset_y;
	}
}