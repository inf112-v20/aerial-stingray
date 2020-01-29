package inf112.skeleton.app;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Board implements ApplicationListener {

    private SpriteBatch batch;
    private BitmapFont font;
    private ShapeRenderer shapeRenderer;

    private Player player;

    private int tileSize = 60;  // Size of each tile in width & height

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        shapeRenderer = new ShapeRenderer();

        player = new Player(5, 5);
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

        drawGrid();
        put(player);
    }

    /**
     * Draws a grid based on width & height of window, and the size of each tile.
     */
    private void drawGrid() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.BLACK);

        // Horizontal
        for (int y = 0; y < Gdx.graphics.getHeight(); y += tileSize) {
            shapeRenderer.line(0, y, Gdx.graphics.getHeight(), y);
        }

        // Vertical
        for (int x = 0; x < Gdx.graphics.getWidth(); x += tileSize) {
            shapeRenderer.line(x, 0, x, Gdx.graphics.getWidth());
        }
        shapeRenderer.end();
    }

    /**
     * Puts and draws entity on a cell in the grid.
     *
     * @param entity The entity to be put
     */
    public void put(Placeable entity) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(entity.getColor());

        shapeRenderer.rect(entity.getX() * tileSize, entity.getY() * tileSize, tileSize, tileSize);
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
