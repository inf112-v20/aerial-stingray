package inf112.roborally.ui;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import inf112.roborally.entities.Player;

public class Board implements ApplicationListener {

    // Misc
    public static final int TILE_SIZE = 60;  // Size of each tile in width & height

    // Map
    private TiledMap map;
    private TiledMapRenderer mapRenderer;
    private OrthographicCamera camera;

    // Rendering
    private SpriteBatch batch;
    private BitmapFont font;

    private Player player;

    @Override
    public void create() {
        // Rendering
        batch = new SpriteBatch();
        font = new BitmapFont();

        // Map
        map = new TmxMapLoader().load("Map.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();

        player = new Player(new Vector2(6, 6));
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        mapRenderer.setView(camera);
        mapRenderer.render();

        drawPlayer(player);
    }

    /**
     * Puts and draws entity on a cell in the grid.
     *
     * @param player The entity to be put
     */
    public void drawPlayer(Player player) {
        batch.begin();
        batch.draw(player.getTextureRegion(), player.getPos().x * TILE_SIZE, player.getPos().y * TILE_SIZE);
        batch.end();
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
