package com.khatangatao.movinggame2.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.khatangatao.movinggame2.Moving;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Moving.WIDTH;
		config.height = Moving.HEIGHT;
		config.title = Moving.TITLE;

		new LwjglApplication(new Moving(), config);
	}
}
