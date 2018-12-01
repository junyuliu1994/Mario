import javafx.scene.image.Image;

public class Koopa extends Monster{
	
	private boolean isShell = false;
	private boolean roll = false;
	private Image originalImage = new Image("resources/enemies2.png");
	private Image convertImage = new Image("resources/enemies2-ConvertImage.png");
	
	public Koopa(int offset_x, int offset_y,int x, int y) {
		super(new Image("resources/enemies2.png"),offset_x,offset_y, 40, 80, x,y);
		setInitiall_offsetX(240);
		setCount(2);
		setCol(2);
		setSpeed(-5);
		setScore(500);
		setCollisionPoint();
		
	}
	
	
	
	public void koopaMoveRight() {
		super.setImage(convertImage);
		setOffset_x(1700);
		setInitiall_offsetX(1700);
	}
	
	public void koopaMoveLeft() {
		super.setImage(originalImage);
		setOffset_x(240);
	    setInitiall_offsetX(240);
	}
	
	public void setRolling() {
		this.roll = true;
	}
	public boolean isRolling() {
		return roll;
	}
	
	public Image getConvertImage() {
		return convertImage;
	}
	public Image getOriginalImage() {
		return originalImage;
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
