package me.giverplay.tatakai;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import me.giverplay.tatakai.world.World;

public final class Tatakai extends ApplicationAdapter
{
	public static final boolean DEBUG = false;

	private static Tatakai instance;

	private OrthographicCamera camera;
	private Viewport viewport;
	private SpriteBatch batch;

	private World world;

	private Tatakai(){}

	@Override
	public void create ()
	{
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.setToOrtho(false);

		viewport = new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
		batch = new SpriteBatch();

		world = new World(this, 256, 256, 2);
		world.generate();
	}

	@Override
	public void render ()
	{
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0.3f, 0.6f, 0.8f, 1);

		world.update(Gdx.graphics.getDeltaTime());
	}

	@Override
	public void resize(int width, int height)
	{
		viewport.update(width, height);
	}

	@Override
	public void dispose ()
	{
		batch.dispose();
		world.dispose();
	}

	public OrthographicCamera getCamera()
	{
		return camera;
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
