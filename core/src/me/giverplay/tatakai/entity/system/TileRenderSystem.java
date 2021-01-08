package me.giverplay.tatakai.entity.system;

import com.artemis.BaseSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import me.giverplay.tatakai.world.World;

import static me.giverplay.tatakai.block.Block.BLOCK_SIZE;

public class TileRenderSystem extends BaseSystem
{
  private final OrthographicCamera camera;
  private final SpriteBatch batch;
  private final World world;

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
    Texture texture;

    for(int x = 0; x < world.getWidth(); x++)
    {
      for(int y = 0; y < world.getHeight(); y++)
      {
        for(int l = 0; l < world.getLayers(); l++)
        {
          texture = world.getBlock(x, y, l).texture;

          if(texture != null)
          {
            batch.draw(texture, x * BLOCK_SIZE, y * BLOCK_SIZE);
          }
        }
      }
    }
  }
}
