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
import javax.swing.DefaultCellEditor;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.table.TableColumn;

import ro.rmc.riskeval.dao.ConsequenceDao;
import ro.rmc.riskeval.dao.ConsequenceLocationDao;
import ro.rmc.riskeval.dao.DaoException;
import ro.rmc.riskeval.dao.GravityDao;
import ro.rmc.riskeval.domain.Consequence;
import ro.rmc.riskeval.domain.ConsequenceLocation;
import ro.rmc.riskeval.domain.Gravity;
import ro.rmc.riskeval.gui.tablemodels.ConsequenceLocCellRenderer;
import ro.rmc.riskeval.gui.tablemodels.ConsequenceLocationTableModel2;
import ro.rmc.riskeval.gui.tablemodels.ConsequenceTableModel;
import ro.rmc.riskeval.hibernate.ConsequenceDaoHibernate;
import ro.rmc.riskeval.hibernate.ConsequenceLocationDaoHibernate;
import ro.rmc.riskeval.hibernate.GravityDaoHibernate;
import ro.rmc.riskeval.utils.ErrorUtils;
import ro.rmc.riskeval.utils.IconsUtils;

public class ConsequenceModule extends JPanel
{

	private static final long serialVersionUID = 1L;
	private JToolBar toolbar = null;
	private JButton buttonAdd = null;
	private JButton buttonModify = null;
	private JButton buttonDelete = null;
	private JScrollPane scrollPane = null;
	private JTable tableConsequence = null;
	
	private List<Consequence> consequenceList = null;  //  @jve:decl-index=0:
	private List<ConsequenceLocation> consequenceLocList = null;
	private List<Gravity> gravityList = null;

	private ConsequenceDao consequenceDao = null;  //  @jve:decl-index=0:
	private ConsequenceLocationDao consequenceLocDao = null;  //  @jve:decl-index=0:
	private GravityDao gravityDao = null;
	
	private JSplitPane jSplitPane = null;
	private JScrollPane jScrollPane = null;
	private JTable tableConsquenceLocation = null;
	
	private Action addAction = null;
	private Action modifyAction = null;
	private Action deleteAction = null;
	private JPopupMenu popupMenu = null;  //  @jve:decl-index=0:visual-constraint="472,36"
	private JMenuItem menuItemAdd = null;
	private JMenuItem menuItemModify = null;
	private JMenuItem menuItemDelete = null;
	
	/**
	 * @return the consequenceDao
	 */
	public ConsequenceDao getConsequenceDao()
	{
		if (consequenceDao == null)
			consequenceDao = new ConsequenceDaoHibernate();
		return consequenceDao;
	}

	/**
	 * This is the default constructor
	 */
	public ConsequenceModule()
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
		this.setSize(400, 256);
		this.setLayout(new BorderLayout());
		this.add(getToolbar(), BorderLayout.NORTH);
		this.add(getJSplitPane(), BorderLayout.CENTER);
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
			toolbar.setBorderPainted(false);
			toolbar.add(getButtonAdd());
			toolbar.add(getButtonModify());
			toolbar.add(getButtonDelete());
		}
		return toolbar;
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
	 * This method initializes scrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getScrollPane()
	{
		if (scrollPane == null)
		{
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTableConsequence());
		}
		return scrollPane;
	}

	/**
	 * This method initializes tableConsequence	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getTableConsequence()
	{
		if (tableConsequence == null)
		{
			tableConsequence = new JTable();
			tableConsequence.setModel(new ConsequenceTableModel(
					getConsequenceList()));
			tableConsequence.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tableConsequence.setRowHeight(20);
			tableConsequence.setGridColor(Color.lightGray);
			tableConsequence.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			tableConsequence.getColumnModel().getColumn(0).setCellRenderer(
					new ConsequenceCellRenderer());
			tableConsequence.getColumnModel().getColumn(0).setPreferredWidth(60);
			tableConsequence.getColumnModel().getColumn(1).setPreferredWidth(150);
			
			// mouse events
			tableConsequence.addMouseListener(new MouseAdapter()
			{
				public void mouseReleased(MouseEvent e)
				{
					if (e.isPopupTrigger())
					{
						getPopupMenu().show(tableConsequence, e.getX(), e.getY());
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
			tableConsequence.addKeyListener(new KeyAdapter()
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
			
			tableConsequence.getSelectionModel().addListSelectionListener(
					new ListSelectionListener()
					{

						public void valueChanged(ListSelectionEvent e)
						{
							// indexul elementului selectat
							int index = tableConsequence.getSelectedRow();
							
							// table model 
							ConsequenceLocationTableModel2 tableModel = 
								(ConsequenceLocationTableModel2) getTableConsquenceLocation()
									.getModel();

							if (index == -1)
							{
								tableModel.setConsequence(null);
							} else
							{
								Consequence consequence = getConsequenceList().get(index);
								tableModel.setConsequence(consequence);
							}
							getTableConsquenceLocation().tableChanged(
									new TableModelEvent(tableModel));
						}
					});
		}
		return tableConsequence;
	}

	public List<Consequence> getConsequenceList()
	{
		if (consequenceList == null)
			try
			{
				consequenceList = getConsequenceDao().findAll();
			} catch (DaoException e)
			{
				ErrorUtils.showError(e, ConsequenceModule.this);
			}
		return consequenceList;
	}

	public List<ConsequenceLocation> getConsequenceLocList()
	{
		if (consequenceLocList == null)
			try
			{
				consequenceLocList = getConsequenceLocDao().findAll();
			} catch (DaoException e)
			{
				ErrorUtils.showError(e, ConsequenceModule.this);
			}
		return consequenceLocList;
	}

	private ConsequenceLocationDao getConsequenceLocDao()
	{
		if (consequenceLocDao == null)
			consequenceLocDao = new ConsequenceLocationDaoHibernate();
		return consequenceLocDao;
	}

	/**
	 * This method initializes jSplitPane	
	 * 	
	 * @return javax.swing.JSplitPane	
	 */
	private JSplitPane getJSplitPane()
	{
		if (jSplitPane == null)
		{
			jSplitPane = new JSplitPane();
			jSplitPane.setDividerLocation(250);
			jSplitPane.setLeftComponent(getScrollPane());
			jSplitPane.setRightComponent(getJScrollPane());
		}
		return jSplitPane;
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
			jScrollPane.setViewportView(getTableConsquenceLocation());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes tableConsquenceLocation	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getTableConsquenceLocation()
	{
		if (tableConsquenceLocation == null)
		{
			tableConsquenceLocation = new JTable();
			tableConsquenceLocation.setGridColor(Color.lightGray);
			tableConsquenceLocation.setRowHeight(20);
			tableConsquenceLocation.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tableConsquenceLocation.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

			tableConsquenceLocation
					.setModel(new ConsequenceLocationTableModel2(
							getConsequenceLocList(), null));
			
			tableConsquenceLocation.getColumnModel().getColumn(0)
					.setCellRenderer(new ConsequenceLocCellRenderer());
			
			tableConsquenceLocation.getColumnModel().getColumn(0).setPreferredWidth(50);
			tableConsquenceLocation.getColumnModel().getColumn(1).setPreferredWidth(150);
			tableConsquenceLocation.getColumnModel().getColumn(2).setPreferredWidth(250);
			
			JComboBox comboBoxGravity = new JComboBox(getGravityList()
					.toArray());
			comboBoxGravity.addItem(null);
			TableColumn columnGravity = tableConsquenceLocation
					.getColumnModel().getColumn(2);
			columnGravity.setCellEditor(new DefaultCellEditor(comboBoxGravity));
		}
		return tableConsquenceLocation;
	}

	public Action getAddAction()
	{
		if (addAction == null)
		{
			Icon imageIcon = IconsUtils.getInstance().getIcon("button.add");
			
			addAction = new AbstractAction("Adaugare consecinta", imageIcon)
			{

				public void actionPerformed(ActionEvent arg0)
				{
					try
					{
						ConsequenceDialog dialog = new ConsequenceDialog(null);
						dialog.setVisible(true);
						
						if (dialog.isOk())
						{
							Consequence consequence = new Consequence();
							consequence.setCode(dialog.getConsequenceCode());
							consequence.setName(dialog.getConsequenceName());
							
							getConsequenceDao().store(consequence);
							
							int row = getTableConsequence().getModel().getRowCount() - 1;
							
							ConsequenceTableModel tableModel = (ConsequenceTableModel) getTableConsequence().getModel();
							tableModel.getConsequenceList().add(consequence);
							
							getTableConsequence().tableChanged(
									new TableModelEvent(tableModel, row + 1, row + 1,
											TableModelEvent.ALL_COLUMNS,
											TableModelEvent.INSERT));
							
							getTableConsequence().getSelectionModel()
									.setSelectionInterval(row + 1, row + 1);
						}
					}
					catch (Exception e)
					{
						ErrorUtils.showError(e, ConsequenceModule.this);
					}
				}
			};
			addAction.putValue(Action.SHORT_DESCRIPTION, "Adaugare consecinta");
		}
		
		return addAction;
	}

	public Action getDeleteAction()
	{
		if (deleteAction == null)
		{
			Icon imageIcon = IconsUtils.getInstance().getIcon("button.delete");
			
			deleteAction = new AbstractAction("Stergere consecinta", imageIcon)
			{

				public void actionPerformed(ActionEvent arg0)
				{
					try
					{
						int index = getTableConsequence().getSelectedRow();
						
						if (index >= 0)
						{
							ConsequenceTableModel tableModel = (ConsequenceTableModel) getTableConsequence()
									.getModel();
							Consequence consequence = (Consequence) tableModel.getConsequenceList().get(index);
							String msg = MessageFormat
									.format(
											"Sunteti sigur ca doriti sa stergeti consecinta \"{0}\" ?",
											consequence.getName());
							
							if (JOptionPane.showConfirmDialog(ConsequenceModule.this, msg) == JOptionPane.YES_OPTION)
							{
								getConsequenceDao().delete(consequence);
								tableModel.getConsequenceList().remove(consequence);
								
								getTableConsequence().tableChanged(
										new TableModelEvent(tableModel, index,
												index, TableModelEvent.ALL_COLUMNS,
												TableModelEvent.DELETE));
							}
						} else
						{
							JOptionPane.showMessageDialog(ConsequenceModule.this, "Selectati o consecinta.");
							return;
						}
					}
					catch (Exception e)
					{
						ErrorUtils.showError(e, ConsequenceModule.this);
					}
				}
			};
			deleteAction.putValue(Action.SHORT_DESCRIPTION, "Stergere consecinta");
		}
		
		return deleteAction;
	}

	public Action getModifyAction()
	{
		if (modifyAction == null)
		{
			Icon imageIcon = IconsUtils.getInstance().getIcon("button.edit");
			
			modifyAction = new AbstractAction("Modificare consecinta", imageIcon)
			{

				public void actionPerformed(ActionEvent arg0)
				{
					try
					{
						int index = getTableConsequence().getSelectedRow();
	
						if (index >= 0)
						{
							ConsequenceTableModel tableModel = (ConsequenceTableModel) getTableConsequence()
									.getModel();
							Consequence consequence = (Consequence) tableModel.getConsequenceList().get(index);
							ConsequenceDialog dialog = new ConsequenceDialog(null);
							dialog.setConsequence(consequence);
							dialog.setVisible(true);
							
							if (dialog.isOk())
							{
								consequence.setCode(dialog.getConsequenceCode());
								consequence.setName(dialog.getConsequenceName());
								
								getConsequenceDao().update(consequence);
								
								getTableConsequence().tableChanged(
										new TableModelEvent(tableModel, index,
												index, TableModelEvent.ALL_COLUMNS,
												TableModelEvent.UPDATE));
							}
							
						} else
						{
							JOptionPane.showMessageDialog(ConsequenceModule.this, "Selectati o consecinta.");
							return;
						}
					}
					catch (Exception e)
					{
						ErrorUtils.showError(e, ConsequenceModule.this);
					}
				}
			};
			modifyAction.putValue(Action.SHORT_DESCRIPTION, "Modificare consecinta");
		}
		
		return modifyAction;
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

	public List<Gravity> getGravityList()
	{
		if (gravityList == null)
			try
			{
				gravityList = getGravityDao().findAll();
			} catch (DaoException e)
			{
				ErrorUtils.showError(e, ConsequenceModule.this);
			}
		return gravityList;
	}

	public GravityDao getGravityDao()
	{
		if (gravityDao == null)
			gravityDao = new GravityDaoHibernate();
		return gravityDao;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
