package fr.ironcraft.catacombs.utils;

public enum PreciseFacing
{
	EAST,
	NORTH_EAST,
	NORTH,
	NORTH_WEST,
	WEST,
	SOUTH_WEST,
	SOUTH,
	SOUTH_EAST;
	
	public float getAngle()
	{
		return ordinal() * MathHelper.QUARTER_PI;
	}
	
	public static PreciseFacing fromAngle(float angle)
	{
		PreciseFacing[] values = values();
		
		for (int i = 0; i < values.length; i++)
		{
			if (MathHelper.isNear(angle, values[i].getAngle()))
			{
				return values[i];
			}
		}
		
		return EAST;
	}
}
