import javafx.scene.image.Image;

/**
 * 
 * @author Pengyu Yang
 * Inherited monster object goomba, moving left and right in pattern
 */
public class Goomba extends Monster {
	private final static Image image = new Image("resources/enemies2.png");
	
	public Goomba(int offset_x, int offest_y,int x,int y) {
		
		super(image,offset_x, offest_y, 40, 40, x,y);
		setInitiall_offsetX(0);
		setCount(3);
		setCol(3);
		setSpeed(5);
		setScore(100);
		setCollisionPoint();
	}
	
	

	/**
	 * setImage of goomba
	 * @param null
	 * @return null
	 */
	public void setImage() {
		super.setImage(image);
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
		
}
