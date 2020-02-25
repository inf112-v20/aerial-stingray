package inf112.roborally.events;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import inf112.roborally.entities.Player;

import static inf112.roborally.ui.Board.TILE_SIZE;

/**
 * Provides static methods for dealing with tile-events.
 */
public class EventHandler {

    /**
     * Handles an event on a current tile with a given map & player.
     *
     * @param map    The current TiledMap
     * @param player The player who stands on the tile
     */
    public static void handleEvent(TiledMap map, Player player) {
        for (MapObject mo : map.getLayers().get("OEvents").getObjects()) {
            if (mo instanceof RectangleMapObject) {
                Rectangle rect = ((RectangleMapObject) mo).getRectangle();

                String type = (String) mo.getProperties().get("type");
                if (playerInsideRectangle(player, rect)) {  // If player is currently on the tile-type
                    switch (type) {
                        case "Hole":
                            player.setPlayerIcon(player.getPlayerDeadCell());
                            player.subtractLife();
                            break;

                        case "Flag1":
                            player.addFlag(1);
                            break;

                        case "Flag2":
                            if (player.getFlags()[0])
                                player.addFlag(2);
                            break;

                        case "Flag3":
                            if (player.getFlags()[0] && player.getFlags()[1])
                                player.addFlag(3);
                            break;

                        case "Flag4":
                            if (player.getFlags()[0] && player.getFlags()[1] && player.getFlags()[2])
                                player.addFlag(4);
                            break;

                        default:
                            player.setPlayerIcon(player.getPlayerIcon());
                            break;
                    }
                }
            }
        }
    }

    /**
     * Check if player intersects rect.
     * Used for long conveyors that span multiple tiles.
     *
     * @param rect A rectangle area to check if player within
     * @return true if player withing the specified rectangle
     */
    private static boolean playerInsideRectangle(Player player, Rectangle rect) {
        int playerX = (int) player.getPos().x;
        int playerY = (int) player.getPos().y;

        int rectX = (int) (rect.x / TILE_SIZE);
        int rectY = (int) (rect.y / TILE_SIZE);
        int rectWidth = (int) rect.getWidth() / TILE_SIZE;
        int rectHeight = (int) rect.getHeight() / TILE_SIZE;

        if (playerX >= rectX && (playerX < rectX + rectWidth)) {  // If x within bounds
            // If y withing bounds
            return playerY <= rectY && (playerY > rectY - rectHeight);
        }
        return false;
    }
}
