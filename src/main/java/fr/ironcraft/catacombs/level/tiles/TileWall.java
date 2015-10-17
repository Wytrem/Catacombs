package fr.ironcraft.catacombs.level.tiles;

import java.util.List;

import fr.ironcraft.catacombs.entity.BoundingBoxOwner;
import fr.ironcraft.catacombs.entity.Entity;
import fr.ironcraft.catacombs.render.Art;
import fr.ironcraft.catacombs.render.Icon;
import fr.ironcraft.catacombs.render.Image;
import fr.ironcraft.catacombs.render.TilesRenderer;
import fr.ironcraft.catacombs.utils.Rectangle;

public class TileWall extends Tile implements BoundingBoxOwner
{
	protected TileWall(int tileId, Icon icon)
	{
		super(tileId, icon);
	}

	@Override
	public void handleCollision(Entity entity, float xa, float ya)
	{
		
	}
	
	public int getRenderId()
	{
		return TilesRenderer.RENDER_WALL;
	}
	
	public void addClipBBs(List<Rectangle> boundingBoxes, Entity entity, int x, int y)
	{
		boundingBoxes.add(new Rectangle(x * TilesRenderer.tileSizeOnScreen, y * TilesRenderer.tileSizeOnScreen, TilesRenderer.tileSizeOnScreen, TilesRenderer.tileSizeOnScreen, this));
	}
	
	public Image getSpriteSheet()
	{
		return Art.wallTiles;
	}
	
	public int getRenderPass()
	{
		return TilesRenderer.FOREGROUND_RENDER_PASS;
	}
}
