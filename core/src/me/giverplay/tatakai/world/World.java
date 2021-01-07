package me.giverplay.tatakai.world;

import static me.giverplay.tatakai.block.Block.BLOCK_SIZE;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import me.giverplay.tatakai.block.Block;
import me.giverplay.tatakai.index.Blocks;

public class World
{
  private final int[][][] blocks = new int[80][45][2];

  public World()
  {

  }

  public void generate()
  {
    for(int x = 0; x < getWidth(); x++)
    {
      for(int y = 0; y < getHeight(); y++)
      {
        if(y < 2)
        {
          blocks[x][y][1] = 5;
        }
        else if(y < 10)
        {
          blocks[x][y][1] = 4;
        }
        else if(y < 15)
        {
          blocks[x][y][1] = 3;
        }
        else if(y < 20)
        {
          blocks[x][y][1] = 1;
        }
        else if(y < 22)
        {
          blocks[x][y][1] = 2;
        }
      }
    }
  }

  public void render(Batch batch)
  {
    Texture texture;

    for(int x = 0; x < getWidth(); x++)
    {
      for(int y = 0; y < getHeight(); y++)
      {
        texture = Blocks.getBlockByID(blocks[x][y][1]).texture;

        if(texture != null)
        {
          batch.draw(texture, x * BLOCK_SIZE, y * BLOCK_SIZE);
        }
      }
    }
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
