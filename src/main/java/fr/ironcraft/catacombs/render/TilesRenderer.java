package fr.ironcraft.catacombs.render;

import java.util.Random;

import fr.ironcraft.catacombs.level.Level;
import fr.ironcraft.catacombs.level.tiles.Tile;
import fr.ironcraft.catacombs.utils.Facing;

public class TilesRenderer
{
	public static final int tileSizeOnScreen = 48;
	public static final int RENDER_BASIC = 0;
	public static final int RENDER_WALL = 1;

	public static final int BACKGROUND_RENDER_PASS = 0;
	public static final int FOREGROUND_RENDER_PASS = 1;
	
	@SuppressWarnings("unused")
	private Level level;
	private Random rendererRand;
	private transient Facing tilesRandomFacing[][];
	
	public TilesRenderer(Level level)
	{
		this.level = level;
		rendererRand = new Random();
		tilesRandomFacing = new Facing[level.getHeight()][level.getWidth()];
		for (int i = 0; i < tilesRandomFacing.length; i++)
		{
			for (int j = 0; j < tilesRandomFacing[i].length; j++)
			{
				tilesRandomFacing[i][j] = Facing.random(rendererRand);
			}
		}
	}
	
	public void renderTileAt(Tile tile, int x, int y, Graphics graphics)
	{
		int absoluteX = x * tileSizeOnScreen;
		int absoluteY = y * tileSizeOnScreen;
		
		graphics.push();
		
		graphics.translate(absoluteX, absoluteY);
		
		Facing facing = null;
		
		if (tile.shouldRandomRotateOnRender())
		{
			facing = tilesRandomFacing[y][x];
			graphics.translate(tileSizeOnScreen / 2, tileSizeOnScreen / 2);
			graphics.rotate(facing.getAngle());
			graphics.translate(tileSizeOnScreen / 2, tileSizeOnScreen / 2);
		}
		
		if (tile.getRenderId() == RENDER_BASIC)
		{
			graphics.drawIcon(tile.getSpriteSheet(), tile.getIcon(), 0, 0, tileSizeOnScreen, tileSizeOnScreen);
		}
		else if (tile.getRenderId() == RENDER_WALL)
		{
			graphics.drawIcon(tile.getSpriteSheet(), tile.getIcon(), 0, -tileSizeOnScreen, tileSizeOnScreen, tileSizeOnScreen * 2);
		}
		
		graphics.pop();
	}
}
