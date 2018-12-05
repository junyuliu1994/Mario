import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * junit testing method for javafx
 * @author Feiran Yang, Junyu Liu
 */

public class MarioTest {
    @Test
    void marioMoveTest()throws Exception{
        Thread.sleep(2000);
        GameModel model = new GameModel();
        GameController controller = new GameController(model);
        //move  and level test
        controller.getMario().setLevel(3);
        controller.getMario().setRight(true);
        model.move();
        assertEquals(controller.getMario().getImage(), model.getWxzImage());
        controller.getMario().setDirection(0);
        model.move();
        controller.getMario().setRight(false);
        controller.getMario().setLeft(true);
        model.move();
        assertEquals(controller.getMario().getImage(), model.getWxzConvertImage());
        controller.getMario().setDirection(1);
        model.move();
        controller.getMario().setJump(true);
        model.move();

        //move  and level test
        controller.getMario().setLevel(1);
        controller.getMario().setDirection(0);
        controller.getMario().setRight(false);
        controller.getMario().setLeft(true);
        controller.getMario().setJump(false);
        model.move();
        assertEquals(controller.getMario().getImage(), model.getMarioConvertImage());

        //right jump test
        controller.getMario().setLevel(1);
        controller.getMario().setRight(false);
        controller.getMario().setLeft(false);
        controller.getMario().setJump(false);

        //stand on brick test
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

        //jump test
        assertFalse(model.jumpStockByBrick());
        brick.setX(controller.getMario().getX());
        brick.setY(controller.getMario().getY() - controller.getMario().getHeight());
        assertTrue(model.jumpStockByBrick());

        brick.setX(controller.getMario().getLeft_tou_x() - 40);
        assertTrue(model.jumpStockByBrick());

        brick.setX(controller.getMario().getRight_tou_x());
        assertTrue(model.jumpStockByBrick());

        brick.setX(controller.getMario().getRightTopC_x());
        brick.setY(controller.getMario().getRightTopC_y() - 40);
        assertTrue(model.jumpStockByBrick());

        brick.setX(controller.getMario().getLeftTopC_x() - 40);
        brick.setY(controller.getMario().getLeftTopC_y() - 40);
        assertTrue(model.jumpStockByBrick());

        brick.setX(controller.getMario().getX());
        brick.setY(controller.getMario().getY() - controller.getMario().getHeight());
        controller.getBricks().remove(brick);
        QuestionBrick qB = new QuestionBrick(null, 40, 40, brick.getX(), brick.getY());
        controller.getBricks().add(qB);
        assertTrue(model.jumpStockByBrick());

        controller.getBricks().remove(qB);
        Wall wall = new Wall(null, 40, 40, qB.getX(), qB.getY());
        controller.getBricks().add(wall);
        controller.getMario().setLevel(2);
        assertTrue(model.jumpStockByBrick());

        //stop test
        model.move();
        controller.getMario().setDirection(0);
        assertTrue(model.stop());

        controller.getMario().setDirection(1);
        assertTrue(model.stop());
        controller.getMario().setLevel(2);
        controller.getMario().setDirection(1);
        model.stop();
        controller.getMario().setDirection(0);
        model.stop();
        assertEquals(controller.getMario().getOffset_y(), controller.getMario().getLv2_offset_y());
        controller.getMario().setLevel(3);
        controller.getMario().setDirection(1);
        model.stop();
        controller.getMario().setDirection(0);
        model.stop();
        assertEquals(controller.getMario().getOffset_y(), controller.getMario().getLv3_offset_y());

        controller.getMario().setRight(true);
        assertFalse(model.stop());
        controller.getMario().setRight(false);

        //jump to hole death test
        assertFalse(model.jumpToHoleDeath());
        controller.getMario().setY(441);
        assertTrue(model.jumpToHoleDeath());

        //move test
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
    }

    @Test
    void eatMushroomTest() throws Exception{
        Thread.sleep(2000);
        GameModel model = new GameModel();
        GameController controller = new GameController(model);

        //eat mushroom test
        assertEquals(controller.getMario().getLevel(), 1);
        assertFalse(model.eatMushroom());
        Mushroom mushroom = new Mushroom(null, 40, 40, controller.getMario().getX(), controller.getMario().getY());
        controller.getMushrooms().add(mushroom);
        assertTrue(model.eatMushroom());
        assertEquals(controller.getMario().getLevel(), 2);
        controller.getMushrooms().add(mushroom);
        assertTrue(model.eatMushroom());
        assertEquals(controller.getMario().getLevel(), 3);

        controller.getMario().setLevel(2);
        controller.getMario().setDirection(2);
        Mushroom mushroom3 = new Mushroom(null, 40, 40, controller.getMario().getX(), controller.getMario().getY());
        controller.getMushrooms().add(mushroom3);
        assertTrue(model.eatMushroom());
        controller.getMario().setLevel(2);
        controller.getMario().setDirection(0);
        Mushroom mushroom4 = new Mushroom(null, 40, 40, controller.getMario().getX(), controller.getMario().getY());
        controller.getMushrooms().add(mushroom4);
        assertTrue(model.eatMushroom());
    }

    @Test
    void bulletTest()throws Exception{
        Thread.sleep(2000);
        GameModel model = new GameModel();
        GameController controller = new GameController(model);

        Brick brick = new Brick(null, 40, 40, controller.getMario().getX(), controller.getMario().getY() + controller.getMario().getHeight());
        controller.getBricks().add(brick);
        //bullet test
        Bullet bullet = new Bullet(null, 40, 40, 0, 0, controller.getMario().getX(), controller.getMario().getY());
        bullet.setSpeed(1);
        controller.getBullets().add(bullet);
        assertFalse(model.bulletCollision(bullet));
        brick.setX(controller.getMario().getX() + controller.getMario().getWidth());
        brick.setY(controller.getMario().getY());
        assertTrue(model.bulletCollision(bullet));
        brick.setX(controller.getMario().getX() - controller.getMario().getWidth());
        assertTrue(model.bulletCollision(bullet));

        brick.setX(-1);
        Bullet bullet1 = new Bullet(null, 40, 40, 0, 0, controller.getMario().getX(), controller.getMario().getY());
        controller.getBullets().add(bullet1);
        bullet1.setSpeed(1);
        model.bulletMove();
        assertEquals(bullet1.getX(),controller.getMario().getX()+1);
        bullet1.setSpeed(-1);
        model.bulletMove();
        assertEquals(bullet1.getX(),controller.getMario().getX());

        //bullet spped test
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
    }

    @Test
    void monsterMoveTest()throws Exception{
        Thread.sleep(2000);
        GameModel model = new GameModel();
        GameController controller = new GameController(model);

        //monster test
        Brick brick = new Brick(null, 40, 40, controller.getMario().getX(), controller.getMario().getY() + controller.getMario().getHeight());
        controller.getBricks().add(brick);
        Bullet bullet = new Bullet(null, 40, 40, 0, 0, controller.getMario().getX(), controller.getMario().getY());
        Goomba goomba = new Goomba(0, 0, controller.getMario().getX() + controller.getMario().getWidth(), controller.getMario().getY());
        controller.getMonsters().add(goomba);
        assertTrue(model.bulletCollision(bullet));
        goomba.setX(controller.getMario().getX() - controller.getMario().getWidth());
        controller.getMonsters().add(goomba);
        assertTrue(model.bulletCollision(bullet));

        //mario collision with monsters test
        goomba = new Goomba(0, 0, controller.getMario().getX() + controller.getMario().getWidth(), controller.getMario().getY());
        controller.getMonsters().add(goomba);
        model.monsterMove();

        assertFalse(model.isRightCollison(goomba));
        brick.setX(goomba.getRightX());
        brick.setY(goomba.getRightY());
        assertTrue(model.isRightCollison(goomba));

        //monster move test
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

        //mario monster collision test
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

    @Test
    void marioMonsterCollsionTest()throws Exception {
        Thread.sleep(2000);
        GameModel model = new GameModel();
        GameController controller = new GameController(model);

        Goomba goomba = new Goomba(0, 0, controller.getMario().getX(), controller.getMario().getY());
        controller.getMonsters().add(goomba);
        controller.getMario().setLevel(3);

        model.monsterMarioCollision();
        assertEquals(controller.getMario().getLevel(), 2);
        controller.getMario().setInvincible(false);
        model.monsterMarioCollision();
        assertEquals(controller.getMario().getLevel(), 1);
        controller.getMario().setInvincible(false);
        model.monsterMarioCollision();
        assertEquals(controller.getMario().getLife(), 2);
        controller.getMario().setInvincible(false);
        model.monsterMarioCollision();
        assertEquals(controller.getMario().getLife(), 1);

        controller.getMario().setInvincible(false);
        controller.getMario().setLevel(3);
        controller.getMario().setLife(3);
        controller.getMario().setDirection(0);
        model.monsterMarioCollision();
        assertEquals(controller.getMario().getLevel(), 2);
        controller.getMario().setInvincible(false);
        model.monsterMarioCollision();
        assertEquals(controller.getMario().getLevel(), 1);
        controller.getMario().setInvincible(false);
        model.monsterMarioCollision();
        assertEquals(controller.getMario().getLife(), 2);
        controller.getMario().setInvincible(false);
        model.monsterMarioCollision();
        assertEquals(controller.getMario().getLife(), 1);
    }

    @Test
    void touchCoinTest()throws Exception {
        Thread.sleep(2000);
        GameModel model = new GameModel();
        GameController controller = new GameController(model);

        Coin coin = new Coin(null, controller.getMario().getHead_x(), controller.getMario().getHead_y() - 37);
        controller.getCoins().add(coin);
        assertTrue(model.touchCoin());

        coin.setX(controller.getMario().getLeft_tou_x() - 38);
        controller.getCoins().add(coin);
        assertTrue(model.touchCoin());

        coin.setX(controller.getMario().getRight_tou_x());
        controller.getCoins().add(coin);
        assertTrue(model.touchCoin());

        coin.setY(controller.getMario().getRightTopC_y() - 37);
        coin.setX(controller.getMario().getRightTopC_x());
        controller.getCoins().add(coin);
        assertTrue(model.touchCoin());

        coin.setX(controller.getMario().getLeftTopC_x() - 38);
        controller.getCoins().add(coin);
        assertTrue(model.touchCoin());

        coin.setY(controller.getMario().getY() - 1);
        coin.setX(controller.getMario().getX() - 38);
        controller.getCoins().add(coin);
        assertTrue(model.touchCoin());

        coin.setY(controller.getMario().getY() + controller.getMario().getHeight());
        coin.setX(controller.getMario().getX() + controller.getMario().getWidth());
        controller.getCoins().add(coin);
        assertTrue(model.touchCoin());

    }

    @Test
    void jumpFallTest()throws Exception {
        Thread.sleep(2000);
        GameModel model = new GameModel();
        GameController controller = new GameController(model);

        Mario mario = controller.getMario();
        int x = mario.getX();
        mario.setRight(true);
        mario.setJump(true);
        mario.setSpeed(2);
        model.jump();
        assertEquals(x + 2, mario.getX());
        mario.setLevel(2);
        model.jump();
        mario.setLevel(3);
        model.jump();
        assertEquals(x + 6, mario.getX());

        x = mario.getX();
        mario.setRight(false);
        mario.setLeft(true);
        mario.setSpeed(-2);
        mario.setDirection(0);
        model.jump();
        assertEquals(x - 2, mario.getX());
        mario.setLevel(2);
        model.jump();
        mario.setLevel(1);
        model.jump();
        assertEquals(x - 6, mario.getX());

        Brick brick = new Brick(null, 40, 40, controller.getMario().getX(), controller.getMario().getY() - controller.getMario().getHeight());
        controller.getBricks().add(brick);
        model.jump();
        assertEquals(mario.getJumpHeight(), mario.getJumpMax());

        x = mario.getX();
        mario.setRight(true);
        mario.setLeft(false);
        mario.setSpeed(1);
        model.fall();
        assertEquals(x+ 1, mario.getX());

        x= mario.getX();
        mario.setRight(false);
        mario.setLeft(true);
        mario.setSpeed(-1);
        model.fall();
        assertEquals(x - 1, mario.getX());
    }

    @Test
    void moveBackGroundTest()throws Exception{
        Thread.sleep(2000);
        GameModel model = new GameModel();
        GameController controller = new GameController(model);

        //move mario or others test
        controller.getMario().setX(199);
        model.moveMarioOrOhters();
        controller.getMario().setSpeed(4);
        controller.getBackground().setMoveLength(1);
        assertTrue(!model.isMoveBackground());

        controller.getMario().setX(300);
        Coin coin = new Coin(null, 1, 1);
        Mushroom mushroom = new Mushroom(null, 40, 40, 80, 80);
        Goomba goomba = new Goomba(40, 40, 40, 100);
        controller.getCoins().add(coin);
        controller.getMushrooms().add(mushroom);
        controller.getMonsters().add(goomba);
        model.moveMarioOrOhters();
        assertTrue(model.isMoveBackground());
    }

    @Test
    void effectsTest() throws Exception {
        // information
        Information newInformation = new Information("test", Color.WHITE,3,4,null);
        Information newInformation1 = new Information("test", Color.WHITE,4,4,null, 1);
        Information newInformation2 = new Information("test", Color.WHITE,3,4,null, 2);
        Information newInformation3 = new Information("test", Color.WHITE,3,4,null, 3);
        Information newInformation4 = new Information("test", Color.WHITE,3,4,null, 4);
        Information newInformation5 = new Information("test", Color.WHITE,3,4,null, 5);
        assertEquals(newInformation.isNeedUpdate(), 0);
        newInformation1.setText("test");
        assertEquals("test", newInformation1.getText());
        newInformation2.getCurrentCount();
        newInformation3.getX();
        newInformation4.getY();
        newInformation5.getColor();
        newInformation.getFont();
        newInformation.getCount();
        // fireworks
        Firework firework = new Firework(new Image("resources/blocks.png"), 20, 20, 4);
        firework.getCount();
        firework.getWidth();
        firework.getCol();
        firework.getHeight();
        firework.getImage();
        firework.getInitial_offsetX();
        firework.getOffsetY();
        firework.getOffsetX();
        firework.getX();
        firework.getY();

    }

    @Test
    void ControllerTest()throws Exception {
        Thread.sleep(2000);
        GameModel model = new GameModel();
        GameController controller = new GameController(model);

        //start test
        Brick brick = new Brick(null, 40, 40, controller.getMario().getX(), controller.getMario().getY() + controller.getMario().getHeight());
        controller.getBricks().add(brick);
        model.setStandBrick(brick);
        model.start();
        assertFalse(controller.isStart());
        controller.setStart(true);
        assertTrue(model.isStart());
        assertFalse(model.getPaused());
        model.pause();
        assertTrue(model.getPaused());
        model.resume();
        assertFalse(model.getPaused());

        model.tick();
        model.setOUT_OF_CONTROL(true);
        model.tick();
        controller.getMario().setX(700);
        model.tick();

        assertEquals(controller.getBlocks(),model.getBlocks());
        assertEquals(controller.getBricks(), model.getBricks());
        assertEquals(controller.getMushrooms(), model.getMushrooms());
        assertEquals(controller.getCoins(), model.getCoins());
        assertEquals(controller.getBullets(), model.getBullets());
        assertEquals(controller.getMonsters(), model.getMonsters());
        assertEquals(controller.getWxzImage(), model.getWxzImage());
        assertEquals(controller.getWxzConvertImage(), model.getWxzConvertImage());
        assertEquals(controller.getBlackCircles(), model.getBlackCircles());
        assertEquals(controller.getFireworks(), model.getFireworks());
        assertEquals(controller.getInformations(), model.getInformations());
        Canvas canvas = new Canvas(10,10);
        controller.restoreModel(model, canvas.getGraphicsContext2D(), canvas);

        assertFalse(model.won());
        assertEquals(model.getMarioLevel(), 1);
        model.setMarioLevel(2);
        assertEquals(model.getMarioLevel(), 2);
        assertEquals(model.getLevel(), 0);
        model.setLevel(2);
        assertEquals(model.getLevel(), 2);

        controller.getMario().setX(691);
        model.setStopMarioCountChangeColor(3);
        model.tick();

        model.setStopMarioCountChangeColor(0);
        model.setDisappearMarioCount(9);
        model.tick();
        assertEquals(model.getDisappearMarioCount(), 0);

        model.setStopMarioCountChangeColor(1);
        model.setDisappearMarioCount(4);
        model.tick();
        assertEquals(model.getDisappearMarioCount(), 0);

        model.setStopMarioCountChangeColor(2);
        model.setDisappearMarioCount(9);
        model.tick();
        assertEquals(model.getDisappearMarioCount(), 40);

        model.setStopMarioCountChangeColor(3);
        model.setDisappearMarioCount(0);
        model.tick();
        assertEquals(model.getDisappearMarioCount(), 0);

        model.setStopMarioCountChangeColor(4);
        model.setDisappearMarioCount(9);
        model.tick();
        assertEquals(model.getDisappearMarioCount(), 11);

        controller.getMario().setX(model.getFlagstaff().getX() - 40);
        model.tick();
        assertEquals(controller.getMario().getX(), model.getFlagstaff().getX() - 40);

        controller.getMario().setY(441);
        model.tick();
        assertEquals(controller.getMario().getLife(), 2);

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