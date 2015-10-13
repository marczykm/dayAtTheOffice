package pl.marczykm.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import pl.marczykm.DayAtTheOffice;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mmarczyk on 2015-10-13.
 */
public abstract class Asset implements Serializable{
    DayAtTheOffice game;
    Vector2 pos = new Vector2();
    Rectangle bounds = new Rectangle();
    Texture texture;
    List<String> messages;
    boolean render = true;

    public Asset(DayAtTheOffice game, float x, float y, String textureName) {
        this.game = game;

        pos.x = x;
        pos.y = y;

        texture = new Texture(Gdx.files.internal(textureName));
        bounds.width = texture.getWidth()*game.MULTIPLY;
        bounds.height = texture.getHeight()*game.MULTIPLY;
        bounds.x = pos.x;
        bounds.y = pos.y;
        messages = new ArrayList<String>();
    }

    public abstract void update(float delta);

    public void render(){
        game.batch.draw(texture, pos.x, pos.y, bounds.width, bounds.height);
    };
    public Rectangle getBounds(){
        return bounds;
    };

    public List<String> getMessages(){
        return messages;
    }

    public boolean isRender() {
        return render;
    }

    public void setRender(boolean render) {
        this.render = render;
    }

    public void setTexture(String textureName) {
        this.texture = new Texture(Gdx.files.internal(textureName));
    }
}
