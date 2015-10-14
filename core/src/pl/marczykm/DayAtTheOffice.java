package pl.marczykm;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pl.marczykm.assets.Asset;
import pl.marczykm.screens.MainMenuScreen;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class DayAtTheOffice extends Game {

    public static final int WIDTH = 1280;
    public static final int HEIGHT = 640;
    public static final int MULTIPLY = 8;

    public Preferences saveStorage;

	public SpriteBatch batch;
	public BitmapFont font;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
        font = new BitmapFont();
        saveStorage = Gdx.app.getPreferences("global_save.sav");
        this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
