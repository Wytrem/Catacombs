package fr.ironcraft.catacombs.entity;


import fr.ironcraft.catacombs.level.Level;
import fr.ironcraft.catacombs.render.Art;
import fr.ironcraft.catacombs.render.Graphics;
import fr.ironcraft.catacombs.render.TilesRenderer;
import fr.ironcraft.catacombs.utils.MathHelper;
import fr.ironcraft.catacombs.utils.PreciseFacing;


public class Bullet extends Entity
{
	public Bullet(Level level)
	{
		super(level, TilesRenderer.tileSizeOnScreen / 3, TilesRenderer.tileSizeOnScreen / 2);
		speed = 3.5f;
	}

	public void udpate(int delta)
	{
		updateFacing();
		
		super.udpate(delta);
	}

	private void updateFacing()
	{
		float angle = MathHelper.atan2(xd, -yd);
		
		facing = PreciseFacing.fromAngle(angle);
	}

	@Override
	public void draw(Graphics graphics)
	{
		graphics.push();
		graphics.translate(x, y);
		graphics.resetColor();
		graphics.drawIcon(Art.bullet, Art.bulletIcons[facingTextureCompatibleId()][0], 0, 0, width, height);
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
