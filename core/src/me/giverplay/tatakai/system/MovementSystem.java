package me.giverplay.tatakai.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import me.giverplay.tatakai.block.Block;
import me.giverplay.tatakai.entity.component.RigidBodyComponent;
import me.giverplay.tatakai.entity.component.TransformComponent;
import me.giverplay.tatakai.world.World;

public class MovementSystem extends IteratingSystem
{
  private ComponentMapper<TransformComponent> transformMapper;
  private ComponentMapper<RigidBodyComponent> rigidBodyMapper;

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

    float delta = super.world.delta;

    transform.position.y = transform.position.y + rigidBody.velocity.y * delta + 0.5f * world.gravity * delta * delta;
    rigidBody.velocity.add(0, world.gravity * delta);

    if(transform.position.y < world.getSeaLevel() * Block.BLOCK_SIZE)
    {
      rigidBody.velocity.y = 0;
      transform.position.y = world.getSeaLevel() * Block.BLOCK_SIZE;
    }
  }
}
