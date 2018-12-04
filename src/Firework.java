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

    /**
     *  get count
     * @return a int  to help show firework
     */
    public int getCount() {return this.count;}

    /**
     *  get col
     * @return a int to help show firework
     */
    public int getCol() {return this.col;}

    /**
     * ge the offset x position of image
     * @return he offset x position of image
     */
    public int getOffsetX() { return this.offsetX; }

    /**
     *  set the offset x position of image
     * @param offsetX set offset x position of image
     */
    public void setOffsetX(int offsetX) { this.offsetX = offsetX; }

    /**
     * get the offset y position of image
     * @return offset y position of image
     */
    public int getOffsetY() { return this.offsetY; }

    /**
     * get the width position of image
     * @return the width position of image
     */
    public int getWidth() { return this.width; }

    /**
     * get the height position of image
     * @return the height position of image
     */
    public int getHeight() {return this.height; }

    /**
     * get the initial offset x position of image
     * @return the initial offset x position
     */
    public int getInitial_offsetX() { return initial_offsetX; }

    /**
     * get the x position of image
     * @return the x position of image
     */
    public int getX() {return this.x; }

    /**
     * get the y position of image
     * @return the y position of image
     */
    public int getY() {return this.y; }


}