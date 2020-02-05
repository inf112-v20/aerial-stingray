package inf112.roborally.app;

import com.badlogic.gdx.math.Vector2;
import inf112.roborally.entities.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerTest {

    Player player;
    Vector2 playerPos;

    @Before
    public void setup() {
        playerPos = new Vector2(5, 5);
        player = new Player(playerPos);
    }

    @Test
    public void constructorPosEqualsVectorPos() {
        assertEquals(playerPos, player.getPos());
    }
}
