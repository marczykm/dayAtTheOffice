package pl.marczykm.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector3;
import pl.marczykm.DayAtTheOffice;
import pl.marczykm.HitHelper;
import pl.marczykm.assets.*;

import java.util.ArrayList;

/**
 * Created by mmarczyk on 2015-10-12.
 */
public class Bedroom extends CoreScreen {
    private static Bedroom instance;

    private Vector3 touchPoint;

    private static Texture background;

    private JohnDoe johnDoe;
    private Door door;
    private Desk desk;
    private Lamp lamp;
    private Bed bed;
    private Clock clock;

    private int floorGap = 10;

    private static float time = 0;

    private Bedroom(final DayAtTheOffice game) {
        super();
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.WIDTH, game.HEIGHT);

        background = new Texture(Gdx.files.internal("bedroom_background.png"));

        door = new Door(game, 400, floorGap+20);
        toUpdateAndRender.add(door);
        desk = new Desk(game, 50, floorGap);
        toUpdateAndRender.add(desk);
        lamp = new Lamp(game, game.WIDTH/2-8*game.MULTIPLY, game.HEIGHT-16*game.MULTIPLY);
        toUpdateAndRender.add(lamp);
        bed = new Bed(game, 880, floorGap);
        toUpdateAndRender.add(bed);
        clock = new Clock(game, 900, 350);
        toUpdateAndRender.add(clock);

        johnDoe = new JohnDoe(game, game.WIDTH-200, floorGap);
        toUpdateAndRender.add(johnDoe);

        touchPoint = new Vector3();
    }

    public static Bedroom getInstance() {
        if (instance == null)
            instance = new Bedroom(DayAtTheOffice.getInstance());
        time = 0;
        background = new Texture(Gdx.files.internal("bedroom_background.png"));
        return instance;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        for (int i = 0; i < toUpdateAndRender.size(); i++) {
            if (toUpdateAndRender.get(i).isRender())
                toUpdateAndRender.get(i).update(delta);
        }

        game.batch.begin();
        game.batch.draw(background, 0, 0,
                background.getWidth() * game.MULTIPLY, background.getHeight() * game.MULTIPLY);

        for (int i = 0; i < toUpdateAndRender.size(); i++) {
            if (toUpdateAndRender.get(i).isRender())
                toUpdateAndRender.get(i).render();
        }

        game.batch.end();

        time += delta;

        camera.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

        if (time > 1) {
            if (Gdx.input.isTouched() && HitHelper.hit(door, touchPoint)) {
                game.setScreen(new MainMenuScreen(game));
                dispose();
            }
            if (Gdx.input.isTouched() && HitHelper.hit(bed, touchPoint)){
                showMessage();
            }
        }
    }

    public void showMessage(){
        bed.make();
    }

    @Override
    public void dispose() {
        background.dispose();
    }
}
