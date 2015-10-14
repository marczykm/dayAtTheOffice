package pl.marczykm.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import pl.marczykm.DayAtTheOffice;

/**
 * Created by mmarczyk on 2015-10-13.
 */
public class Bed extends Asset {

    private Status status;

    public Bed(DayAtTheOffice game, float x, float y) {
        super(game, x, y, "bed.png");
        status = Status.NOT_MADE;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public void update(float delta) {
    }

    public void make() {
        status = Status.MADE;
        setTexture("bed_made.png");
    }

    public enum Status {
        MADE, NOT_MADE
    }
}
