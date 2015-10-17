package fr.ironcraft.catacombs.render.lwjgl;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import fr.ironcraft.catacombs.render.Image;


public class LwjglImage implements Image
{
	private int textureId;

	public LwjglImage(int texture)
	{
		textureId = texture;
	}

	@Override
	public void drawImage(float x, float y, float width, float height, float minU, float minV, float maxU, float maxV)
	{
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, getTextureId());

		GL11.glBegin(GL11.GL_QUADS);
		{
			GL11.glTexCoord2f(minU, minV);
			GL11.glVertex2f(x, y);

			GL11.glTexCoord2f(minU, maxV);
			GL11.glVertex2f(x, y + height);

			GL11.glTexCoord2f(maxU, maxV);
			GL11.glVertex2f(x + width, y + height);

			GL11.glTexCoord2f(maxU, minV);
			GL11.glVertex2f(x + width, y);
		}
		GL11.glEnd();

		GL11.glDisable(GL11.GL_TEXTURE_2D);
	}

	public static LwjglImage load(InputStream stream)
	{
		try
		{
			BufferedImage image = ImageIO.read(stream);
			int textureId = loadTexture(image);
			
			return new LwjglImage(textureId);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}

	private static int loadTexture(BufferedImage image)
	{
		int[] pixels = new int[image.getWidth() * image.getHeight()];
		image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());

		ByteBuffer buffer = BufferUtils.createByteBuffer(pixels.length * 4);

		for (int y = 0; y < image.getHeight(); y++)
		{
			for (int x = 0; x < image.getWidth(); x++)
			{
				int pixel = pixels[y * image.getWidth() + x];
				buffer.put((byte) ((pixel >> 16) & 0xff));
				buffer.put((byte) ((pixel >> 8) & 0xff));
				buffer.put((byte) ((pixel) & 0xff));
				buffer.put((byte) ((pixel >> 24) & 0xff));
			}
		}

		// DO NOT forget
		buffer.flip();

		int textureId = GL11.glGenTextures();

		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureId);
		
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);

		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, image.getWidth(), image.getHeight(), 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);
		
		return textureId;
	}

	public int getTextureId()
	{
		return textureId;
	}
}
