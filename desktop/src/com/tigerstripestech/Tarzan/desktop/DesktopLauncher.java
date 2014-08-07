package com.tigerstripestech.Tarzan.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.tigerstripestech.Tarzan.ZBGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Tarzan";
        config.useGL30 = false;
        config.width = 272;
        config.height = 408;

		new LwjglApplication(new ZBGame(), config);
	}
}
