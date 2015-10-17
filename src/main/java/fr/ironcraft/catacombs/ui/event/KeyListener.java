package fr.ironcraft.catacombs.ui.event;


import org.lwjgl.input.Keyboard;


@FunctionalInterface
public interface KeyListener
{
	/**
	 * Called whenever a key is typed (= pressed + released).
	 * 
	 * @param key Key code ({@link Keyboard}
	 */
	void onKeyTyped(KeyboardEvent event);

	/**
	 * Called whenever a key is pressed.
	 * 
	 * @param key Key code ({@link Keyboard}
	 */
	default void onKeyPressed(KeyboardEvent event)
	{

	}

	/**
	 * Called whenever a key is released.
	 * 
	 * @param key Key code ({@link Keyboard}
	 */
	default void onKeyReleased(KeyboardEvent event)
	{

	}
}
