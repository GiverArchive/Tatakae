package me.giverplay.tatakai.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import me.giverplay.tatakai.block.Block;
import me.giverplay.tatakai.entity.component.CollidableComponent;
import me.giverplay.tatakai.entity.component.RigidBodyComponent;
import me.giverplay.tatakai.entity.component.TransformComponent;
import me.giverplay.tatakai.world.World;

public class MovementSystem extends IteratingSystem
{
  private ComponentMapper<TransformComponent> transformMapper;
  private ComponentMapper<RigidBodyComponent> rigidBodyMapper;
  private ComponentMapper<CollidableComponent> collidableMapper;

  private final World world;

  public MovementSystem(World world)
  {
    super(Aspect.all(TransformComponent.class, RigidBodyComponent.class));

    this.world = world;
  }

  @Override
  protected void process(int entityId)
  {
    TransformComponent transform = transformMapper.get(entityId);
    RigidBodyComponent rigidBody = rigidBodyMapper.get(entityId);
    CollidableComponent collidable = collidableMapper.get(entityId);

    float delta = super.world.delta;

    transform.position.mulAdd(rigidBody.velocity, delta);
    rigidBody.velocity.y += world.gravity * delta;

    if(collidable != null)
    {
      if(transform.position.y < world.getSeaLevel() * Block.BLOCK_SIZE)
      {
        rigidBody.velocity.y = 0;
        transform.position.y = world.getSeaLevel() * Block.BLOCK_SIZE;
        collidable.onGround = true;
      }
      else
      {
        collidable.onGround = false;
      }
    }
  }
}
