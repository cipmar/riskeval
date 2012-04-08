package ro.rmc.riskeval.gui;

import java.awt.SystemColor;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import ro.rmc.riskeval.hibernate.HibernateUtils;

public class RiskEvalStart
{
	public static RiskEvalMain riskEvalMain;

	private static void createAndShowGui()
	{
		JFrame.setDefaultLookAndFeelDecorated(false);

		riskEvalMain = new RiskEvalMain();
		riskEvalMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		riskEvalMain.setTitle("Evaluarea Riscurilor");
		riskEvalMain.setSize(800, 600);
		riskEvalMain.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		riskEvalMain.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e)
			{
				HibernateUtils.closeSession();
			}
		});
		
		GuiTool.center(riskEvalMain, null);

		riskEvalMain.setVisible(true);
	}

	public static void main(String[] args) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException
	{
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		UIManager.getDefaults().put("ComboBox.disabledForeground",
				SystemColor.textText);
		
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				createAndShowGui();
			}
		});
	}

}
