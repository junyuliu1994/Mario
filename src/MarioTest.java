import static org.junit.jupiter.api.Assertions.*;

import javafx.application.Application;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * junit testing method for javafx
 * @author Feiran Yang, Junyu Liu
 */

public class MarioTest {
    @Test
    void ControllerTest()throws Exception {
        Thread.sleep(2000);
        GameModel model = new GameModel();
        GameController controller = new GameController(model);

        controller.getMario().setLevel(3);
        controller.getMario().setRight(true);
        model.move();
        controller.getMario().setDirection(0);
        model.move();
        controller.getMario().setRight(false);
        controller.getMario().setLeft(true);
        model.move();
        controller.getMario().setDirection(1);
        model.move();
        controller.getMario().setJump(true);
        model.move();

        controller.getMario().setLevel(1);
        controller.getMario().setRight(false);
        controller.getMario().setLeft(false);
        controller.getMario().setJump(false);

        assertFalse(model.standOnBlocks());
        Brick brick = new Brick(null, 40, 40, controller.getMario().getX(), controller.getMario().getY() + controller.getMario().getHeight());
        controller.getBricks().add(brick);
        assertTrue(model.standOnBlocks());
        assertFalse(model.moveRightStockByBlocks());
        assertFalse(model.moveLeftStockByBlocks());

        brick.setX(controller.getMario().getX() + 10);
        assertTrue(model.standOnBlocks());

        brick.setX(controller.getMario().getX() + controller.getMario().getWidth());
        brick.setY(controller.getMario().getY());
        assertTrue(model.moveRightStockByBlocks());

        brick.setX(controller.getMario().getX() - controller.getMario().getWidth());
        assertTrue(model.moveLeftStockByBlocks());

        brick.setX(controller.getMario().getRightTopC_x());
        brick.setY(controller.getMario().getRightTopC_y());
        assertTrue(model.moveRightStockByBlocks());

        brick.setX(controller.getMario().getLeftTopC_x());
        brick.setY(controller.getMario().getLeftTopC_y());
        assertTrue(model.moveLeftStockByBlocks());

        assertFalse(model.jumpStockByBrick());
        brick.setX(controller.getMario().getX());
        brick.setY(controller.getMario().getY() - controller.getMario().getHeight());
        assertTrue(model.jumpStockByBrick());

        assertEquals(controller.getMario().getLevel(), 1);
        assertFalse(model.eatMushroom());
        Mushroom mushroom = new Mushroom(null, 40, 40, controller.getMario().getX(), controller.getMario().getY());
        controller.getMushrooms().add(mushroom);
        assertTrue(model.eatMushroom());
        assertEquals(controller.getMario().getLevel(), 2);
        Mushroom mushroom1 = new Mushroom(null, 40, 40, controller.getMario().getX(), controller.getMario().getY());
        controller.getMushrooms().add(mushroom);
        assertTrue(model.eatMushroom());
        assertEquals(controller.getMario().getLevel(), 3);
        controller.getMario().setLevel(2);
        controller.getMario().setDirection(2);
        Mushroom mushroom2 = new Mushroom(null, 40, 40, controller.getMario().getX(), controller.getMario().getY());
        controller.getMushrooms().add(mushroom);
        assertTrue(model.eatMushroom());
        controller.getMario().setLevel(1);
        controller.getMario().setDirection(1);

        model.move();
        controller.getMario().setDirection(0);
        assertTrue(model.stop());

        controller.getMario().setDirection(1);
        assertTrue(model.stop());

        controller.getMario().setRight(true);
        assertFalse(model.stop());
        controller.getMario().setRight(false);

        Bullet bullet = new Bullet(null, 40, 40, 0, 0, controller.getMario().getX(), controller.getMario().getY());
        controller.getBullets().add(bullet);
        assertFalse(model.bulletCollision(bullet));
        brick.setX(controller.getMario().getX() + controller.getMario().getWidth());
        brick.setY(controller.getMario().getY());
        assertTrue(model.bulletCollision(bullet));
        brick.setX(controller.getMario().getX() - controller.getMario().getWidth());
        assertTrue(model.bulletCollision(bullet));
        brick.setX(0);

        Goomba goomba = new Goomba(0, 0, controller.getMario().getX() + controller.getMario().getWidth(), controller.getMario().getY());
        controller.getMonsters().add(goomba);
        assertTrue(model.bulletCollision(bullet));
        goomba.setX(controller.getMario().getX() - controller.getMario().getWidth());
        controller.getMonsters().add(goomba);
        assertTrue(model.bulletCollision(bullet));


        controller.getMario().setRight(true);
        model.setMoveBackground(true);
        controller.getBullets().add(bullet);
        bullet.setSpeed(6);
        model.resetBulletSpeed();
        assertEquals(bullet.getSpeed(), 4);
        controller.getMario().setRight(false);
        model.setMoveBackground(false);
        controller.getBullets().add(bullet);
        model.resetBulletSpeed();
        assertEquals(bullet.getSpeed(), 6);

        assertFalse(model.jumpToHoleDeath());
        controller.getMario().setY(441);
        assertTrue(model.jumpToHoleDeath());

        controller.getMario().setRight(true);
        model.move();
        controller.getMario().setDirection(0);
        model.move();
        controller.getMario().setRight(false);
        controller.getMario().setLeft(true);
        model.move();
        controller.getMario().setDirection(1);
        model.move();
        controller.getMario().setJump(true);
        model.move();

        goomba = new Goomba(0, 0, controller.getMario().getX() + controller.getMario().getWidth(), controller.getMario().getY());
        controller.getMonsters().add(goomba);
        model.monsterMove();

        assertFalse(model.isRightCollison(goomba));
        brick.setX(goomba.getRightX());
        brick.setY(goomba.getRightY());
        assertTrue(model.isRightCollison(goomba));

        assertTrue(model.isRightCliff(goomba));
        assertTrue(model.isLeftCliff(goomba));
        brick.setY(goomba.getY() + goomba.getHeight());
        brick.setX(goomba.getX());
        assertFalse(model.isRightCliff(goomba));
        assertFalse(model.isLeftCliff(goomba));

        assertFalse(model.isLeftCollsion(goomba));
        brick.setX(goomba.getLeftX() - brick.getWidth());
        brick.setY(goomba.getLeftY());
        assertTrue(model.isLeftCollsion(goomba));

        assertFalse(model.stepOnByMario(goomba));
        controller.getMario().setJump(true);
        goomba.setY(controller.getMario().getLeftF_y());
        goomba.setX(controller.getMario().getLeftF_x());
        assertTrue(model.stepOnByMario(goomba));
        goomba.setX(controller.getMario().getRightF_x());
        assertTrue(model.stepOnByMario(goomba));

        assertFalse(model.isMarioCollideMonster(goomba));
        controller.getMario().setRightH_x((int) goomba.getLeftX());
        controller.getMario().setRightH_y((int) goomba.getUpLeftY() + 1);
        assertTrue(model.isMarioCollideMonster(goomba));

        controller.getMario().resetCollisionCor();
        controller.getMario().setInvincible(false);
        assertFalse(model.isMarioCollideMonster(goomba));
        controller.getMario().setLeftH_x((int) goomba.getRightX());
        controller.getMario().setLeftH_y((int) goomba.getUpRightY() + 1);
        assertTrue(model.isMarioCollideMonster(goomba));

        controller.getMario().resetCollisionCor();
        controller.getMario().setInvincible(false);
        controller.getMario().setLevel(2);

        controller.getMario().setRightH_x((int) goomba.getLeftX());
        controller.getMario().setRightH_y((int) goomba.getUpLeftY() - 19);
        assertTrue(model.isMarioCollideMonster(goomba));

        controller.getMario().resetCollisionCor();
        controller.getMario().setInvincible(false);
        assertFalse(model.isMarioCollideMonster(goomba));
        controller.getMario().setLeftH_x((int) goomba.getRightX());
        controller.getMario().setLeftH_y((int) goomba.getUpRightY() -19);
        assertTrue(model.isMarioCollideMonster(goomba));
    }



    @BeforeAll
    /**
     * init a javafx application before testing
     * @throws InterruptedException
     */
    public static void initSimpleFX() throws InterruptedException{
        Thread t = new Thread("new"){
            @Override
            public void run() {
                Application.launch(SimpleFX.class, new String[0]);
            }
        };
        t.setDaemon(true);
        t.start();
    }

    /**
     * sample javafx application for test environment
     */
    public static class SimpleFX extends Application {
        public void start(Stage primaryStage) {
        }
    }

}