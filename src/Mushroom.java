/**
 * It is a mushroom class of mario game
 * @author Junyu Liu， Pengyu Yang, Zhengxiang Jin, Feiran Yange
 *
 */

import javafx.scene.image.Image;

import java.io.Serializable;


public class Mushroom implements Serializable {
    private static Image image;
    private final int offset_x = 0;
    private final int offset_y = 0;
    private int width;
    private int height;
    private double x;
    private double y;
    static final long serialVersionUID = 1;

    /**
     * default mashuroom constructor
     * @param image
     * @param width
     * @param height
     * @param x
     * @param y
     */
    public Mushroom(Image image, int width, int height, double x, double y) {
        this.image = image;
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }


    /**
     * @return image of mashuroom
     */
    public Image getImage() {
        return image;
        }
    /**
     * @param image - image of mashuroom
     */
    public void setImage(Image image) {
        this.image = image;
    }
    /**
     * @return int offset_x - offset of mushroom
     */
    public int getOffset_x() {
        return offset_x;
    }

    /**
     * @return int offset_y - offset_y of mushroom
     */
    public int getOffset_y() {
        return offset_y;
    }

    /**
     * @return nt width - width of mushroom
     */
    public int getWidth() {
        return width;
    }
    /**
     * @param int width - width of mushroom
     * 
     */
    public void setWidth(int width) {
        this.width = width;
    }
    
    
    /**
     * @return int - height of mushroom
     */
    public int getHeight() {
        return height;
    }
    
    
    /**
     * @param int height - height of mushroom
     */
    public void setHeight(int height) {
        this.height = height;
    }
    
    
    /**
     * @return int x - x coordinate of mushroom
     */
    public double getX() {
        return x;
    }
    
    
    /**
     * @param int x - x coordinate of mushroom
     */
    public void setX(double x) {
        this.x = x;
    }
    
    
    /**
     * @return int y - y coordinate of mushroom
     */
    public double getY() {
        return y;
    }
    /**
     * @param int y - y coordinateof mushroom
     */
    public void setY(double y) {
        this.y = y;
    }
}
