import javafx.scene.image.Image;

public class Wall extends Brick{
    public Wall(Image image, int width, int height, double x, double y) {
        super(image, width, height, x, y);
        this.setOffset_x(40);
        this.setOffset_y(0);
    }
}
