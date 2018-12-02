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
	public boolean isDead;
	
	private int initial_offset_x;
	
	private double upLeftX;
	private double upLeftY;
	
	private double upRightX;
	private double upRightY;
	
	private double leftX;
	private double leftY;
	
	private double rightX;
	private double rightY;
	
	private double downLeftX;
	private double downLeftY;
	
	private double downRightX;
	private double downRightY;

	
    
    
    public Monster(Image image,int offset_x, int offset_y,int width, int height, int x, int y) {
    	this.image = image;
    	this.offset_x = offset_x;
    	this.offset_y = offset_y;
    	this.width = width;
    	this.height = height;
    	this.x = x;
    	this.y = y;
    	this.isDead = false;
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
	
	public void setInitiall_offsetX(int initial_offset_x) {
		this.initial_offset_x = initial_offset_x;
	}
	
	public void setOffset_x(int offset_x) {
		this.offset_x = offset_x;
	}
	
	public void setOffset_y(int offset_y) {
		this.offset_y = offset_y;
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
	
	public double getUpLeftX() {
		return upLeftX;
	}
	
	public double getUpLeftY() {
		return upLeftY;
	}
	
	public double getRightLeftX() {
		return upRightX;
	}
	
	public double getUpRightY() {
		return upRightY;
	}
	
	public double getDownLeftX() {
		return downLeftX;
	}
	
	public double getDownLeftY() {
		return downLeftY;
	}
	
	public double getDownRightX() {
		return downRightX;
	}
	
	public double getDownRightY() {
		return downRightY;
	}
	
	public double getRightX() {
		return rightX;
	}
	
    public double getRightY() {
		return rightY;
	}
    
    public double getLeftX() {
		return leftX;
	}
    
    public double getLeftY() {
		return leftY;
	}
	
	public void setCollisionPoint() {
		
		 upLeftX = this.getX() +  this.getWidth()*0.25;
		 upLeftY = this.getY() + this.getHeight()*0.25;
		 
		 upRightX = this.getX() + this.getWidth()*0.75;
		 upRightY = this.getY() + this.getHeight()*0.25;
		
		 leftX = this.getX();
		 leftY = this.getY() + this.getHeight()*0.5;
		 
		 rightX = this.getX() + this.getWidth();
		 rightY = this.getY() + this.getHeight()*0.5;
		
		 downLeftX = this.getX();
		 downLeftY = this.getY() + this.getHeight();
		 
		 downRightX = this.getX() + this.getWidth();
		 downRightY = this.getX() + this.getHeight();
		 
	}

	public int getIniOffsetX() {
		// TODO Auto-generated method stub
		return initial_offset_x;
	}

	
}
