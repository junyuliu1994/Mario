import javafx.scene.image.Image;

import java.io.Serializable;

/**
 * This is a firework class to help Mario to show fireworks
 * @author Shawn Jin
 */
public class Firework implements Serializable {
    static Image  image;
    private int initial_offsetX = 0;
    private int offsetX = 0;
    private int offsetY = 0;
    private int x;
    private int y;
    private final int width = 16;
    private final int height = 16;
    private int count;
    private final int col = 4;

    /**
     * constructor of firework
     * @param image the image of firework
     * @param x the x position of firework
     * @param y the y position of firework
     * @param count a int to help show firework
     */
    Firework(Image image, int x, int y, int count) {
        this.image = image;
        this.x = x;
        this.y = y;
        // this parameter is for show the first image of fireworks
        this.count = count;
    }


    /**
     *  return the image of firework
     * @return the image of firework
     */
    public Image getImage() { return this.image; }

    /**
     * set the count to help show firework
     * @param count a int to help show firework
     */
    public void setCount(int count) { this.count = count; }

    public int getCount() {return this.count;}

    public int getCol() {return this.col;}

    public int getOffsetX() { return this.offsetX; }

    public void setOffsetX(int offsetX) { this.offsetX = offsetX; }

    public int getOffsetY() { return this.offsetY; }

    public int getWidth() { return this.width; }

    public int getHeight() {return this.height; }

    public int getInitial_offsetX() { return initial_offsetX; }

    public int getX() {return this.x; }

    public int getY() {return this.y; }


}