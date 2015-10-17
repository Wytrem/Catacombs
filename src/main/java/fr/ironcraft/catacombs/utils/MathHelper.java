package fr.ironcraft.catacombs.utils;

public class MathHelper
{
	public static final float PI = (float) Math.PI;
	public static final float HALF_PI = PI / 2.0f;
	public static final float QUARTER_PI = PI / 4.0f;

	public static final float RAD_TO_DEG = 180.0f / PI;
	public static final float DEG_TO_RAD = PI / 180.0f;
	public static final float ZERO_TOLERANCE = 0.001f;

	public static float toDegrees(float angle)
	{
		return RAD_TO_DEG * angle;
	}
	
	public static boolean isNear(float x, float x0)
	{
		return Math.abs(x - x0) <= ZERO_TOLERANCE;
	}

	public static float sqrt(float x)
	{
		return (float) Math.sqrt(x);
	}

	public static float atan2(float x, float y)
	{
		return (float) Math.atan2(y, x);
	}

	public static float cos(float angle)
	{
		return (float) Math.cos(angle);
	}
	
	public static float sin(float angle)
	{
		return (float) Math.sin(angle);
	}
}
