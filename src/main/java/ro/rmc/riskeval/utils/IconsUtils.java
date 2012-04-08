package ro.rmc.riskeval.utils;

import java.util.ResourceBundle;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class IconsUtils
{
	private static IconsUtils instance = null;
	
	private ResourceBundle resourceBundle = null;
	
	private ResourceBundle getResourceBundle()
	{
		if (resourceBundle == null)
			resourceBundle = ResourceBundle.getBundle("icons");
		return resourceBundle;
	}
	
	public String getProperty(String property)
	{
		return getResourceBundle().getString(property);
	}
	
	public Icon getIcon(String name)
	{
		return new ImageIcon(getClass().getResource(getProperty(name)));
	}
	
	public static IconsUtils getInstance()
	{
		if (instance == null)
			instance = new IconsUtils();
		return instance;
	}
	
}
