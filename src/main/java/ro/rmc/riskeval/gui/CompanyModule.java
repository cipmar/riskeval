	package ro.rmc.riskeval.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.MessageFormat;
import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.filechooser.FileFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import ro.rmc.riskeval.dao.CompanyDao;
import ro.rmc.riskeval.dao.DaoException;
import ro.rmc.riskeval.dao.RiskFactorDao;
import ro.rmc.riskeval.dao.SectionDao;
import ro.rmc.riskeval.dao.SectorDao;
import ro.rmc.riskeval.dao.WorkPlaceDao;
import ro.rmc.riskeval.domain.Company;
import ro.rmc.riskeval.domain.ProcessorEvaluationDoc;
import ro.rmc.riskeval.domain.ProcessorWorkPlacesTable;
import ro.rmc.riskeval.domain.RiskFactor;
import ro.rmc.riskeval.domain.Section;
import ro.rmc.riskeval.domain.Sector;
import ro.rmc.riskeval.domain.WorkPlace;
import ro.rmc.riskeval.gui.tablemodels.CompanyCellRenderer;
import ro.rmc.riskeval.gui.tablemodels.CompanyTableModel;
import ro.rmc.riskeval.hibernate.CompanyDaoHibernate;
import ro.rmc.riskeval.hibernate.RiskFactorDaoHibernate;
import ro.rmc.riskeval.hibernate.SectionDaoHibernate;
import ro.rmc.riskeval.hibernate.SectorDaoHibernate;
import ro.rmc.riskeval.hibernate.WorkPlaceDaoHibernate;
import ro.rmc.riskeval.utils.ErrorUtils;
import ro.rmc.riskeval.utils.IconsUtils;

public class CompanyModule extends JPanel
{
	private static final long serialVersionUID = 1L;
	private JToolBar toolbar = null;
	private JTable tableCompany = null;
	
	private CompanyDao companyDao = null;
	private SectionDao sectionDao = null;  //  @jve:decl-index=0:
	private SectorDao sectorDao = null;
	private WorkPlaceDao workPlaceDao = null;
	private RiskFactorDao riskFactorDao = null;
	
	private JScrollPane jScrollPane = null;
	private Action addAction;
	private Action modifyAction;
	private Action deleteAction;
	private Action riskSheetsAction;
	private Action genWorkPlacesDocAction;
	private Action generateDocumentsAction;  //  @jve:decl-index=0:
	
	private CompanyTableModel tableModel = null;
	
	private JFileChooser fileChooser;
	
	private JButton buttonAdd = null;
	private JButton buttonModify = null;
	private JButton buttonDelete = null;
	private JPopupMenu popupMenu = null;  //  @jve:decl-index=0:visual-constraint="594,275"
	private JMenuItem menuItemAdd = null;
	private JMenuItem menuItemModify = null;
	private JMenuItem menuItemDelete = null;
	
	private List<Company> companies = null;
	private JPanel panelCenter = null;
	private JPanel jPanel = null;
	private JToolBar jJToolBarBar = null;
	private JScrollPane jScrollPane1 = null;
	private JTree treeCompanyOrg = null;
	private JButton buttonAddSection = null;
	private JButton buttonDeleteSection = null;
	private JButton buttonAddSector = null;
	private JButton buttonDeleteSector = null;
	private JButton buttonAddWorkPlace = null;
	private JButton buttonDeleteWorkPlace = null;

	private Action addSectionAction = null;  //  @jve:decl-index=0:
	private Action modifySectionAction = null;
	private Action deleteSectionAction = null;
	private Action addSectorAction = null;  //  @jve:decl-index=0:
	private Action modifySectorAction = null;
	private Action deleteSectorAction = null; 
	private Action addWorkPlaceAction = null;  //  @jve:decl-index=0:
	private Action modifyWorkPlaceAction = null;  //  @jve:decl-index=0:
	private Action deleteWorkPlaceAction = null;
	private JPopupMenu popupMenuSection = null;  //  @jve:decl-index=0:visual-constraint="586,67"
	private JPopupMenu popupMenuSector = null;  //  @jve:decl-index=0:visual-constraint="583,126"
	private JPopupMenu popupMenuWorkPlace = null;  //  @jve:decl-index=0:visual-constraint="584,178"
	private JMenuItem menuItemAddSector = null;
	private JMenuItem menuItemDeleteSection = null;
	private JMenuItem menuItemAddWorkPlace = null;
	private JMenuItem menuItemDeleteSector = null;
	private JMenuItem menuItemDeleteWorkPlace = null;
	private JPopupMenu popupMenuCompany = null;  //  @jve:decl-index=0:visual-constraint="586,228"
	private JMenuItem menuItemAddSection = null;
	private JSplitPane jSplitPane = null;
	private JButton buttonModifySection = null;
	private JButton buttonModifySector = null;
	private JButton buttonModifyWorkPlace = null;
	private JMenuItem menuItemModifySection = null;
	private JMenuItem menuItemModifySector = null;
	private JMenuItem menuItemModifyWorkPlace = null;
	private JMenuItem menuItemRiskSheet = null;
	private JMenuItem menuItemGenerateDocuments = null;
	private JButton buttonWorkPlacesDocument = null;
	private JButton buttonGenRiskFactorsDoc = null;
	private JMenuItem menuItemGenWorkPlacesDoc = null;
	private JButton buttonRiskSheet = null;
	/**
	 * @return the tableModel
	 */
	public CompanyTableModel getTableModel()
	{
		if (tableModel == null)
			tableModel = new CompanyTableModel(getCompanies());
		return tableModel;
	}

	/**
	 * @return the companies
	 */
	public List<Company> getCompanies()
	{
		if (companies == null)
		{
			try
			{
				companies = getCompanyDao().findAll();
			} catch (DaoException e)
			{
				ErrorUtils.showError(e, CompanyModule.this);
			}
		}
		return companies;
	}

	/**
	 * This is the default constructor
	 */
	public CompanyModule()
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
		try
		{
			this.setSize(562, 447);
			this.setLayout(new BorderLayout());
			this.setBackground(SystemColor.window);
			this.add(getPanelCenter(), BorderLayout.CENTER);
			this.add(getToolbar(), BorderLayout.NORTH);
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
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
			toolbar.setPreferredSize(new Dimension(0, 30));
			toolbar.setFloatable(false);
			toolbar.setPreferredSize(new Dimension(0, 32));
			toolbar.add(getButtonAdd());
			toolbar.add(getButtonModify());
			toolbar.add(getButtonDelete());
			toolbar.addSeparator();
			toolbar.add(getButtonWorkPlacesDocument());
		}
		return toolbar;
	}

	/**
	 * @return the treeModel
	 */
	public DefaultTreeModel createTreeModel(Company company)
	{
		DefaultTreeModel treeModel = null;
		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(company);
		
		for (Iterator iter = company.getSections().iterator(); iter.hasNext();)
		{
			Section section = (Section) iter.next();
			addSectionToTree(section, root);
		}
		
		treeModel = new DefaultTreeModel(root);
		
		return treeModel;
	}

	private void addSectionToTree(Section section, DefaultMutableTreeNode parent)
	{
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(section);
		parent.add(node);
		
		for (Iterator iter = section.getSectors().iterator(); iter.hasNext();)
		{
			Sector sector = (Sector) iter.next();
			addSectorToTree(sector, node);
		}
	}

	private void addSectorToTree(Sector sector, DefaultMutableTreeNode parent)
	{
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(sector);
		parent.add(node);
		
		for (Iterator iter = sector.getWorkPlaces().iterator(); iter.hasNext();)
		{
			WorkPlace workPlace = (WorkPlace) iter.next();
			DefaultMutableTreeNode nodeWorkPlace = new DefaultMutableTreeNode(workPlace);
			node.add(nodeWorkPlace);
		}
	}

	/**
	 * This method initializes tableCompany	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getTableCompany()
	{
		if (tableCompany == null)
		{
			tableCompany = new JTable();
			
			// other properties
			tableCompany.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tableCompany.setGridColor(Color.LIGHT_GRAY);
			tableCompany.setBackground(SystemColor.window);
			tableCompany.setShowGrid(true);
			tableCompany.setRowHeight(20);
			tableCompany.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			
			// mouse clicked
			tableCompany.addMouseListener(new MouseAdapter(){
				public void mouseReleased(MouseEvent e)
				{
					if (e.isPopupTrigger())
					{
						getPopupMenu().show(tableCompany, e.getX(), e.getY());
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
			tableCompany.addKeyListener(new KeyAdapter()
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

			// selection events
			getTableCompany().getSelectionModel().addListSelectionListener(new ListSelectionListener()
			{
				public void valueChanged(ListSelectionEvent e)
				{
					int index = tableCompany.getSelectedRow();
					
					if (index >= 0)
					{
						Company company = (Company) getCompanies().get(index);
						DefaultTreeModel treeModel = createTreeModel(company);
						getTreeCompanyOrg().setModel(null);
						getTreeCompanyOrg().setModel(treeModel);
					}
					else
					{
						getTreeCompanyOrg().setModel(null);
					}
				}
			});	
			
			 // the model
			tableCompany.setModel(getTableModel());
			
			// cell renderer
			tableCompany.getColumnModel().getColumn(0).setCellRenderer(
					new CompanyCellRenderer());
			tableCompany.getColumnModel().getColumn(0).setPreferredWidth(200);
			tableCompany.getColumnModel().getColumn(1).setPreferredWidth(300);
			tableCompany.getColumnModel().getColumn(2).setPreferredWidth(120);
			tableCompany.getColumnModel().getColumn(3).setPreferredWidth(120);
			tableCompany.getColumnModel().getColumn(4).setPreferredWidth(150);
		}
		return tableCompany;
	}

	/**
	 * @return the companyDao
	 */
	public CompanyDao getCompanyDao()
	{
		if (companyDao == null)
			companyDao = new CompanyDaoHibernate();
		return companyDao;
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
			jScrollPane.setPreferredSize(new Dimension(453, 200));
			jScrollPane.setBackground(SystemColor.window);
			jScrollPane.setViewportView(getTableCompany());
		}
		return jScrollPane;
	}

	/**
	 * @return the addAction
	 */
	public Action getAddAction()
	{
		if (addAction == null)
		{
			Icon addIcon = IconsUtils.getInstance().getIcon("button.add");
			addAction = new AbstractAction("", addIcon)
			{

				public void actionPerformed(ActionEvent arg0)
				{
					try
					{
						CompanyDialog dialog = new CompanyDialog(null);
						dialog.setVisible(true);
						
						if (dialog.isOk())
						{
							Company company = new Company();
							company.setName(dialog.getCompanyName());
							company.setAddress(dialog.getCompanyAddress());
							company.setPhone(dialog.getCompanyPhone());
							company.setFax(dialog.getCompanyFax());
							company.setEmail(dialog.getCompanyEmail());
							
							getCompanyDao().store(company);
							
							
							int row = getTableCompany().getModel().getRowCount();
							getTableModel().getCompanyList().add(company);
							
							getTableCompany().tableChanged(new TableModelEvent(getTableModel(), row, row,
									TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT));
							
							getTableCompany().getSelectionModel().setSelectionInterval(row, row);
						}
					}
					catch (Exception e)
					{
						ErrorUtils.showError(e, CompanyModule.this);
					}
				}
			};
			
			addAction.putValue(Action.NAME, "Adaugare companie");
			addAction.putValue(Action.SHORT_DESCRIPTION, "Adaugare companie");
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
			Icon deleteIcon = IconsUtils.getInstance().getIcon("button.delete");
			deleteAction = new AbstractAction("", deleteIcon)
			{

				public void actionPerformed(ActionEvent e)
				{
					try
					{
						int index = getTableCompany().getSelectedRow();
						
						if (index < 0)
						{
							String msg = "Selectati o companie";
							JOptionPane.showMessageDialog(CompanyModule.this, msg);
							return;
						}
						
						Company company = getTableModel().getCompanyList().get(index);
						
						String msg = MessageFormat
								.format(
										"Sunteti sigur ca doriti sa stergeti compania \"{0}\" ?",
										company.getName());
						
						if (JOptionPane.showConfirmDialog(CompanyModule.this, msg) == JOptionPane.YES_OPTION)
						{
							getCompanyDao().delete(company);
							getTableModel().getCompanyList().remove(company);
							
							getTableCompany().tableChanged(new TableModelEvent(getTableModel(), index, index,
									TableModelEvent.ALL_COLUMNS, TableModelEvent.DELETE));
							
							getTreeCompanyOrg().setModel(null);
						}
					}
					catch (Exception ex)
					{
						ErrorUtils.showError(ex, CompanyModule.this);
					}
				}
			};
			deleteAction.putValue(Action.NAME, "Stergere companie");
			deleteAction.putValue(Action.SHORT_DESCRIPTION, "Stergere companie");
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
			Icon modifyIcon = IconsUtils.getInstance().getIcon("button.edit");

			modifyAction = new AbstractAction("", modifyIcon)
			{
				public void actionPerformed(ActionEvent e)
				{
					try
					{
						if (getTableCompany().getSelectedRow() >= 0)
						{
							int index = getTableCompany().getSelectedRow();
							
							if (index < 0)
							{
								JOptionPane.showMessageDialog(CompanyModule.this, "Selectati o companie");
								return;
							}
							
							Company company = getTableModel().getCompanyList().get(index);
							
							CompanyDialog dialog = new CompanyDialog(null);
							GuiTool.center(dialog, RiskEvalStart.riskEvalMain);
							dialog.setCompany(company);
							dialog.setVisible(true);
							
							if (dialog.isOk())
							{
								company.setName(dialog.getCompanyName());
								company.setAddress(dialog.getCompanyAddress());
								company.setPhone(dialog.getCompanyPhone());
								company.setFax(dialog.getCompanyFax());
								company.setEmail(dialog.getCompanyEmail());
								getCompanyDao().update(company);
								
								getTableCompany().tableChanged(new TableModelEvent(getTableModel(), index, index,
										TableModelEvent.ALL_COLUMNS, TableModelEvent.UPDATE));
							}
						}
					}
					catch (Exception ex)
					{
						ErrorUtils.showError(ex, CompanyModule.this);
					}
				}
			};
			modifyAction.putValue(Action.NAME, "Modificare companie");
			modifyAction.putValue(Action.SHORT_DESCRIPTION, "Modificare companie");
		}
		return modifyAction;
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
			popupMenu.addSeparator();
			popupMenu.add(getMenuItemGenWorkPlacesDoc());
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

	/**
	 * This method initializes panelCenter	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelCenter()
	{
		if (panelCenter == null)
		{
			panelCenter = new JPanel();
			panelCenter.setLayout(new BorderLayout());
			panelCenter.setBackground(SystemColor.window);
			panelCenter.add(getJSplitPane(), BorderLayout.CENTER);
		}
		return panelCenter;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel()
	{
		if (jPanel == null)
		{
			jPanel = new JPanel();
			jPanel.setLayout(new BorderLayout());
			jPanel.add(getJJToolBarBar(), BorderLayout.NORTH);
			jPanel.add(getJScrollPane1(), BorderLayout.CENTER);
		}
		return jPanel;
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
			jJToolBarBar.add(getButtonAddSection());
			jJToolBarBar.add(getButtonModifySection());
			jJToolBarBar.add(getButtonDeleteSection());
			jJToolBarBar.addSeparator();
			jJToolBarBar.add(getButtonAddSector());
			jJToolBarBar.add(getButtonModifySector());
			jJToolBarBar.add(getButtonDeleteSector());
			jJToolBarBar.addSeparator();
			jJToolBarBar.add(getButtonAddWorkPlace());
			jJToolBarBar.add(getButtonModifyWorkPlace());
			jJToolBarBar.add(getButtonDeleteWorkPlace());
			jJToolBarBar.addSeparator();
			jJToolBarBar.add(getButtonRiskSheet());
			jJToolBarBar.add(getButtonGenRiskFactorsDoc());
		}
		return jJToolBarBar;
	}

	/**
	 * This method initializes jScrollPane1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane1()
	{
		if (jScrollPane1 == null)
		{
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setViewportView(getTreeCompanyOrg());
		}
		return jScrollPane1;
	}

	/**
	 * This method initializes treeCompanyOrg	
	 * 	
	 * @return javax.swing.JTree	
	 */
	private JTree getTreeCompanyOrg()
	{
		if (treeCompanyOrg == null)
		{
			treeCompanyOrg = new JTree();
			treeCompanyOrg.setModel(null);
			treeCompanyOrg.setRowHeight(20);
			
			treeCompanyOrg.addMouseListener(new MouseAdapter()
			{
				@Override
				public void mouseReleased(MouseEvent e)
				{
					if (e.isPopupTrigger())
					{
						TreePath treePath = treeCompanyOrg.getSelectionPath();
						JPopupMenu popupMenu = null;
						
						if (treePath != null)
						{
							DefaultMutableTreeNode node = (DefaultMutableTreeNode)
								treePath.getLastPathComponent();
							Object obj = node.getUserObject();
							
							if (Company.class.isInstance(obj))
								popupMenu = getPopupMenuCompany();
							else if (Section.class.isInstance(obj))
								popupMenu = getPopupMenuSection();
							else if (Sector.class.isInstance(obj))
								popupMenu = getPopupMenuSector();
							else if (WorkPlace.class.isInstance(obj))
								popupMenu = getPopupMenuWorkPlace();
						}
						
						if (popupMenu != null)
						{
							popupMenu.show(treeCompanyOrg, e.getX(), e.getY());
						}
					}
				}
			});
		}
		return treeCompanyOrg;
	}

	/**
	 * This method initializes buttonAddSection	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getButtonAddSection()
	{
		if (buttonAddSection == null)
		{
			buttonAddSection = new JButton();
			buttonAddSection.setFocusPainted(false);
			buttonAddSection.setAction(getAddSectionAction());
			buttonAddSection.setText("");
		}
		return buttonAddSection;
	}

	/**
	 * This method initializes buttonDeleteSection	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getButtonDeleteSection()
	{
		if (buttonDeleteSection == null)
		{
			buttonDeleteSection = new JButton();
			buttonDeleteSection.setFocusPainted(false);
			buttonDeleteSection.setAction(getDeleteSectionAction());
			buttonDeleteSection.setText("");
		}
		return buttonDeleteSection;
	}

	/**
	 * This method initializes buttonAddSector	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getButtonAddSector()
	{
		if (buttonAddSector == null)
		{
			buttonAddSector = new JButton(getAddSectorAction());
			buttonAddSector.setText("");
			buttonAddSector.setFocusPainted(false);
		}
		return buttonAddSector;
	}

	/**
	 * This method initializes buttonDeleteSector	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getButtonDeleteSector()
	{
		if (buttonDeleteSector == null)
		{
			buttonDeleteSector = new JButton(getDeleteSectorAction());
			buttonDeleteSector.setText("");
			buttonDeleteSector.setFocusPainted(false);
		}
		return buttonDeleteSector;
	}

	/**
	 * This method initializes buttonAddWorkPlace	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getButtonAddWorkPlace()
	{
		if (buttonAddWorkPlace == null)
		{
			buttonAddWorkPlace = new JButton(getAddWorkPlaceAction());
			buttonAddWorkPlace.setText("");
			buttonAddWorkPlace.setFocusPainted(false);
		}
		return buttonAddWorkPlace;
	}

	/**
	 * This method initializes buttonDeleteWorkPlace	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getButtonDeleteWorkPlace()
	{
		if (buttonDeleteWorkPlace == null)
		{
			buttonDeleteWorkPlace = new JButton(getDeleteWorkPlaceAction());
			buttonDeleteWorkPlace.setText("");
			buttonDeleteWorkPlace.setFocusPainted(false);
		}
		return buttonDeleteWorkPlace;
	}

	/**
	 * @return the addSectionAction
	 */
	public Action getAddSectionAction()
	{
		if (addSectionAction == null)
		{
			Icon addIcon = IconsUtils.getInstance().getIcon("button.add.section");

			addSectionAction = new AbstractAction("", addIcon)
			{
				public void actionPerformed(ActionEvent e)
				{
					try
					{
						TreePath treePath = getTreeCompanyOrg().getSelectionPath();
						DefaultMutableTreeNode parentNode = null;
						
						if (treePath != null)
							parentNode = (DefaultMutableTreeNode) treePath.getLastPathComponent();
	
						// company object must be selected as parent node
						if ((parentNode == null) || (!Company.class.isInstance(parentNode.getUserObject())))
						{
							JOptionPane
									.showMessageDialog(CompanyModule.this,
											"Pentru a adauga o sectie, selectati compania.");
							return;
						}
	
						Company company = (Company) parentNode.getUserObject();
	
						// display the dialog
						SectionDialog dialog = new SectionDialog(null);
						dialog.setCompany(company);
						dialog.setVisible(true);
	
						if (dialog.isOk())
						{
							// ok pressed
							Section newSection = new Section();
							newSection.setName(dialog.getName());
							newSection.setCompany(company);
	
							// store the section
							getSectionDao().store(newSection);
	
							// add the section to the companies list
							company.getSections().add(newSection);
							// add section into the tree model
							addSectionToTreeModel(newSection, parentNode);
						}
					}
					catch (Exception ex)
					{
						ErrorUtils.showError(ex, CompanyModule.this);
					}
				}
			};
			addSectionAction.putValue(Action.NAME, "Adaugare sectie");
			addSectionAction.putValue(Action.SHORT_DESCRIPTION, "Adaugare sectie");
		}
			
		return addSectionAction;
	}
	
	/**
	 * @return the addSectionAction
	 */
	public Action getModifySectionAction()
	{
		if (modifySectionAction == null)
		{
			Icon addIcon = IconsUtils.getInstance().getIcon("button.edit.section");

			modifySectionAction = new AbstractAction("Modificare sectie", addIcon)
			{
				public void actionPerformed(ActionEvent e)
				{
					try
					{
						TreePath treePath = getTreeCompanyOrg().getSelectionPath();
						DefaultMutableTreeNode parentNode = null;
						
						if (treePath != null)
							parentNode = (DefaultMutableTreeNode) treePath.getLastPathComponent();
	
						// company object must be selected as parent node
						if ((parentNode == null) || (!Section.class.isInstance(parentNode.getUserObject())))
						{
							JOptionPane
									.showMessageDialog(CompanyModule.this,
											"Selectati o sectie.");
							return;
						}
	
						Section section = (Section) parentNode.getUserObject();
						Company company = section.getCompany();
	
						// display the dialog
						SectionDialog dialog = new SectionDialog(null);
						dialog.setCompany(company);
						dialog.setSection(section);
						dialog.setVisible(true);
	
						if (dialog.isOk())
						{
							// ok pressed
							section.setName(dialog.getName());
	
							// update the section
							getSectionDao().update(section);
							
							getTreeCompanyOrg().updateUI();
						}
					}
					catch (Exception ex)
					{
						ErrorUtils.showError(ex, CompanyModule.this);
					}
				}
			};
			modifySectionAction.putValue(Action.SHORT_DESCRIPTION, "Modificare sectie");
		}
			
		return modifySectionAction;
	}


	protected SectionDao getSectionDao()
	{
		if (sectionDao == null)
			sectionDao = new SectionDaoHibernate();
		return sectionDao;
	}

	protected void addSectionToTreeModel(Section newSection, DefaultMutableTreeNode parent)
	{
		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(newSection);
		DefaultTreeModel treeModel = (DefaultTreeModel) getTreeCompanyOrg().getModel();
		treeModel.insertNodeInto(newNode, parent, parent.getChildCount());
		TreePath treePath = getTreeCompanyOrg().getSelectionPath();
		treePath = treePath.pathByAddingChild(newNode);
		getTreeCompanyOrg().scrollPathToVisible(treePath);
		getTreeCompanyOrg().setSelectionPath(treePath);
	}

	/**
	 * @return the addSectorAction
	 */
	public Action getAddSectorAction()
	{
		if (addSectorAction == null)
		{
			Icon addIcon = IconsUtils.getInstance().getIcon("button.add.sector");

			addSectorAction = new AbstractAction("", addIcon)
			{

				public void actionPerformed(ActionEvent e)
				{
					try
					{
						TreePath treePath = getTreeCompanyOrg().getSelectionPath();
						DefaultMutableTreeNode parentNode = null;
						
						if (treePath != null)
							parentNode = (DefaultMutableTreeNode) treePath.getLastPathComponent();
	
						// section object must be selected as parent node
						if ((parentNode == null) || (!Section.class.isInstance(parentNode.getUserObject())))
						{
							JOptionPane
									.showMessageDialog(CompanyModule.this,
											"Pentru a adauga un sector, selectati o sectie.");
							return;
						}
	
						Section section = (Section) parentNode.getUserObject();
	
						// display the dialog
						SectorDialog dialog = new SectorDialog(null);
						dialog.setParentSection(section);
						dialog.setVisible(true);
	
						if (dialog.isOk())
						{
							// ok pressed
							Sector newSector = new Sector();
							newSector.setName(dialog.getSectorName());
							newSector.setSection(section);
	
							// store the sector
							getSectorDao().store(newSector);
	
							// add the sector
							section.getSectors().add(newSector);
							// add sector into the tree model
							addSectorToTreeModel(newSector, parentNode);
						}
					}
					catch (Exception ex)
					{
						ErrorUtils.showError(ex, CompanyModule.this);
					}
				}
			};
			addSectorAction.putValue(Action.NAME, "Adaugare sector");
			addSectorAction.putValue(Action.SHORT_DESCRIPTION, "Adaugare sector");
		}
			
		return addSectorAction;
	}

	public Action getModifySectorAction()
	{
		if (modifySectorAction == null)
		{
			Icon addIcon = IconsUtils.getInstance().getIcon("button.edit.sector");

			modifySectorAction = new AbstractAction("Modificare sector", addIcon)
			{

				public void actionPerformed(ActionEvent e)
				{
					try
					{
						TreePath treePath = getTreeCompanyOrg().getSelectionPath();
						DefaultMutableTreeNode parentNode = null;
						
						if (treePath != null)
							parentNode = (DefaultMutableTreeNode) treePath.getLastPathComponent();
	
						// section object must be selected as parent node
						if ((parentNode == null) || (!Sector.class.isInstance(parentNode.getUserObject())))
						{
							JOptionPane
									.showMessageDialog(CompanyModule.this,
											"Selectati un sector.");
							return;
						}
	
						Sector sector = (Sector) parentNode.getUserObject();
	
						// display the dialog
						SectorDialog dialog = new SectorDialog(null);
						dialog.setParentSection(sector.getSection());
						dialog.setSector(sector);
						dialog.setVisible(true);
	
						if (dialog.isOk())
						{
							// ok pressed
							sector.setName(dialog.getSectorName());
	
							// update the sector
							getSectorDao().update(sector);
							
							getTreeCompanyOrg().updateUI();
						}
					}
					catch (Exception ex)
					{
						ErrorUtils.showError(ex, CompanyModule.this);
					}
				}
			};
			modifySectorAction.putValue(Action.SHORT_DESCRIPTION, "Modificare sector");
		}
			
		return modifySectorAction;
	}

	protected void addSectorToTreeModel(Sector newSector, DefaultMutableTreeNode parent)
	{
		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(newSector);
		DefaultTreeModel treeModel = (DefaultTreeModel) getTreeCompanyOrg().getModel();
		treeModel.insertNodeInto(newNode, parent, parent.getChildCount());
		TreePath treePath = getTreeCompanyOrg().getSelectionPath();
		treePath = treePath.pathByAddingChild(newNode);
		getTreeCompanyOrg().scrollPathToVisible(treePath);
		getTreeCompanyOrg().setSelectionPath(treePath);
	}

	/**
	 * @return the addWorkPlaceAction
	 */
	public Action getAddWorkPlaceAction()
	{
		if (addWorkPlaceAction == null)
		{
			Icon addIcon = IconsUtils.getInstance().getIcon("button.add.workplace");

			addWorkPlaceAction = new AbstractAction("", addIcon)
			{

				public void actionPerformed(ActionEvent e)
				{
					try
					{
						TreePath treePath = getTreeCompanyOrg().getSelectionPath();
						DefaultMutableTreeNode parentNode = null;
						
						if (treePath != null)
							parentNode = (DefaultMutableTreeNode) treePath.getLastPathComponent();
	
						// work place object must be selected as parent node
						if ((parentNode == null) || (!Sector.class.isInstance(parentNode.getUserObject())))
						{
							JOptionPane
									.showMessageDialog(CompanyModule.this,
											"Pentru a adauga un post de lucru, selectati un sector.");
							return;
						}
	
						Sector sector = (Sector) parentNode.getUserObject();
	
						// display the dialog
						WorkPlaceDialog dialog = new WorkPlaceDialog(null);
						dialog.setParentSector(sector);
						dialog.setVisible(true);
	
						if (dialog.isOk())
						{
							// ok pressed
							WorkPlace newWorkPlace = new WorkPlace();
							newWorkPlace.setName(dialog.getWorkPlaceName());
							newWorkPlace.setPersonsNo(dialog.getPersonsNo());
							newWorkPlace.setWorkingHours(dialog.getWorkingHours());
							newWorkPlace.setOperations(dialog.getOperations());
							newWorkPlace.setEvaluationTeam(dialog.getEvaluationTeam());
							newWorkPlace.setSector(sector);
	
							// store the work place
							getWorkPlaceDao().store(newWorkPlace);
	
							// add the work place
							sector.getWorkPlaces().add(newWorkPlace);
							// add sector into the tree model
							addWorkPlaceToTreeModel(newWorkPlace, parentNode);
						}
					}
					catch (Exception ex)
					{
						ErrorUtils.showError(ex, CompanyModule.this);
					}
				}
			};
			addWorkPlaceAction.putValue(Action.NAME, "Adaugare post lucru");
			addWorkPlaceAction.putValue(Action.SHORT_DESCRIPTION, "Adaugare post lucru");
		}
			
		return addWorkPlaceAction;
	}
	
	public Action getModifyWorkPlaceAction()
	{
		if (modifyWorkPlaceAction == null)
		{
			Icon addIcon = IconsUtils.getInstance().getIcon("button.edit.workplace");

			modifyWorkPlaceAction = new AbstractAction("Modificare post lucru", addIcon)
			{

				public void actionPerformed(ActionEvent e)
				{
					try
					{
						TreePath treePath = getTreeCompanyOrg().getSelectionPath();
						DefaultMutableTreeNode parentNode = null;
						
						if (treePath != null)
							parentNode = (DefaultMutableTreeNode) treePath.getLastPathComponent();
	
						// work place object must be selected
						if ((parentNode == null) || (!WorkPlace.class.isInstance(parentNode.getUserObject())))
						{
							JOptionPane
									.showMessageDialog(CompanyModule.this,
											"Selectati un post de lucru.");
							return;
						}
	
						WorkPlace workPlace = (WorkPlace) parentNode.getUserObject();
	
						// display the dialog
						WorkPlaceDialog dialog = new WorkPlaceDialog(null);
						dialog.setParentSector(workPlace.getSector());
						dialog.setWorkPlace(workPlace);
						dialog.setVisible(true);
	
						if (dialog.isOk())
						{
							// ok pressed
							workPlace.setName(dialog.getWorkPlaceName());
							workPlace.setPersonsNo(dialog.getPersonsNo());
							workPlace.setOperations(dialog.getOperations());
							workPlace.setEvaluationTeam(dialog.getEvaluationTeam());
							workPlace.setWorkingHours(dialog.getWorkingHours());
	
							// store the work place
							getWorkPlaceDao().update(workPlace);
							
							getTreeCompanyOrg().updateUI();
						}
					}
					catch (Exception ex)
					{
						ErrorUtils.showError(ex, CompanyModule.this);
					}
				}
			};
			modifyWorkPlaceAction.putValue(Action.SHORT_DESCRIPTION, "Modificare post lucru");
		}
			
		return modifyWorkPlaceAction;
	}

	protected void addWorkPlaceToTreeModel(WorkPlace newWorkPlace, DefaultMutableTreeNode parent)
	{
		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(newWorkPlace);
		DefaultTreeModel treeModel = (DefaultTreeModel) getTreeCompanyOrg().getModel();
		treeModel.insertNodeInto(newNode, parent, parent.getChildCount());
		TreePath treePath = getTreeCompanyOrg().getSelectionPath();
		treePath = treePath.pathByAddingChild(newNode);
		getTreeCompanyOrg().scrollPathToVisible(treePath);
		getTreeCompanyOrg().setSelectionPath(treePath);
	}

	/**
	 * @return the deleteSectionAction
	 */
	public Action getDeleteSectionAction()
	{
		if (deleteSectionAction == null)
		{
			Icon addIcon = IconsUtils.getInstance().getIcon("button.delete.section");

			deleteSectionAction = new AbstractAction("", addIcon)
			{

				public void actionPerformed(ActionEvent e)
				{
					try
					{
						// a section must be selected
						TreePath treePath = getTreeCompanyOrg().getSelectionPath();
						DefaultMutableTreeNode node = null;
						
						if (treePath != null)
							node = (DefaultMutableTreeNode) treePath.getLastPathComponent();
						
						if ((node == null) || (!Section.class.isInstance(node.getUserObject())))
						{
							JOptionPane.showMessageDialog(CompanyModule.this, "Selectati o sectie.");
							return;
						}
						
						Section section = (Section) node.getUserObject();
						
						String msg = MessageFormat
								.format(
										"Sunteti sigur ca doriti sa stergeti sectia \"{0}\" ?",
										section.getName());
				
						if (JOptionPane.showConfirmDialog(CompanyModule.this, msg) == JOptionPane.YES_OPTION)
						{
							// delete from database
							getSectionDao().delete(section);
	
							// delete from tree
							DefaultTreeModel treeModel = (DefaultTreeModel) getTreeCompanyOrg().getModel();
							treeModel.removeNodeFromParent(node);
	
							// delete from domain
							section.getCompany().getSections().remove(section);
						}
					}
					catch (Exception ex)
					{
						ErrorUtils.showError(ex, CompanyModule.this);
					}
				}
			};
			deleteSectionAction.putValue(Action.NAME, "Stergere sectie");
			deleteSectionAction.putValue(Action.SHORT_DESCRIPTION, "Stergere sectie");
		}
			
		return deleteSectionAction;
	}

	/**
	 * @return the deleteSectorAction
	 */
	public Action getDeleteSectorAction()
	{
		if (deleteSectorAction == null)
		{
			Icon addIcon = IconsUtils.getInstance().getIcon("button.delete.sector");

			deleteSectorAction = new AbstractAction("", addIcon)
			{

				public void actionPerformed(ActionEvent e)
				{
					try
					{
						// a sector must be selected
						TreePath treePath = getTreeCompanyOrg().getSelectionPath();
						DefaultMutableTreeNode node = null;
						
						if (treePath != null)
							node = (DefaultMutableTreeNode) treePath.getLastPathComponent();
						
						if ((node == null) || (!Sector.class.isInstance(node.getUserObject())))
						{
							JOptionPane.showMessageDialog(CompanyModule.this, "Selectati un sector.");
							return;
						}
						
						Sector sector = (Sector) node.getUserObject();
						
						String msg = MessageFormat
								.format(
										"Sunteti sigur ca doriti sa stergeti sectorul \"{0}\" ?",
										sector.getName());
				
						if (JOptionPane.showConfirmDialog(CompanyModule.this, msg) == JOptionPane.YES_OPTION)
						{
							// delete from database
							getSectorDao().delete(sector);
	
							// delete from tree
							DefaultTreeModel treeModel = (DefaultTreeModel) getTreeCompanyOrg().getModel();
							treeModel.removeNodeFromParent(node);
	
							// delete from domain
							sector.getSection().getSectors().remove(sector);
						}
					}
					catch (Exception ex)
					{
						ErrorUtils.showError(ex, CompanyModule.this);
					}
				}
			};
			deleteSectorAction.putValue(Action.NAME, "Stergere sector");
			deleteSectorAction.putValue(Action.SHORT_DESCRIPTION, "Stergere sector");
		}
			
		return deleteSectorAction;
	}

	/**
	 * @return the deleteWorkPlaceAction
	 */
	public Action getDeleteWorkPlaceAction()
	{
		if (deleteWorkPlaceAction == null)
		{
			Icon addIcon = IconsUtils.getInstance().getIcon("button.delete.workplace");

			deleteWorkPlaceAction = new AbstractAction("", addIcon)
			{

				public void actionPerformed(ActionEvent e)
				{
					try
					{
						// a work place must be selected
						TreePath treePath = getTreeCompanyOrg().getSelectionPath();
						DefaultMutableTreeNode node = null;
						
						if (treePath != null)
							node = (DefaultMutableTreeNode) treePath.getLastPathComponent();
						
						if ((node == null) || (!WorkPlace.class.isInstance(node.getUserObject())))
						{
							JOptionPane.showMessageDialog(CompanyModule.this, "Selectati un post de lucru.");
							return;
						}
						
						WorkPlace workPlace = (WorkPlace) node.getUserObject();
						
						String msg = MessageFormat
								.format(
										"Sunteti sigur ca doriti sa stergeti postul de lucru \"{0}\" ?",
										workPlace.getName());
				
						if (JOptionPane.showConfirmDialog(CompanyModule.this, msg) == JOptionPane.YES_OPTION)
						{
							// delete from database
							getWorkPlaceDao().delete(workPlace);
	
							// delete from tree
							DefaultTreeModel treeModel = (DefaultTreeModel) getTreeCompanyOrg().getModel();
							treeModel.removeNodeFromParent(node);
	
							// delete from domain
							workPlace.getSector().getWorkPlaces().remove(workPlace);
						}
					}
					catch (Exception ex)
					{
						ErrorUtils.showError(ex, CompanyModule.this);
					}
				}
			};
			deleteWorkPlaceAction.putValue(Action.NAME, "Stergere post lucru");
			deleteWorkPlaceAction.putValue(Action.SHORT_DESCRIPTION, "Stergere post lucru");
		}
			
		return deleteWorkPlaceAction;
	}

	/**
	 * @return the sectorDao
	 */
	public SectorDao getSectorDao()
	{
		if (sectorDao == null)
			sectorDao = new SectorDaoHibernate();
		return sectorDao;
	}

	/**
	 * @return the workPlaceDao
	 */
	public WorkPlaceDao getWorkPlaceDao()
	{
		if (workPlaceDao == null)
			workPlaceDao = new WorkPlaceDaoHibernate();
		return workPlaceDao;
	}

	/**
	 * This method initializes popupMenuSection	
	 * 	
	 * @return javax.swing.JPopupMenu	
	 */
	private JPopupMenu getPopupMenuSection()
	{
		if (popupMenuSection == null)
		{
			popupMenuSection = new JPopupMenu();
			popupMenuSection.add(getMenuItemAddSector());
			popupMenuSection.addSeparator();
			popupMenuSection.add(getMenuItemModifySection());
			popupMenuSection.add(getMenuItemDeleteSection());
		}
		return popupMenuSection;
	}

	/**
	 * This method initializes popupMenuSector	
	 * 	
	 * @return javax.swing.JPopupMenu	
	 */
	private JPopupMenu getPopupMenuSector()
	{
		if (popupMenuSector == null)
		{
			popupMenuSector = new JPopupMenu();
			popupMenuSector.add(getMenuItemAddWorkPlace());
			popupMenuSector.addSeparator();
			popupMenuSector.add(getMenuItemModifySector());
			popupMenuSector.add(getMenuItemDeleteSector());
		}
		return popupMenuSector;
	}

	/**
	 * This method initializes popupMenuWorkPlace	
	 * 	
	 * @return javax.swing.JPopupMenu	
	 */
	private JPopupMenu getPopupMenuWorkPlace()
	{
		if (popupMenuWorkPlace == null)
		{
			popupMenuWorkPlace = new JPopupMenu();
			popupMenuWorkPlace.add(getMenuItemModifyWorkPlace());
			popupMenuWorkPlace.add(getMenuItemDeleteWorkPlace());
			popupMenuWorkPlace.addSeparator();
			popupMenuWorkPlace.add(getMenuItemRiskSheet());
			popupMenuWorkPlace.addSeparator();
			popupMenuWorkPlace.add(getMenuItemGenerateDocuments());
		}
		return popupMenuWorkPlace;
	}

	/**
	 * This method initializes menuItemAddSector	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMenuItemAddSector()
	{
		if (menuItemAddSector == null)
		{
			menuItemAddSector = new JMenuItem(getAddSectorAction());
		}
		return menuItemAddSector;
	}

	/**
	 * This method initializes menuItemDeleteSection	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMenuItemDeleteSection()
	{
		if (menuItemDeleteSection == null)
		{
			menuItemDeleteSection = new JMenuItem(getDeleteSectionAction());
		}
		return menuItemDeleteSection;
	}

	/**
	 * This method initializes menuItemAddWorkPlace	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMenuItemAddWorkPlace()
	{
		if (menuItemAddWorkPlace == null)
		{
			menuItemAddWorkPlace = new JMenuItem(getAddWorkPlaceAction());
		}
		return menuItemAddWorkPlace;
	}

	/**
	 * This method initializes menuItemDeleteSector	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMenuItemDeleteSector()
	{
		if (menuItemDeleteSector == null)
		{
			menuItemDeleteSector = new JMenuItem(getDeleteSectorAction());
		}
		return menuItemDeleteSector;
	}

	/**
	 * This method initializes menuItemDeleteWorkPlace	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMenuItemDeleteWorkPlace()
	{
		if (menuItemDeleteWorkPlace == null)
		{
			menuItemDeleteWorkPlace = new JMenuItem(getDeleteWorkPlaceAction());
		}
		return menuItemDeleteWorkPlace;
	}

	/**
	 * This method initializes popupMenuCompany	
	 * 	
	 * @return javax.swing.JPopupMenu	
	 */
	private JPopupMenu getPopupMenuCompany()
	{
		if (popupMenuCompany == null)
		{
			popupMenuCompany = new JPopupMenu();
			popupMenuCompany.add(getMenuItemAddSection());
		}
		return popupMenuCompany;
	}

	/**
	 * This method initializes menuItemAddSection	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMenuItemAddSection()
	{
		if (menuItemAddSection == null)
		{
			menuItemAddSection = new JMenuItem(getAddSectionAction());
		}
		return menuItemAddSection;
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
			jSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
			jSplitPane.setPreferredSize(new Dimension(455, 100));
			jSplitPane.setDividerLocation(250);
			jSplitPane.setContinuousLayout(true);
			jSplitPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			jSplitPane.setBorder(null);
			jSplitPane.setBackground(SystemColor.window);
			jSplitPane.setBottomComponent(getJPanel());
			jSplitPane.setTopComponent(getJScrollPane());
		}
		return jSplitPane;
	}

	/**
	 * This method initializes buttonModifySection	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getButtonModifySection()
	{
		if (buttonModifySection == null)
		{
			buttonModifySection = new JButton();
			buttonModifySection.setFocusPainted(false);
			buttonModifySection.setAction(getModifySectionAction());
			buttonModifySection.setText("");
		}
		return buttonModifySection;
	}

	/**
	 * This method initializes buttonModifySector	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getButtonModifySector()
	{
		if (buttonModifySector == null)
		{
			buttonModifySector = new JButton();
			buttonModifySector.setFocusPainted(false);
			buttonModifySector.setAction(getModifySectorAction());
			buttonModifySector.setText("");
		}
		return buttonModifySector;
	}

	/**
	 * This method initializes buttonModifyWorkPlace	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getButtonModifyWorkPlace()
	{
		if (buttonModifyWorkPlace == null)
		{
			buttonModifyWorkPlace = new JButton();
			buttonModifyWorkPlace.setFocusPainted(false);
			buttonModifyWorkPlace.setAction(getModifyWorkPlaceAction());
			buttonModifyWorkPlace.setText("");
		}
		return buttonModifyWorkPlace;
	}

	/**
	 * This method initializes menuItemModifySection	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMenuItemModifySection()
	{
		if (menuItemModifySection == null)
		{
			menuItemModifySection = new JMenuItem();
			menuItemModifySection.setAction(getModifySectionAction());
		}
		return menuItemModifySection;
	}

	/**
	 * This method initializes menuItemModifySector	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMenuItemModifySector()
	{
		if (menuItemModifySector == null)
		{
			menuItemModifySector = new JMenuItem();
			menuItemModifySector.setAction(getModifySectorAction());
		}
		return menuItemModifySector;
	}

	/**
	 * This method initializes menuItemModifyWorkPlace	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMenuItemModifyWorkPlace()
	{
		if (menuItemModifyWorkPlace == null)
		{
			menuItemModifyWorkPlace = new JMenuItem();
			menuItemModifyWorkPlace.setAction(getModifyWorkPlaceAction());
		}
		return menuItemModifyWorkPlace;
	}

	public Action getRiskSheetsAction()
	{
		if (riskSheetsAction == null)
		{
			
			Icon icon = IconsUtils.getInstance().getIcon("button.risksheet");
			
			riskSheetsAction = new AbstractAction("Completare fisa ricuri", icon)
			{
				public void actionPerformed(ActionEvent e)
				{
					int indexCompany = getTableCompany().getSelectedRow();
					
					Company company = null;
					if (indexCompany >= 0)
						company = getCompanies().get(indexCompany);
					
					WorkPlace workPlace = getSelectedWorkPlace();
					
					if (company == null)
					{
						JOptionPane.showMessageDialog(CompanyModule.this, "Selectati o companie");
						return;
					}
					
					if (workPlace == null)
					{
						JOptionPane.showMessageDialog(CompanyModule.this,
								"Selectati un post de lucru.");
						return;
					}
					
					WorkPlacesRiskSheets dialog = new WorkPlacesRiskSheets(null);
					dialog.setCompany(company);
					dialog.setWorkPlace(workPlace);
					dialog.setVisible(true);
					getTreeCompanyOrg().updateUI();
				}
			};
			riskSheetsAction.putValue(Action.SHORT_DESCRIPTION, "Completare fisa ricuri");
		}
		return riskSheetsAction;
	}

	/**
	 * This method initializes menuItemRiskSheet	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMenuItemRiskSheet()
	{
		if (menuItemRiskSheet == null)
		{
			menuItemRiskSheet = new JMenuItem();
			menuItemRiskSheet.setAction(getRiskSheetsAction());
		}
		return menuItemRiskSheet;
	}

	public WorkPlace getSelectedWorkPlace()
	{
		TreePath treePath = getTreeCompanyOrg().getSelectionPath();
		DefaultMutableTreeNode node = null;
		
		if (treePath != null)
			node = (DefaultMutableTreeNode) treePath.getLastPathComponent();
		
		if ((node == null) || (!WorkPlace.class.isInstance(node.getUserObject())))
		{
			return null;
		}
		
		return (WorkPlace) node.getUserObject();
	}
	
	public Action getGenerateDocumentsAction()
	{
		if (generateDocumentsAction == null)
		{
			Icon icon = IconsUtils.getInstance().getIcon("button.generate.documents");
			generateDocumentsAction = new AbstractAction("Generare documente post lucru", icon)
			{
				public void actionPerformed(ActionEvent e)
				{
					WorkPlace workPlace = getSelectedWorkPlace();

					if (workPlace == null)
					{
						JOptionPane.showMessageDialog(CompanyModule.this, "Selectati un post de lucru.");
						return;
					}

					JFileChooser fc = new JFileChooser();
					fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					
					if (fc.showSaveDialog(CompanyModule.this) != JFileChooser.APPROVE_OPTION)
					{
						return;
					}
					
					try
					{
						String selectedFolderName = fc.getSelectedFile().getAbsolutePath();
						
						ProcessorEvaluationDoc processor = new ProcessorEvaluationDoc();
						processor.setResultFolderName(selectedFolderName);
						processor.setWorkPlace(workPlace);
						processor.generate();

						String msg = "Generarea documentelor s-a realizat cu succes."
								+ "\n"
								+ "Doriti sa deschideti folderul in care s-au generat??";

						if (JOptionPane.showConfirmDialog(CompanyModule.this, msg) == JOptionPane.OK_OPTION)
						{
							Runtime.getRuntime().exec("explorer.exe " +  "\"" + selectedFolderName + "\"");
						}
					} 
					catch (Exception e1)
					{
						ErrorUtils.showError(e1, CompanyModule.this);
						e1.printStackTrace();
					}
				}
			};
			generateDocumentsAction.putValue(Action.SHORT_DESCRIPTION, 
					"Generare documente post lucru");
		}
		return generateDocumentsAction;
	}

	/**
	 * This method initializes menuItemGenerateDocuments
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getMenuItemGenerateDocuments()
	{
		if (menuItemGenerateDocuments == null)
		{
			menuItemGenerateDocuments = new JMenuItem();
			menuItemGenerateDocuments.setAction(getGenerateDocumentsAction());
		}
		return menuItemGenerateDocuments;
	}

	public RiskFactorDao getRiskFactorDao()
	{
		if (riskFactorDao == null)
			riskFactorDao = new RiskFactorDaoHibernate();

		return riskFactorDao;
	}
	
	public RiskFactor getRootRiskFactor()
	{
		try
		{
			return getRiskFactorDao().findAll().get(0);
		} catch (DaoException e)
		{
			ErrorUtils.showError(e, CompanyModule.this);
			return null;
		}
	}

	/**
	 * This method initializes buttonWorkPlacesDocument	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getButtonWorkPlacesDocument()
	{
		if (buttonWorkPlacesDocument == null)
		{
			buttonWorkPlacesDocument = new JButton();
			buttonWorkPlacesDocument.setFocusPainted(false);
			buttonWorkPlacesDocument.setAction(getGenWorkPlacesDocAction());
			buttonWorkPlacesDocument.setText("");
		}
		return buttonWorkPlacesDocument;
	}

	/**
	 * Genereaa documentul sumarului punctelor de lucru pentru o companie.
	 * @return
	 */
	public Action getGenWorkPlacesDocAction()
	{
		if (genWorkPlacesDocAction == null)
		{
			Icon icon = IconsUtils.getInstance().getIcon("button.generate.workplance.doc");
			
			genWorkPlacesDocAction = new AbstractAction("Generare document sumar posturi lucru", icon)
			{
				public void actionPerformed(ActionEvent e)
				{
					int index = getTableCompany().getSelectedRow();
					
					if (index < 0)
					{
						String msg = "Selectati o companie";
						JOptionPane.showMessageDialog(CompanyModule.this, msg);
						return;
					}
					
					Company company = getTableModel().getCompanyList().get(index);
				
					JFileChooser fc = getFileChooser();
					fc.setSelectedFile(new File(company.getName() + ".rtf"));
					
					if (fc.showSaveDialog(CompanyModule.this) != JFileChooser.APPROVE_OPTION)
					{
						return;
					}
					
					try
					{
						String selectedFileName = fc.getSelectedFile().getAbsolutePath();
						
						if (fc.getSelectedFile().exists())
						{
							String msg = MessageFormat
									.format(
											"Fisierul \"{0}\" exista deja. Suprascrieti acest fisier?",
											selectedFileName);
							if (JOptionPane.showConfirmDialog(CompanyModule.this, msg) != JOptionPane.OK_OPTION)
								return;
						}
						
						ProcessorWorkPlacesTable processor = new ProcessorWorkPlacesTable();
						processor.setResultFileName(selectedFileName);
						processor.setCompany(company);
						
						processor.generate();
						
						String msg = "Generarea documentului s-a realizat cu succes."
								+ "\n"
								+ "Doriti sa deschideti documentul generat?";
						
						if (JOptionPane.showConfirmDialog(CompanyModule.this, msg) == JOptionPane.OK_OPTION)
						{
							Runtime.getRuntime().exec("cmd /c \"" + selectedFileName + "\"");
						}
					} 
					catch (Exception e1)
					{
						e1.printStackTrace();
					}
				}
			};
			
			genWorkPlacesDocAction.putValue(Action.SHORT_DESCRIPTION,
					"Generare document sumar posturi lucru");		
		}
		return genWorkPlacesDocAction;
	}

	public JFileChooser getFileChooser()
	{
		if (fileChooser == null)
		{
			fileChooser = new JFileChooser();
			
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

			fileChooser.addChoosableFileFilter(new FileFilter()
			{

				public boolean accept(File f)
				{
					return f.isDirectory() || f.getName().endsWith(".rtf");
				}

				public String getDescription()
				{
					return "RTF Files";
				}
			});

		}
		return fileChooser;
	}

	/**
	 * This method initializes buttonGenRiskFactorsDoc	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getButtonGenRiskFactorsDoc()
	{
		if (buttonGenRiskFactorsDoc == null)
		{
			buttonGenRiskFactorsDoc = new JButton();
			buttonGenRiskFactorsDoc.setFocusPainted(false);
			buttonGenRiskFactorsDoc.setAction(getGenerateDocumentsAction());
			buttonGenRiskFactorsDoc.setText("");
		}
		return buttonGenRiskFactorsDoc;
	}

	/**
	 * This method initializes menuItemGenWorkPlacesDoc	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMenuItemGenWorkPlacesDoc()
	{
		if (menuItemGenWorkPlacesDoc == null)
		{
			menuItemGenWorkPlacesDoc = new JMenuItem();
			menuItemGenWorkPlacesDoc.setAction(getGenWorkPlacesDocAction());
		}
		return menuItemGenWorkPlacesDoc;
	}

	/**
	 * This method initializes buttonRiskSheet	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getButtonRiskSheet()
	{
		if (buttonRiskSheet == null)
		{
			buttonRiskSheet = new JButton();
			buttonRiskSheet.setFocusPainted(false);
			buttonRiskSheet.setAction(getRiskSheetsAction());
			buttonRiskSheet.setText("");
		}
		return buttonRiskSheet;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
