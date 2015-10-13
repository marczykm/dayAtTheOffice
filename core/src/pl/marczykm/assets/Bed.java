package pl.marczykm.assets;

import pl.marczykm.DayAtTheOffice;

/**
 * Created by mmarczyk on 2015-10-13.
 */
public class Bed extends Asset {

    public Bed(DayAtTheOffice game, float x, float y) {
        super(game, x, y, "bed.png");
    }

    @Override
    public void update(float delta) {
    }

    public void make() {
        setTexture("bed_made.png");
    }
}
