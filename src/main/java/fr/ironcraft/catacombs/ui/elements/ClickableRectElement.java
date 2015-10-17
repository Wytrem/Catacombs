package fr.ironcraft.catacombs.ui.elements;


import java.util.ArrayList;

import fr.ironcraft.catacombs.ui.event.ActionListener;
import fr.ironcraft.catacombs.ui.event.MouseClickEvent;
import fr.ironcraft.catacombs.ui.event.MouseListener;


public abstract class ClickableRectElement implements GuiElement, MouseListener
{
	private final ArrayList<ActionListener> actionListeners = new ArrayList<ActionListener>();

	protected int x, y, width, height;

	public ClickableRectElement(int x, int y, int width, int height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	@Override
	public void mouseClicked(MouseClickEvent event)
	{
		actionListeners.forEach((listener) -> listener.onAction());
	}

	@Override
	public boolean isClickable()
	{
		return true;
	}

	@Override
	public boolean isMouseInBounds(float mouseX, float mouseY)
	{
		return mouseX >= x && mouseX < x + width && mouseY >= y && mouseY < y + height;
	}

}
