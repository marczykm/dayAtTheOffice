package pl.marczykm;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pl.marczykm.screens.MainMenuScreen;

public class DayAtTheOffice extends Game {
    private static DayAtTheOffice instance;

    public static final int WIDTH = 1280;
    public static final int HEIGHT = 640;
    public static final int MULTIPLY = 8;

	public SpriteBatch batch;
	Texture img;
	public BitmapFont font;

    private DayAtTheOffice() {
    }

    public static DayAtTheOffice getInstance(){
        if (instance == null)
            instance = new DayAtTheOffice();
        return instance;
    }
	
	@Override
	public void create () {
		batch = new SpriteBatch();
        font = new BitmapFont();
        this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
//        img.dispose();
        font.dispose();
    }
}
