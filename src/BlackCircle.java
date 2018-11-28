/**
 * @author Shawn Jin
 */
public class BlackCircle {

    // black
    private int width =300;
    private int height = 300;
    private int x;
    private int y;
    private boolean isFill;
    public BlackCircle(int x, int y, int diameter, boolean isFill) {
        this.x = x;
        this.y = y;
        this.height = diameter;
        this.width = diameter;
        this.isFill = isFill;
    }

    public boolean isFill() { return this.isFill; }

    public int getWidth() {return this.width;}

    public int getHeight() { return this.height;}

    public int getX() { return this.x; }

    public int getY() { return this.y; }



}