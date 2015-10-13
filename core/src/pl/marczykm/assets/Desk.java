package pl.marczykm.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import pl.marczykm.DayAtTheOffice;

/**
 * Created by mmarczyk on 2015-10-13.
 */
public class Desk implements Asset{
    final DayAtTheOffice game;

    Vector2 pos = new Vector2();
    private Rectangle bounds = new Rectangle();
    Texture texture;

    public Desk(DayAtTheOffice game, float x, float y){
        this.game = game;

        pos.x = x;
        pos.y = y;

        bounds.width = 32*game.MULTIPLY;
        bounds.height = 32*game.MULTIPLY;
        bounds.x = pos.x;
        bounds.y = pos.y;

        texture = new Texture(Gdx.files.internal("desk.png"));
    }

    public void update(float delta){

    }

    public void render(){
        game.batch.draw(texture, pos.x, pos.y, bounds.width, bounds.height);
    }

    @Override
    public Rectangle getBounds() {
        return bounds;
    }

}
