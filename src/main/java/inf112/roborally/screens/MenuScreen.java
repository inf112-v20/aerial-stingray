package inf112.roborally.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import inf112.roborally.Main;

public class MenuScreen implements Screen {

    private Stage stage;

    public MenuScreen() {
        stage = new Stage(new StretchViewport(Main.WIDTH, Main.HEIGHT));
    }

    @Override
    public void show() {
        Table menuComponents = new Table();
        Skin menuSkin = new Skin(Gdx.files.internal("rusty-robot/skin/rusty-robot-ui.json"));

        Image robot = new Image(new Texture(Gdx.files.internal("rusty-robot/raw/robot.png")));
        robot.setPosition(1000, 0);

        TextField textField = new TextField("RoboRally", menuSkin);
        textField.setDisabled(true);
        textField.setAlignment(Align.center);
        TextButton start = new TextButton("Start Game", menuSkin);
        TextButton settings = new TextButton("Settings", menuSkin);
        TextButton exit = new TextButton("Exit", menuSkin);

        start.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                new RoboRally(4);
            }
        });

        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                Gdx.app.exit();
            }
        });

        menuComponents.add(textField).size(200, 75).expandX().padBottom(20);
        menuComponents.row();
        menuComponents.add(start).size(300, 120).expandX();
        menuComponents.row();
        menuComponents.add(settings).size(300, 120).expandX();
        menuComponents.row();
        menuComponents.add(exit).size(300, 120).expandX();
        menuComponents.setFillParent(true);

        stage.addActor(menuComponents);
        stage.addActor(robot);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float v) {
        // Clear screen
        Gdx.gl.glClearColor(178 / 255f, 148 / 255f, 119 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(v);
        stage.draw();
    }

    @Override
    public void resize(int i, int i1) {

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

    @Override
    public void dispose() {

    }
}
