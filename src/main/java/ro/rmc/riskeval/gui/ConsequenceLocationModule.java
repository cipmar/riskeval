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

import ro.rmc.riskeval.dao.ConsequenceLocationDao;
import ro.rmc.riskeval.dao.DaoException;
import ro.rmc.riskeval.domain.ConsequenceLocation;
import ro.rmc.riskeval.gui.tablemodels.ConsequenceLocCellRenderer;
import ro.rmc.riskeval.gui.tablemodels.ConsequenceLocationTableModel;
import ro.rmc.riskeval.hibernate.ConsequenceLocationDaoHibernate;
import ro.rmc.riskeval.utils.ErrorUtils;
import ro.rmc.riskeval.utils.IconsUtils;

public class ConsequenceLocationModule extends JPanel
{

	private static final long serialVersionUID = 1L;
	private JScrollPane jScrollPane = null;
	private JTable tableConsequenceLocation = null;
	private JToolBar toolBar = null;
	private JButton buttonAdd = null;
	private JButton buttonModify = null;
	private JButton buttonDelete = null;
	private JPopupMenu jPopupMenu = null;  //  @jve:decl-index=0:visual-constraint="534,60"
	private JMenuItem menuItemAdd = null;
	private JMenuItem menuItemModify = null;
	private JMenuItem menuItemDelete = null;
	
	private Action addAction;  
	private Action modifyAction;
	private Action deleteAction;
	
	ConsequenceLocationTableModel tableModel = null;  //  @jve:decl-index=0:visual-constraint="540,19"
	List<ConsequenceLocation> consequenceLocationList = null;
	ConsequenceLocationDao consequenceLocationDao = null;


	/**
	 * @return the tableModel
	 */
	public ConsequenceLocationTableModel getTableModel()
	{
		if (tableModel == null)
			tableModel = new ConsequenceLocationTableModel(
					getConsequenceLocationList());
		return tableModel;
	}

	/**
	 * This is the default constructor
	 */
	public ConsequenceLocationModule()
	{
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize()
	{
		this.setSize(386, 234);
		this.setLayout(new BorderLayout());
		this.add(getJScrollPane(), BorderLayout.CENTER);
		this.add(getToolBar(), BorderLayout.NORTH);
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
			jScrollPane.setViewportView(getTableConsequenceLocation());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes tableConsequenceLocation	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getTableConsequenceLocation()
	{
		if (tableConsequenceLocation == null)
		{
			tableConsequenceLocation = new JTable();
			tableConsequenceLocation.setModel(getTableModel());
			tableConsequenceLocation.setGridColor(Color.lightGray);
			tableConsequenceLocation.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tableConsequenceLocation.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			tableConsequenceLocation.setRowHeight(20);
			tableConsequenceLocation.getColumnModel().getColumn(0)
					.setCellRenderer(new ConsequenceLocCellRenderer());
			tableConsequenceLocation.getColumnModel().getColumn(0).setPreferredWidth(50);
			tableConsequenceLocation.getColumnModel().getColumn(1).setPreferredWidth(400);
			
			// mouse events
			tableConsequenceLocation.addMouseListener(new MouseAdapter()
			{
				public void mouseReleased(MouseEvent e)
				{
					if (e.isPopupTrigger())
					{
						getJPopupMenu().show(tableConsequenceLocation, e.getX(), e.getY());
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
			tableConsequenceLocation.addKeyListener(new KeyAdapter()
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
		return tableConsequenceLocation;
	}

	/**
	 * This method initializes toolBar	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getToolBar()
	{
		if (toolBar == null)
		{
			toolBar = new JToolBar();
			toolBar.setPreferredSize(new Dimension(18, 32));
			toolBar.setFloatable(false);
			toolBar.add(getButtonAdd());
			toolBar.add(getButtonModify());
			toolBar.add(getButtonDelete());
		}
		return toolBar;
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
			buttonAdd.setFocusPainted(false);
			buttonAdd.setAction(getAddAction());
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
			buttonModify.setFocusPainted(false);
			buttonModify.setAction(getModifyAction());
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
			buttonDelete.setFocusPainted(false);
			buttonDelete.setAction(getDeleteAction());
			buttonDelete.setText("");
		}
		return buttonDelete;
	}

	/**
	 * This method initializes jPopupMenu	
	 * 	
	 * @return javax.swing.JPopupMenu	
	 */
	private JPopupMenu getJPopupMenu()
	{
		if (jPopupMenu == null)
		{
			jPopupMenu = new JPopupMenu();
			jPopupMenu.add(getMenuItemAdd());
			jPopupMenu.add(getMenuItemModify());
			jPopupMenu.add(getMenuItemDelete());
		}
		return jPopupMenu;
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

	/**
	 * @return the addAction
	 */
	public Action getAddAction()
	{
		if (addAction == null)
		{
			Icon imageIcon = IconsUtils.getInstance().getIcon("button.add");
			
			addAction = new AbstractAction("Adaugare localizare consecinta", imageIcon)
			{
				public void actionPerformed(ActionEvent arg0)
				{
					try
					{
						ConsequenceLocationDialog dialog = new ConsequenceLocationDialog(null);
						dialog.setVisible(true);
						
						if (dialog.isOk())
						{
							ConsequenceLocation consequenceLocation = new ConsequenceLocation();
							consequenceLocation.setCode(dialog.getConsequenceLocationCode());
							consequenceLocation.setName(dialog.getConsequenceLocationName());
							
							getConsequenceLocationDao().store(consequenceLocation);
							
							int row = getTableConsequenceLocation().getModel().getRowCount() - 1;
							
							getTableModel().getConsequenceLocationList().add(consequenceLocation);
							
							getTableConsequenceLocation().tableChanged(
									new TableModelEvent(tableModel, row + 1, row + 1,
											TableModelEvent.ALL_COLUMNS,
											TableModelEvent.INSERT));
							
							getTableConsequenceLocation().getSelectionModel()
									.setSelectionInterval(row + 1, row + 1);
						}
					}
					catch (Exception e)
					{
						ErrorUtils.showError(e, ConsequenceLocationModule.this);
					}
				}
			};
			addAction.putValue(Action.SHORT_DESCRIPTION,
					"Adaugare localizare consecinta");
		}
		
		return addAction;
	}

	/**
	 * @return the deleteAction
	 */
	public Action getDeleteAction()
	{
		if (deleteAction == null)
		{
			Icon imageIcon = IconsUtils.getInstance().getIcon("button.delete");
			
			deleteAction = new AbstractAction("Stergere localizare consecinta", imageIcon)
			{

				public void actionPerformed(ActionEvent arg0)
				{
					try
					{
						int index = getTableConsequenceLocation().getSelectedRow();
						
						if (index >= 0)
						{
							ConsequenceLocation consequenceLocation = getTableModel().getConsequenceLocationList().get(index);
							String msg = MessageFormat
									.format(
											"Sunteti sigur ca doriti sa stergeti localizarea consecintei \"{0}\" ?",
											consequenceLocation.getName());
							
							if (JOptionPane.showConfirmDialog(ConsequenceLocationModule.this, msg) == JOptionPane.YES_OPTION)
							{
								getConsequenceLocationDao().delete(consequenceLocation);
								getTableModel().getConsequenceLocationList().remove(consequenceLocation);
								
								getTableConsequenceLocation().tableChanged(
										new TableModelEvent(getTableModel(), index,
												index, TableModelEvent.ALL_COLUMNS,
												TableModelEvent.DELETE));
							}
						} else
						{
							JOptionPane.showMessageDialog(ConsequenceLocationModule.this, "Selectati o localizare a gravitatii.");
							return;
						}
					}
					catch (Exception e)
					{
						ErrorUtils.showError(e, ConsequenceLocationModule.this);
					}
				}
			};
			deleteAction.putValue(Action.SHORT_DESCRIPTION,
					"Stergere localizare consecinta");
		}
		
		return deleteAction;
	}

	/**
	 * @return the modifyAction
	 */
	public Action getModifyAction()
	{
		if (modifyAction == null)
		{
			Icon imageIcon = IconsUtils.getInstance().getIcon("button.edit");
			
			modifyAction = new AbstractAction("Modificare localizare consecinta", imageIcon)
			{

				public void actionPerformed(ActionEvent arg0)
				{
					try
					{
						int index = getTableConsequenceLocation().getSelectedRow();
	
						if (index >= 0)
						{
							ConsequenceLocation consequenceLocation = getTableModel().getConsequenceLocationList().get(index);
							ConsequenceLocationDialog dialog = new ConsequenceLocationDialog(null);
							dialog.setConsequenceLocation(consequenceLocation);
							dialog.setVisible(true);
							
							if (dialog.isOk())
							{
								consequenceLocation.setCode(dialog.getConsequenceLocationCode());
								consequenceLocation.setName(dialog.getConsequenceLocationName());
								
								getConsequenceLocationDao().update(consequenceLocation);
								
								getTableConsequenceLocation().tableChanged(
										new TableModelEvent(getTableModel(), index,
												index, TableModelEvent.ALL_COLUMNS,
												TableModelEvent.UPDATE));
							}
							
						} else
						{
							JOptionPane.showMessageDialog(ConsequenceLocationModule.this, "Selectati o localizare a gravitatii.");
							return;
						}
					}
					catch (Exception e)
					{
						ErrorUtils.showError(e, ConsequenceLocationModule.this);
					}
				}
			};
			modifyAction.putValue(Action.SHORT_DESCRIPTION,
					"Modificare localizare consecinta");
		}
		
		return modifyAction;
	}

	/**
	 * @return the consequenceLocationList
	 */
	public List<ConsequenceLocation> getConsequenceLocationList()
	{
		if (consequenceLocationList == null)
			try
			{
				consequenceLocationList = getConsequenceLocationDao().findAll();
			} catch (DaoException e)
			{
				ErrorUtils.showError(e, ConsequenceLocationModule.this);
			}
		return consequenceLocationList;
	}

	/**
	 * @return the consequenceLocationDao
	 */
	public ConsequenceLocationDao getConsequenceLocationDao()
	{
		if (consequenceLocationDao == null)
			consequenceLocationDao = new ConsequenceLocationDaoHibernate();
		return consequenceLocationDao;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
