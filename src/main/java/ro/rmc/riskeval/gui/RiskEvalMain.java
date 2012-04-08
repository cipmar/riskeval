package ro.rmc.riskeval.gui;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ro.rmc.riskeval.gui.tablemodels.ModuleListCellRenderer;
import ro.rmc.riskeval.utils.IconsUtils;

import javax.swing.border.SoftBevelBorder;
import javax.swing.JMenuItem;

public class RiskEvalMain extends JFrame
{

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private JList listModules = null;
	private JPanel currentModule = null;  //  @jve:decl-index=0:visual-constraint="469,10"
	private int currentModuleIndex = -1;

	private JMenuBar jJMenuBar = null;

	private JMenu menuAbout = null;

	private JMenuItem menuItemAbout = null;

	/**
	 * This is the default constructor
	 */
	public RiskEvalMain()
	{
		super();
		initialize();
		showModule(0);
		getListModules().setSelectedIndex(0);	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize()
	{
		this.setSize(442, 252);
		this.setJMenuBar(getJJMenuBar());
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(IconsUtils.getInstance().getProperty("main.icon"))));
		this.setContentPane(getJContentPane());
		this.setTitle("JFrame");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane()
	{
		if (jContentPane == null)
		{
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.setFont(new Font("Dialog", Font.PLAIN, 12));
			jContentPane.add(getListModules(), BorderLayout.WEST);
		}
		return jContentPane;
	}

	/**
	 * This method initializes listModules	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getListModules()
	{
		if (listModules == null)
		{
			listModules = new JList();
			listModules.setPreferredSize(new Dimension(60, 0));
			listModules.setFont(new Font("Dialog", Font.BOLD, 12));
			listModules.setCursor(new Cursor(Cursor.HAND_CURSOR));
			listModules.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
			listModules.setPreferredSize(new Dimension(100, 0));
			listModules.setListData(GuiUtils.getModules().toArray());
			listModules.getSelectionModel().setSelectionMode(
					ListSelectionModel.SINGLE_SELECTION);
			listModules.setCellRenderer(new ModuleListCellRenderer());
			
			listModules.getSelectionModel().addListSelectionListener(
					new ListSelectionListener()
					{
						public void valueChanged(ListSelectionEvent e)
						{
							int module = listModules.getSelectedIndex();
							showModule(module);
						}
					});
		}
		return listModules;
	}
	
	private void showModule(int moduleIndex)
	{
		if (moduleIndex == currentModuleIndex)
			return;
		
		if (currentModule != null)
			getContentPane().remove(currentModule);
		
		Class classDef;
		
		try
		{
			classDef = Class.forName(GuiUtils.getModules().get(moduleIndex).getViewClassName());
			if (classDef != null)
			{
				currentModule =(JPanel) classDef.newInstance();
				currentModuleIndex = moduleIndex;
			}
		} catch (Exception e1)
		{
			currentModule = null;
			currentModuleIndex = -1;
			e1.printStackTrace();
		}
		
		if (currentModule != null)
		{
			getContentPane().add(currentModule, BorderLayout.CENTER);
		}
		
		getContentPane().validate();
		repaint();

	}

	/**
	 * This method initializes jJMenuBar	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	private JMenuBar getJJMenuBar()
	{
		if (jJMenuBar == null)
		{
			jJMenuBar = new JMenuBar();
			jJMenuBar.add(getMenuAbout());
		}
		return jJMenuBar;
	}

	/**
	 * This method initializes menuAbout	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getMenuAbout()
	{
		if (menuAbout == null)
		{
			menuAbout = new JMenu();
			menuAbout.setText("RiskEval");
			menuAbout.add(getMenuItemAbout());
		}
		return menuAbout;
	}

	/**
	 * This method initializes menuItemAbout	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMenuItemAbout()
	{
		if (menuItemAbout == null)
		{
			menuItemAbout = new JMenuItem();
			menuItemAbout.setText("About");
			menuItemAbout.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					AboutDialog aboutDialog = new AboutDialog(null);
					aboutDialog.setVisible(true);
				}});
		}
		return menuItemAbout;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
