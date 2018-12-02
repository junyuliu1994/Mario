import javafx.scene.image.Image;

public class PiranhaPlant extends Monster{
	private static Image image = new Image("resources/enemies2.png");
	
	public PiranhaPlant(int offset_x, int offset_y,int x, int y) {
		super(image, offset_x, offset_y, 40, 80, x, y);
		setInitiall_offsetX(480);
		setSpeed(0);
		setCol(2);
		setCount(2);
		setScore(750);
		setCollisionPoint();
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
