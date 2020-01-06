import org.junit.jupiter.api.Test;
import uk.ac.glos.ct5025.s1804317.footballStats.Player;
import uk.ac.glos.ct5025.s1804317.footballStats.Team;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    Player player;
    Team team;

    @org.junit.jupiter.api.BeforeEach
    void setUp(){
        team = new Team("Hull");
        player = new Player("Harry","19/02/1993",team);
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown(){

    }

    @Test
    void getGoals(){
        assertEquals(0,player.getGoals());
    }

    @Test
    void getDoB(){
        assertEquals("19/02/1993",player.getPlayerDoB());
    }

    @Test
    void scoreGoal(){
        player.scoreGoal();
        assertEquals(1,player.getGoals());
        assertEquals(1,player.getGoalsFor());
        assertEquals(1,team.getGoals());
    }

    @Test
    void addToTeam(){
        player.addToTeam();
        assertEquals(1,team.getTeamPlayers().size());
    }
}



