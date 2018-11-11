import javafx.scene.image.Image;

public class Mario {
	private Image image;
    private int col;
    private int count;
    private int offset_x;
    private int offset_y;
    private int width;
    private int height;
    private int x;
	private int y;
	private int direction; //1 right 0 left
	public final static double SPEED = 5;

	public Mario(Image image, int col, int count, int offset_x, int offset_y, int width, int height, int x, int y, int direction) {
    	this.image = image;
        this.col  =   col;
        this.count    =  count;
        this.offset_x =  offset_x;
        this.offset_y =  offset_y;
        this.width    = width;
        this.height   = height;
        this.x = x;
        this.y = y;
        this.direction = direction;
    }
    

    public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getOffset_x() {
		return offset_x;
	}

	public void setOffset_x(int offset_x) {
		this.offset_x = offset_x;
	}

	public int getOffset_y() {
		return offset_y;
	}

	public void setOffset_y(int offset_y) {
		this.offset_y = offset_y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}
	
	public int getDirection() {
		return direction;
	}


	public void setDirection(int direction) {
		this.direction = direction;
	}

	public static double getSpeed() {
		return SPEED;
	}

}
