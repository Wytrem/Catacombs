package fr.ironcraft.catacombs.entity;


import org.lwjgl.input.Keyboard;

import fr.ironcraft.catacombs.level.Level;
import fr.ironcraft.catacombs.render.Art;
import fr.ironcraft.catacombs.render.Graphics;
import fr.ironcraft.catacombs.render.TilesRenderer;
import fr.ironcraft.catacombs.utils.MathHelper;
import fr.ironcraft.catacombs.utils.PreciseFacing;

public class Player extends Entity
{
	protected double elapsedWalkTime;
	protected int walkTime;
	
	protected int lastShot;
	
	public Player(Level level)
	{
		super(level, TilesRenderer.tileSizeOnScreen - 4, TilesRenderer.tileSizeOnScreen - 4);
		speed = 2.3f;
	}
	
	

	@Override
	public void udpate(int delta)
	{
		if (Keyboard.isKeyDown(Keyboard.KEY_D))
		{
			xd += 1.0f;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_Q))
		{
			xd += -1.0f;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_Z))
		{
			yd += -1.0f;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_S))
		{
			yd += 1.0f;
		}
		
		if (Math.abs(xd) == 1.0f && Math.abs(yd) == 1.0f)
		{
			xd *= 2 / speed;
			yd *= 2 / speed;
		}
		
		if (xd != 0 || yd != 0)
		{
			walkTime = ticksAlive;
		}
		else
		{
			walkTime = 0;
		}
		
		if (checkFacing(Keyboard.KEY_UP, Keyboard.KEY_DOWN, Keyboard.KEY_LEFT, Keyboard.KEY_RIGHT))
		{
			if (ticksAlive - lastShot >= 5)
			{
				Bullet bullet = new Bullet(level);
				
				float angle = facing.getAngle();
				float cos = MathHelper.cos(angle);
				float sin = -MathHelper.sin(angle);
				
				bullet.x = x + width / 2 + cos * TilesRenderer.tileSizeOnScreen;
				bullet.y = y + height / 2 - 4 + sin * TilesRenderer.tileSizeOnScreen;

				bullet.xd = cos;
				bullet.yd = sin;
				
				level.addEntity(bullet);
				
				lastShot = ticksAlive;
			}
		}
		else
		{
			checkFacing(Keyboard.KEY_Z, Keyboard.KEY_S, Keyboard.KEY_Q, Keyboard.KEY_D);
		}
		
		super.udpate(delta);

		xd = 0;
		yd = 0;
	}
	
	private boolean checkFacing(int up, int down, int left, int right)
	{
		if (Keyboard.isKeyDown(up) && Keyboard.isKeyDown(right))
		{
			facing = PreciseFacing.NORTH_EAST;
			return true;
		}
		else if (Keyboard.isKeyDown(up) && Keyboard.isKeyDown(left))
		{
			facing = PreciseFacing.NORTH_WEST;
			return true;
		}
		else if (Keyboard.isKeyDown(down) && Keyboard.isKeyDown(right))
		{
			facing = PreciseFacing.SOUTH_EAST;
			return true;
		}
		else if (Keyboard.isKeyDown(down) && Keyboard.isKeyDown(left))
		{
			facing = PreciseFacing.SOUTH_WEST;
			return true;
		}
		else if (Keyboard.isKeyDown(up))
		{
			facing = PreciseFacing.NORTH;
			return true;
		}
		else if (Keyboard.isKeyDown(down))
		{
			facing = PreciseFacing.SOUTH;
			return true;
		}
		else if (Keyboard.isKeyDown(right))
		{
			facing = PreciseFacing.EAST;
			return true;
		}
		else if (Keyboard.isKeyDown(left))
		{
			facing = PreciseFacing.WEST;
			return true;
		}
		
		return false;
	}

	@Override
	public void draw(Graphics graphics)
	{
		graphics.push();
		graphics.translate(x, y);
		graphics.resetColor();
		graphics.drawIcon(Art.player, Art.playerIcons[walkTime % 6][facingTextureCompatibleId()], 0, 0, width, height);
		graphics.pop();
	}
	
	protected int facingTextureCompatibleId()
	{
		int id = (6 - facing.ordinal());
		int length = PreciseFacing.values().length;

		if (id < 0)
		{
			id += length;
		}
		
		return id % length;
	}
}
