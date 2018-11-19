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

    public int[] getS_offset_x() {
        return s_offset_x;
    }

    public void setS_offset_x(int[] s_offset_x) {
        this.s_offset_x = s_offset_x;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getContains() {
        return contains;
    }

    public void setContains(int contains) {
        this.contains = contains;
    }
}
