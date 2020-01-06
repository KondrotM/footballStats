import org.junit.jupiter.api.Test;
import uk.ac.glos.ct5025.s1804317.footballStats.Game;
import uk.ac.glos.ct5025.s1804317.footballStats.Player;
import uk.ac.glos.ct5025.s1804317.footballStats.Team;

import static org.junit.Assert.assertEquals;

public class GameTest {

    Team team0;
    Team team1;
    Game game;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        team0 = new Team("Team Zero");
        team1 = new Team("Team One");
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {

    }

    @Test
    void possessionChange(){
        game = new Game(team0, team1);
        game.changePossession();
        assertEquals(false,game.getPossession());
    }

    @Test
    void multipleGamesPlayed(){
        game = new Game(team0, team1);
        team0.scoreGoal();
        team0.scoreGoal();
        team1.scoreGoal();
        // team0 scores two goals
        assertEquals(2,team0.getGoals());
        game.finishGame();
        game.initialiseGame();
        team0.scoreGoal();
        // team0 scores one goal since initialised
        assertEquals(1,team0.getGoals());
        team1.scoreGoal();
        game.finishGame();
        // team0 scored three goals over two games
        assertEquals(3,team0.getGoalsFor());
        // goal difference of one
        assertEquals(1,team0.getGoalsDifference());
        // game won (+3 pts) and game drawn (+1 pts) = 4 pts
        assertEquals(4,team0.getPoints());
        // team1 has lost one game
        assertEquals(1,team1.getGamesLost());
    }

    @Test
    void activePlayerRetention(){
        Player player = new Player("Player","",team0);
        player.addToTeam();

        team0.addActivePlayer(0);

        game = new Game(team0, team1);
        game.finishGame();
        // active players reset
        assertEquals(0,team0.getActivePlayers().size());
    }


}