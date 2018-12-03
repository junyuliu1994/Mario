/**
 * It is a bullet class of mario game
 * @author Junyu Liu， Pengyu Yang, Zhengxiang Jin, Feiran Yange
 *
 */

import javafx.scene.image.Image;

public class Bullet {
    private Image image;
    private int offset_x;
    private int offset_y;
    private int width;
    private int height;
    private double x;
    private double y;
    private int speed;
    private static int roffset_x = 51;
    private static int roffset_y = 0;
    private static int loffset_x = 7;
    private static int loffset_y = 0;
    private boolean move = true;

    public Bullet(Image image, int width, int height, int offset_x, int offset_y, double x, double y) {
        this.image = image;
        this.width = width;
        this.height = height;
        this.offset_x = offset_x;
        this.offset_y = offset_y;
        this.x = x;
        this.y = y;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getOffset_x() {
        return offset_x;
    }

    public void setOffset_x(int offset_x) {
        this.offset_x = offset_x;
    }

    public int getOffset_y() {
        return offset_y;
    }

    public void setOffset_y(int offset_y) {
        this.offset_y = offset_y;
    }

    public static int getLoffset_x() {
        return loffset_x;
    }


    public static int getLoffset_y() {
        return loffset_y;
    }


    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public static int getRoffset_x() {
        return roffset_x;
    }


    public static int getRoffset_y() {
        return roffset_y;
    }

    public boolean isMove() {
        return move;
    }

    public void setMove(boolean move) {
        this.move = move;
    }
}
