package inf112.roborally.tests;

import inf112.roborally.entities.Direction;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DirectionsTest {

    @Test
    public void testDirectionsLength() {
        assertEquals(4, Direction.values().length);
    }

    @Test
    public void testHasAllDirections() {
        for (Direction dir : Direction.values()) {
            assertTrue(
                    dir == Direction.NORTH ||
                            dir == Direction.EAST ||
                            dir == Direction.SOUTH ||
                            dir == Direction.WEST
            );
        }
    }
}
