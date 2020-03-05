package inf112.roborally;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {

    /**
     * Num. of horizontal tiles x size of tiles
     */
    public final static int WIDTH = 1440;

    /**
     * Num. of vertical tiles x size of tiles
     */
    public final static int HEIGHT = 720;

    public static void main(String[] args) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "RoboRally by aerial-stingray";
        cfg.width = WIDTH;
        cfg.height = HEIGHT;

        new LwjglApplication(new RoboRally(), cfg);
    }
}