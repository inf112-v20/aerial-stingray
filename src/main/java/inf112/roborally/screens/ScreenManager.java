package inf112.roborally.screens;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

/**
 * Singleton class for managing different screens.
 */
public class ScreenManager extends Game implements ApplicationListener {

    private static ScreenManager screenManagerInstance = null;

    private ScreenManager() {
        super();
    }

    /**
     * Retrieves the one instance of the ScreenManager.
     *
     * @return A ScreenManager-object
     */
    public static ScreenManager getInstance() {
        if (screenManagerInstance == null)
            screenManagerInstance = new ScreenManager();

        return screenManagerInstance;
    }

    @Override
    public void create() {
        super.setScreen(new MenuScreen());  // Starting with menu
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
