package fr.ironcraft.catacombs;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import fr.ironcraft.catacombs.entity.Player;
import fr.ironcraft.catacombs.level.Level;
import fr.ironcraft.catacombs.render.Art;
import fr.ironcraft.catacombs.render.Graphics;
import fr.ironcraft.catacombs.render.lwjgl.LwjglDirectGraphics;
import fr.ironcraft.catacombs.ui.Gui;

public class CatacombsGame
{
	public static final String VERSION = "0.1";
	public static CatacombsGame instance;
	
	private int displayWidth = 840;
	private int displayHeight = 480;
	
	private boolean isRunning;
	private Graphics mainGraphics;
	private Level level;
	private Gui currentGui;
	
	/**
	 * Inits the game, and call run().
	 */
	public void start()
	{
		// Init stuff...
		try
		{
			// TODO : set display icon.
			
			Display.setDisplayMode(new DisplayMode(displayWidth, displayHeight));
			Display.setTitle("Catacombs - Ironcraft - v" + VERSION);
			Display.create();
		}
		catch (LWJGLException e)
		{
			e.printStackTrace();
			System.exit(-1);
		}
		
		resize();
		
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		
		mainGraphics = new LwjglDirectGraphics();
		
		Art.loadArt();
		
		level = new Level(7, 9);
		
		Player player = new Player(level);
		player.x = 100;
		player.y = 100;

		level.addEntity(player);
		
		// Do not forget that...
		isRunning = true;
		
		lastUpdate = getTime();
		
		// Running
		run();
	}
	
	private static long getTime()
	{
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	/**
	 * Runs the main game loop.
	 */
	private void run()
	{
		while (isRunning)
		{
			isRunning = !(Display.isCloseRequested() || Keyboard.isKeyDown(Keyboard.KEY_ESCAPE));
			
			updateAll();
			render(mainGraphics);
			
			Display.update();
			Display.sync(getFPS());
		}
		
		System.exit(0);
	}
	
	private int getFPS()
	{
		return 30;
	}

	private void render(Graphics graphics)
	{
		graphics.clearScreen();
		
		if (level != null)
		{
			level.render(graphics);
		}
		
		if (currentGui != null)
		{
			currentGui.draw(graphics);
		}
	}
	
	private long lastUpdate;

	private void updateAll()
	{
		long now = getTime();
		int delta = (int) (now - lastUpdate);
		lastUpdate = now;
		
		if (Display.wasResized())
		{
			displayWidth = Display.getWidth();
			displayHeight = Display.getHeight();
			resize();
		}
		
		if (level != null)
		{
			level.update(delta);
		}
	}
	
	public void displayGui(Gui guiIn)
	{
		if (currentGui != null)
		{
			currentGui.close();
		}
		
		currentGui = guiIn;
		
		guiIn.setResolution(displayWidth, displayHeight);
		guiIn.init();
	}
	
	public void resize()
	{
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, displayWidth, displayHeight, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		
		if (currentGui != null)
		{
			currentGui.clear();
			currentGui.setResolution(displayWidth, displayWidth);
			currentGui.init();
		}
	}
}
