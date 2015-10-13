package pl.marczykm.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import pl.marczykm.DayAtTheOffice;

/**
 * Created by mmarczyk on 2015-10-12.
 */
public class MainMenuScreen implements Screen {

    final DayAtTheOffice game;

    float time = 0;

    OrthographicCamera camera;

    public MainMenuScreen(final DayAtTheOffice game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.WIDTH, game.HEIGHT);
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        game.batch.begin();
        game.font.draw(game.batch, "Welcome to Day at the Office", 100, 150);
        game.font.draw(game.batch, "Click anywhere or press spacebar to start", 100, 100);
        game.batch.end();

        time += delta;

        if(time > .5f) {
            if (Gdx.input.isTouched() || Gdx.input.isKeyPressed(Keys.SPACE)) {
                game.setScreen(new Bedroom(game));
                dispose();
            }
        }

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

    @Override
    public void dispose() {

    }
}
