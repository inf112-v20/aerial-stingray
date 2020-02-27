package inf112.roborally.tests;

import inf112.roborally.entities.Directions;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DirectionsTest {

    @Test
    public void test4Directions() {
        assertEquals(4, Directions.values().length);
    }
}
