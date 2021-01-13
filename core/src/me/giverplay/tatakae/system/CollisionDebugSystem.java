package me.giverplay.tatakae.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import me.giverplay.tatakae.entity.component.CollidableComponent;
import me.giverplay.tatakae.entity.component.RigidBodyComponent;
import me.giverplay.tatakae.entity.component.TransformComponent;

public class CollisionDebugSystem extends IteratingSystem
{
  private ComponentMapper<TransformComponent> transformMapper;
  private ComponentMapper<RigidBodyComponent> rigidBodyMapper;
  private ComponentMapper<CollidableComponent> collidableMapper;

  private final ShapeRenderer shape = new ShapeRenderer();
  private final Camera camera;

  public CollisionDebugSystem(Camera camera)
  {
    super(Aspect.all(TransformComponent.class, RigidBodyComponent.class, CollidableComponent.class));
    this.camera = camera;
  }

  @Override
  protected void begin()
  {
    shape.setProjectionMatrix(camera.combined);
    shape.begin(ShapeType.Line);
  }

  @Override
  protected void process(int entityId)
  {
    TransformComponent transform = transformMapper.get(entityId);
    RigidBodyComponent rigidBody = rigidBodyMapper.get(entityId);
    CollidableComponent collidable = collidableMapper.get(entityId);

    Vector2 min = collidable.collisionBox.getPosition(new Vector2());
    Vector2 max = collidable.collisionBox.getSize(new Vector2()).add(min);
    Vector2 size = collidable.collisionBox.getSize(new Vector2());

    shape.setColor(Color.GREEN);
    shape.rect(min.x, min.y, size.x, size.y);

    shape.setColor(Color.BLUE);
    shape.line(transform.position.x, transform.position.y, transform.position.x + rigidBody.velocity.x, transform.position.y + rigidBody.velocity.y);

    shape.setColor(Color.RED);

    if(collidable.onGround)
    {
      shape.line(min.x, min.y, max.x, min.y);
    }

    if(collidable.onCeiling)
    {
      shape.line(min.x, max.y, max.x, max.y);
    }

    if(collidable.onLeftWall)
    {
      shape.line(min.x, min.y, min.x, max.y);
    }

    if(collidable.onRightWall)
    {
      shape.line(max.x, min.y, max.x, max.y);
    }
  }

  @Override
  protected void end()
  {
    shape.end();
  }

  @Override
  protected void dispose()
  {
    shape.dispose();
  }
}
