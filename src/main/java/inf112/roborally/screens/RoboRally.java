package inf112.roborally.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import inf112.roborally.Main;
import inf112.roborally.entities.Color;
import inf112.roborally.entities.Player;
import inf112.roborally.events.EventHandler;
import inf112.roborally.ui.Board;

import java.util.ArrayList;

/**
 * Handles logic of game & controlling players.
 */
public class RoboRally extends InputAdapter implements Screen {

    /**
     * Const.
     */
    private final int DECK_WINDOW_SIZE = 280;

    /**
     * Rendering
     */
    public SpriteBatch batch;
    private BitmapFont font;
    private TiledMapRenderer mapRenderer;
    private OrthographicCamera camera;

    /**
     * Components
     */
    private Stage stage;

    /**
     * Board to be played on.
     */
    private Board board;

    /**
     * Players on the board
     */
    private ArrayList<Player> players;

    /**
     * As of now, only handle one player.
     * Reference to the player associated with this instance of RoboRally
     */
    private Player thisPlayer;


    public RoboRally() {
        setupBoard();
        setupPlayers();
        setupRendering();
        setupUI();
        setupInput();
    }

    private void setupBoard() {
        board = new Board();
    }

    private void setupPlayers() {
        players = new ArrayList<>();

        // Adding a player
        Player p1 = new Player(new Vector2(13, 1), Color.RED);
        players.add(p1);

        thisPlayer = players.get(0);
    }

    private void setupRendering() {
        // Resize
        resize(Main.WIDTH, Main.HEIGHT + DECK_WINDOW_SIZE);

        // Rendering
        batch = new SpriteBatch();
        font = new BitmapFont();
        mapRenderer = new OrthogonalTiledMapRenderer(board.getMap());
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.y -= DECK_WINDOW_SIZE;
        camera.update();
    }

    private void setupUI() {
        // Components
        TextureRegionDrawable clicked = new TextureRegionDrawable(new Texture(Gdx.files.internal("cards/uTurn_pressed.png")));
        TextureRegionDrawable unclicked = new TextureRegionDrawable(new Texture(Gdx.files.internal("cards/uTurn_notPressed.png")));

        stage = new Stage();
        for (int i = 21; i < Main.WIDTH - 140; i += 140 + 15) {
            ImageButton btn = new ImageButton(unclicked, clicked, clicked);
            btn.setSize(140, 260);
            btn.setPosition(i, 0);

            stage.addActor(btn);
        }
        ScreenManager.getInstance().setScreen(this);
    }

    private void setupInput() {
        // Input - both for controlling player and selecting cards
        InputMultiplexer im = new InputMultiplexer();
        im.addProcessor(this);
        im.addProcessor(stage);
        Gdx.input.setInputProcessor(im);
    }

    @Override
    public boolean keyUp(int keycode) {
        boolean moved = false;
        int x = (int) thisPlayer.getPos().x;
        int y = (int) thisPlayer.getPos().y;

        if (keycode == Input.Keys.UP) {
            thisPlayer.move(board, thisPlayer.getDir(), 1);
            moved = true;
        } else if (keycode == Input.Keys.DOWN) {
            thisPlayer.move(board, thisPlayer.getOppositeDir(), 1);
            moved = true;
        } else if (keycode == Input.Keys.LEFT) {
            thisPlayer.rotate(false);
            moved = true;
        } else if (keycode == Input.Keys.RIGHT) {
            thisPlayer.rotate(true);
            moved = true;
        }

        if (moved) {
            board.getPlayerLayer().setCell(x, y, null);
            reactToCurrentTile();
            System.out.println(thisPlayer.showStatus());
            return true;
        }
        return false;
    }

    /**
     * Player icon changes based on which tile the player stands on.
     */
    private void reactToCurrentTile() {
        EventHandler.handleEvent(board, thisPlayer);
        if (EventHandler.outOfBounds(thisPlayer)) {
            thisPlayer.subtractLife();
            thisPlayer.respawn();
        }
    }

    @Override
    public void render(float v) {
        // Clear screen
        Gdx.gl.glClearColor(178 / 255f, 148 / 255f, 119 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        drawPlayer();
        thisPlayer.winCondition();

        stage.act(v);
        stage.draw();

        // Render
        camera.update();
        mapRenderer.setView(camera);
        mapRenderer.render();
    }

    /**
     * Draws player on the grid.
     */
    public void drawPlayer() {
        board.getPlayerLayer().setCell((int) thisPlayer.getPos().x, (int) thisPlayer.getPos().y, thisPlayer.getPlayerIcon());
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    @Override
    public void show() {
    }

    @Override
    public void resize(int width, int height) {
        Gdx.graphics.setWindowedMode(width, height);
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
