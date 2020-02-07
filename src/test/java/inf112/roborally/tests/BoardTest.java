package inf112.roborally.tests;

import inf112.roborally.ui.Board;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BoardTest {

    @Test
    public void testTileSizeEquals60px() {
        assertEquals(Board.TILE_SIZE, 60);
    }
}
