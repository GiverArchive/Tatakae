package me.giverplay.tatakae.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import me.giverplay.tatakae.Tatakae;
import me.giverplay.tatakae.block.Block;
import me.giverplay.tatakae.world.World;

public class GameScreen extends ScreenAdapter
{
  private final Vector3 screenCoordinates = new Vector3();

  private OrthographicCamera camera;
  private SpriteBatch batch;

  private me.giverplay.tatakae.world.World world;

  @Override
  public void show ()
  {
    camera = new OrthographicCamera(Tatakae.SCREEN_WIDTH, Tatakae.SCREEN_HEIGHT);
    camera.setToOrtho(false, Tatakae.SCREEN_WIDTH, Tatakae.SCREEN_HEIGHT);

    batch = new SpriteBatch();

    world = new World(camera, Tatakae.SCREEN_WIDTH / me.giverplay.tatakae.block.Block.BLOCK_SIZE, Tatakae.SCREEN_HEIGHT / Block.BLOCK_SIZE, 2);
    world.generate();
  }

  @Override
  public void render(float deltaTime)
  {
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    Gdx.gl.glClearColor(0.3f, 0.6f, 0.8f, 1);

    world.update(deltaTime);
  }

  @Override
  public void resize(int width, int height)
  {

  }

  @Override
  public void dispose ()
  {
    batch.dispose();
    world.dispose();
  }
}
