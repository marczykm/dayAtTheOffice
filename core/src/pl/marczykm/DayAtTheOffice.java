package pl.marczykm;

import com.badlogic.gdx.Game;
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

    private static Map<String, Asset> temporarySave;

	public SpriteBatch batch;
	Texture img;
	public BitmapFont font;
	
	@Override
	public void create () {
        temporarySave = new HashMap<String, Asset>();
		batch = new SpriteBatch();
        font = new BitmapFont();
        this.setScreen(new MainMenuScreen(this));
	}

    public static void save(String name, Asset asset){
        temporarySave.put(name, asset);
    }

    public static Asset load(String name){
        return temporarySave.get(name);
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
