import org.junit.jupiter.api.Test;
import uk.ac.glos.ct5025.s1804317.footballStats.Player;
import uk.ac.glos.ct5025.s1804317.footballStats.Team;
import uk.ac.glos.ct5025.s1804317.footballStats.Tournament;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TeamTest {

    Player player;
    Team team;
    Tournament tournament;

    @org.junit.jupiter.api.BeforeEach
    void setUp(){
        team = new Team("Hull");
        player = new Player("Harry","19/02/1993",team);
        tournament = new Tournament("League Tournament");
        player.addToTeam();

    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {

    }


    @Test
    void getPlayer(){
        // player name is in list model
        assertEquals(1,team.getTeamPlayersModel().size());
        // player object is in team players
        assertEquals(1,team.getTeamPlayers().size());

    }

    @Test
    void getActivePlayer(){
        // player is listed as an active player
        team.addActivePlayer(0);
        assertEquals(1,team.getActivePlayers().size());
        assertEquals(1, team.getActivePlayers().size());
        team.resetActivePlayers();
        assertEquals(0,team.getActivePlayers().size());
    }

    @Test
    void scoreGoal(){
        team.scoreGoal();
        assertEquals(1,team.getGoals());
        // further tests aren't done here as they do not include public functions
    }

    @Test
    void gamesPlayed(){
        team.gameWon();
        team.gameLost();
        team.gameDrawn();
        assertEquals(1,team.getGamesWon());
        assertEquals(1,team.getGamesLost());
        assertEquals(1,team.getGamesDrawn());
        // tournament points, 3 from win 1 from draw
        assertEquals(4,team.getPoints());

    }

}
