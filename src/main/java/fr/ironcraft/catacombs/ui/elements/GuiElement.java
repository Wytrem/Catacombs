package fr.ironcraft.catacombs.ui.elements;

import fr.ironcraft.catacombs.render.Graphics;

public interface GuiElement
{	
	void draw(Graphics graphics);
	
	boolean isClickable();
	
	default boolean isMouseInBounds(float mouseX, float mouseY)
	{
		return false;
	}
}
