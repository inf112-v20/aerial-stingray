package inf112.roborally.ui;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import inf112.roborally.entities.Player;

public class Board extends InputAdapter implements ApplicationListener{

    // Misc
    public static final int TILE_SIZE = 60;  // Size of each tile in width & height

    // Map
    private TiledMap map;
    private TiledMapRenderer mapRenderer;
    private OrthographicCamera camera;

    // Map layers
    TiledMapTileLayer playerLayer;
    TiledMapTileLayer holeLayer;
    TiledMapTileLayer conLeft;

    // Rendering
    private SpriteBatch batch;
    private BitmapFont font;

    private Player player;
    TiledMapTileLayer.Cell playerIcon;

    @Override
    public void create() {
        // Rendering
        batch = new SpriteBatch();
        font = new BitmapFont();

        Gdx.input.setInputProcessor(this);

        // Map
        map = new TmxMapLoader().load("Map.tmx");
        playerLayer = (TiledMapTileLayer) map.getLayers().get("Player");
        holeLayer = (TiledMapTileLayer) map.getLayers().get("Hole");
        conLeft = (TiledMapTileLayer) map.getLayers().get("ConLeft");


        mapRenderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();

        player = new Player(new Vector2(13, 1));
        playerIcon = player.getPlayerIcon();

    }

    @Override
    public boolean keyUp(int keycode) {
        boolean moved = false;
        int x = (int) player.getPos().x;
        int y = (int) player.getPos().y;

        if (keycode == Input.Keys.UP) {
            player.getPos().y += 1;
            moved = true;
        } else if (keycode == Input.Keys.DOWN){
            player.getPos().y -= 1;
            moved = true;
        }else if(keycode ==Input.Keys.LEFT){
            player.getPos().x -=1;
            moved = true;
        }else if (keycode == Input.Keys.RIGHT) {
            player.getPos().x += 1;
            moved = true;
        }

        if (moved) {
            playerLayer.setCell(x, y, null);
            playerIcon = player.getPlayerIcon();
            System.out.println(player.showStatus());
            activateBoard();
            return true;
        }
        return false;
    }

    private void activateBoard(){
        if (holeLayer.getCell((int) player.getPos().x, (int) player.getPos().y) != null){
            playerIcon = player.getDeadPlayerIcon();
            player.setLife();
        }else{
            if (conLeft.getCell((int) player.getPos().x, (int) player.getPos().y) != null) {
                player.getPos().x--;
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
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        mapRenderer.setView(camera);
        mapRenderer.render();


        //if (holeLayer.getCell((int) player.getPos().x, (int) player.getPos().y) != null){
        //    playerLayer.setCell((int) player.getPos().x, (int) player.getPos().y, player.getDeadPlayerIcon());
        //}else
        //    playerLayer.setCell((int) player.getPos().x, (int) player.getPos().y, player.getPlayerIcon());


        playerLayer.setCell((int) player.getPos().x, (int) player.getPos().y, playerIcon);
        //drawPlayer(player);
    }

    /**
     * Puts and draws entity on a cell in the grid.
     *
     * @param player The entity to be put
     */
    public void drawPlayer(Player player) {
        //batch.begin();
        //batch.draw(player.getTextureRegion(), player.getPos().x * TILE_SIZE, player.getPos().y * TILE_SIZE);
        //batch.end();
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
