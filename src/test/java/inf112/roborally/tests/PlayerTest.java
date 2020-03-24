package inf112.roborally.tests;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import inf112.roborally.cards.CardType;
import inf112.roborally.cards.ProgramCard;
import inf112.roborally.entities.Color;
import inf112.roborally.entities.Direction;
import inf112.roborally.entities.Player;
import inf112.roborally.events.EventHandler;
import inf112.roborally.screens.ScreenManager;
import inf112.roborally.screens.WinScreen;
import inf112.roborally.ui.Board;
import org.junit.Before;
import org.junit.Test;
//import org.mockito.Mock;
//import org.mockito.Mockito;

import static org.junit.Assert.*;
//import static org.mockito.Mockito.*;

public class PlayerTest {

    /**
     * Used for cases where new player is not necessary -> saving lines of code
     */
    private Vector2 playerPos;
    private Vector2 alternativePos;
    private Player player;
    private boolean[] flags;
    //private Board board = Mockito.mock(Board.class);
    //private RectangleMapObject tile = Mockito.mock(RectangleMapObject.class);


    @Before
    public void initialize() {
        flags = new boolean[]{false, false, false, false};
        playerPos = new Vector2(0, 0);
        alternativePos = new Vector2(10, 10);
        player = new Player(playerPos, Color.GREEN);
    }

    @Test
    public void playerColorIsGreen() {
        assertEquals(Color.GREEN, player.color);
    }

    @Test
    public void testGetAndSetPos() {
        assertEquals(player.getPos(), playerPos);
        player.setPos(alternativePos);
        assertEquals(player.getPos(), alternativePos);
    }
    @Test
    public void testAddAndGetFlag(){
        player.addFlag(1);
        assertTrue(player.getFlags()[0]);
        assertFalse(player.getFlags()[1]);

        player.addFlag(2);
        assertTrue(player.getFlags()[1]);
        assertFalse(player.getFlags()[2]);

        player.addFlag(3);
        assertTrue(player.getFlags()[2]);
        assertFalse(player.getFlags()[3]);

        player.addFlag(4);
        assertTrue(player.getFlags()[3]);
    }


    @Test
    public void testHasAllFlagsIsTrue() {
        player.addFlag(1);
        player.addFlag(2);
        player.addFlag(3);
        player.addFlag(4);

        assertTrue(player.hasAllFlags());
    }

    @Test
    public void testRespawn(){
        player.setPos(alternativePos);
        player.respawn();
        assertEquals(player.getPos(), playerPos); //since backup (for now) is starting point.
        assertNotEquals(player.getPos(),alternativePos);
    }

    @Test
    public void testSubtractLifeAndShowStatus(){
        player.subtractLife();
        assertEquals(player.showStatus(), "Life: 2, Damage: 0");
        player.subtractLife();
        assertEquals(player.showStatus(), "Life: 1, Damage: 0");
    }


  /*  @Test
    public void testExecuteCardTurnLeft(){
        ProgramCard card = Mockito.mock(ProgramCard.class);
        doReturn(CardType.TURN_LEFT).when(card.getType());
        player.executeCard(board, card);
        assertEquals(Direction.EAST, player.getDir());


    }*/

//Need to find a way to test this without the static method EventHandler
 /*   @Test
    public void testMove1Step() {
        String layer = (String) tile.getProperties().get("type");
        Vector2 pos = new Vector2(0, 0);

        when(EventHandler.getTileType(board, layer, pos)).getMock();


        player.move(board, Direction.NORTH, 1);
        assertEquals(player.getPos(), new Vector2(0, 1));

    }
*/
}
