import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class SpriteAnimation extends Transition {

    private Image image;
	private int count;
	private int columns;
    private int offsetX;
    private int offsetY;
    private int width;
    private int height;
    private double cor_x;
    private double cor_y;
    private GraphicsContext gc;
    private int direction; //1 right 0 left
    private boolean gcForM; //gc for mario or not

    private int lastIndex;

    public SpriteAnimation(
            Image image, 
            Duration duration, 
            int count,   int columns,
            int offsetX, int offsetY,
            int width,   int height, double cor_x, double cor_y, GraphicsContext gc, int direction, boolean gcForM) {
        this.image = image;
        this.count     = count;
        this.columns   = columns;
        this.offsetX   = offsetX;
        this.offsetY   = offsetY;
        this.width     = width;
        this.height    = height;
        this.cor_x = cor_x;
        this.cor_y = cor_y;
        this.gc = gc;
        this.direction = direction;
        this.gcForM = gcForM;
        setCycleDuration(duration);
        setInterpolator(Interpolator.LINEAR);
    }

	protected void interpolate(double k) {		
		if (count == 1 && count == columns) {
			System.out.println("2");
			gc.drawImage(image, // the image to be drawn or null.
					offsetX, // the source rectangle's X coordinate position.
					offsetY, // the source rectangle's Y coordinate position.
            		width, // the source rectangle's width.
            		height, // the source rectangle's height.
            		cor_x, // the destination rectangle's X coordinate position.
            		cor_y, // the destination rectangle's Y coordinate position.
            		width, // the destination rectangle's width.
            		height); // the destination rectangle's height. 
			this.stop();
		}
		
		
        int index = Math.min((int) Math.floor(k * count), count - 1);

        if (index != lastIndex) {
        	int x;
        	if (direction == 1) {
        		x = (index % columns) * width  + offsetX;
        	}
        	else {
        		x =  offsetX - (index % columns) * width;
        	}
            int y = (index / columns) * height + offsetY;

            gc.drawImage(image, // the image to be drawn or null.
            		x, // the source rectangle's X coordinate position.
            		y, // the source rectangle's Y coordinate position.
            		width, // the source rectangle's width.
            		height, // the source rectangle's height.
            		cor_x, // the destination rectangle's X coordinate position.
            		cor_y, // the destination rectangle's Y coordinate position.
            		width, // the destination rectangle's width.
            		height); // the destination rectangle's height. 
            lastIndex = index;
        }
    }
    
    public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	public int getOffsetX() {
		return offsetX;
	}

	public void setOffsetX(int offsetX) {
		this.offsetX = offsetX;
	}

	public int getOffsetY() {
		return offsetY;
	}

	public void setOffsetY(int offsetY) {
		this.offsetY = offsetY;
	}
	
	public double getCor_x() {
		return cor_x;
	}

	public void setCor_x(double d) {
		this.cor_x = d;
	}

	public double getCor_y() {
		return cor_y;
	}

	public void setCor_y(double cor_y) {
		this.cor_y = cor_y;
	}

	public GraphicsContext getGc() {
		return gc;
	}

	public void setGc(GraphicsContext gc) {
		this.gc = gc;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

}