package me.giverplay.tatakae.resource;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import net.dermetfan.gdx.assets.AnnotationAssetManager;
import net.dermetfan.gdx.assets.AnnotationAssetManager.Asset;

public class Assets
{
  public static final AnnotationAssetManager manager = new AnnotationAssetManager(new InternalFileHandleResolver());

  @Asset
  public static AssetDescriptor<Texture> stoneTexture = new AssetDescriptor<>("blocks/stone.png", Texture.class);

  @Asset
  public static AssetDescriptor<Texture> dirtTexture = new AssetDescriptor<>("blocks/dirt.png", Texture.class);

  @Asset
  public static AssetDescriptor<Texture> cobblestoneTexture = new AssetDescriptor<>("blocks/cobblestone.png", Texture.class);

  @Asset
  public static AssetDescriptor<Texture> obsidianTexture = new AssetDescriptor<>("blocks/obsidian.png", Texture.class);

  @Asset
  public static AssetDescriptor<Texture> bedrockTexture = new AssetDescriptor<>("blocks/bedrock.png", Texture.class);

  @Asset
  public static AssetDescriptor<Texture> playerTexture = new AssetDescriptor<>("player/player.png", Texture.class);

  public static void load()
  {
    Texture.setAssetManager(manager);
    manager.load(Assets.class);
  }

  public static Texture getStoneTexture()
  {
    return manager.get(stoneTexture);
  }

  public static Texture getDirtStone()
  {
    return manager.get(dirtTexture);
  }

  public static Texture getCobblestoneTexture()
  {
    return manager.get(cobblestoneTexture);
  }

  public static Texture getObsidianTexture()
  {
    return manager.get(obsidianTexture);
  }

  public static Texture getBedrockTexture()
  {
    return manager.get(bedrockTexture);
  }

  public static Texture getPlayerTexture()
  {
    return manager.get(playerTexture);
  }
}
