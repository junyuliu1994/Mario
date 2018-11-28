import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

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
