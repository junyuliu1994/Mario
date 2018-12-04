/**
 * It is a question brick class of mario game
 * @author Junyu Liu， Pengyu Yang, Zhengxiang Jin, Feiran Yange
 *
 */

import javafx.scene.image.Image;

public class QuestionBrick extends Brick{
    private int []s_offset_x = {947, 947 + 40, 947+40*2, 945+40*3};
    private int count = 0;
    private int col = 3;
    private int contains = 1;

    public QuestionBrick(Image image, int width, int height, double x, double y) {
        super(image, width, height, x, y);
        this.setOffset_x(947);
        this.setOffset_y(0);
    }

    /**
     * @return int s_offset_x of Question brick
     */
    public int[] getS_offset_x() {
        return s_offset_x;
    }

    /**
     * @param int s_offset_x of Question brick
     */
    public void setS_offset_x(int[] s_offset_x) {
        this.s_offset_x = s_offset_x;
    }

    /**
     * @return int count  of Question brick
     */
    public int getCount() {
        return count;
    }

    /**
     * @param int count - count of Question brick 
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * @return int col - col of Question brick
     */
    public int getCol() {
        return col;
    }

    /**
     * @param int col - col of Question brick
     */
    public void setCol(int col) {
        this.col = col;
    }

    /**
     * @return int contains - contians of Question brick
     */
    public int getContains() {
        return contains;
    }

    /**
     * @param int contains- contains of Question brick
     */
    public void setContains(int contains) {
        this.contains = contains;
    }
}
