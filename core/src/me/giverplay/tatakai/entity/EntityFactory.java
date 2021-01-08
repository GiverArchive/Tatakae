package me.giverplay.tatakai.entity;

import com.artemis.Entity;
import com.artemis.EntityEdit;
import com.artemis.World;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import me.giverplay.tatakai.entity.component.PlayerComponent;
import me.giverplay.tatakai.entity.component.RigidBodyComponent;
import me.giverplay.tatakai.entity.component.SpriteComponent;
import me.giverplay.tatakai.entity.component.TransformComponent;

public class EntityFactory
{
  public static Entity createPlayer(World world, float x, float y)
  {
    Entity entity = world.createEntity();

    EntityEdit edit = entity.edit();

    TransformComponent transformComponent = new TransformComponent();
    transformComponent.position.set(x, y);

    SpriteComponent spriteComponent = new SpriteComponent();
    spriteComponent.sprite = new Sprite(new Texture("player/player.png"));

    PlayerComponent playerComponent = new PlayerComponent();
    RigidBodyComponent rigidBodyComponent = new RigidBodyComponent();

    edit.add(transformComponent);
    edit.add(spriteComponent);
    edit.add(playerComponent);
    edit.add(rigidBodyComponent);

    return entity;
  }
}
