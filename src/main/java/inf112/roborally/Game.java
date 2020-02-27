package inf112.roborally;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import inf112.roborally.entities.Player;
import inf112.roborally.events.EventHandler;
import inf112.roborally.ui.Board;

/**
 * Main class for RoboRally.
 * <p>
 * Contains logic & main-loop for the game.
 */
public class Game extends InputAdapter implements ApplicationListener {

    /**
     * Rendering
     */
    private SpriteBatch batch;
    private BitmapFont font;
    private TiledMapRenderer mapRenderer;
    private OrthographicCamera camera;

    /**
     * Players
     */
    private Player player;

    /**
     * Board
     */
    private Board board;


    @Override
    public void create() {
        // Board
        board = new Board();

        // Rendering
        batch = new SpriteBatch();
        font = new BitmapFont();
        mapRenderer = new OrthogonalTiledMapRenderer(board.getMap());
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();

        // Input
        Gdx.input.setInputProcessor(this);

        // Players
        player = new Player(new Vector2(13, 1));
    }

    @Override
    public boolean keyUp(int keycode) {
        boolean moved = false;
        int x = (int) player.getPos().x;
        int y = (int) player.getPos().y;

        if (keycode == Input.Keys.UP) {
            if (EventHandler.canGo(board, player, 1))
                player.move(1);
            moved = true;
        } else if (keycode == Input.Keys.DOWN) {
            if (EventHandler.canGo(board, player, 1))
                player.move(-1);
            moved = true;
        } else if (keycode == Input.Keys.LEFT) {
            player.rotate(false);
            moved = true;
        } else if (keycode == Input.Keys.RIGHT) {
            player.rotate(true);
            moved = true;
        }

        if (moved) {
            board.getPlayerLayer().setCell(x, y, null);
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
        EventHandler.handleEvent(board, player);
    }

    @Override
    public void render() {
        // Clear screen
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        drawPlayer();

        // Render
        camera.update();
        mapRenderer.setView(camera);
        mapRenderer.render();
    }

    /**
     * Draws player on the grid.
     */
    public void drawPlayer() {
        board.getPlayerLayer().setCell((int) player.getPos().x, (int) player.getPos().y, player.getPlayerIcon());
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
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
