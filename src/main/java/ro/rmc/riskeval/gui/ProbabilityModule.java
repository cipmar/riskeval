package ro.rmc.riskeval.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
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
import ro.rmc.riskeval.dao.ProbabilityDao;
import ro.rmc.riskeval.domain.Probability;
import ro.rmc.riskeval.gui.tablemodels.ProbabilityCellRenderer;
import ro.rmc.riskeval.gui.tablemodels.ProbabilityTableModel;
import ro.rmc.riskeval.hibernate.ProbabilityDaoHibernate;
import ro.rmc.riskeval.utils.ErrorUtils;
import ro.rmc.riskeval.utils.IconsUtils;

public class ProbabilityModule extends JPanel
{

	private static final long serialVersionUID = 1L;
	
	private ProbabilityDao probabilityDao = null;
	
	private JToolBar toolbar = null;
	private JButton buttonAdd = null;
	private JButton buttonModify = null;
	private JButton buttonDelete = null;
	private JTable tableProbability = null;

	private JScrollPane scrollPane = null;
	
	private List<Probability> probabilityList = null;
	private ProbabilityTableModel tableModel = null;
	
	private Action addAction;
	private Action modifyAction;  //  @jve:decl-index=0:
	private Action deleteAction;

	private JPopupMenu jPopupMenu = null;  //  @jve:decl-index=0:visual-constraint="322,58"

	private JMenuItem menuItemAdd = null;

	private JMenuItem menuItemModify = null;

	private JMenuItem menuItemDelete = null;

	/**
	 * @return the tableModel
	 */
	public ProbabilityTableModel getTableModel()
	{
		if (tableModel == null)
			tableModel = new ProbabilityTableModel(getProbabilityList());
		return tableModel;
	}

	/**
	 * This is the default constructor
	 */
	public ProbabilityModule()
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
		this.setSize(300, 200);
		this.setLayout(new BorderLayout());
		this.add(getScrollPane(), BorderLayout.CENTER);
		this.add(getJJToolBarBar(), BorderLayout.NORTH);
	}

	/**
	 * This method initializes jJToolBarBar	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJJToolBarBar()
	{
		if (toolbar == null)
		{
			toolbar = new JToolBar();
			toolbar.setPreferredSize(new Dimension(0, 32));
			toolbar.setFloatable(false);
			toolbar.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			toolbar.add(getJButton());
			toolbar.add(getJButton1());
			toolbar.add(getJButton2());
		}
		return toolbar;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton()
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
	 * This method initializes jButton1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton1()
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
	 * This method initializes jButton2	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton2()
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
	 * This method initializes jTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getTableProbability()
	{
		if (tableProbability == null)
		{
			tableProbability = new JTable();
			tableProbability.setRowHeight(20);
			tableProbability.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tableProbability.setModel(getTableModel());
			tableProbability.setGridColor(Color.lightGray);
			tableProbability.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			tableProbability.getColumnModel().getColumn(0).setCellRenderer(
					new ProbabilityCellRenderer());
			tableProbability.getColumnModel().getColumn(0).setPreferredWidth(150);
			tableProbability.getColumnModel().getColumn(1).setPreferredWidth(350);
			tableProbability.getColumnModel().getColumn(2).setPreferredWidth(50);
			
			// mouse events
			tableProbability.addMouseListener(new MouseAdapter()
			{
				public void mouseReleased(MouseEvent e)
				{
					if (e.isPopupTrigger())
					{
						getJPopupMenu().show(tableProbability, e.getX(), e.getY());
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
			tableProbability.addKeyListener(new KeyAdapter()
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
		return tableProbability;
	}

	/**
	 * @return the probabilityDao
	 */
	public ProbabilityDao getProbabilityDao()
	{
		if (probabilityDao == null)
			probabilityDao = new ProbabilityDaoHibernate();
		return probabilityDao;
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
			scrollPane.setViewportView(getTableProbability());
			scrollPane.setViewportView(getTableProbability());
		}
		return scrollPane;
	}

	/**
	 * @return the probabilityList
	 */
	public List<Probability> getProbabilityList()
	{
		if (probabilityList == null)
			try
			{
				probabilityList = getProbabilityDao().findAll();
			} catch (DaoException e)
			{
				ErrorUtils.showError(e, ProbabilityModule.this);
			}
		return probabilityList;
	}

	/**
	 * @return the addAction
	 */
	public Action getAddAction()
	{
		if (addAction == null)
		{
			Icon imageIcon = IconsUtils.getInstance().getIcon("button.add");
			
			addAction = new AbstractAction("Adaugare clasa probabilitate", imageIcon)
			{

				public void actionPerformed(ActionEvent arg0)
				{
					try
					{
						ProbabilityDialog dialog = new ProbabilityDialog(null);
						dialog.setVisible(true);
						
						if (dialog.isOk())
						{
							Probability probability = new Probability();
							probability.setCode(dialog.getProbabilityCode());
							probability.setName(dialog.getProbabilityName());
							probability.setDescription(dialog.getProbabilityDescription());
							
							getProbabilityDao().store(probability);
							
							int row = getTableProbability().getModel().getRowCount() - 1;
							
							ProbabilityTableModel tableModel = (ProbabilityTableModel) getTableProbability().getModel();
							tableModel.getProbabilityList().add(probability);
							
							getTableProbability().tableChanged(
									new TableModelEvent(tableModel, row + 1, row + 1,
											TableModelEvent.ALL_COLUMNS,
											TableModelEvent.INSERT));
							
							getTableProbability().getSelectionModel()
									.setSelectionInterval(row + 1, row + 1);
						}
					}
					catch (Exception e)
					{
						ErrorUtils.showError(e, ProbabilityModule.this);
					}
				}
			};
			addAction.putValue(Action.SHORT_DESCRIPTION, "Adaugare clasa probabilitate");
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
			
			deleteAction = new AbstractAction("Stergere clasa probabilitate", imageIcon)
			{

				public void actionPerformed(ActionEvent arg0)
				{
					try
					{
						int index = getTableProbability().getSelectedRow();
						
						if (index >= 0)
						{
							Probability probability = (Probability) getTableModel().getProbabilityList().get(index);
							String msg = MessageFormat
									.format(
											"Sunteti sigur ca doriti sa stergeti clasa de probabilitate \"{0}\" ?",
											probability.getName());
							
							if (JOptionPane.showConfirmDialog(ProbabilityModule.this, msg) == JOptionPane.YES_OPTION)
							{
								getProbabilityDao().delete(probability);
								getTableModel().getProbabilityList().remove(probability);
								
								getTableProbability().tableChanged(
										new TableModelEvent(getTableModel(), index,
												index, TableModelEvent.ALL_COLUMNS,
												TableModelEvent.DELETE));
							}
						} else
						{
							JOptionPane.showMessageDialog(ProbabilityModule.this, "Selectati o clasa de probabilitate.");
							return;
						}
					}
					catch (Exception e)
					{
						ErrorUtils.showError(e, ProbabilityModule.this);
					}
				}
			};
			deleteAction.putValue(Action.SHORT_DESCRIPTION, "Stergere clasa probabilitate");
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
			
			modifyAction = new AbstractAction("Modificare clasa probabilitate", imageIcon)
			{

				public void actionPerformed(ActionEvent arg0)
				{
					try
					{
						int index = getTableProbability().getSelectedRow();
	
						if (index >= 0)
						{
							Probability probability = (Probability) getTableModel().getProbabilityList().get(index);
							ProbabilityDialog dialog = new ProbabilityDialog(null);
							dialog.setProbability(probability);
							dialog.setVisible(true);
							
							if (dialog.isOk())
							{
								probability.setCode(dialog.getProbabilityCode());
								probability.setName(dialog.getProbabilityName());
								probability.setDescription(dialog.getProbabilityDescription());
								
								getProbabilityDao().update(probability);
								
								getTableProbability().tableChanged(
										new TableModelEvent(getTableModel(), index,
												index, TableModelEvent.ALL_COLUMNS,
												TableModelEvent.UPDATE));
							}
							
						} else
						{
							JOptionPane.showMessageDialog(ProbabilityModule.this, "Selectati o clasa de probabilitate.");
							return;
						}
					}
					catch (Exception e)
					{
						ErrorUtils.showError(e, ProbabilityModule.this);
					}
				}
			};
			modifyAction.putValue(Action.SHORT_DESCRIPTION, "Modificare clasa probabilitate");
		}
		
		return modifyAction;
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

} //  @jve:decl-index=0:visual-constraint="10,10"
