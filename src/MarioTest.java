import static org.junit.jupiter.api.Assertions.*;

import javafx.application.Platform;
import org.junit.jupiter.api.Test;

import javafx.application.Application;

public class MarioTest {
    @Test
    void ControllerTest(){
        TestThread testThread = new TestThread();
        testThread.run();
        while(testThread.controller != null) {
        }
        System.out.print("init complete");
    }
}
