package pl.marczykm.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import pl.marczykm.DayAtTheOffice;
import pl.marczykm.HitHelper;
import pl.marczykm.assets.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mmarczyk on 2015-10-12.
 */
public class Bedroom extends CoreScreen {
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

    private final String BED_STATUS_SAVE_NAME = this.getClass().getSimpleName()+Bed.class.getSimpleName()+Bed.Status.class.getSimpleName();

    public Bedroom(final DayAtTheOffice game) {
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
        if (bed == null)
            bed = new Bed(game, 880, floorGap);
        toUpdateAndRender.add(bed);
        clock = new Clock(game, 900, 350);
        toUpdateAndRender.add(clock);

        johnDoe = new JohnDoe(game, game.WIDTH-200, floorGap);
        toUpdateAndRender.add(johnDoe);

        touchPoint = new Vector3();
        load();
    }

    @Override
    public void save() {
        game.saveStorage.putString(BED_STATUS_SAVE_NAME, bed.getStatus().name());
        game.saveStorage.flush();
    }

    @Override
    public void load() {
        Bed.Status bedStatus = Bed.Status.valueOf(game.saveStorage.getString(BED_STATUS_SAVE_NAME, Bed.Status.NOT_MADE.toString()));
        if (bedStatus == Bed.Status.MADE)
            bed.make();
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
                bed.make();
            }
        }
    }

    @Override
    public void dispose() {
        save();
        background.dispose();
    }
}
