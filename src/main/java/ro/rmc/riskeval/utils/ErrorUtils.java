package ro.rmc.riskeval.utils;

import java.awt.Component;

import javax.swing.JOptionPane;

public class ErrorUtils
{
	public static void showError(Exception e, Component c)
	{
		JOptionPane.showMessageDialog(c, e, "Error",
				JOptionPane.ERROR_MESSAGE);
	}
}
