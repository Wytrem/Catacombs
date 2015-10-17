package fr.ironcraft.catacombs.level;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import fr.ironcraft.catacombs.entity.Entity;
import fr.ironcraft.catacombs.entity.EntityComparator;
import fr.ironcraft.catacombs.level.tiles.Tile;
import fr.ironcraft.catacombs.render.Graphics;
import fr.ironcraft.catacombs.render.TilesRenderer;
import fr.ironcraft.catacombs.utils.Rectangle;


public class Level
{
	/**
	 * tilesId[row][column]
	 */
	private final TilesRenderer renderer;
	private final int[][] tilesId;

	private final List<Entity> entities;

	public Level(int width, int height)
	{
		tilesId = new int[width][height];

		Arrays.fill(tilesId[width - 1], Tile.wall.id);
		Arrays.fill(tilesId[0], Tile.wall.id);
		
		for (int i = 0; i < tilesId.length; i++)
		{
			tilesId[i][0] = Tile.wall.id;
			tilesId[i][height - 1] = Tile.wall.id;
		}

		renderer = new TilesRenderer(this);

		entities = new ArrayList<>();
	}

	public int getHeight()
	{
		return tilesId[0].length;
	}

	public int getWidth()
	{
		return tilesId.length;
	}

	public void render(Graphics graphics)
	{
		renderTiles(TilesRenderer.BACKGROUND_RENDER_PASS, graphics);
		
		for (int i = 0; i < entities.size(); i++)
		{
			entities.get(i).draw(graphics);
		}
		
		renderTiles(TilesRenderer.FOREGROUND_RENDER_PASS, graphics);
	}
	
	public void renderTiles(int renderPass, Graphics graphics)
	{
		for (int i = 0; i < tilesId.length; i++)
		{
			int[] column = tilesId[i];

			for (int j = 0; j < column.length; j++)
			{
				Tile tile = Tile.tilesList[column[j]];

				if (tile.getRenderPass() == renderPass)
				{
					renderer.renderTileAt(tile, i, j, graphics);
				}
			}
		}
	}

	public void update(int delta)
	{
		for (int i = 0; i < entities.size(); i++)
		{
			entities.get(i).udpate(delta);
		}
	}

	public void addEntity(Entity entity)
	{
		entities.add(entity);
	}

	public void removeEntity(Entity entity)
	{
		entities.remove(entity);
	}

	public List<Rectangle> getClipRectangles(Entity entity)
	{
		List<Rectangle> boundingBoxes = new ArrayList<>();

		Rectangle rectangle = entity.bbCopy().grow(TilesRenderer.tileSizeOnScreen);

		for (int i = 0; i < tilesId.length; i++)
		{
			int[] column = tilesId[i];

			for (int j = 0; j < column.length; j++)
			{
				Tile tile = Tile.tilesList[column[j]];

				tile.addClipBBs(boundingBoxes, entity, i, j);
			}
		}

		Set<Entity> visibleEntities = getEntities(rectangle);
		for (Entity entry : visibleEntities)
		{
			if (entry != entity && entry.blocks(entity))
			{
				boundingBoxes.add(entry);
			}
		}

		boundingBoxes.remove(entity);

		return boundingBoxes;
	}

	private Set<Entity> getEntities(Rectangle rectangle)
	{
		final Set<Entity> result = new TreeSet<Entity>(new EntityComparator());

		for (Entity e : entities)
		{
			if (e.intersects(rectangle))
			{
				result.add(e);
			}
		}

		return result;
	}
}
