/**
 * It is a Wall class of mario game
 * @author Junyu Liu， Pengyu Yang, Zhengxiang Jin, Feiran Yange
 * It can be destroyed
 */

import javafx.scene.image.Image;

public class Wall extends Brick{
	
    /**
     * default wall contructor 
     * @param Imgae image - imgae of wall
     * @param int width - width of wall
     * @param int height - height of wall 
     * @param int x - x coordinate of wall
     * @param int y - y coordiante of wall
     */
    public Wall(Image image, int width, int height, double x, double y) {
        super(image, width, height, x, y);
        this.setOffset_x(40);
        this.setOffset_y(0);
    }
}
