package pl.marczykm.assets;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import pl.marczykm.DayAtTheOffice;

/**
 * Created by mmarczyk on 2015-10-12.
 */
public class JohnDoe extends Asset implements ApplicationListener {

    private State currentState;

    private float targetPosition;
    private float time = 0;

    private static final int FRAME_COLS = 4;
    private Animation walkAnimation;
    private Texture walkSheet;
    private TextureRegion[] walkFrames;
    private TextureRegion currentFrame;

    private ParticleEffect effect;
    private boolean shouldFart = false;
    private float fartTime = 0;
    private Sound fartSound;

    private float stateTime;

    public JohnDoe(DayAtTheOffice game, float x, float y){
        super(game, x, y, "john_doe.png");
        this.game = game;

        targetPosition = x+60;

        currentState = State.STAND;
        create();
    }

    @Override
    public void create() {
        walkSheet = new Texture(Gdx.files.internal("john_doe_walk.png"));
        walkFrames = new TextureRegion[FRAME_COLS];
        TextureRegion[][] temp = TextureRegion.split(walkSheet, walkSheet.getWidth()/FRAME_COLS, walkSheet.getHeight());
        int index = 0;
        for (int j = 0; j<FRAME_COLS; j++){
            walkFrames[index++] = temp[0][j];
        }
        walkAnimation = new Animation(0.05f, walkFrames);
        stateTime = 0f;

        effect = new ParticleEffect();
        effect.load(Gdx.files.internal("effects/fart.p"), Gdx.files.internal("img"));
        effect.setPosition(pos.x, pos.y);
        effect.allowCompletion();
        effect.start();

        fartSound = Gdx.audio.newSound(Gdx.files.internal("sound/fart.wav"));

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

        effect.setPosition(pos.x+bounds.width/2, pos.y+bounds.height/2);

        if (shouldFart){
            fartTime += Gdx.graphics.getDeltaTime();
            effect.draw(game.batch, Gdx.graphics.getDeltaTime());

            if (fartTime > 3) {
                shouldFart = false;
                effect.reset();
                fartTime = 0;
            }
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
            currentState = State.WALK;
        }
        if (pos.x > targetPosition - bounds.width/2) {
            currentState = State.WALK;
        }
        if (pos.x - 10 < targetPosition - bounds.width/2 && pos.x + 10 > targetPosition - bounds.width/2) {
            currentState = State.STAND;
        }
    }

    private void processKeys(float deltaTime) {
        time += deltaTime;
        if (time > 1) {
            if (Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.LEFT)){
                targetPosition -= 10;
                currentState = State.WALK;
            }
            if (Gdx.input.isKeyPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.RIGHT)){
                targetPosition += 10;
                currentState = State.WALK;
            }

            if (targetPosition > DayAtTheOffice.WIDTH - bounds.getWidth()/2) {
                targetPosition = DayAtTheOffice.WIDTH - bounds.getWidth()/2;
                currentState = State.STAND;
            }
            if (targetPosition < 0 + bounds.getWidth()/2) {
                targetPosition = 0 + bounds.getWidth()/2;
                currentState = State.STAND;
            }
            if (Gdx.input.isKeyPressed(Keys.F)){
                shouldFart = true;
                if (fartTime == 0)
                    fartSound.play();
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
