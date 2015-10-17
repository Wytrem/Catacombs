package fr.ironcraft.catacombs.ui.elements;

import fr.ironcraft.catacombs.render.Color;
import fr.ironcraft.catacombs.render.Graphics;

public class GuiButton extends ClickableRectElement
{
	private String buttonText;
	
	public GuiButton(int x, int y, int width, int height, String text)
	{
		super(x, y, width, height);
		buttonText = text;
	}

	@Override
	public void draw(Graphics graphics)
	{
		graphics.setColor(Color.WHITE);
		graphics.fillRect(x, y, width, height);
		graphics.drawString(buttonText, x, y);
	}
}
