package fr.ironcraft.catacombs.render;

public interface Graphics
{
	default void drawRect(float x, float y, float width, float height)
	{
		drawHorizontalLine(x, y, width);
		drawHorizontalLine(x, y + height, width);
		
		drawVerticalLine(x, y, height);
		drawVerticalLine(x + width, y, height);
	}
	
	void cut(float x, float y, float width, float height);
	
	void clearScreen();
	
	void fillRect(float x, float y, float width, float height);
	
	void setLineWidth(float lineWidth);
	
	default void drawIcon(Image image, Icon icon, float x, float y, float width, float height)
	{
		drawImage(image, x, y, width, height, icon.getMinU(), icon.getMinV(), icon.getMaxU(), icon.getMaxV());
	}
	
	default void drawImage(Image image, float x, float y, float width, float height, float minU, float minV, float maxU, float maxV)
	{
		image.drawImage(x, y, width, height, minU, minV, maxU, maxV);
	}
	
	void setColor(Color color);
	
	default void resetColor()
	{
		setColor(Color.WHITE);
	}
	
	void drawLine(float x1, float y1, float x2, float y2);
	
	default void drawHorizontalLine(float x, float y, float width)
	{
		drawLine(x, y, x + width, y);
	}
	
	default void drawVerticalLine(float x, float y, float height)
	{
		drawLine(x, y, x, y + height);
	}
	
	void drawString(String str, float x, float y);
	
	void setFont(Font font);
	
	void rotate(float angle);
	
	void translate(float x, float y);

	void push();
	
	void pop();
}
