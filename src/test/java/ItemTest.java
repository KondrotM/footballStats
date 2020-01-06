import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemTest {


    ItemMock item;
    @org.junit.jupiter.api.BeforeEach
    void setUp(){
        item = new ItemMock("Item");

    }

    @Test
    void getName(){
        assertEquals("Item",item.getName());
    }

    @Test
    void increments(){
        item.incrementGoalsFor();
        item.incrementGoals();
        item.incrementGamesWon();
        item.incrementGamesLost();
        item.incrementGamesDrawn();
        assertEquals(1,item.getGoalsFor());
        assertEquals(1,item.getGoals());
        assertEquals(1,item.getGamesWon());
        assertEquals(1,item.getGamesLost());
        assertEquals(1,item.getGamesDrawn());

    }

}
