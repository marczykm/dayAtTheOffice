package pl.marczykm.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import pl.marczykm.DayAtTheOffice;

import java.util.Date;

/**
 * Created by mmarczyk on 2015-10-13.
 */
public class Clock extends Asset {

    private BitmapFont time;

    public Clock(DayAtTheOffice game, float x, float y) {
        super(game, x, y, "clock.png");
        bounds.width = texture.getWidth()*4;
        bounds.height = texture.getHeight()*4;
        time = new BitmapFont(Gdx.files.internal("miriam.fnt"));
        time.setColor(1, 0, 0, .8f);
        time.getData().setScale(1.2f);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render() {
        super.render();
        Date now = new Date();
        time.draw(game.batch,
                (now.getHours()<10 ? "0"+now.getHours():now.getHours())+":"
                        +(now.getMinutes()<10 ? "0"+now.getMinutes() : now.getMinutes()),
                pos.x+25, pos.y+52);
    }
}
