package me.giverplay.tatakai;

import com.badlogic.gdx.Game;
import me.giverplay.tatakai.screen.GameScreen;

public final class Tatakae extends Game
{
	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 720;

	public static final boolean DEBUG = false;

	private static Tatakae instance;

	private Tatakae(){}

	@Override
	public void create()
	{
		setScreen(new GameScreen());
	}

	public static Tatakae getInstance()
	{
		if(instance == null)
		{
			synchronized(Tatakae.class)
			{
				instance = new Tatakae();
			}
		}

		return instance;
	}
}
