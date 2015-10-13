package pl.marczykm.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import pl.marczykm.DayAtTheOffice;
import pl.marczykm.assets.Asset;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mmarczyk on 2015-10-12.
 */
public class CoreScreen implements Screen {

    public DayAtTheOffice game;
    OrthographicCamera camera;

    List<Asset> toUpdateAndRender;

    public CoreScreen() {
        toUpdateAndRender = new ArrayList<Asset>();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

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
