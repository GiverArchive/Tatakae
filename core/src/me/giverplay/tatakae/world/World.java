package me.giverplay.tatakae.world;

import com.artemis.Entity;
import com.artemis.WorldConfigurationBuilder;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Disposable;
import me.giverplay.tatakae.Tatakae;
import me.giverplay.tatakae.block.Block;
import me.giverplay.tatakae.entity.EntityFactory;
import me.giverplay.tatakae.index.Blocks;
import me.giverplay.tatakae.system.CollisionDebugSystem;
import me.giverplay.tatakae.system.MovementSystem;
import me.giverplay.tatakae.system.PlayerControllerSystem;
import me.giverplay.tatakae.system.SpriteRenderSystem;
import me.giverplay.tatakae.system.TileRenderSystem;

import java.util.Random;

public class World implements Disposable
{
  private final int[][][] blocks;

  private final com.artemis.World artemisWorld;
  private final me.giverplay.tatakae.entity.EntityFactory entityFactory;

  private final int playerId;

  public float gravity = -576;

  private int seaLevel = 12;

  public World(OrthographicCamera camera, int width, int height, int layers)
  {
    this.blocks = new int[width][height][layers];

    WorldConfigurationBuilder config = new WorldConfigurationBuilder()
            .with(new PlayerControllerSystem())
            .with(new MovementSystem(this))
            .with(new TileRenderSystem(this, camera))
            .with(new SpriteRenderSystem(camera));

    if(Tatakae.DEBUG)
    {
      config.with(new CollisionDebugSystem(camera));
    }

    artemisWorld = new com.artemis.World(config.build());

    entityFactory = new EntityFactory();
    artemisWorld.inject(entityFactory);

    playerId = entityFactory.createPlayer(artemisWorld, 0, 720);
  }

  public void generate()
  {
    Random random = new Random();

    for(int x = 0; x < getWidth(); x++)
    {
      for(int y = 0; y < getHeight(); y++)
      {
        for(int l = 0; l < getLayers(); l++)
        {
          Block block;

          if(y < getSeaLevel() -10)
            block = Blocks.getBlockByID(5);
          else if(y < getSeaLevel() -8)
            block = Blocks.getBlockByID(4);
          else if(y < getSeaLevel() -5)
            block = Blocks.getBlockByID(3);
          else if(y < getSeaLevel() - 2)
            block = Blocks.getBlockByID(1);
          else if(y < getSeaLevel())
            block = Blocks.getBlockByID(2);
          else if(l == 0)
            block = Blocks.DIRT;
          else
            block = random.nextInt(100) < 10 ? Blocks.DIRT : Blocks.AIR;

          blocks[x][y][l] = block.ID;
        }
      }
    }
  }

  public void update(float deltaTime)
  {
    artemisWorld.setDelta(deltaTime);
    artemisWorld.process();
  }

  public Block getBlock(int x, int y, int layer)
  {
    return Blocks.getBlockByID(isValidCoordinate(x, y) ? blocks[x][y][layer] : 0);
  }

  public Block getBlock(float x, float y, int layer)
  {
    return getBlock(toWorldSize(x), toWorldSize(y), layer);
  }

  public boolean isValidCoordinate(int x, int y)
  {
    return x >= 0 && x < getWidth() && y >= 0 && y <getHeight();
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

  public Entity getPlayer()
  {
    return artemisWorld.getEntity(playerId);
  }

  public int getPlayerId()
  {
    return playerId;
  }

  public com.artemis.World getArtemisWorld()
  {
    return artemisWorld;
  }

  public int getSeaLevel()
  {
    return this.seaLevel;
  }

  public void setSeaLevel(int seaLevel)
  {
    this.seaLevel = seaLevel;
  }

  @Override
  public void dispose()
  {
    artemisWorld.dispose();
  }

  public static float toBlockSize(int coordinate)
  {
    return coordinate * Block.BLOCK_SIZE;
  }

  public static int toWorldSize(float size)
  {
    return (int) (size / Block.BLOCK_SIZE);
  }
}
