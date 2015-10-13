package pl.marczykm.assets;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import pl.marczykm.DayAtTheOffice;

/**
 * Created by mmarczyk on 2015-10-12.
 */
public class JohnDoe implements ApplicationListener{
    State currentState;
    Direction lastWalkDirection;

    Vector2 pos = new Vector2();
    public Rectangle bounds = new Rectangle();
    Texture texture;
    final DayAtTheOffice game;
    float targetPosition;
    float time = 0;

    private static final int FRAME_COLS = 4;
    Animation walkAnimation;
    Texture walkSheet;
    TextureRegion[] walkFrames;
    TextureRegion currentFrame;

    float stateTime;

    public JohnDoe(final DayAtTheOffice game, float x, float y){
        this.game = game;
        pos.x = x;
        pos.y = y;

        bounds.width = 16*game.MULTIPLY;
        bounds.height = 32*game.MULTIPLY;
        bounds.x = pos.x;
        bounds.y = pos.y;

        targetPosition = x;

        currentState = State.STAND;

        create();
    }

    @Override
    public void create() {
        texture = new Texture(Gdx.files.internal("JohnDoe.png"));
        walkSheet = new Texture(Gdx.files.internal("JohnDoeWalk.png"));
        walkFrames = new TextureRegion[FRAME_COLS];
        TextureRegion[][] temp = TextureRegion.split(walkSheet, walkSheet.getWidth()/FRAME_COLS, walkSheet.getHeight());
        int index = 0;
        for (int j = 0; j<FRAME_COLS; j++){
            walkFrames[index++] = temp[0][j];
        }
        walkAnimation = new Animation(0.05f, walkFrames);
        stateTime = 0f;
    }

    @Override
    public void resize(int width, int height) {

    }

    public void render(){
        stateTime += Gdx.graphics.getDeltaTime();

        boolean flip = (getDirection() == Direction.LEFT);

        switch (currentState) {
            case STAND:
                currentFrame = new TextureRegion(texture);
                break;
            case WALK:
                currentFrame = walkAnimation.getKeyFrame(stateTime, true);
                break;
            default:
                currentFrame = new TextureRegion(texture);
                break;
        }
        game.batch.draw(currentFrame, flip ? pos.x + bounds.width : pos.x, pos.y, flip ? -bounds.width : bounds.width, bounds.height);
    }

    private Direction getDirection(){
        if (currentState == State.WALK) {
            if (pos.x < targetPosition - bounds.width / 2) {
                return Direction.LEFT;
            }
            if (pos.x > targetPosition - bounds.width / 2) {
                return Direction.RIGHT;
            }
        }
        if (currentState == State.STAND) {
            return lastWalkDirection;
        }
        return Direction.RIGHT;
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    public void update(float deltaTime) {
        moveToTarget(deltaTime);
        checkState();
        processKeys(deltaTime);
    }

    private void moveToTarget(float deltaTime){
        if (pos.x < targetPosition - bounds.width/2)
            pos.x += 10;

        if (pos.x > targetPosition - bounds.width/2)
            pos.x -= 10;

    }

    private void checkState() {
        if (pos.x < targetPosition - bounds.width/2) {
            if (currentState == State.WALK)
                lastWalkDirection = Direction.LEFT;
            currentState = State.WALK;
        }
        if (pos.x > targetPosition - bounds.width/2) {
            if (currentState == State.WALK)
                lastWalkDirection = Direction.RIGHT;
            currentState = State.WALK;
        }
        if (pos.x - 10 < targetPosition - bounds.width/2 && pos.x + 10 > targetPosition - bounds.width/2) {
            currentState = State.STAND;
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

    enum State {
        STAND, WALK
    }
    enum Direction {
        LEFT, RIGHT
    }
}
