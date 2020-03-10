package inf112.roborally.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import inf112.roborally.RoboRally;
import inf112.roborally.entities.Player;
import inf112.roborally.events.EventHandler;
import inf112.roborally.ui.Board;

public class GameScreen extends InputAdapter implements Screen {
    private RoboRally parent;

    /**
     * Rendering
     */
    public SpriteBatch batch;
    private BitmapFont font;
    private TiledMapRenderer mapRenderer;
    private OrthographicCamera camera;
    MenuScreen menuScreen;

    /**
     * Players
     */
    private Player player;

    /**
     * Board
     */
    private Board board;


    public GameScreen(RoboRally parent) {
        this.parent = parent;

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
        player = new Player(new Vector2(13, 1), parent);
    }

    @Override
    public boolean keyUp(int keycode) {
        boolean moved = false;
        int x = (int) player.getPos().x;
        int y = (int) player.getPos().y;

        if (keycode == Input.Keys.UP) {
            player.move(board, player.getDir(), 1);
            moved = true;
        } else if (keycode == Input.Keys.DOWN) {
            player.move(board, player.getOppositeDir(), 1);
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

    @Override
    public void show() {
    }

    /**
     * Player icon changes based on which tile the player stands on.
     */
    private void reactToCurrentTile() {
        EventHandler.handleEvent(board, player);
    }

    @Override
    public void render(float v) {
        // Clear screen
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        drawPlayer();
        player.winCondition();

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

    @Override
    public void hide() {

    }
}
