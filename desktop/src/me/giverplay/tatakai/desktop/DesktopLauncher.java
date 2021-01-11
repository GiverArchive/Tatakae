package me.giverplay.tatakai.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import me.giverplay.tatakai.Tatakae;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setWindowedMode(Tatakae.SCREEN_WIDTH, Tatakae.SCREEN_HEIGHT);
		config.setResizable(false);
		config.useVsync(true);

		new Lwjgl3Application(Tatakae.getInstance(), config);
	}
}
