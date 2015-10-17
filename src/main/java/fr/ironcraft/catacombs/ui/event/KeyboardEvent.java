package fr.ironcraft.catacombs.ui.event;

public class KeyboardEvent
{
	private final int keyCode;

	public KeyboardEvent(int keyCode)
	{
		this.keyCode = keyCode;
	}
	
	public int getKeyCode()
	{
		return keyCode;
	}
}
