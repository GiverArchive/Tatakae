package me.giverplay.tatakae.block;

import com.badlogic.gdx.graphics.Texture;

public class Block
{
  public static final int BLOCK_SIZE = 16;

  public final Texture texture;
  public final int ID;

  public Block(int id, Texture texture)
  {
    this.texture = texture;
    this.ID = id;
  }
}
