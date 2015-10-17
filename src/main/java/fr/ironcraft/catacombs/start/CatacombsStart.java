package fr.ironcraft.catacombs.start;


import java.io.File;
import java.io.IOException;

import org.lwjgl.LWJGLUtil;

import fr.ironcraft.catacombs.CatacombsGame;
import fr.ironcraft.catacombs.utils.LWJGLSetup;


public class CatacombsStart
{
	/**
	 * Starts the game.
	 */
	public static void main(String[] args)
	{
		try
		{
			LWJGLSetup.load(getBaseWorkingDirectory());

			CatacombsGame.instance = new CatacombsGame();
			CatacombsGame.instance.start();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static File getBaseWorkingDirectory()
	{
		String identifier = "catacombs";
		final String userHome = System.getProperty("user.home", ".");
		File workingDirectory;
		switch (LWJGLUtil.getPlatform())
		{
			case LWJGLUtil.PLATFORM_LINUX:
				workingDirectory = new File(userHome, "." + identifier + "/");
				break;
			case LWJGLUtil.PLATFORM_WINDOWS:
				final String applicationData = System.getenv("APPDATA");
				final String folder = applicationData != null ? applicationData : userHome;

				workingDirectory = new File(folder, "." + identifier + "/");
				break;
			case LWJGLUtil.PLATFORM_MACOSX:
				workingDirectory = new File(userHome, "Library/Application Support/" + identifier);
				break;
			default:
				workingDirectory = new File(userHome, identifier + "/");
		}

		return workingDirectory;
	}
}
