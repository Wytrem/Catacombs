package fr.ironcraft.catacombs.render.lwjgl;

import org.lwjgl.opengl.GL11;

import fr.ironcraft.catacombs.render.Color;
import fr.ironcraft.catacombs.render.Font;
import fr.ironcraft.catacombs.render.Graphics;
import fr.ironcraft.catacombs.utils.MathHelper;

public class LwjglDirectGraphics implements Graphics
{
	private Font font;

	@Override
	public void fillRect(float x, float y, float width, float height)
	{
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2f(x, y);
		GL11.glVertex2f(x, y + height);
		GL11.glVertex2f(x + width, y + height);
		GL11.glVertex2f(x + width, y);
		GL11.glEnd();
	}

	@Override
	public void setLineWidth(float lineWidth)
	{
		GL11.glLineWidth(lineWidth);
	}

	@Override
	public void setColor(Color color)
	{
		GL11.glColor4f(color.getFloatRed(), color.getFloatGreen(), color.getFloatBlue(), color.getFloatAlpha());
	}

	@Override
	public void drawLine(float x1, float y1, float x2, float y2)
	{
		GL11.glBegin(GL11.GL_LINES);
		GL11.glVertex2f(x1, y1);
		GL11.glVertex2f(x2, y2);
		GL11.glEnd();
	}

	@Override
	public void cut(float x, float y, float width, float height)
	{
		
	}

	@Override
	public void clearScreen()
	{
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
	}

	@Override
	public void drawString(String str, float x, float y)
	{
		
	}

	@Override
	public void setFont(Font font)
	{
		this.font = font;
	}

	@Override
	public void rotate(float angle)
	{
		GL11.glRotatef(MathHelper.toDegrees(angle), 0, 0, -1.0f);
	}

	@Override
	public void translate(float x, float y)
	{
		GL11.glTranslatef(x, y, 0.0f);
	}

	@Override
	public void push()
	{
		GL11.glPushMatrix();
	}

	@Override
	public void pop()
	{
		GL11.glPopMatrix();
	}
}
