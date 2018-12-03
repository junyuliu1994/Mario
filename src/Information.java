import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * @author Shawn Jin
 * this class is helping to show information fo Mario.
 */
public class Information {
    // the info that will be shown
    private String text;
    // the text color
    private Color color;
    // the position and int to update location info
    private int x, y, currentCount, count = 20;
    // the font style
    private Font font;
    // value of needUpdate -> (0 is don't need to update, 1 is need to update for score, 2 is need to update for coins,
    // 3 is need to disappear the information after showing in enough time)
    private int needUpdate = 0;

    /**
     * constrict information that don't need to move
     * @param text the info that will
     * @param color the color of font
     * @param x the x position of text
     * @param y the y position of text
     * @param font the font style
     */
    public Information(String text, Color color, int x, int y, Font font) {
        this.text = text;
        this.color = color;
        this.x = x;
        this.y = y;
        this.font = font;
    }

    /**
     *
     * @param text the info that will
     * @param color the color of font
     * @param x the x position of text
     * @param y the y position of text
     * @param font the font style
     * @param needUpdate the integer to present if the data needs to update and how to update
     */
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