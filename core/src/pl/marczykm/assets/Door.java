package pl.marczykm.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import pl.marczykm.DayAtTheOffice;
import pl.marczykm.screens.CoreScreen;

/**
 * Created by mmarczyk on 2015-10-12.
 */
public class Door {



    final DayAtTheOffice game;

    Vector2 pos = new Vector2();
    public Rectangle bounds = new Rectangle();
    Texture texture;

    public Door(DayAtTheOffice game, float x, float y){
        this.game = game;

        pos.x = x;
        pos.y = y;

        bounds.width = 100;
        bounds.height = 200;
        bounds.x = pos.x;
        bounds.y = pos.y;

        texture = new Texture(Gdx.files.internal("door.png"));
    }

    public void update(){

    }

    public void render(){
        game.batch.draw(texture, pos.x, pos.y);
    }

}
