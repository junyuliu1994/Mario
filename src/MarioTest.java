import static org.junit.jupiter.api.Assertions.*;

import javafx.application.Application;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class MarioTest {
    @Test
    void ControllerTest(){
        GameModel model = new GameModel();
        GameController controller = new GameController(model);
    }


    public static class SimpleFX extends Application {
        public void start(Stage primaryStage) {
        }
    }

    @BeforeAll
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

}
