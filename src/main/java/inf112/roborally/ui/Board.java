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
    private TiledMap map;

    /**
     * Map Layer
     */
    private TiledMapTileLayer playerLayer;

    /**
     * Object Layers
     */
    private MapObjects objectEvents;
    private MapObjects objectMovers;
    private MapObjects objectLasers;
    private MapObjects objectWalls;


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
        }

        return null;
    }
}
