import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.Serializable;

/**
 * @author Shawn Jin
 * this class is helping to show information fo Mario.
 */
public class Information implements Serializable {
    // the info that will be shown
    private String text;
    // the text color
    private static Color color;
    // the position and int to update location info
    private int x, y, currentCount, count = 20;
    // the font style
    private static Font font;
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

    /**
     *  return is need to update
     * @return a int represent if the data need to be updated
     */
    public int isNeedUpdate() { return this.needUpdate; }

    /**
     * return the text that the data has
     * @return a String represent the info
     */
    public String getText() { return this.text; }

    /**
     *  set the text that the  data has
     * @param text the new text
     */
    public void setText(String text) { this.text = text; }

    /**
     * get color the data has
     * @return a color
     */
    public Color getColor() { return this.color; }

    /**
     * set x
     * @param x the x position
     */
    public void setX(int x) { this.x = x; }

    /**
     * get x postion
     * @return
     */
    public int getX() { return this.x; }

    /**
     * set y position
     * @param y the y position of data will show
     */
    public void setY(int y) { this.y = y; }

    /**
     * get y position
     * @return the y position of data will show
     */
    public int getY() { return this.y; }

    /**
     * get the font that the data has
     * @return the font that the data has
     */
    public Font getFont() { return this.font; }

    /**
     *  get count that to help show animation
     * @return a int that to help show animation
     */
    public int getCount() { return this.count; }

    /**
     * get current count that to help show animation
     * @return  a int that to help show animation
     */
    public int getCurrentCount() { return this.currentCount; }

    /**
     * set current count that to help show animation
     * @param x a int that to help show animation
     */
    public void setCurrentCount(int x) { this.currentCount = x; }


}