package ro.rmc.riskeval.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.MessageFormat;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableModelEvent;

import ro.rmc.riskeval.dao.DaoException;
import ro.rmc.riskeval.dao.RiskLevelDao;
import ro.rmc.riskeval.domain.RiskLevel;
import ro.rmc.riskeval.gui.tablemodels.RiskLevelTableModel;
import ro.rmc.riskeval.hibernate.RiskLevelDaoHibernate;
import ro.rmc.riskeval.utils.ErrorUtils;
import ro.rmc.riskeval.utils.IconsUtils;

public class RiskLevelModule extends JPanel {

	private static final long serialVersionUID = 1L;
	private JToolBar jJToolBarBar = null;
	private JButton buttonAdd = null;
	private JButton buttonModify = null;
	private JButton buttonDelete = null;
	private JTable tableRiskLevel = null;
	private JScrollPane jScrollPane = null;
	private JPopupMenu popupMenu = null;  //  @jve:decl-index=0:visual-constraint="24,220"
	private JMenuItem menuItemAdd = null;
	private JMenuItem menuItemModify = null;
	private JMenuItem menuItemDelete = null;
	
	private RiskLevelTableModel tableModel = null;
	private List<RiskLevel> riskLevelList = null;
	private RiskLevelDao riskLevelDao = null;  //  @jve:decl-index=0:
	
	private Action addAction;  
	private Action modifyAction;
	private Action deleteAction;

	/**
	 * This is the default constructor
	 */
	public RiskLevelModule() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.setLayout(new BorderLayout());
		this.add(getJJToolBarBar(), BorderLayout.NORTH);
		this.add(getJScrollPane(), BorderLayout.CENTER);
	}

	/**
	 * This method initializes jJToolBarBar	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJJToolBarBar()
	{
		if (jJToolBarBar == null)
		{
			jJToolBarBar = new JToolBar();
			jJToolBarBar.setPreferredSize(new Dimension(18, 32));
			jJToolBarBar.setFloatable(false);
			jJToolBarBar.add(getButtonAdd());
			jJToolBarBar.add(getButtonModify());
			jJToolBarBar.add(getButtonDelete());
		}
		return jJToolBarBar;
	}

	/**
	 * This method initializes buttonAdd	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getButtonAdd()
	{
		if (buttonAdd == null)
		{
			buttonAdd = new JButton();
			buttonAdd.setAction(getAddAction());
			buttonAdd.setFocusPainted(false);
			buttonAdd.setText("");
		}
		return buttonAdd;
	}

	/**
	 * This method initializes buttonModify	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getButtonModify()
	{
		if (buttonModify == null)
		{
			buttonModify = new JButton();
			buttonModify.setAction(getModifyAction());
			buttonModify.setFocusPainted(false);
			buttonModify.setText("");
		}
		return buttonModify;
	}

	/**
	 * This method initializes buttonDelete	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getButtonDelete()
	{
		if (buttonDelete == null)
		{
			buttonDelete = new JButton();
			buttonDelete.setAction(getDeleteAction());
			buttonDelete.setFocusPainted(false);
			buttonDelete.setText("");
		}
		return buttonDelete;
	}

	/**
	 * This method initializes tableRiskLevel	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getTableRiskLevel()
	{
		if (tableRiskLevel == null)
		{
			tableRiskLevel = new JTable();
			
			tableRiskLevel.setModel(getTableModel());
			tableRiskLevel.setGridColor(Color.lightGray);
			tableRiskLevel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tableRiskLevel.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			tableRiskLevel.setRowHeight(20);
			tableRiskLevel.getColumnModel().getColumn(0).setCellRenderer(new RiskLevelCellRenderer());
			tableRiskLevel.getColumnModel().getColumn(0).setPreferredWidth(80);
			tableRiskLevel.getColumnModel().getColumn(1).setPreferredWidth(120);
			tableRiskLevel.getColumnModel().getColumn(2).setPreferredWidth(400);

			// mouse events
			tableRiskLevel.addMouseListener(new MouseAdapter()
			{
				public void mouseReleased(MouseEvent e)
				{
					if (e.isPopupTrigger())
					{
						getPopupMenu().show(tableRiskLevel, e.getX(), e.getY());
					}
				}
				
				public void mouseClicked(MouseEvent e)
				{
					if (e.getClickCount() == 2)
					{
						getModifyAction().actionPerformed(null);
					}
				}
			});
			
			// keyboad events
			tableRiskLevel.addKeyListener(new KeyAdapter()
			{
				public void keyPressed(KeyEvent e)
				{
					switch (e.getKeyCode())
					{
					case KeyEvent.VK_INSERT:
						getAddAction().actionPerformed(null);
						break;
					case KeyEvent.VK_ENTER:
						getModifyAction().actionPerformed(null);
						break;
					case KeyEvent.VK_DELETE:
						getDeleteAction().actionPerformed(null);
						break;
					}
				}
			});
		}
		
		return tableRiskLevel;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane()
	{
		if (jScrollPane == null)
		{
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getTableRiskLevel());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes popupMenu	
	 * 	
	 * @return javax.swing.JPopupMenu	
	 */
	private JPopupMenu getPopupMenu()
	{
		if (popupMenu == null)
		{
			popupMenu = new JPopupMenu();
			popupMenu.add(getMenuItemAdd());
			popupMenu.add(getMenuItemModify());
			popupMenu.add(getMenuItemDelete());
		}
		return popupMenu;
	}

	/**
	 * This method initializes menuItemAdd	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMenuItemAdd()
	{
		if (menuItemAdd == null)
		{
			menuItemAdd = new JMenuItem();
			menuItemAdd.setAction(getAddAction());
		}
		return menuItemAdd;
	}

	/**
	 * This method initializes menuItemModify	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMenuItemModify()
	{
		if (menuItemModify == null)
		{
			menuItemModify = new JMenuItem();
			menuItemModify.setAction(getModifyAction());
		}
		return menuItemModify;
	}

	/**
	 * This method initializes menuItemDelete	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMenuItemDelete()
	{
		if (menuItemDelete == null)
		{
			menuItemDelete = new JMenuItem();
			menuItemDelete.setAction(getDeleteAction());
		}
		return menuItemDelete;
	}

	public RiskLevelTableModel getTableModel()
	{
		if (tableModel == null)
			tableModel = new RiskLevelTableModel(getRiskLevelList());
		return tableModel;
	}

	private List<RiskLevel> getRiskLevelList()
	{
		if (riskLevelList == null)
			try
			{
				riskLevelList = getRiskLevelDao().findAll();
			} catch (DaoException e)
			{
				ErrorUtils.showError(e, RiskLevelModule.this);
			}
		return riskLevelList;
	}

	private RiskLevelDao getRiskLevelDao()
	{
		if (riskLevelDao == null)
			riskLevelDao = new RiskLevelDaoHibernate();
		return riskLevelDao;
	}

	public Action getAddAction()
	{
		if (addAction == null)
		{
			Icon imageIcon = IconsUtils.getInstance().getIcon("button.add");
			
			addAction = new AbstractAction("Adaugare nivel risc", imageIcon)
			{

				public void actionPerformed(ActionEvent arg0)
				{
					try
					{
						RiskLevelDialog dialog = new RiskLevelDialog(null);
						dialog.setVisible(true);
						
						if (dialog.isOk())
						{
							RiskLevel riskLevel = new RiskLevel();
							riskLevel.setCode(dialog.getRiskLevelCode());
							riskLevel.setName(dialog.getRiskLevelName());
							
							getRiskLevelDao().store(riskLevel);
							
							int row = getTableRiskLevel().getModel().getRowCount() - 1;
							
							RiskLevelTableModel tableModel = (RiskLevelTableModel) getTableRiskLevel().getModel();
							tableModel.getRiskLevelList().add(riskLevel);
							
							getTableRiskLevel().tableChanged(
									new TableModelEvent(tableModel, row + 1, row + 1,
											TableModelEvent.ALL_COLUMNS,
											TableModelEvent.INSERT));
							
							getTableRiskLevel().getSelectionModel()
									.setSelectionInterval(row + 1, row + 1);
						}
					}
					catch (Exception e)
					{
						ErrorUtils.showError(e, RiskLevelModule.this);
					}
				}
			};
			addAction.putValue(Action.SHORT_DESCRIPTION, "Adaugare nivel risc");
		}
		
		return addAction;
	}

	public Action getDeleteAction()
	{
		if (deleteAction == null)
		{
			Icon imageIcon = IconsUtils.getInstance().getIcon("button.delete");
			
			deleteAction = new AbstractAction("Stergere nivel risc", imageIcon)
			{

				public void actionPerformed(ActionEvent arg0)
				{
					try
					{
						int index = getTableRiskLevel().getSelectedRow();
						
						if (index >= 0)
						{
							RiskLevel riskLevel = getTableModel().getRiskLevelList().get(index);
							String msg = MessageFormat
									.format(
											"Sunteti sigur ca doriti sa stergeti nivelul de risc \"{0}\" ?",
											riskLevel.getName());
							
							if (JOptionPane.showConfirmDialog(RiskLevelModule.this, msg) == JOptionPane.YES_OPTION)
							{
								getRiskLevelDao().delete(riskLevel);
								getTableModel().getRiskLevelList().remove(riskLevel);
								
								getTableRiskLevel().tableChanged(
										new TableModelEvent(getTableModel(), index,
												index, TableModelEvent.ALL_COLUMNS,
												TableModelEvent.DELETE));
							}
						} else
						{
							JOptionPane.showMessageDialog(RiskLevelModule.this, "Selectati un nivel de risc.");
							return;
						}
					}
					catch (Exception e)
					{
						ErrorUtils.showError(e, RiskLevelModule.this);
					}
				}
			};
			deleteAction.putValue(Action.SHORT_DESCRIPTION, "Stergere nivel risc");
		}
		
		return deleteAction;
	}

	public Action getModifyAction()
	{
		if (modifyAction == null)
		{
			Icon imageIcon = IconsUtils.getInstance().getIcon("button.edit");
			
			modifyAction = new AbstractAction("Modificare nivel risc", imageIcon)
			{

				public void actionPerformed(ActionEvent arg0)
				{
					try
					{
						int index = getTableRiskLevel().getSelectedRow();
	
						if (index >= 0)
						{
							RiskLevel riskLevel = getTableModel().getRiskLevelList().get(index);
							RiskLevelDialog dialog = new RiskLevelDialog(null);
							dialog.setRiskLevel(riskLevel);
							dialog.setVisible(true);
							
							if (dialog.isOk())
							{
								riskLevel.setCode(dialog.getRiskLevelCode());
								riskLevel.setName(dialog.getRiskLevelName());
								
								getRiskLevelDao().update(riskLevel);
								
								getTableRiskLevel().tableChanged(
										new TableModelEvent(getTableModel(), index,
												index, TableModelEvent.ALL_COLUMNS,
												TableModelEvent.UPDATE));
							}
							
						} else
						{
							JOptionPane.showMessageDialog(RiskLevelModule.this, "Selectati un nivel de risc.");
							return;
						}
					}
					catch (Exception e)
					{
						ErrorUtils.showError(e, RiskLevelModule.this);
					}
				}
			};
			modifyAction.putValue(Action.SHORT_DESCRIPTION, "Modificare nivel risc");
		}
		
		return modifyAction;
	}

}
