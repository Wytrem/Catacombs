package fr.ironcraft.catacombs.render;

public class IconImpl implements Icon
{
	private final float minU, minV, maxU, maxV;

	public IconImpl(float minU, float minV, float maxU, float maxV)
	{
		super();
		this.minU = minU;
		this.minV = minV;
		this.maxU = maxU;
		this.maxV = maxV;
	}

	@Override
	public float getMinU()
	{
		return minU;
	}

	@Override
	public float getMinV()
	{
		return minV;
	}

	@Override
	public float getMaxU()
	{
		return maxU;
	}

	@Override
	public float getMaxV()
	{
		return maxV;
	}
	
	@Override
	public String toString()
	{
		return "(" + minU + ", " + minV + ", " + maxU + ", " + maxV + ")";
	}
	
	public static IconImpl fromSize(float u, float v, float width, float height)
	{
		return new IconImpl(u, v, u + width, v + height);
	}
}
