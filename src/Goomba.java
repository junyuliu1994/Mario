import javafx.scene.image.Image;

public class Goomba extends Monster {

	public Goomba(int offset_x, int offest_y,int x,int y) {
		super(new Image("resources/enemies2.png"),offset_x, offest_y, 40, 40, x,y);
		setCount(3);
		setCol(3);
		setSpeed(-5);
		setScore(100);
		setCollisionPoint();
	}

	
	
	@Override
	public void setX(double x) {
		super.setX(x);
		setCollisionPoint();
	}
	
	@Override
	public void setY(double y) {
		super.setX(y);
		setCollisionPoint();
	}
		
}
