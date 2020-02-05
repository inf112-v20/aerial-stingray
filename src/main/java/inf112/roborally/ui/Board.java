package inf112.roborally.ui;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import inf112.roborally.entities.Placeable;
import inf112.roborally.entities.Player;

public class Board implements ApplicationListener {

    // Misc
    private final int TILE_SIZE = 60;  // Size of each tile in width & height
    private BitmapFont font;
    private ShapeRenderer shapeRenderer;
    // Map
    TiledMap map;
    TiledMapRenderer mapRenderer;
    OrthographicCamera camera;
    // Rendering
    private SpriteBatch batch;

    private Player player;

    @Override
    public void create() {
        // Rendering
        batch = new SpriteBatch();
        font = new BitmapFont();
        shapeRenderer = new ShapeRenderer();

        // Map
        map = new TmxMapLoader().load("Map.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();

        player = new Player(13, 1);
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

        put(player);
    }

    /**
     * Puts and draws entity on a cell in the grid.
     *
     * @param entity The entity to be put
     */
    public void put(Placeable entity) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(entity.getColor());

        shapeRenderer.rect(entity.getX() * TILE_SIZE, entity.getY() * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        shapeRenderer.end();
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
