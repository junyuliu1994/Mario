import java.io.Serializable;

/**
 * this class is helping define the location of flagstaff
 * @author Shawn Jin
 */
public class Flagstaff implements Serializable {
    // the flagstaff x location is 1476
    private int initial_x = 1486;
    private int x;
    // don't need to check the y axis, but need y when getting score from flagstaff
    private int y;
    private final int totalScore = 1000;

    // do not need draw the flag, because the map has it.

    /**
     * initial the flagstaff
     */
    public Flagstaff() {
        x = initial_x;
    }

    /**
     *  get the x position of the flagstaff
     * @return a
     */
    public int getX() {
        return this.x;
    }

    // move to right x pixels

    /**
     * move the position of flag
     * @param x the location of flag
     */
    public void moveFlag(double x) {
        this.x -= x;
    }
}