import org.junit.jupiter.api.Test;
import uk.ac.glos.ct5025.s1804317.footballStats.Game;
import uk.ac.glos.ct5025.s1804317.footballStats.Player;
import uk.ac.glos.ct5025.s1804317.footballStats.Team;
import uk.ac.glos.ct5025.s1804317.footballStats.Timeline;

import static org.junit.Assert.assertEquals;

public class TimelineTest {
    Team team0;
    Team team1;
    Game game;
    Timeline timeline;

    @org.junit.jupiter.api.BeforeEach
    void setUp(){
        team0 = new Team("Team Zero");
        team1 = new Team("Team One");
        game = new Game(team0,team1);
        // timeline initiated during game constructor
        timeline = game.getTimeLine();
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown(){

    }

    @Test
    void possessionWithAwayEnd(){
        // adds timeline events
        timeline.writePossession("00:00", true);
        timeline.writePossession("00:10", false);
        timeline.writePossession("00:25", true);
        timeline.writePossession("00:30", false);
        // ends game
        timeline.writeEndGame("00:40",false);
        // 15/40 == 0.375
        assertEquals(0.375f,timeline.calculatePossession(),0.0002);
    }

    @Test
    void possessionWithHomeEnd(){
        timeline.writePossession("00:00", true);
        timeline.writePossession("00:10", false);
        timeline.writePossession("00:20", true);
        timeline.writeEndGame("00:40",true);
        // 30/40 == 0.75
        assertEquals(0.75f,timeline.calculatePossession(),0.0002);
    }



}
