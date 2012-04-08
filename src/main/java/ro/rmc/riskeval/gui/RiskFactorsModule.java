package ro.rmc.riskeval.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.MessageFormat;
import java.util.Iterator;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import ro.rmc.riskeval.dao.RiskFactorDao;
import ro.rmc.riskeval.domain.RiskFactor;
import ro.rmc.riskeval.hibernate.RiskFactorDaoHibernate;
import ro.rmc.riskeval.utils.ErrorUtils;
import ro.rmc.riskeval.utils.IconsUtils;

public class RiskFactorsModule extends JPanel
{

	private static final long serialVersionUID = 1L;

	private JToolBar toolbar = null;

	private JButton buttonAdd = null;

	private JButton buttonModify = null;

	private JButton buttonDelete = null;

	private JScrollPane scrollPane = null;

	private JTree treeRiskFactors = null;

	private RiskFactorDao riskFactorDao = null;

	private JPopupMenu popupMenu = null;  //  @jve:decl-index=0:visual-constraint="17,332"

	private JMenuItem jMenuItem = null;

	private JMenuItem jMenuItem1 = null;

	private JMenuItem jMenuItem2 = null;
	
	private Action actionAdd = null;  //  @jve:decl-index=0:
	
	private Action actionModify = null;
	
	private Action actionDelete = null;  //  @jve:decl-index=0:
	
	DefaultTreeModel treeModel = null;

	/**
	 * @return the riskFactorDao
	 */
	public RiskFactorDao getRiskFactorDao()
	{
		if (riskFactorDao == null)
			riskFactorDao = new RiskFactorDaoHibernate();

		return riskFactorDao;
	}

	/**
	 * This is the default constructor
	 */
	public RiskFactorsModule()
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
		this.setSize(504, 304);
		this.setLayout(new BorderLayout());
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
			buttonAdd.setAction(getActionAdd());
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
			buttonModify.setAction(getActionModify());
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
			buttonDelete.setAction(getActionDelete());
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
			scrollPane.setViewportView(getTreeRiskFactors());
		}
		return scrollPane;
	}

	/**
	 * 
	 * @param riskFactor
	 * @param treeNode
	 */
	private void addRiskFactorToTree(RiskFactor riskFactor,
			DefaultMutableTreeNode node)
	{
		for (Iterator iter = riskFactor.getRiskFactors().iterator(); iter.hasNext();)
		{
			RiskFactor element = (RiskFactor) iter.next();
			DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(element);
			node.add(newNode);
			addRiskFactorToTree(element, newNode);
		}
	}

	/**
	 * This method initializes treeRiskFactors
	 * 
	 * @return javax.swing.JTree
	 */
	private JTree getTreeRiskFactors()
	{
		if (treeRiskFactors == null)
		{
			treeRiskFactors = new JTree(getTreeModel());
			treeRiskFactors.setRowHeight(20);
			
			treeRiskFactors.addMouseListener(new MouseAdapter()
			{
				public void mouseReleased(MouseEvent e)
				{
					if (e.isPopupTrigger())
					{
						getPopupMenu().show(e.getComponent(), e.getX(), e.getY());
					}
				}
			});
			
			treeRiskFactors.setRootVisible(true);
		}
		return treeRiskFactors;
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
			popupMenu.add(getJMenuItem());
			popupMenu.add(getJMenuItem1());
			popupMenu.add(getJMenuItem2());
		}
		return popupMenu;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItem()
	{
		if (jMenuItem == null)
		{
			jMenuItem = new JMenuItem();
			jMenuItem.setAction(getActionAdd());
		}
		return jMenuItem;
	}

	/**
	 * This method initializes jMenuItem1	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItem1()
	{
		if (jMenuItem1 == null)
		{
			jMenuItem1 = new JMenuItem();
			jMenuItem1.setAction(getActionModify());
		}
		return jMenuItem1;
	}

	/**
	 * This method initializes jMenuItem2	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItem2()
	{
		if (jMenuItem2 == null)
		{
			jMenuItem2 = new JMenuItem();
			jMenuItem2.setAction(getActionDelete());
		}
		return jMenuItem2;
	}

	/**
	 * Add an risk factor into the tree
	 * @param riskFactor
	 * @param parent
	 */
	private void addRiskFactor(RiskFactor riskFactor,
			DefaultMutableTreeNode parent)
	{
		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(riskFactor);
		getTreeModel().insertNodeInto(newNode, parent, parent.getChildCount());
		TreePath treePath = getTreeRiskFactors().getSelectionPath();
		treePath = treePath.pathByAddingChild(newNode);
		getTreeRiskFactors().scrollPathToVisible(treePath);
		getTreeRiskFactors().setSelectionPath(treePath);
	}
	
	/**
	 * @return the actionAdd
	 */
	public Action getActionAdd()
	{
		if (actionAdd == null)
		{
			Icon addIcon = IconsUtils.getInstance().getIcon("button.add");
			actionAdd = new AbstractAction("Adaugare factor risc", addIcon)
			{
				public void actionPerformed(ActionEvent arg0)
				{
					try
					{
						TreePath treePath = getTreeRiskFactors().getSelectionPath();
						
						if (treePath == null)
						{
							JOptionPane.showMessageDialog(RiskFactorsModule.this,
									"Selectati un factor de risc");
							return;
						}
						
						DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) treePath.getLastPathComponent();
						
						RiskFactor parentRiskFactor = (RiskFactor) parentNode.getUserObject();
	
						RiskFactorDialog dialog = new RiskFactorDialog(null);
						dialog.setParentRiskFactor(parentRiskFactor);
	
						dialog.setVisible(true);
	
						if (dialog.isOk())
						{
							RiskFactor newRiskFactor = new RiskFactor();
							newRiskFactor.setCode(dialog.getCode());
							newRiskFactor.setName(dialog.getName());
							parentRiskFactor.getRiskFactors().add(newRiskFactor);
							newRiskFactor.setParentRiskFactor(parentRiskFactor);
							
							getRiskFactorDao().store(newRiskFactor);
							addRiskFactor(newRiskFactor, parentNode);
						}
					}
					catch (Exception e)
					{
						ErrorUtils.showError(e, RiskFactorsModule.this);
					}
				}
			};
			actionAdd.putValue(Action.SHORT_DESCRIPTION, "Adaugare factor risc");
		}
		return actionAdd;
	}

	/**
	 * @return the actionDelete
	 */
	public Action getActionDelete()
	{
		if (actionDelete == null)
		{
			Icon deleteIcon = IconsUtils.getInstance().getIcon("button.delete");
			actionDelete = new AbstractAction("Stergere factor risc",
					deleteIcon)
			{

				public void actionPerformed(ActionEvent arg0)
				{
					try
					{
						TreePath treePath = getTreeRiskFactors().getSelectionPath();
						
						DefaultMutableTreeNode node = null;
						
						if (treePath != null)
							node = (DefaultMutableTreeNode) treePath.getLastPathComponent();
	
						if (node == null || node.isRoot())
						{
							JOptionPane.showMessageDialog(RiskFactorsModule.this,
									"Selectati un factor de risc (diferit de factorul de risc radacina).");
							return;
						}
	
						RiskFactor riskFactor = (RiskFactor) node.getUserObject();
						RiskFactor parentRiskFactor = riskFactor.getParentRiskFactor();
	
						String msg = MessageFormat
								.format(
										"Sunteti sigur ca doriti sa stergeti factorul de risk \"{0}\" ?",
										riskFactor.getName());
						
						if (JOptionPane.showConfirmDialog(RiskFactorsModule.this,
								msg) == JOptionPane.YES_OPTION)
						{
							getRiskFactorDao().delete(riskFactor);
							treeModel.removeNodeFromParent(node);
							if (parentRiskFactor != null)
								parentRiskFactor.getRiskFactors().remove(riskFactor);
						}
					}
					catch (Exception e)
					{
						ErrorUtils.showError(e, RiskFactorsModule.this);
					}
				}
			};
			actionDelete.putValue(Action.SHORT_DESCRIPTION, "Stergere factor risc");
		}
		return actionDelete;
	}

	/**
	 * @return the actionModify
	 */
	public Action getActionModify()
	{
		if (actionModify == null)
		{
			Icon modifyIcon = IconsUtils.getInstance().getIcon("button.edit");
			actionModify = new AbstractAction("Modificare factor risc",
					modifyIcon)
			{

				public void actionPerformed(ActionEvent arg0)
				{
					try
					{
						TreePath treePath = getTreeRiskFactors().getSelectionPath();
						
						DefaultMutableTreeNode node = null;
						
						if (treePath != null)
							node = (DefaultMutableTreeNode) treePath.getLastPathComponent();
	
						if (node == null || node.isRoot())
						{
							JOptionPane.showMessageDialog(RiskFactorsModule.this,
									"Selectati un factor de risc (diferit de factorul de risc radacina).");
							return;
						}
						
						RiskFactor riskFactor = (RiskFactor) node.getUserObject();
	
						RiskFactorDialog dialog = new RiskFactorDialog(null);
						dialog.setCurrentRiskFactor(riskFactor);
						dialog.setParentRiskFactor(riskFactor.getParentRiskFactor());
						dialog.setVisible(true);
						
						if (dialog.isOk())
						{
							riskFactor.setCode(dialog.getCode());
							riskFactor.setName(dialog.getName());
							getRiskFactorDao().update(riskFactor);
							getTreeRiskFactors().updateUI();
						}
					}
					catch (Exception e)
					{
						ErrorUtils.showError(e, RiskFactorsModule.this);
					}
				}
			};
			actionModify.putValue(Action.SHORT_DESCRIPTION, "Modificare factor risc");
		}
		
		return actionModify;
	}

	/**
	 * @return the treeModel
	 */
	public DefaultTreeModel getTreeModel()
	{
		if (treeModel == null)
		{
			try
			{
				RiskFactor riskFactor = (RiskFactor) getRiskFactorDao().findAll().get(0);
				DefaultMutableTreeNode root = new DefaultMutableTreeNode(riskFactor);
				addRiskFactorToTree(riskFactor, root);
				treeModel = new DefaultTreeModel(root);
			}
			catch (Exception e)
			{
				ErrorUtils.showError(e, RiskFactorsModule.this);
			}
		}
		return treeModel;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
