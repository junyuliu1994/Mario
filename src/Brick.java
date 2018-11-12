import javafx.scene.image.Image;

public class Brick {
	private Image image;
	private int offset_x;
	private int offset_y;
    private int width;
    private int height;
    private double x;
	private double y;
	
	public Brick(Image image, int offset_x, int offset_y, int width, int height, double x, double y) {
		this.image = image;
		this.offset_x = offset_x;
		this.offset_y = offset_y;
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
	}
	
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
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
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
}
