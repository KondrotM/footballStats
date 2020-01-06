import org.junit.jupiter.api.Test;
import uk.ac.glos.ct5025.s1804317.footballStats.*;

import static org.junit.Assert.assertEquals;

public class TournamentTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp(){
        Tournament.createTournament("Tournament Zero");
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown(){

    }

    @Test
    void getActiveTournament(){
        assertEquals("Tournament Zero", Tournament.getActiveTournament().getTournamentName());
        Tournament.createTournament("Tournament One");
        // Previous tournament remains active
        assertEquals("Tournament Zero", Tournament.getActiveTournament().getTournamentName());
        Tournament.selectTournament(1);
        // Changed active tournament
        assertEquals("Tournament One", Tournament.getActiveTournament().getTournamentName());
    }

    @Test
    void addElements(){
        Tournament tournament = Tournament.getActiveTournament();
        Team team0 = new Team("Team Zero");
        tournament.addTeam(team0);
        // team added correctly
        assertEquals(team0,tournament.getTeam(0));
        Team team1 = new Team("Team One");
        Game game = new Game(team0,team1);
        StaticGame staticGame = new StaticGame(game);
        tournament.addGame(staticGame);
        // game added correctly
        assertEquals(staticGame,tournament.getGame(0));
    }
}
