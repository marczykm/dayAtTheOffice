package pl.marczykm.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import pl.marczykm.DayAtTheOffice;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(DayAtTheOffice.WIDTH, DayAtTheOffice.HEIGHT);
        }

        @Override
        public ApplicationListener getApplicationListener () {
                return new DayAtTheOffice();
        }
}