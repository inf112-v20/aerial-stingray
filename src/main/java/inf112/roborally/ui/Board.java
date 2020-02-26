package inf112.roborally.ui;

import com.badlogic.gdx.maps.MapLayer;
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

    /** Object Layers */
    private MapLayer objectEvents;
    private MapLayer objectLasers;
    private MapLayer objectWalls;


    public Board() {
        // Map
        map = new TmxMapLoader().load("Map.tmx");
        playerLayer = (TiledMapTileLayer) map.getLayers().get("Player");

        // Objects
        objectEvents = map.getLayers().get("OEvents");
        objectLasers = map.getLayers().get("OLasers");
        objectWalls = map.getLayers().get("OWalls");
    }

    public TiledMap getMap() {
        return map;
    }

    public TiledMapTileLayer getPlayerLayer() {
        return playerLayer;
    }

    public MapLayer getObjectEvents() {
        return objectEvents;
    }

    public MapLayer getObjectLasers() {
        return objectLasers;
    }

    public MapLayer getObjectWalls() {
        return objectWalls;
    }
}
