/**
 * @author penguyu yang
 * This is monster object in the game, there three inheritence of monster
 * goomba, koopa and piranhaplant
 */
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

	
    
	/**
	 * defualt constructor of monster 
	 * @param image - image resource of monster 
	 * @param offset_x - x coordinate offset of image
	 * @param offset_y - y coordinate offset of image
	 * @param width - width of image
	 * @param height - height of image
	 * @param x - x of pane
	 * @param y - y of pane
	 */
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
    
    /**
     *  @param null
     *  @return image - image of monster
     */
    public Image getImage() {
		return image;
	}
    
    
    /**
     *  @param image - image need to be set 
     *  @return null
     */
	public void setImage(Image image) {
		this.image = image;
	}
	
	/**
     *  @param null
     *  @return offset_x- offset of image
     */
	public int getOffset_x() {
		return offset_x;
	}
	
	/**
     *  @param initial_offset_x - the initiall offsetx of monste image
     *  @return offset_x- offset of image
     */
	public void setInitiall_offsetX(int initial_offset_x) {
		this.initial_offset_x = initial_offset_x;
	}
	
	/**
     *  @param Offset_x - Offset_x need to be set 
     *  @return null
     */
	public void setOffset_x(int offset_x) {
		this.offset_x = offset_x;
	}
	
	/**
     *  @param Offset_y - Offset_y need to be set 
     *  @return null
     */
	public void setOffset_y(int offset_y) {
		this.offset_y = offset_y;
	}
	
	/**
     *  @param count - count of image to be set 
     *  @return null
     */
	public void setCount(int count){
		this.count = count;
	}
	
	/**
     *  @param col - col of image to be set 
     *  @return null
     */
	public void setCol(int col){
		this.col = col;
	}

	/**
     *  @param null
     *  @return offset_y- offsety of image
     */
	public int getOffset_y() {
		return offset_y;
	}
	
	/**
     *  @param null
     *  @return count- count number of image
     */
	public int getCount() {
		return count;
	}
	
	/**
     *  @param null
     *  @return col- col of image
     */
	public int getCol() {
		return col;
	}
	
	/**
     *  @param null
     *  @return width- width of image
     */
	public int getWidth() {
		return width;
	}
	
	/**
	 * @param int width - width of monster image 
	 * @return null
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	
	/**
	 * @param null
	 * @return height of monster image 
	 */
	public int getHeight() {
		return height;
	}
	
	
	/**
	 * @param int - height of monster 
	 * @return null
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	
	/**
	 * @param null
	 * @return height of monster image 
	 */
	public double getX() {
		return x;
	}
	
	
	/**
	 * @param double - x coordinate of monster 
	 * @return null
	 */
	public void setX(double x) {
		this.x = x;
	}
	
	/**
	 * @param null
	 * @return y coordinate of monster 
	 */
	public double getY() {
		return y;
	}
	
	/**
	 * @param double - y coordinate of monster 
	 * @return null
	 */
	public void setY(double y) {
		this.y = y;
	}
	
	/**
	 * @param null
	 * @return score of monster 
	 */
	public int getScore() {
		return score;
	}
    
	/**
	 * 
	 * @param score int - score of monster
	 * @return null
	 */
	public void setScore(int score) {
		this.score = score;
	}
	
	/**
	 * 
	 * @param speed doule - movind speed of monster
	 * @return null
	 */
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	/**
	 * @param null
	 * @return speed of monster
	 */
	public double getSpeed() {
		return speed;
	}
	
	/**
	 * @param null
	 * @return upLeftX coordinate of monster 
	 */
	public double getUpLeftX() {
		return upLeftX;
	}
	
	/**
	 * @param null
	 * @return upLeftY coordinate of monster 
	 */
	public double getUpLeftY() {
		return upLeftY;
	}
	
	
	/**
	 * @param null
	 * @return upRightX  coordinate of monster 
	 */
	public double getRightLeftX() {
		return upRightX;
	}
	
	/**
	 * @param null
	 * @return  upRightY  coordinate of monster 
	 */
	public double getUpRightY() {
		return upRightY;
	}
	
	/**
	 * @param null
	 * @return downLeftX  coordinate of monster 
	 */
	public double getDownLeftX() {
		return downLeftX;
	}
	
	/**
	 * @param null
	 * @return downLeftY coordinate of monster 
	 */
	public double getDownLeftY() {
		return downLeftY;
	}
	
	/**
	 * @param null
	 * @return downRightX coordinate of monster 
	 */
	public double getDownRightX() {
		return downRightX;
	}
	
	/**
	 * @param null
	 * @return  downRightY coordinate of monster 
	 */
	public double getDownRightY() {
		return downRightY;
	}
	
	/**
	 * @param null
	 * @return RightX coordinate of monster
	 */
	public double getRightX() {
		return rightX;
	}
	
	/**
	 * @param null
	 * @return RightY coordinate of monster
	 */
    public double getRightY() {
		return rightY;
	}
    
    /**
	 * @param null
	 * @return LeftX coordinate of monster
	 */
    public double getLeftX() {
		return leftX;
	}
    
    /**
	 * @param null
	 * @return LeftY coordinate of monster
	 */
    public double getLeftY() {
		return leftY;
	}
    /**
     * This method reset the collision point of monster based on its height and width
	 * @param null
	 * @return null 
	 */
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

	/**
	 * @param null
	 * @return Initial OffsetX of monster image 
	 */
	public int getIniOffsetX() {
		// TODO Auto-generated method stub
		return initial_offset_x;
	}

	
}
