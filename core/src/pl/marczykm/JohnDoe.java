package pl.marczykm;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by mmarczyk on 2015-10-12.
 */
public class JohnDoe {

    Vector2 pos = new Vector2();
    public Rectangle bounds = new Rectangle();
    Texture texture;
    final DayAtTheOffice game;
    float targetPosition;
    float time = 0;

    public JohnDoe(final DayAtTheOffice game, float x, float y){
        this.game = game;
        pos.x = x;
        pos.y = y;

        bounds.width = 2;
        bounds.height = 4;
        bounds.x = pos.x;
        bounds.y = pos.y;

        targetPosition = x;

        texture = new Texture(Gdx.files.internal("JohnDoe.png"));
    }

    public void render(){
        game.batch.draw(texture, pos.x, pos.y);
    }

    public void update(float deltaTime) {
        moveToTarget(deltaTime);
        processKeys(deltaTime);
    }

    private void moveToTarget(float deltaTime){

            if (pos.x < targetPosition) {
                pos.x += 10;
            }
            if (pos.x > targetPosition) {
                pos.x -= 10;
            }

    }

    private void processKeys(float deltaTime) {
        time += deltaTime;
        if (time > 1) {
            if (Gdx.input.isTouched()) {
                targetPosition = Gdx.input.getX();
            }
        }
    }
}
