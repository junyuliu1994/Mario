import java.io.Serializable;

/**
 * this class is helping to show the black circles
 * @author Shawn Jin
 */
public class BlackCircle implements Serializable {

    // black
    private int width;
    private int height;
    private int x;
    private int y;
    private boolean isFill;

    /**
     * constructor of black circle
     * @param x the x position of circle
     * @param y the y position of circle
     * @param diameter the diameter of circle
     * @param isFill if the circle need to be fill
     */
    public BlackCircle(int x, int y, int diameter, boolean isFill) {
        this.x = x;
        this.y = y;
        this.height = diameter;
        this.width = diameter;
        this.isFill = isFill;
    }

    /**
     * return if the circle need to be fill
     * @return if the circle need to be fill
     */
    public boolean isFill() { return this.isFill; }

    /**
     * return the width of circle
     * @return  the width of circle
     */
    public int getWidth() {return this.width;}

    /**
     *  return the height of circle
     * @return the height of circle
     */
    public int getHeight() { return this.height;}

    /**
     * return the x position of black circle
     * @return the x position of black circle
     */
    public int getX() { return this.x; }

    /**
     * return the y position of black circle
     * @return the y position of black circle
     */
    public int getY() { return this.y; }



}