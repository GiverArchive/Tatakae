package me.giverplay.tatakai.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import me.giverplay.tatakai.entity.component.SpriteComponent;
import me.giverplay.tatakai.entity.component.TransformComponent;

public class SpriteRenderSystem extends IteratingSystem
{
  private ComponentMapper<TransformComponent> transformComponentMapper;
  private ComponentMapper<SpriteComponent> spriteComponentMapper;

  private final OrthographicCamera camera;
  private final SpriteBatch batch;

  public SpriteRenderSystem(OrthographicCamera camera)
  {
    super(Aspect.all(TransformComponent.class, SpriteComponent.class));

    this.batch = new SpriteBatch();
    this.camera = camera;
  }

  @Override
  protected void begin()
  {
    batch.setProjectionMatrix(camera.combined);
    batch.begin();
  }

  @Override
  protected void process(int entityId)
  {
    TransformComponent transformComp = transformComponentMapper.get(entityId);
    SpriteComponent spriteComp = spriteComponentMapper.get(entityId);

    if(transformComp.originCenter) {
      spriteComp.sprite.setOriginCenter();
    }
    else {
      spriteComp.sprite.setOrigin(transformComp.origin.x, transformComp.origin.y);
    }

    spriteComp.sprite.setScale(transformComp.scaleX, transformComp.scaleY);
    spriteComp.sprite.setRotation(transformComp.rotation);
    spriteComp.sprite.setPosition(transformComp.position.x, transformComp.position.y);

    spriteComp.sprite.draw(batch);
  }

  @Override
  protected void end()
  {
    batch.end();
  }

  @Override
  protected void dispose()
  {
    batch.dispose();
  }
}
