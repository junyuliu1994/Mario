import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * @author Shawn Jin
 */
public class Information {

    private String text;
    private Color color;
    private int x, y;
    private Font font;

    public Information(String text, Color color, int x, int y, Font font) {
        this.text = text;
        this.color = color;
        this.x = x;
        this.y = y;
        this.font = font;
    }

    public String getText() { return this.text; }

    public Color getColor() { return this.color; }

    public int getX() { return this.x; }

    public int getY() { return this.y; }

    public Font getFont() { return this.font; }
}
