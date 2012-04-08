package ro.rmc.riskeval.gui;

import ro.rmc.riskeval.utils.IconsUtils;

public class Splasher
{
	public static void main(String[] args)
	{
		SplashWindow.splash(Splasher.class.getResource(IconsUtils.getInstance()
				.getProperty("splash.icon")));
		SplashWindow.invokeMain("ro.rmc.riskeval.gui.RiskEvalStart", args);
		SplashWindow.disposeSlash();
	}
}
