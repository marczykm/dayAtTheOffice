package pl.marczykm;

import com.badlogic.gdx.math.Vector3;
import pl.marczykm.assets.Asset;

/**
 * Created by mmarczyk on 2015-10-13.
 */
public class HitHelper {
    public static boolean hit(Asset asset, Vector3 point){
        return  asset.getBounds().x <= point.x &&
                asset.getBounds().x + asset.getBounds().width >= point.x &&
                asset.getBounds().y <= point.y &&
                asset.getBounds().y + asset.getBounds().height >= point.y;
    }
}
