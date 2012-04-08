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
import javax.swing.BorderFactory;
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
import ro.rmc.riskeval.dao.GravityDao;
import ro.rmc.riskeval.domain.Gravity;
import ro.rmc.riskeval.gui.tablemodels.GravityCellRenderer;
import ro.rmc.riskeval.gui.tablemodels.GravityTableModel;
import ro.rmc.riskeval.hibernate.GravityDaoHibernate;
import ro.rmc.riskeval.utils.ErrorUtils;
import ro.rmc.riskeval.utils.IconsUtils;

public class GravityModule extends JPanel
{
	private GravityDao gravityDao = null;  //  @jve:decl-index=0:
	
	private JToolBar toolbar = null;
	private JTable tableGravity = null;
	private JButton buttonAdd = null;
	private JButton buttonModify = null;
	private JButton buttonDelete = null;
	private JScrollPane scrollPane = null;

	private JPopupMenu jPopupMenu = null;  

	private JMenuItem menuItemAdd = null;

	private JMenuItem menuItemModify = null;

	private JMenuItem menuItemDelete = null;
	
	private Action addAction;  
	private Action modifyAction;
	private Action deleteAction;
	
	private List<Gravity> gravityList = null;  //  @jve:decl-index=0:
	
	private GravityTableModel tableModel = null;

	/**
	 * @return the tableModel
	 */
	public GravityTableModel getTableModel()
	{
		if (tableModel == null)
			tableModel = new GravityTableModel(getGravityList());
		return tableModel;
	}

	/**
	 * This method initializes 
	 * 
	 */
	public GravityModule() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
        this.setLayout(new BorderLayout());
        this.setSize(new Dimension(346, 198));
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.add(getScrollPane(), BorderLayout.CENTER);
        this.add(getToolbar(), BorderLayout.NORTH);
			
	}

	/**
	 * This method initializes toolbar	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getToolbar()
	{
		if (toolbar == null)
		{
			toolbar = new JToolBar();
			toolbar.setPreferredSize(new Dimension(0, 32));
			toolbar.setFloatable(false);
			toolbar.setBorder(null);
			toolbar.add(getButtonAdd());
			toolbar.add(getButtonModify());
			toolbar.add(getButtonDelete());
		}
		return toolbar;
	}

	/**
	 * This method initializes tableGravity	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getTableGravity()
	{
		if (tableGravity == null)
		{
			tableGravity = new JTable();
			tableGravity.setModel(getTableModel());
			tableGravity.setGridColor(Color.lightGray);
			tableGravity.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tableGravity.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			tableGravity.setRowHeight(20);
			tableGravity.getColumnModel().getColumn(0).setCellRenderer(
					new GravityCellRenderer());
			tableGravity.getColumnModel().getColumn(0).setPreferredWidth(150);
			tableGravity.getColumnModel().getColumn(1).setPreferredWidth(400);
			tableGravity.getColumnModel().getColumn(2).setPreferredWidth(50);

			// mouse events
			tableGravity.addMouseListener(new MouseAdapter()
			{
				public void mouseReleased(MouseEvent e)
				{
					if (e.isPopupTrigger())
					{
						getJPopupMenu().show(tableGravity, e.getX(), e.getY());
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
			tableGravity.addKeyListener(new KeyAdapter()
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
		return tableGravity;
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
			buttonAdd.setSize(new Dimension(20, 20));
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
	 * @return the gravityDao
	 */
	public GravityDao getGravityDao()
	{
		if (gravityDao == null)
			gravityDao = new GravityDaoHibernate();
		return gravityDao;
	}

	/**
	 * This method initializes scrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getScrollPane()
	{
		if (scrollPane == null)
		{
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTableGravity());
		}
		return scrollPane;
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
			
			addAction = new AbstractAction("Adaugare clasa gravitate", imageIcon)
			{

				public void actionPerformed(ActionEvent arg0)
				{
					try
					{
						GravityDialog dialog = new GravityDialog(null);
						dialog.setVisible(true);
						
						if (dialog.isOk())
						{
							Gravity gravity = new Gravity();
							gravity.setCode(dialog.getGravityCode());
							gravity.setName(dialog.getGravityName());
							gravity.setDescription(dialog.getGravityDescription());
							
							getGravityDao().store(gravity);
							
							int row = getTableGravity().getModel().getRowCount() - 1;
							
							GravityTableModel tableModel = (GravityTableModel) getTableGravity().getModel();
							tableModel.getGravityList().add(gravity);
							
							getTableGravity().tableChanged(
									new TableModelEvent(tableModel, row + 1, row + 1,
											TableModelEvent.ALL_COLUMNS,
											TableModelEvent.INSERT));
							
							getTableGravity().getSelectionModel()
									.setSelectionInterval(row + 1, row + 1);
						}
					}
					catch (Exception e)
					{
						ErrorUtils.showError(e, GravityModule.this);
					}
				}
			};
			addAction.putValue(Action.SHORT_DESCRIPTION, "Adaugare clasa gravitate");
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
			
			deleteAction = new AbstractAction("Stergere clasa gravitate", imageIcon)
			{
				public void actionPerformed(ActionEvent arg0)
				{
					try
					{
						int index = getTableGravity().getSelectedRow();
						
						if (index >= 0)
						{
							Gravity gravity = (Gravity) getTableModel().getGravityList().get(index);
							String msg = MessageFormat
									.format(
											"Sunteti sigur ca doriti sa stergeti clasa de gravitate \"{0}\" ?",
											gravity.getName());
							
							if (JOptionPane.showConfirmDialog(GravityModule.this, msg) == JOptionPane.YES_OPTION)
							{
								getGravityDao().delete(gravity);
								getTableModel().getGravityList().remove(gravity);
								
								getTableGravity().tableChanged(
										new TableModelEvent(getTableModel(), index,
												index, TableModelEvent.ALL_COLUMNS,
												TableModelEvent.DELETE));
							}
						} else
						{
							JOptionPane.showMessageDialog(GravityModule.this, "Selectati o clasa de gravitate.");
							return;
						}
					}
					catch (Exception e)
					{
						ErrorUtils.showError(e, GravityModule.this);
					}
				}
			};
			deleteAction.putValue(Action.SHORT_DESCRIPTION, "Stergere clasa gravitate");
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
			
			modifyAction = new AbstractAction("Modificare clasa gravitate", imageIcon)
			{
				public void actionPerformed(ActionEvent arg0)
				{
					try
					{
						int index = getTableGravity().getSelectedRow();
	
						if (index >= 0)
						{
							Gravity gravity = (Gravity) getTableModel().getGravityList().get(index);
							GravityDialog dialog = new GravityDialog(null);
							dialog.setGravity(gravity);
							dialog.setVisible(true);
							
							if (dialog.isOk())
							{
								gravity.setCode(dialog.getGravityCode());
								gravity.setName(dialog.getGravityName());
								gravity.setDescription(dialog.getGravityDescription());
								
								getGravityDao().update(gravity);
								
								getTableGravity().tableChanged(
										new TableModelEvent(getTableModel(), index,
												index, TableModelEvent.ALL_COLUMNS,
												TableModelEvent.UPDATE));
							}
							
						} else
						{
							JOptionPane.showMessageDialog(GravityModule.this, "Selectati o clasa de gravitate.");
							return;
						}
					}
					catch (Exception e)
					{
						ErrorUtils.showError(e, GravityModule.this);
					}
				}
			};
			modifyAction.putValue(Action.SHORT_DESCRIPTION, "Modificare clasa gravitate");
		}
		
		return modifyAction;
	}

	/**
	 * @return the gravityList
	 */
	public List<Gravity> getGravityList()
	{
		if (gravityList == null)
		{
			try
			{
				gravityList = getGravityDao().findAll();
			} catch (DaoException e)
			{
				ErrorUtils.showError(e, GravityModule.this);
			}
		}
		return gravityList;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
