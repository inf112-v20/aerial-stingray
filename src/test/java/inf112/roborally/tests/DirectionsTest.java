package inf112.roborally.tests;

import inf112.roborally.entities.Direction;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DirectionsTest {

    @Test
    public void test4Directions() {
        assertEquals(4, Direction.values().length);
    }
}
