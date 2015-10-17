package fr.ironcraft.catacombs.render;

import java.io.InputStream;

import fr.ironcraft.catacombs.render.lwjgl.LwjglImage;

public class Art
{
	public static Image floorTiles;
	public static Image player;
	public static Image wallTiles;
	public static Image bullet;


	public static Icon[][] playerIcons;
	public static Icon[][] bulletIcons;

	public static final void loadArt()
	{
		floorTiles = LwjglImage.load(resource("/textures/tiles/floor.png"));
		player = LwjglImage.load(resource("/textures/entity/lord_lard_sheet.png"));
		playerIcons = createSprites(6, 16, 0.1666666667f, 0.0625f);
		
		wallTiles = LwjglImage.load(resource("/textures/tiles/walltiles.png"));
		bullet = LwjglImage.load(resource("/textures/entity/bullet.png"));

		bulletIcons = createSprites(8, 1, 0.125f, 1.0f);
	}
	
	private static final Icon[][] createSprites(int sheetWidth, int sheetHeight, float spriteWidth, float spriteHeight)
	{
		Icon[][] icons = new Icon[sheetWidth][sheetHeight];
		
		for (int i = 0; i < icons.length; i++)
		{
			Icon[] column = icons[i];
			
			for (int j = 0; j < column.length; j++)
			{
				column[j] = IconImpl.fromSize(i / (float) sheetWidth, j / (float) sheetHeight, spriteWidth, spriteHeight);
			}
		}
		
		return icons;
	}
	
	private static final InputStream resource(String path)
	{
		return Art.class.getResourceAsStream(path);
	}
}
