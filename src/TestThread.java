import javafx.application.Application;
import javafx.stage.Stage;

public class TestThread extends Thread {
    public GameModel model;
    public GameController controller;
    public void run(){
        SimpleFX application = new SimpleFX();
    }
    public class SimpleFX extends Application{
        public void start(Stage primaryStage) {
            model = new GameModel();
            controller = new GameController(model);
        }
    }

}
