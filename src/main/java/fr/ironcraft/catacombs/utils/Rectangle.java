package fr.ironcraft.catacombs.utils;

import java.util.ArrayList;

import fr.ironcraft.catacombs.entity.BoundingBoxOwner;
import fr.ironcraft.catacombs.entity.Entity;

public class Rectangle
{
	public float x, y, width, height;
	public BoundingBoxOwner owner = null;
	
	public Rectangle()
	{
		// Serialize
	}
	
	public Rectangle(float x, float y, float width, float height)
	{
		this(x, y, width, height, null);
	}

	public Rectangle(float x, float y, float width, float height, BoundingBoxOwner owner)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.owner = owner;
	}
	
	public boolean intersects(Rectangle other)
	{
		if (width == 0 || height == 0 || other.width == 0 || other.height == 0)
		{
			return false;
		}
		
		return other.x + other.width > x && other.y + other.height > y && other.x < x + width && other.y < y + height;
	}
	
	@Override
	public String toString()
	{
		return "(" + x + ", " + y + "," + width + "," + height + ")";
	}
	
	public Rectangle bbCopy()
	{
		return new Rectangle(x, y, width, height, owner);
	}

	public Rectangle grow(int radius)
	{
		x -= radius;
		y -= radius;
		width += radius * 2;
		height += radius * 2;
		
		return this;
	}
}
