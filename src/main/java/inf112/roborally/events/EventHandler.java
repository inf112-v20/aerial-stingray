package inf112.roborally.events;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import inf112.roborally.entities.Player;

import static inf112.roborally.ui.Board.TILE_SIZE;

/**
 * Provides static methods for dealing with tile-events.
 */
public class EventHandler {

    /**
     * Handles an event on a current tile.
     *
     * @param map    The current TiledMap
     * @param player The player who stands on the tile
     */
    public static void handleEvent(TiledMap map, Player player) {
        int pX = (int) player.getPos().x;
        int pY = (int) player.getPos().y;

        for (MapObject mo : map.getLayers().get("OEvents").getObjects()) {
            if (mo instanceof RectangleMapObject) {
                int rectX = (int) ((RectangleMapObject) mo).getRectangle().x / TILE_SIZE;
                int rectY = (int) ((RectangleMapObject) mo).getRectangle().y / TILE_SIZE;
                String type = (String) mo.getProperties().get("type");

                if (pX == rectX && pY == rectY) {
                    switch (type) {
                        case "Hole":
                            // Falls in hole
                            player.setPlayerIcon(player.getPlayerDeadCell());
                            break;

                        case "Flag1":
                            player.addFlag1();
                            break;

                        case "Flag2":
                            if (player.getFlags()[0])
                                player.addFlag2();
                            break;

                        case "Flag3":
                            if (player.getFlags()[0] && player.getFlags()[1])
                                player.addFlag3();
                            break;

                        case "Flag4":
                            if (player.getFlags()[0] && player.getFlags()[1] && player.getFlags()[2])
                                player.addFlag4();
                            break;

                        default:
                            player.setPlayerIcon(player.getPlayerIcon());
                            break;
                    }
                }
            }
        }
    }
}
