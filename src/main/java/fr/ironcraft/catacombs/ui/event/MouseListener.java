package fr.ironcraft.catacombs.ui.event;

@FunctionalInterface
public interface MouseListener
{
	default void mouseMoved(MouseEvent event)
	{
		
	}
	
	default void mousePressed(MouseClickEvent event)
	{
		
	}
	
	default void mouseReleased(MouseClickEvent event)
	{
		
	}
	
	void mouseClicked(MouseClickEvent event);
}
