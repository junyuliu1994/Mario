import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.Serializable;

/**
 * @author Shawn Jin
 * this class
 */
public class Information implements Serializable {

    private String text;
    private static Color color;
    private int x, y, currentCount, count = 20;
    private static Font font;
    // value of needUpdate -> (0 is don't need to update, 1 is need to update for score, 2 is need to update for coins)
    private int needUpdate = 0;

    public Information(String text, Color color, int x, int y, Font font) {
        this.text = text;
        this.color = color;
        this.x = x;
        this.y = y;
        this.font = font;
    }
    public Information(String text, Color color, int x, int y, Font font, int needUpdate) {
        this.text = text;
        this.color = color;
        this.x = x;
        this.y = y;
        this.font = font;
        this.needUpdate = needUpdate;
    }

    public int isNeedUpdate() { return this.needUpdate; }

    public String getText() { return this.text; }

    public void setText(String text) { this.text = text; }

    public Color getColor() { return this.color; }

    public void setX(int x) { this.x = x; }

    public int getX() { return this.x; }

    public void setY(int y) { this.y = y; }

    public int getY() { return this.y; }

    public Font getFont() { return this.font; }

    public int getCount() { return this.count; }

    public int getCurrentCount() { return this.currentCount; }

    public void setCurrentCount(int x) { this.currentCount = x; }


}