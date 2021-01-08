package me.giverplay.tatakai.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import me.giverplay.tatakai.world.World;

public class GameScreen extends ScreenAdapter
{
  private OrthographicCamera camera;
  private Viewport viewport;
  private SpriteBatch batch;

  private World world;

  @Override
  public void show ()
  {
    camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    camera.setToOrtho(false);

    viewport = new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
    batch = new SpriteBatch();

    world = new World(camera, 256, 256, 2);
    world.generate();
  }

  @Override
  public void render (float deltaTime)
  {
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    Gdx.gl.glClearColor(0.3f, 0.6f, 0.8f, 1);

    world.update(deltaTime);
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
}
