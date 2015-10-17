package fr.ironcraft.catacombs.level.tiles;

import java.util.List;

import fr.ironcraft.catacombs.entity.Entity;
import fr.ironcraft.catacombs.render.Art;
import fr.ironcraft.catacombs.render.Icon;
import fr.ironcraft.catacombs.render.IconImpl;
import fr.ironcraft.catacombs.render.Image;
import fr.ironcraft.catacombs.render.TilesRenderer;
import fr.ironcraft.catacombs.utils.Rectangle;

public class Tile
{
	public static final Tile[] tilesList = new Tile[256];
	public static final Tile cobbles = new Tile(0, IconImpl.fromSize(0, 0, 0.125f, 0.125f));
	public static final Tile sand = new TileSand(1, IconImpl.fromSize(0.125f, 0, 0.125f, 0.125f));
	public static final Tile hole = new Tile(2, IconImpl.fromSize(0.375f, 0, 0.125f, 0.125f));
	public static final TileWall wall = new TileWall(3, IconImpl.fromSize(0, 0, 1 / 23f, 1));

	public final int id;

	private Icon tileIcon;
	
	protected Tile(int tileId, Icon icon)
	{
		if (tilesList[tileId] == null)
		{
			id = tileId;
			tilesList[id] = this;
			tileIcon = icon;
		}
		else
		{
			throw new IllegalArgumentException("Tile ID conflict, at " + tileId);
		}
	}
	
	public Image getSpriteSheet()
	{
		return Art.floorTiles;
	}
	
	public int getRenderId()
	{
		return TilesRenderer.RENDER_BASIC;
	}
	
	public int getRenderPass()
	{
		return TilesRenderer.BACKGROUND_RENDER_PASS;
	}
	
	public Icon getIcon()
	{
		return tileIcon;
	}
	
	public boolean shouldRandomRotateOnRender()
	{
		return false;
	}
	
	public void addClipBBs(List<Rectangle> boundingBoxes, Entity entity, int x, int y)
	{
		
	}
}
