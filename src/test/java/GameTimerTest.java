import org.junit.jupiter.api.Test;
import uk.ac.glos.ct5025.s1804317.footballStats.Game;
import uk.ac.glos.ct5025.s1804317.footballStats.GameTimer;
import uk.ac.glos.ct5025.s1804317.footballStats.Team;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class GameTimerTest {

    GameTimer gameTimer;

    @org.junit.jupiter.api.AfterEach
    void tearDown() {

    }

    @Test
    void testGameTimer() {
        gameTimer = new GameTimer();
        try {
            TimeUnit.SECONDS.sleep(3);
            assertEquals(3, gameTimer.getStopWatchTime(), 0.1);
        } catch (InterruptedException e){
            fail("Test Interrupted");
            e.printStackTrace();
        }
    }


}
