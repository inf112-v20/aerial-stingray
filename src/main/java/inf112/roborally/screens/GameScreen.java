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
import inf112.roborally.RoboRally;
import inf112.roborally.entities.Color;
import inf112.roborally.entities.Player;
import inf112.roborally.events.EventHandler;
import inf112.roborally.ui.Board;

public class GameScreen extends InputAdapter implements Screen {


    /**
     * Rendering
     */
    public SpriteBatch batch;
    private BitmapFont font;
    private TiledMapRenderer mapRenderer;
    private OrthographicCamera camera;
    private final int DECK_WINDOW_SIZE = 280;

    /**
     * Components
     */
    private Stage stage;

    /**
     * Players
     */
    private Player player;

    /**
     * Board
     */
    private Board board;


    public GameScreen(RoboRally parent) {
        // Resize
        Gdx.graphics.setWindowedMode(Main.WIDTH, Main.HEIGHT + DECK_WINDOW_SIZE);

        // Board
        board = new Board();

        // Rendering
        batch = new SpriteBatch();
        font = new BitmapFont();
        mapRenderer = new OrthogonalTiledMapRenderer(board.getMap());
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.y -= DECK_WINDOW_SIZE;
        camera.update();

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

        // Input - both for controlling player and selecting cards
        InputMultiplexer im = new InputMultiplexer();
        im.addProcessor(this);
        im.addProcessor(stage);
        Gdx.input.setInputProcessor(im);

        // Players
        player = new Player(new Vector2(13, 1), parent, Color.RED);
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
        if(EventHandler.outOfBounds(player)) {
            player.subtractLife();
            player.respawn();
        }
    }

    @Override
    public void render(float v) {
        // Clear screen
        Gdx.gl.glClearColor(178 / 255f, 148 / 255f, 119 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        drawPlayer();
        player.winCondition();

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
