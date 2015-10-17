package fr.ironcraft.catacombs.ui;

import java.util.ArrayList;

import fr.ironcraft.catacombs.render.Graphics;
import fr.ironcraft.catacombs.ui.elements.GuiElement;
import fr.ironcraft.catacombs.ui.event.KeyListener;
import fr.ironcraft.catacombs.ui.event.KeyboardEvent;
import fr.ironcraft.catacombs.ui.event.MouseClickEvent;
import fr.ironcraft.catacombs.ui.event.MouseEvent;
import fr.ironcraft.catacombs.ui.event.MouseListener;

public abstract class Gui implements KeyListener, MouseListener
{
	protected int screenWidth;
	protected int screenHeight;
	
	protected ArrayList<GuiElement> elements = new ArrayList<>();

	public abstract void init();
	
	public void draw(Graphics graphics)
	{
		for (int i = 0; i < elements.size(); i++)
		{
			elements.get(i).draw(graphics);
		}
	}
	
	public abstract void close();
	
	public void addElement(GuiElement element)
	{
		elements.add(element);
		
		postAdd(element);
	}

	protected void postAdd(GuiElement element)
	{
		// Nothing yet.
	}

	public void setResolution(int width, int height)
	{
		this.screenWidth = width;
		this.screenHeight = height;
	}

	public void clear()
	{
		elements.clear();
	}

	@Override
	public void mouseMoved(MouseEvent event)
	{
		MouseListener.super.mouseMoved(event);
	}

	@Override
	public void mousePressed(MouseClickEvent event)
	{
		MouseListener.super.mousePressed(event);
	}

	@Override
	public void mouseReleased(MouseClickEvent event)
	{
		MouseListener.super.mouseReleased(event);
	}

	@Override
	public void mouseClicked(MouseClickEvent event)
	{
	}

	@Override
	public void onKeyTyped(KeyboardEvent event)
	{
		
	}

	@Override
	public void onKeyPressed(KeyboardEvent event)
	{
		KeyListener.super.onKeyPressed(event);
	}

	@Override
	public void onKeyReleased(KeyboardEvent event)
	{
		KeyListener.super.onKeyReleased(event);
	}
}
