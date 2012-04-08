package ro.rmc.riskeval.gui;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Window;

public class GuiTool
{
	/**
	 * Centreaza fereastra window centrata pe fereastra toWindow Daca toWindow
	 * este null, se ia ecranul.
	 * 
	 * @param window
	 * @param toWindow
	 */
	public static void center(Window window, Window toWindow)
	{
		Dimension dim;
		Point loc;

		if (toWindow != null)
		{
			loc = toWindow.getLocation();
			dim = toWindow.getSize();
		} else
		{
			loc = new Point(0, 0);
			dim = Toolkit.getDefaultToolkit().getScreenSize();
		}

		int newX = (int) (loc.getX() + (dim.getWidth() - window.getSize().width) / 2);
		int newY = (int) (loc.getY() + (dim.getHeight() - window.getSize().height) / 2);

		window.setLocation(newX, newY);
	}
}
