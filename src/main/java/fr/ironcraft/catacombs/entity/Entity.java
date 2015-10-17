package fr.ironcraft.catacombs.entity;

import java.util.List;

import fr.ironcraft.catacombs.level.Level;
import fr.ironcraft.catacombs.render.Graphics;
import fr.ironcraft.catacombs.render.TilesRenderer;
import fr.ironcraft.catacombs.utils.PreciseFacing;
import fr.ironcraft.catacombs.utils.Rectangle;

public abstract class Entity extends Rectangle implements BoundingBoxOwner
{
	protected Level level;
	
	protected PreciseFacing facing;
	protected int ticksAlive;
	protected transient double elapsedTicks;

	public Entity(Level level, int width, int height)
	{
		super(0, 0,  width, height);
		owner = this;
		this.level = level;
		facing = PreciseFacing.EAST;
	}
	
	/**
	 * Velocity vector.
	 */
	public float xd, yd;
	
	/**
	 * This entity's speed (tiles / second).
	 */
	protected float speed = 1.0f;
	
	public abstract void draw(Graphics graphics);

	public void udpate(int delta)
	{
		elapsedTicks += delta / 50.0;
		ticksAlive = (int) elapsedTicks;
		
		float moveCoef = ((delta * speed) / 1000.0f) * TilesRenderer.tileSizeOnScreen;
		move(xd * moveCoef, yd * moveCoef);
	}
	
	protected void move(float xa, float ya)
	{
		List<? extends Rectangle> bbs = level.getClipRectangles(this);
		
		partMove(bbs, xa, 0);
		partMove(bbs, 0, ya);
	}
	
	private void partMove(List<? extends Rectangle> bbs, float xa, float ya)
	{
		float origXa = xa;
		
		float origYa = ya;
		
		Rectangle closest = null;
		float epsilon = 0.01f;
		
		for (int i = 0; i < bbs.size(); i++)
		{
			Rectangle other = bbs.get(i);
			if (other == this)
			{
				continue;
			}
			
			if (intersects(other))
			{
				continue;
			}
			
			if (ya == 0)
			{
				if (other.y >= y + height || other.y + other.height <= y)
				{
					continue;
				}
				
				if (xa > 0)
				{
					float xDist = other.x - x - width;
					
					if (xDist >= 0 && xa > xDist)
					{
						closest = other;
						xa = xDist - epsilon;
						if (xa < 0)
						{
							xa = 0;
						}
					}
				}
				else if (xa < 0)
				{
					float xDist = other.x + other.width - x;
					
					if (xDist <= 0 && xa < xDist)
					{
						closest = other;
						xa = xDist + epsilon;
						if (xa > 0)
						{
							xa = 0;
						}
					}
				}
			}
			
			if (xa == 0)
			{
				if (other.x >= x + width || other.x + other.width <= x)
				{
					continue;
				}
				
				if (ya > 0)
				{
					float yDist = other.y - y - height;
					
					if (yDist >= 0 && ya > yDist)
					{
						closest = other;
						ya = yDist - epsilon;
						if (ya < 0)
						{
							ya = 0;
						}
					}
				}
				else if (ya < 0)
				{
					float yDist = other.y + other.height - y;
					
					if (yDist <= 0 && ya < yDist)
					{
						closest = other;
						ya = yDist + epsilon;
						if (ya > 0)
						{
							ya = 0;
						}
					}
				}
			}
		}
		
		if (closest != null && closest.owner != null)
		{
			closest.owner.handleCollision(this, origXa, origYa);
		}
		
		if (xa != 0 || ya != 0)
		{
			x += xa;
			y += ya;
		}
	}

	public boolean blocks(Entity entity)
	{
		return true;
	}
	
	protected int facingTextureCompatibleId()
	{
		return facing.ordinal();
	}
}
