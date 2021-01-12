package me.giverplay.tatakae.system;

import com.artemis.BaseSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import me.giverplay.tatakae.world.World;

public class TileRenderSystem extends BaseSystem
{
  private final OrthographicCamera camera;
  private final SpriteBatch batch;
  private final World world;

  private final Color tileColor = new Color();

  public TileRenderSystem(World world, OrthographicCamera camera)
  {
    this.world = world;
    this.camera = camera;
    this.batch = new SpriteBatch();
  }

  @Override
  protected void begin()
  {
    batch.setProjectionMatrix(camera.combined);
    batch.begin();
  }

  @Override
  protected void processSystem()
  {
    render(batch);
  }

  @Override
  protected void end()
  {
    batch.end();
  }

  @Override
  protected void dispose()
  {
    batch.dispose();
  }

  private void render(Batch batch)
  {
    tileColor.set(0xCCCCCCFF);
    batch.setColor(tileColor);
    renderLayer(batch, 0);

    tileColor.set(Color.WHITE);
    batch.setColor(tileColor);
    renderLayer(batch, 1);
  }

  private void renderLayer(Batch batch, int layer)
  {
    Texture texture;

    for(int x = 0; x < world.getWidth(); x++)
    {
      for(int y = 0; y < world.getHeight(); y++)
      {
        texture = world.getBlock(x, y, layer).texture;

        if(texture != null)
        {
          batch.draw(texture, World.toBlockSize(x), World.toBlockSize(y));
        }
      }
    }
  }
}
