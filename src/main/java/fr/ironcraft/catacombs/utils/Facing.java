package fr.ironcraft.catacombs.utils;

import java.util.Random;

public enum Facing
{
	NORTH,
	SOUTH,
	EAST,
	WEST;
	
	public float getAngle()
	{
		return ordinal() * MathHelper.HALF_PI;
	}

	public static Facing random(Random random)
	{
		return values()[random.nextInt(4)];
	}
}
