package inf112.roborally;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import inf112.roborally.screens.MenuScreen;

/**
 * Main class for RoboRally.
 * <p>
 * Contains logic & main-loop for the game.
 */
public class RoboRally extends Game implements ApplicationListener {

    @Override
    public void create() {
        super.setScreen(new MenuScreen(this));  // Starting with menu
        super.getScreen().show();
    }

    @Override
    public void render() {
        // Clear screen
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Rendering screen
        super.getScreen().render(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void dispose() {
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
