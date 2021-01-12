package me.giverplay.tatakae;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.TimeUtils;

public class FPSLogger
{
  public final String title;

  private long startTime = TimeUtils.nanoTime();

  public FPSLogger(String title)
  {
    this.title = title;
  }

  public void log()
  {
    final long nanoTime = TimeUtils.nanoTime();

    if (nanoTime - startTime > 1_000_000_000)
    {
      Gdx.graphics.setTitle(String.format("%s - %d fps", title, Gdx.graphics.getFramesPerSecond()));
      startTime = nanoTime;
    }
  }
}
