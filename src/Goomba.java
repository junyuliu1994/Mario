import javafx.scene.image.Image;

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
	
	
	public void setImage() {
		super.setImage(image);
	}
	
	@Override
	public void setX(double x) {
		super.setX(x);
		setCollisionPoint();
	}
	
	@Override
	public void setY(double y) {
		super.setY(y);
		setCollisionPoint();
	}
		
}
