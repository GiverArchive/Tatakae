package me.giverplay.tatakai.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import me.giverplay.tatakai.Tatakai;
import me.giverplay.tatakai.block.Block;
import me.giverplay.tatakai.entity.component.RigidBodyComponent;
import me.giverplay.tatakai.entity.component.TransformComponent;
import me.giverplay.tatakai.world.World;

public class GameScreen extends ScreenAdapter
{
  private final Vector3 screenCoordinates = new Vector3();

  private OrthographicCamera camera;
  private SpriteBatch batch;

  private World world;

  @Override
  public void show ()
  {
    camera = new OrthographicCamera(Tatakai.SCREEN_WIDTH, Tatakai.SCREEN_HEIGHT);
    camera.setToOrtho(false, Tatakai.SCREEN_WIDTH, Tatakai.SCREEN_HEIGHT);

    batch = new SpriteBatch();

    world = new World(camera, Tatakai.SCREEN_WIDTH / Block.BLOCK_SIZE, Tatakai.SCREEN_HEIGHT / Block.BLOCK_SIZE, 2);
    world.generate();

    if (Tatakai.DEBUG) {
      Gdx.input.setInputProcessor(new InputAdapter() {
        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
          screenCoordinates.set(screenX, screenY, 0);
          camera.unproject(screenCoordinates);
          world.getPlayer().getComponent(TransformComponent.class).position.set(screenCoordinates.x, screenCoordinates.y);
          world.getPlayer().getComponent(RigidBodyComponent.class).velocity.set(0, 0);
          return true;
        }
      });
    }
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
