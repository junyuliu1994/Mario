/**
 * @author Shawn Jin
 */
public class Flagstaff {
    // the flagstaff x location is 1476
    private int initial_x = 1476;
    private int x;
    // don't need to check the y axis, but need y when getting score from flagstaff
    private int y;
    private final int totalScore = 1000;

    // do not need draw the flag, because the map has it.
    public Flagstaff() {
        x = initial_x;
    }

    public int getX() {
        return this.x;
    }

    // move to right x pixels
    public void moveFlag(double x) {
        this.x -= x;
    }


}
