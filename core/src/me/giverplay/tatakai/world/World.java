package me.giverplay.tatakai.world;

import com.artemis.Entity;
import com.artemis.WorldConfigurationBuilder;
import me.giverplay.tatakai.Tatakai;
import me.giverplay.tatakai.block.Block;
import me.giverplay.tatakai.entity.EntityFactory;
import me.giverplay.tatakai.entity.system.SpriteRenderSystem;
import me.giverplay.tatakai.entity.system.TileRenderSystem;
import me.giverplay.tatakai.index.Blocks;

public class World
{
  private final int[][][] blocks;

  private final com.artemis.World world;
  private final Tatakai game;

  public World(Tatakai tatakai, int width, int height, int layers)
  {
    this.blocks = new int[width][height][layers];
    this.game = tatakai;

    WorldConfigurationBuilder config = new WorldConfigurationBuilder()
            .with(new TileRenderSystem(this, game.getCamera()))
            .with(new SpriteRenderSystem(game.getCamera()));

    world = new com.artemis.World(config.build());

    Entity player = EntityFactory.createPlayer(world, 0, 0);
  }

  public void generate()
  {
    for(int x = 0; x < getWidth(); x++)
    {
      for(int y = 0; y < getHeight(); y++)
      {
        for(int l = 0; l < getLayers(); l++)
        {
          if(y < 2)
          {
            blocks[x][y][l] = 5;
          }
          else if(y < 10)
          {
            blocks[x][y][l] = 4;
          }
          else if(y < 15)
          {
            blocks[x][y][l] = 3;
          }
          else if(y < 20)
          {
            blocks[x][y][l] = 1;
          }
          else if(y < 22)
          {
            blocks[x][y][l] = 2;
          }
        }
      }
    }
  }

  public void update(float deltaTime)
  {
    world.setDelta(deltaTime);
    world.process();
  }

  public Block getBlock(int x, int y, int layer)
  {
    return Blocks.getBlockByID(blocks[x][y][layer]);
  }

  public int getWidth()
  {
    return blocks.length;
  }

  public int getHeight()
  {
    return blocks[0].length;
  }

  public int getLayers()
  {
    return blocks[0][0].length;
  }
}