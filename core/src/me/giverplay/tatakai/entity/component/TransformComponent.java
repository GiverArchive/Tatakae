package me.giverplay.tatakai.entity.component;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector2;

public class TransformComponent extends Component
{
  public final Vector2 position = new Vector2();
  public final Vector2 origin = new Vector2();

  public boolean originCenter = false;

  public float rotation = 0.0f;
  public float scaleX = 1f;
  public float scaleY = 1f;

}
