package fr.ironcraft.catacombs.render;


public class Color
{
	public static final Color WHITE = new Color(0xffffffff);
	public static final Color RED = new Color(0xffff0000);
	public static final Color GREEN = new Color(0xff00ff00);
	public static final Color BLUE = new Color(0xff0000ff);

	private final byte red;
	private final byte green;
	private final byte blue;
	private final byte alpha;
	
	public Color(byte red, byte green, byte blue, byte alpha)
	{
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.alpha = alpha;
	}
	
	public Color(int rgba)
	{
		this((byte) ((rgba >> 16) & 0xff), (byte) ((rgba >> 8) & 0xff), (byte) ((rgba) & 0xff), (byte) ((rgba >> 24) & 0xff));
	}

	public byte getRed()
	{
		return red;
	}

	public byte getGreen()
	{
		return green;
	}

	public byte getBlue()
	{
		return blue;
	}

	public byte getAlpha()
	{
		return alpha;
	}
	
	public float getFloatRed()
	{
		return (red & 0xff) / 255.0f;
	}
	
	public float getFloatGreen()
	{
		return (green & 0xff) / 255.0f;
	}
	
	public float getFloatBlue()
	{
		return (blue & 0xff) / 255.0f;
	}
	
	public float getFloatAlpha()
	{
		return (alpha & 0xff) / 255.0f;
	}
}
