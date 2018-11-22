import javafx.scene.image.Image;

import java.io.Serializable;

public abstract class Monster implements Serializable {
	private static Image image;
	private  int col;
	private  int count;
	private int offset_x;
    private int offset_y;
    private int width ;
    private int height;
    private double x;
    private double y;
    private double speed;
    private int score;
    
    
    public Monster(Image image,int offset_x, int offset_y,int width, int height, int x, int y) {
    	this.image = image;
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
	public void setImage(Image image) {
		this.image = image;
	}
	public int getOffset_x() {
		return offset_x;
	}
	
	void setOffset_x(int offset_x) {
		this.offset_x = offset_x;
	}
	
	public void setCount(int count){
		this.count = count;
	}
	
	public void setCol(int col){
		this.col = col;
	}

	public int getOffset_y() {
		return offset_y;
	}
	public int getCount() {
		return count;
	}
	
	public int getCol() {
		return col;
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
	
	public int getScore() {
		return score;
	}
    
	public void setScore(int score) {
		this.score = score;
	}
	
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public double getSpeed() {
		return speed;
	}
}
