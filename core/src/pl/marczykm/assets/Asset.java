package pl.marczykm.assets;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by mmarczyk on 2015-10-13.
 */
public interface Asset {
    public void update(float delta);
    public void render();
    public Rectangle getBounds();
}
