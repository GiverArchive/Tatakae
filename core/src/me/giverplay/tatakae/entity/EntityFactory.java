package me.giverplay.tatakae.entity;

import com.artemis.ComponentMapper;
import com.artemis.World;
import com.badlogic.gdx.graphics.g2d.Sprite;
import me.giverplay.tatakae.entity.component.CollidableComponent;
import me.giverplay.tatakae.entity.component.JumpComponent;
import me.giverplay.tatakae.entity.component.PlayerComponent;
import me.giverplay.tatakae.entity.component.RigidBodyComponent;
import me.giverplay.tatakae.entity.component.SpriteComponent;
import me.giverplay.tatakae.entity.component.TransformComponent;
import me.giverplay.tatakae.resource.Assets;

public class EntityFactory
{
  private ComponentMapper<CollidableComponent> collidableComponentMapper;
  private ComponentMapper<me.giverplay.tatakae.entity.component.TransformComponent> transformComponentMapper;
  private ComponentMapper<RigidBodyComponent> rigidBodyComponentMapper;
  private ComponentMapper<PlayerComponent> playerComponentMapper;
  private ComponentMapper<me.giverplay.tatakae.entity.component.SpriteComponent> spriteComponentMapper;
  private ComponentMapper<JumpComponent> jumpComponentMapper;

  public int createPlayer(World world, float x, float y)
  {
    int entityId = world.create();

    TransformComponent transformComponent = transformComponentMapper.create(entityId);
    transformComponent.position.set(x, y);

    SpriteComponent spriteComponent = spriteComponentMapper.create(entityId);
    spriteComponent.sprite = new Sprite(Assets.getPlayerTexture());

    collidableComponentMapper.create(entityId);
    rigidBodyComponentMapper.create(entityId);
    playerComponentMapper.create(entityId);
    jumpComponentMapper.create(entityId);

    return entityId;
  }
}
