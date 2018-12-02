import javafx.scene.image.Image;

public class Koopa extends Monster{
	
	private boolean isShell = false;
	private boolean roll = false;
	private static Image image = new Image("resources/enemies2.png");


	
	
	public Koopa(int offset_x, int offset_y,int x, int y) {
		super(image,offset_x,offset_y, 40, 80, x,y);
		setInitiall_offsetX(240);
		setCount(2);
		setCol(2);
		setSpeed(-5);
		setScore(500);
		setCollisionPoint();
		
	}
	
	
	public void koopaMoveRight() {
		
		setOffset_x(2020);
		setInitiall_offsetX(2020);
	}
	
	public void koopaMoveLeft() {
		
		setOffset_x(240);
	    setInitiall_offsetX(240);
	}
	
	public void setRolling() {
		this.roll = true;
	}
	public boolean isRolling() {
		return roll;
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
	
	public void setShell() {
		this.isShell = true;
	}
	
	public boolean getShell() {
		return this.isShell;
	}

}
