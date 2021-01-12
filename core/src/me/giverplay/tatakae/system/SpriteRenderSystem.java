package me.giverplay.tatakae.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import me.giverplay.tatakae.entity.component.SpriteComponent;
import me.giverplay.tatakae.entity.component.TransformComponent;

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
    Sprite sprite = spriteComp.sprite;

    if(transformComp.originCenter) {
      sprite.setOriginCenter();
    }
    else {
      sprite.setOrigin(transformComp.origin.x, transformComp.origin.y);
    }

    sprite.setScale(transformComp.scaleX, transformComp.scaleY);
    sprite.setRotation(transformComp.rotation);
    sprite.setPosition(transformComp.position.x, transformComp.position.y);

    batch.draw(
            sprite.getTexture(),
            sprite.getX() - sprite.getOriginX(),
            sprite.getY() - sprite.getOriginY(),
            sprite.getOriginX(),
            sprite.getOriginY(),
            sprite.getWidth(),
            sprite.getHeight(),
            sprite.getScaleX(),
            sprite.getScaleY(),
            sprite.getRotation(),
            sprite.getRegionX(),
            sprite.getRegionY(),
            sprite.getRegionWidth(),
            sprite.getRegionHeight(),
            spriteComp.flipX,
            spriteComp.flipY);
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
