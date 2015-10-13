package pl.marczykm.assets;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by mmarczyk on 2015-10-13.
 */
public interface Asset {
    void update(float delta);
    void render();
    Rectangle getBounds();
}
