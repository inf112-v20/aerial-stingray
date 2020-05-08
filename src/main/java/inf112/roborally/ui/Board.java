package inf112.roborally.ui;

import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class Board {

    /**
     * Dimension
     */
    public static final int TILE_SIZE = 60;  // Size of each tile in width & height

    /**
     * Map
     */
    private final TiledMap map;

    /**
     * Map Layer
     */
    private final TiledMapTileLayer playerLayer;

    /**
     * Object Layers
     */
    private final MapObjects objectEvents;
    private final MapObjects objectMovers;
    private final MapObjects objectLasers;
    private final MapObjects objectWalls;


    public Board() {
        map = new TmxMapLoader().load("Map.tmx");
        playerLayer = (TiledMapTileLayer) map.getLayers().get("Player");

        objectEvents = map.getLayers().get("OEvents").getObjects();
        objectMovers = map.getLayers().get("OMovers").getObjects();
        objectLasers = map.getLayers().get("OLasers").getObjects();
        objectWalls = map.getLayers().get("OWalls").getObjects();
    }

    public TiledMap getMap() {
        return map;
    }

    public TiledMapTileLayer getPlayerLayer() {
        return playerLayer;
    }

    public MapObjects getObjectLayer(String layer) {
        switch (layer) {
            case "OEvents":
                return objectEvents;

            case "OMovers":
                return objectMovers;

            case "OLasers":
                return objectLasers;

            case "OWalls":
                return objectWalls;

            default:
                System.err.println("Layer \"" + layer + "\" not found.");
                return null;
        }
    }

}
