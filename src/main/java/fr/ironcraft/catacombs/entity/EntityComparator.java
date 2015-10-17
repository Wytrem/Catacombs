package fr.ironcraft.catacombs.entity;

import java.util.Comparator;

public class EntityComparator implements Comparator<Entity>
{
	public int compare(Entity e0, Entity e1)
	{
		if (e0.y < e1.y)
			return -1;
		if (e0.y > e1.y)
			return +1;
		if (e0.x < e1.x)
			return -1;
		if (e0.x > e1.x)
			return +1;
		return 0;
	}
}
