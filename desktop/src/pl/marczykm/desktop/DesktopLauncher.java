package pl.marczykm.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import pl.marczykm.DayAtTheOffice;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Day at the Office";
		config.width = DayAtTheOffice.WIDTH;
		config.height = DayAtTheOffice.HEIGHT;
		new LwjglApplication(new DayAtTheOffice(), config);
	}
}
