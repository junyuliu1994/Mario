import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class SpriteAnimation extends Transition {

    private final ImageView imageView;
    private final int count;
    private final int columns;
    private final int offsetX;
    private final int offsetY;
    private final int width;
    private final int height;
    private final GraphicsContext gc;

    private int lastIndex;

    public SpriteAnimation(
            ImageView imageView, 
            Duration duration, 
            int count,   int columns,
            int offsetX, int offsetY,
            int width,   int height, GraphicsContext gc) {
        this.imageView = imageView;
        this.count     = count;
        this.columns   = columns;
        this.offsetX   = offsetX;
        this.offsetY   = offsetY;
        this.width     = width;
        this.height    = height;
        this.gc = gc;
        setCycleDuration(duration);
        setInterpolator(Interpolator.LINEAR);
    }

    protected void interpolate(double k) {
        final int index = Math.min((int) Math.floor(k * count), count - 1);
        if (index != lastIndex) {
            final int x = (index % columns) * width  + offsetX;
            final int y = (index / columns) * height + offsetY;
            gc.clearRect(0, 0, 1280, 720);
            gc.drawImage(imageView.getImage(), // the image to be drawn or null.
            		x, // the source rectangle's X coordinate position.
            		y, // the source rectangle's Y coordinate position.
            		width, // the source rectangle's width.
            		height, // the source rectangle's height.
            		0, // the destination rectangle's X coordinate position.
            		0, // the destination rectangle's Y coordinate position.
            		width, // the destination rectangle's width.
            		height); // the destination rectangle's height. 
            System.out.println("QWE");
            lastIndex = index;
        }
    }
}