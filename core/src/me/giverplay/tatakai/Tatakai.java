package me.giverplay.tatakai;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public final class Tatakai extends ApplicationAdapter
{
	public static final boolean DEBUG = false;

	private static Tatakai instance;

	private OrthographicCamera camera;
	private Viewport viewport;
	private SpriteBatch batch;

	public Tatakai()
	{
		instance = this;
	}

	@Override
	public void create ()
	{
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.setToOrtho(false);

		viewport = new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
		batch = new SpriteBatch();
	}

	@Override
	public void render ()
	{
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();

		batch.end();
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
	}

	public static Tatakai getInstance()
	{
		return instance;
	}
}
