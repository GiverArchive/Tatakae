package me.giverplay.tatakae;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import me.giverplay.tatakae.screen.PreloadScreen;

public final class Tatakae extends Game
{
	public static final String NAME = "Tatakae";

	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 720;

	public static final boolean DEBUG = false;

	private static Tatakae instance;

	private final FPSLogger fpsLogger = new FPSLogger(NAME);

	private Tatakae(){}

	@Override
	public void create()
	{
		setScreen(new PreloadScreen());
	}

	@Override
	public void render()
	{
		super.render();

		if(DEBUG && Gdx.app.getType() == Application.ApplicationType.Desktop)
		{
			fpsLogger.log();
		}
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
