import org.junit.jupiter.api.Test;
import uk.ac.glos.ct5025.s1804317.footballStats.Entry;

import static org.junit.Assert.assertEquals;

public class EntryTest {

    @Test
    void testEntry(){
        String[] data = new String[]{"user comment"};
        Entry entry = new Entry("15:50","COMMENT",data,"USER COMMENT");
        assertEquals("15:50",entry.getTime());
        assertEquals("COMMENT",entry.getAction());
        assertEquals(data,entry.getData());
        assertEquals("USER COMMENT",entry.getOutput());
    }

}
