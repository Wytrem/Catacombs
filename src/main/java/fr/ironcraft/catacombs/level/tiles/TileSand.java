package fr.ironcraft.catacombs.level.tiles;

import fr.ironcraft.catacombs.entity.BoundingBoxOwner;
import fr.ironcraft.catacombs.render.Icon;

public class TileSand extends Tile implements BoundingBoxOwner
{
	protected TileSand(int tileId, Icon icon)
	{
		super(tileId, icon);
	}
	
	public boolean shouldRandomRotateOnRender()
	{
		return true;
	}
}
