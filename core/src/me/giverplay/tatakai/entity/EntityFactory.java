package me.giverplay.tatakai.entity;

import com.artemis.ComponentMapper;
import com.artemis.World;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import me.giverplay.tatakai.entity.component.CollidableComponent;
import me.giverplay.tatakai.entity.component.JumpComponent;
import me.giverplay.tatakai.entity.component.PlayerComponent;
import me.giverplay.tatakai.entity.component.RigidBodyComponent;
import me.giverplay.tatakai.entity.component.SpriteComponent;
import me.giverplay.tatakai.entity.component.TransformComponent;

public class EntityFactory
{
  private ComponentMapper<CollidableComponent> collidableComponentMapper;
  private ComponentMapper<TransformComponent> transformComponentMapper;
  private ComponentMapper<RigidBodyComponent> rigidBodyComponentMapper;
  private ComponentMapper<PlayerComponent> playerComponentMapper;
  private ComponentMapper<SpriteComponent> spriteComponentMapper;
  private ComponentMapper<JumpComponent> jumpComponentMapper;

  public int createPlayer(World world, float x, float y)
  {
    int entityId = world.create();

    TransformComponent transformComponent = transformComponentMapper.create(entityId);
    transformComponent.position.set(x, y);

    SpriteComponent spriteComponent = spriteComponentMapper.create(entityId);
    spriteComponent.sprite = new Sprite(new Texture("player/player.png"));

    collidableComponentMapper.create(entityId);
    rigidBodyComponentMapper.create(entityId);
    playerComponentMapper.create(entityId);
    jumpComponentMapper.create(entityId);

    return entityId;
  }
}
