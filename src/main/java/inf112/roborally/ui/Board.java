package inf112.roborally.ui;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import inf112.roborally.entities.Player;

public class Board extends InputAdapter implements ApplicationListener {

    // Misc
    public static final int TILE_SIZE = 60;  // Size of each tile in width & height

    // Map
    private TiledMap map;

    // Map layers
    TiledMapTileLayer playerLayer;

    // Objects within the map
    MapLayer objectEvents;
    MapLayer objectLasers;
    MapLayer objectWalls;

    // Rendering
    private SpriteBatch batch;
    private BitmapFont font;
    private TiledMapRenderer mapRenderer;
    private OrthographicCamera camera;

    // Players
    private Player player;
    TiledMapTileLayer.Cell playerIcon;

    @Override
    public void create() {
        // Rendering
        batch = new SpriteBatch();
        font = new BitmapFont();

        // Input
        Gdx.input.setInputProcessor(this);

        // Map
        map = new TmxMapLoader().load("Map.tmx");
        playerLayer = (TiledMapTileLayer) map.getLayers().get("Player");

        // Objects
        objectEvents = map.getLayers().get("OEvents");
        objectLasers = map.getLayers().get("OLasers");
        objectWalls = map.getLayers().get("OWalls");

        // Camera
        mapRenderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();

        // Player
        player = new Player(new Vector2(13, 1));
        playerIcon = player.getPlayerIcon();
    }

    @Override
    public boolean keyUp(int keycode) {
        boolean moved = false;
        int x = (int) player.getPos().x;
        int y = (int) player.getPos().y;

        if (keycode == Input.Keys.UP) {
            player.move(1);
            moved = true;
        } else if (keycode == Input.Keys.DOWN){
            player.move(-1);
            moved = true;
        }else if(keycode ==Input.Keys.LEFT){
            player.rotate(false);
            moved = true;
        }else if (keycode == Input.Keys.RIGHT) {
            player.rotate(true);
            moved = true;
        }

        if (moved) {
            playerLayer.setCell(x, y, null);
            playerIcon = player.getPlayerIcon();
            System.out.println(player.showStatus());
            reactToCurrentTile();
            return true;
        }
        return false;
    }

    /**
     * Player icon changes based on which tile the player stands on.
     */
    private void reactToCurrentTile() {
        for (MapObject mo : map.getLayers().get("OEvents").getObjects()) {
            if (mo instanceof RectangleMapObject) {
                int x = (int) ((RectangleMapObject) mo).getRectangle().x / TILE_SIZE;
                int y = (int) ((RectangleMapObject) mo).getRectangle().y / TILE_SIZE;
                String type = (String) mo.getProperties().get("type");

                if ((int) player.getPos().x == x && (int) player.getPos().y == y) {
                    switch (type) {
                        case "Hole":
                            // Falls in hole
                            playerIcon = player.getPlayerDeadCell();
                            break;

                        case "Flag1":
                            player.addFlag1();
                            break;

                        case "Flag2":
                            player.addFlag2();
                            break;

                        case "Flag3":
                            player.addFlag3();
                            break;

                        case "Flag4":
                            player.addFlag4();
                            break;
                    }
                }
            }
        }
    }


    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    @Override
    public void render() {
        // Clears screen
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        drawPlayer();

        camera.update();
        mapRenderer.setView(camera);
        mapRenderer.render();
    }

    /**
     * Draws player on the grid.
     */
    public void drawPlayer() {
        playerLayer.setCell((int) player.getPos().x, (int) player.getPos().y, playerIcon);
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }
}
