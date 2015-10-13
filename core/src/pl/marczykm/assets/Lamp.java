package pl.marczykm.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import pl.marczykm.DayAtTheOffice;

/**
 * Created by mmarczyk on 2015-10-13.
 */
public class Lamp implements Asset {

    final DayAtTheOffice game;

    private Vector2 pos = new Vector2();
    private Rectangle bounds = new Rectangle();
    private Texture texture;

    public Lamp(DayAtTheOffice game, float x, float y) {
        this.game = game;

        pos.x = x;
        pos.y = y;

        texture = new Texture(Gdx.files.internal("lamp.png"));
        bounds.width = texture.getWidth()*game.MULTIPLY;
        bounds.height = texture.getHeight()*game.MULTIPLY;
        bounds.x = pos.x;
        bounds.y = pos.y;
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render() {
        game.batch.draw(texture, pos.x, pos.y, bounds.width, bounds.height);
    }

    @Override
    public Rectangle getBounds() {
        return bounds;
    }
}
