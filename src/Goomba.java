import javafx.scene.image.Image;

public class Goomba extends Monster {
	
	private int initial_offset_x = 0;
	
	//five collision check points of goomba
	public double upLeftX;
	public double upLeftY;
	
	public double upRightX;
	public double upRightY;
	
	public double headX;
	public double headY;
	
	public double leftX;
	public double leftY;
	public double rightX;
	public double rightY;
	
	public double downLeftX;
	public double downLeftY;
	
	public double downRightX;
	public double downRightY;
	
	public boolean isDead = false;
	public int timer = 0;

	public Goomba(int offset_x, int offest_y,int x,int y) {
		super(new Image("resources/enemies2.png"),offset_x, offest_y, 40, 40, x,y);
		setCount(3);
		setCol(3);
		setSpeed(-5);
		setScore(100);
		setCollisionPoint();
	}

	public void setCollisionPoint() {
		
		 upLeftX = this.getX() +  this.getWidth()*0.25;
		 upLeftY = this.getY() + this.getHeight()*0.25;
		 
		 upRightX = this.getX() + this.getWidth()*0.75;
		 upRightY = this.getY() + this.getHeight()*0.25;
		 
		 headX = this.getX() + this.getWidth()*0.5;
		 headY = this.getY();
		 
		 leftX = this.getX();
		 leftY = this.getY() + this.getHeight()*0.5;
		 
		 rightX = this.getX() + this.getWidth();
		 rightY = this.getY() + this.getHeight()*0.5;
		
		 downLeftX = this.getX();
		 downLeftY = this.getY() + this.getHeight();
		 
		 downRightX = this.getX() + this.getWidth();
		 downRightY = this.getX() + this.getHeight();
		 
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
	
	public int getIniOffsetX() {
		return initial_offset_x;
	}	
}
