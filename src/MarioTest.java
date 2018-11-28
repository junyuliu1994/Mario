import javafx.application.Application;
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
    void ControllerTest(){
        GameModel model = new GameModel();
        GameController controller = new GameController(model);
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
        Thread.sleep(100);
    }

    /**
     * sample javafx application for test environment
     */
    public static class SimpleFX extends Application {
        public void start(Stage primaryStage) {
        }
    }

}