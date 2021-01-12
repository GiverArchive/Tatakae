package me.giverplay.tatakae.screen;

import com.badlogic.gdx.ScreenAdapter;
import me.giverplay.tatakae.Tatakae;
import me.giverplay.tatakae.resource.Assets;

public class PreloadScreen extends ScreenAdapter
{
  @Override
  public void show()
  {
    Assets.load();
    Assets.manager.finishLoading();
  }

  @Override
  public void render(float delta)
  {
    if(Assets.manager.update())
      Tatakae.getInstance().setScreen(new GameScreen());
  }
}
