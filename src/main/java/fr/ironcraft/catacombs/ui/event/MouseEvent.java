package fr.ironcraft.catacombs.ui.event;


public abstract class MouseEvent
{
	private final float mouseX;
	private final float mouseY;

	public MouseEvent(float mouseX, float mouseY)
	{
		this.mouseX = mouseX;
		this.mouseY = mouseY;
	}

	public float getMouseX()
	{
		return mouseX;
	}
	
	public float getMouseY()
	{
		return mouseY;
	}
}
