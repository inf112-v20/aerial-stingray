package inf112.roborally;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import inf112.roborally.ui.RoboGame;

public class Main {
    public static void main(String[] args) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "RoboRally by aerial-stingray";
        cfg.width = 1440;  // Num. of horizontal tiles x size of tiles
        cfg.height = 720;  // Num. of vertical tiles x size of tiles

        new LwjglApplication(new RoboGame(), cfg);
    }
}