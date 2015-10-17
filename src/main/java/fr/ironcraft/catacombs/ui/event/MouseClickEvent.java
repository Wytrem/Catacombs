package fr.ironcraft.catacombs.ui.event;

public class MouseClickEvent extends MouseEvent
{
	private final int button;
	
	public MouseClickEvent(float mouseX, float mouseY, int button)
	{
		super(mouseX, mouseY);
		this.button = button;
	}
	
	public int getButton()
	{
		return button;
	}
}
