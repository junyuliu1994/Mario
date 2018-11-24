import javafx.scene.image.Image;

public class Koopa extends Monster{
	
	

	public Koopa(int offset_x, int offset_y,int x, int y) {
		super(new Image("resources/enemies2.png"),offset_x,offset_y, 40, 80, x,y);
		setInitiall_offsetX(240);
		setCount(2);
		setCol(2);
		setSpeed(-5);
		setScore(500);
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
