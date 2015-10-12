package pl.marczykm.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import pl.marczykm.DayAtTheOffice;
import pl.marczykm.JohnDoe;
import pl.marczykm.assets.Door;

/**
 * Created by mmarczyk on 2015-10-12.
 */
public class Bedroom extends CoreScreen {

    final DayAtTheOffice game;
    OrthographicCamera camera;
    Texture background;
    JohnDoe johnDoe;
    Door door;
    private Vector3 touchPoint;

    float time = 0;

    public Bedroom(final DayAtTheOffice game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1024, 768);

        background = new Texture(Gdx.files.internal("bedroom.png"));

        johnDoe = new JohnDoe(game, 1024-100, 150);

        door = new Door(game, 400, 170);

        touchPoint = new Vector3();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        door.update();
        johnDoe.update(delta);

        game.batch.begin();
        game.batch.draw(background, 0, 0, 1024, 768);
        door.render();
        johnDoe.render();
        game.batch.end();

        time += delta;

        camera.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

        if (time > 1) {
            if (Gdx.input.isTouched() && hit()) {
                game.setScreen(new MainMenuScreen(game));
                dispose();
            }
        }
    }



    private boolean hit(){
        System.out.println(touchPoint.x + "x" + touchPoint.y);
        System.out.println(door.bounds.x + " " + door.bounds.y + " " + door.bounds.width + " " + door.bounds.height);
        return  door.bounds.x <= touchPoint.x &&
                door.bounds.x + door.bounds.width >= touchPoint.x &&
                door.bounds.y <= touchPoint.y &&
                door.bounds.y + door.bounds.height >= touchPoint.y;
    }

    @Override
    public void dispose() {
        background.dispose();
    }
}
