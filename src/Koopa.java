import javafx.scene.image.Image;
/**
 * 
 * @author Pengyu Yang
 * Inherited monster object goomba, moving left and right in pattern
 * Step on it wont let it die, instead change it to shell, step on it
 * again will make it rolling, can kill Goomba.
 *
 */
public class Koopa extends Monster{
	
	private boolean isShell = false;
	private boolean roll = false;
	private static Image image = new Image("resources/enemies2.png");


	/**
	 * default constructor of Koopa
	 * @param offset_x - offsetX of koopa
	 * @param offset_y - offsety of koopa
	 * @param x- X of koopa on board 
	 * @param y- y of koopa on board
	 */
	public Koopa(int offset_x, int offset_y,int x, int y) {
		super(image,offset_x,offset_y, 40, 80, x,y);
		setInitiall_offsetX(240);
		setCount(2);
		setCol(2);
		setSpeed(-5);
		setScore(500);
		setCollisionPoint();
		
	}
	
	

	/**
	 * set koopa image  move to right
	 * @param null
	 * @return null
	 */
	public void koopaMoveRight() {
		
		setOffset_x(2020);
		setInitiall_offsetX(2020);
	}
	
	/**
	 * set koopa image  move to left
	 * @param null
	 * @return null
	 */
	public void koopaMoveLeft() {
		
		setOffset_x(240);
	    setInitiall_offsetX(240);
	}
	
	/**
	 * set koopat to be rolling
	 * @param null
	 * @return null
	 */
	public void setRolling() {
		this.roll = true;
	}
	/**
	 * 
	 * @return if koopa is rolling
	 */
	public boolean isRolling() {
		return roll;
	}
	
  
	/**
	 * overide method, call parent setX() and reset collisioon point
	 * @param double - x coordiante of monster
	 * @return null
	 */
	@Override
	public void setX(double x) {
		super.setX(x);
		setCollisionPoint();
	}
	
	/**
	 * overide method, call parent setY() and reset collisioon point
	 * @param double - y coordiante of monster
	 * @return null
	 */
	@Override
	public void setY(double y) {
		super.setY(y);
		setCollisionPoint();
	}
	
	/**
	 * change Koopa to be shell 
	 * @param null
	 * @retur null
	 */
	public void setShell() {
		this.isShell = true;
	}
	
	/**
	 * 
	 * @return if koopa is shell or not
	 */
	public boolean getShell() {
		return this.isShell;
	}

}
