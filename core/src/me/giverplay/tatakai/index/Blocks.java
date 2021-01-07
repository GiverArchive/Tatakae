package me.giverplay.tatakai.index;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.IntMap;
import me.giverplay.tatakai.block.Block;

public final class Blocks
{
  public static final Block AIR;
  public static final Block STONE;
  public static final Block DIRT;
  public static final Block COBBLESTONE;
  public static final Block OBSIDIAN;
  public static final Block BEDROCK;

  private static final IntMap<Block> REGISTRY = new IntMap<>();

  public static Block getBlockByID(int id)
  {
    return REGISTRY.get(id);
  }

  public static int getBlockID(Block block)
  {
    return REGISTRY.findKey(block, true, 0);
  }

  private static Block register(int id, Block block)
  {
    REGISTRY.put(id, block);
    return block;
  }

  static {
    AIR = register(0, new Block(null));
    STONE = register(1, new Block(new Texture("blocks/stone.png")));
    DIRT = register(2, new Block(new Texture("blocks/dirt.png")));
    COBBLESTONE = register(3, new Block(new Texture("blocks/cobblestone.png")));
    OBSIDIAN = register(4, new Block(new Texture("blocks/obsidian.png")));
    BEDROCK = register(5, new Block(new Texture("blocks/bedrock.png")));
  }
}
