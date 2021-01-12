package me.giverplay.tatakae.index;

import com.badlogic.gdx.utils.IntMap;
import me.giverplay.tatakae.block.Block;
import me.giverplay.tatakae.resource.Assets;

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
    COBBLESTONE = register(3, new Block(Assets.getCobblestoneTexture()));
    OBSIDIAN = register(4, new Block(Assets.getObsidianTexture()));
    BEDROCK = register(5, new Block(Assets.getBedrockTexture()));
    STONE = register(1, new Block(Assets.getStoneTexture()));
    DIRT = register(2, new Block(Assets.getDirtStone()));
    AIR = register(0, new Block(null));
  }
}
