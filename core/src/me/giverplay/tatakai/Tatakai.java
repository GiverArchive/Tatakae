package me.giverplay.tatakai;

import com.badlogic.gdx.Game;
import me.giverplay.tatakai.screen.GameScreen;

public final class Tatakai extends Game
{
	public static final boolean DEBUG = false;

	private static Tatakai instance;

	private Tatakai(){}

	@Override
	public void create()
	{
		setScreen(new GameScreen());
	}

	public static Tatakai getInstance()
	{
		if(instance == null)
		{
			synchronized(Tatakai.class)
			{
				instance = new Tatakai();
			}
		}

		return instance;
	}
}
