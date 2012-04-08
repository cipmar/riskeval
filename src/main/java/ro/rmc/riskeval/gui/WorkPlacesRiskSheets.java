package ro.rmc.riskeval.gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import ro.rmc.riskeval.dao.ConsequenceDao;
import ro.rmc.riskeval.dao.DaoException;
import ro.rmc.riskeval.dao.GravityDao;
import ro.rmc.riskeval.dao.ProbabilityDao;
import ro.rmc.riskeval.dao.RiskFactorDao;
import ro.rmc.riskeval.dao.RiskLevelDao;
import ro.rmc.riskeval.dao.RiskSheetDao;
import ro.rmc.riskeval.domain.ClgCouple;
import ro.rmc.riskeval.domain.Company;
import ro.rmc.riskeval.domain.Consequence;
import ro.rmc.riskeval.domain.GpCouple;
import ro.rmc.riskeval.domain.Gravity;
import ro.rmc.riskeval.domain.Probability;
import ro.rmc.riskeval.domain.RiskFactor;
import ro.rmc.riskeval.domain.RiskLevel;
import ro.rmc.riskeval.domain.RiskSheet;
import ro.rmc.riskeval.domain.RiskSheetConsequence;
import ro.rmc.riskeval.domain.WorkPlace;
import ro.rmc.riskeval.gui.tablemodels.RiskSheetConsequenceTableModel;
import ro.rmc.riskeval.gui.tablemodels.TreeRiskFactorCellRenderer;
import ro.rmc.riskeval.hibernate.ConsequenceDaoHibernate;
import ro.rmc.riskeval.hibernate.GravityDaoHibernate;
import ro.rmc.riskeval.hibernate.ProbabilityDaoHibernate;
import ro.rmc.riskeval.hibernate.RiskFactorDaoHibernate;
import ro.rmc.riskeval.hibernate.RiskLevelDaoHibernate;
import ro.rmc.riskeval.hibernate.RiskSheetDaoHibernate;
import ro.rmc.riskeval.utils.ErrorUtils;
import ro.rmc.riskeval.utils.IconsUtils;

public class WorkPlacesRiskSheets extends JDialog
{
	// lists
	private List<RiskFactor> riskFactorList;  
	private WorkPlace workPlace; 
	private List<Gravity> gravityList;
	private List<Probability> probabilityList;
	private List<RiskLevel> riskLevelList;
	private List<Consequence> consequenceList;
	
	// dao
	private RiskFactorDao riskFactorDao;  //  @jve:decl-index=0:
	private GravityDao gravityDao;
	private ProbabilityDao probabilityDao;
	private RiskLevelDao riskLevelDao;
	private RiskSheetDao riskSheetDao;  //  @jve:decl-index=0:
	private ConsequenceDao consequenceDao;  //  @jve:decl-index=0:
	
	// other fields
	RiskSheet currentRiskSheet;
	RiskFactor currentRiskFactor;
	
	// actions
	private Action addRiskSheetAction;  //  @jve:decl-index=0:
	private Action editRiskSheetAction;
	private Action deleteRiskSheetAction;
	private Action saveRiskSheetAction;  //  @jve:decl-index=0:
	private Action abortRiskSheetAction;
	private Action addConsequenceAction;
	private Action deleteConsequenceAction;

	// GUI fields
	private JPanel jContentPane = null;

	private DefaultTreeModel treeModel = null; 

	private JScrollPane jScrollPane = null;

	private JTree treeRiskFactor = null;

	private JPanel panelEast = null;

	private JTextPane textPaneManifestation = null;

	private JScrollPane jScrollPane1 = null;

	private JLabel jLabel2 = null;

	private JScrollPane jScrollPane2 = null;

	private JTable tableConsequences = null;

	private JLabel jLabel3 = null;

	private JScrollPane jScrollPane3 = null;

	private JTextPane TextPaneMeasures = null;

	private JScrollPane jScrollPane4 = null;

	private JScrollPane jScrollPane5 = null;

	private JLabel jLabel4 = null;

	private JLabel jLabel5 = null;

	private JTextPane textPaneCompetence = null;

	private JTextPane textPaneTerms = null;

	private JLabel jLabel6 = null;

	private JLabel jLabel7 = null;

	private JLabel jLabel8 = null;

	private JComboBox comboBoxGravity = null;

	private JComboBox comboBoxProbability = null;

	private JComboBox comboBoxRiskLevel = null;

	private JLabel jLabel9 = null;
	private JPopupMenu popupMenu = null;  //  @jve:decl-index=0:visual-constraint="283,722"
	private JMenuItem menuItemAddRiskSheet = null;
	private JMenuItem menuItemDeleteRiskSheet = null;
	private JMenuItem menuItemEditRiskSheet = null;
	private JPanel jPanel = null;
	private JPanel jPanel1 = null;
	private JLabel jLabel = null;
	private JTextField textFieldCompany = null;
	private JLabel jLabel1 = null;
	private JTextField textFieldWorkPlace = null;
	private JToolBar jJToolBarBar = null;
	private JButton buttonSave = null;
	private JButton buttonCancel = null;
	private JButton buttonAdd = null;
	private JButton buttonEdit = null;
	private JButton buttonDelete = null;
	private JToolBar jJToolBarBar1 = null;
	private JButton buttonAddConsequence = null;
	private JButton buttonDeleteConsequence = null;
	private JLabel labelRiskLevel1 = null;
	private JLabel labelRiskLevel = null;

	/**
	 * @param owner
	 */
	public WorkPlacesRiskSheets(Frame owner)
	{
		super(owner);
		initialize();
		GuiTool.center(this, owner);
		setRiskSheetRO(true);
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize()
	{
		this.setSize(970, 690);
		this.setPreferredSize(new Dimension(970, 730));
		this.setContentPane(getJContentPane());
		this.setModal(true);
		this.setTitle("Fise risc post lucru");
		this.setContentPane(getJContentPane());
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
			jContentPane.add(getPanelEast(), BorderLayout.EAST);
			jContentPane.add(getJPanel(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	public RiskFactorDao getRiskFactorDao()
	{
		if (riskFactorDao == null)
			riskFactorDao = new RiskFactorDaoHibernate();

		return riskFactorDao;
	}

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

	public DefaultTreeModel getTreeModel()
	{
		if (treeModel  == null)
		{
			RiskFactor riskFactor = null;
			
			if (getRiskFactorList().size() > 0)
				riskFactor = getRiskFactorList().get(0);
			
			DefaultMutableTreeNode root = new DefaultMutableTreeNode(riskFactor);
			addRiskFactorToTree(riskFactor, root);
			treeModel = new DefaultTreeModel(root);
		}
		return treeModel;
	}

	public List<RiskFactor> getRiskFactorList()
	{
		if (riskFactorList == null)
			try
			{
				riskFactorList = getRiskFactorDao().findAll();
			} catch (DaoException e)
			{
				ErrorUtils.showError(e, WorkPlacesRiskSheets.this);
			}
		return riskFactorList;
	}

	public void setCompany(Company company)
	{
		getTextFieldCompany().setText(company.getName());
	}

	public void setWorkPlace(WorkPlace workPlace)
	{
		this.workPlace = workPlace;
		
		getTextFieldWorkPlace().setText(workPlace.getName());
		TreeRiskFactorCellRenderer cellRenderer = (TreeRiskFactorCellRenderer) getTreeRiskFactor()
				.getCellRenderer();
		cellRenderer.setWorkPlace(workPlace);
		updateLabelRiskLevel();
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
			jScrollPane.setPreferredSize(new Dimension(300, 400));
			jScrollPane.setViewportView(getTreeRiskFactor());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes treeRiskFactor	
	 * 	
	 * @return javax.swing.JTree	
	 */
	private JTree getTreeRiskFactor()
	{
		if (treeRiskFactor == null)
		{
			treeRiskFactor = new JTree(getTreeModel());
			
			treeRiskFactor.setRowHeight(20);
			
			treeRiskFactor.addMouseListener(new MouseAdapter()
			{
				public void mouseReleased(MouseEvent e)
				{
					if (e.isPopupTrigger())
					{
						getPopupMenu().show(e.getComponent(), e.getX(), e.getY());
					}
				}
			});
			
			treeRiskFactor.getSelectionModel().addTreeSelectionListener(
					new TreeSelectionListener()
					{
						public void valueChanged(TreeSelectionEvent e)
						{
							RiskFactor riskFactor = null;
							RiskSheet riskSheet = null;
							TreePath treePath = treeRiskFactor.getSelectionPath();
							
							if (treePath != null)
							{
								DefaultMutableTreeNode node = (DefaultMutableTreeNode) treePath
										.getLastPathComponent();
								
								riskFactor = (RiskFactor) node.getUserObject();
								riskSheet = workPlace.getRiskSheet(riskFactor);
								
								currentRiskSheet = riskSheet;
								currentRiskFactor = riskFactor;
							} else
							{
								currentRiskSheet = null;
								currentRiskFactor = null;
							}	
								
							if (riskSheet == null)
							{
								// nu am fisa de risc pentru factorul de risc selectat
								setRiskSheetEmpty();
								setRiskSheetRO(true);
							} else
							{
								// am fisa de risc pentru factorul de risc selectat
								displayRiskSheet(riskSheet);
								setRiskSheetRO(true);
							}
						}
					});
			
			// renderer
			treeRiskFactor.setCellRenderer(new TreeRiskFactorCellRenderer());
			
			treeRiskFactor.setRootVisible(false);
		}
		return treeRiskFactor;
	}

	/**
	 * This method initializes panelEast	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelEast()
	{
		if (panelEast == null)
		{
			jLabel9 = new JLabel();
			jLabel9.setBounds(new Rectangle(15, 330, 181, 16));
			jLabel9.setText("Masuri propuse");
			jLabel8 = new JLabel();
			jLabel8.setBounds(new Rectangle(390, 285, 70, 16));
			jLabel8.setText("Nivel de risc");
			jLabel7 = new JLabel();
			jLabel7.setBounds(new Rectangle(240, 285, 76, 16));
			jLabel7.setText("Probabilitate");
			jLabel6 = new JLabel();
			jLabel6.setBounds(new Rectangle(15, 285, 76, 16));
			jLabel6.setText("Gravitate");
			jLabel5 = new JLabel();
			jLabel5.setBounds(new Rectangle(270, 495, 181, 16));
			jLabel5.setText("Termene");
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(15, 495, 181, 16));
			jLabel4.setText("Competente");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(15, 150, 211, 16));
			jLabel3.setText("Consecinte posibile");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(15, 30, 211, 16));
			jLabel2.setText("Forma concreta de manifestare");
			panelEast = new JPanel();
			panelEast.setLayout(null);
			panelEast.setPreferredSize(new Dimension(550, 0));
			panelEast.add(getJScrollPane1(), null);
			panelEast.add(jLabel2, null);
			panelEast.add(getJScrollPane2(), null);
			panelEast.add(jLabel3, null);
			panelEast.add(getJScrollPane3(), null);
			panelEast.add(getJScrollPane4(), null);
			panelEast.add(getJScrollPane5(), null);
			panelEast.add(jLabel4, null);
			panelEast.add(jLabel5, null);
			panelEast.add(jLabel6, null);
			panelEast.add(jLabel7, null);
			panelEast.add(jLabel8, null);
			panelEast.add(getComboBoxGravity(), null);
			panelEast.add(getComboBoxProbability(), null);
			panelEast.add(getComboBoxRiskLevel(), null);
			panelEast.add(jLabel9, null);
			panelEast.add(getJJToolBarBar(), null);
			panelEast.add(getJJToolBarBar1(), null);
		}
		return panelEast;
	}

	/**
	 * This method initializes textPaneManifestation	
	 * 	
	 * @return javax.swing.JTextPane	
	 */
	private JTextPane getTextPaneManifestation()
	{
		if (textPaneManifestation == null)
		{
			textPaneManifestation = new JTextPane();
			textPaneManifestation.setDisabledTextColor(SystemColor.textText);
			textPaneManifestation.setFont(new Font("Arial", Font.PLAIN, 12));
		}
		return textPaneManifestation;
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
			jScrollPane1.setBounds(new Rectangle(15, 45, 511, 106));
			jScrollPane1.setViewportView(getTextPaneManifestation());
		}
		return jScrollPane1;
	}

	/**
	 * This method initializes jScrollPane2	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane2()
	{
		if (jScrollPane2 == null)
		{
			jScrollPane2 = new JScrollPane();
			jScrollPane2.setBounds(new Rectangle(15, 195, 511, 76));
			jScrollPane2.setViewportView(getTableConsequences());
		}
		return jScrollPane2;
	}

	/**
	 * This method initializes tableConsequences	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getTableConsequences()
	{
		if (tableConsequences == null)
		{
			tableConsequences = new JTable();
			tableConsequences.setFont(new Font("Arial", Font.PLAIN, 12));
			tableConsequences.setGridColor(Color.LIGHT_GRAY);
		}
		return tableConsequences;
	}

	/**
	 * This method initializes jScrollPane3	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane3()
	{
		if (jScrollPane3 == null)
		{
			jScrollPane3 = new JScrollPane();
			jScrollPane3.setBounds(new Rectangle(15, 345, 511, 136));
			jScrollPane3.setViewportView(getTextPaneMeasures());
		}
		return jScrollPane3;
	}

	/**
	 * This method initializes TextPaneMeasures	
	 * 	
	 * @return javax.swing.JTextPane	
	 */
	private JTextPane getTextPaneMeasures()
	{
		if (TextPaneMeasures == null)
		{
			TextPaneMeasures = new JTextPane();
			TextPaneMeasures.setDisabledTextColor(SystemColor.textText);
			TextPaneMeasures.setFont(new Font("Arial", Font.PLAIN, 12));
		}
		return TextPaneMeasures;
	}

	/**
	 * This method initializes jScrollPane4	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane4()
	{
		if (jScrollPane4 == null)
		{
			jScrollPane4 = new JScrollPane();
			jScrollPane4.setBounds(new Rectangle(15, 510, 241, 136));
			jScrollPane4.setViewportView(getTextPaneCompetence());
		}
		return jScrollPane4;
	}

	/**
	 * This method initializes jScrollPane5	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane5()
	{
		if (jScrollPane5 == null)
		{
			jScrollPane5 = new JScrollPane();
			jScrollPane5.setBounds(new Rectangle(270, 510, 256, 136));
			jScrollPane5.setViewportView(getTextPaneTerms());
		}
		return jScrollPane5;
	}

	/**
	 * This method initializes textPaneCompetence	
	 * 	
	 * @return javax.swing.JTextPane	
	 */
	private JTextPane getTextPaneCompetence()
	{
		if (textPaneCompetence == null)
		{
			textPaneCompetence = new JTextPane();
			textPaneCompetence.setDisabledTextColor(SystemColor.textText);
			textPaneCompetence.setFont(new Font("Arial", Font.PLAIN, 12));
		}
		return textPaneCompetence;
	}

	/**
	 * This method initializes textPaneTerms	
	 * 	
	 * @return javax.swing.JTextPane	
	 */
	private JTextPane getTextPaneTerms()
	{
		if (textPaneTerms == null)
		{
			textPaneTerms = new JTextPane();
			textPaneTerms.setCursor(new Cursor(Cursor.TEXT_CURSOR));
			textPaneTerms.setFont(new Font("Arial", Font.PLAIN, 12));
			textPaneTerms.setDisabledTextColor(SystemColor.textText);
		}
		return textPaneTerms;
	}

	/**
	 * This method initializes comboBoxGravity	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getComboBoxGravity()
	{
		if (comboBoxGravity == null)
		{
			comboBoxGravity = new JComboBox();
			comboBoxGravity.setBounds(new Rectangle(15, 300, 211, 25));
			comboBoxGravity.setFont(new Font("Arial", Font.BOLD, 12));
			comboBoxGravity.setModel(new DefaultComboBoxModel(getGravityList().toArray()));
			comboBoxGravity.setSelectedItem(null);
			
			comboBoxGravity.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					Gravity gravity = (Gravity) getComboBoxGravity().getSelectedItem();
					Probability probability = (Probability) getComboBoxProbability().getSelectedItem();
					RiskLevel riskLevel = getRiskLevel(gravity, probability);
					getComboBoxRiskLevel().setSelectedItem(riskLevel);
				}
			});
		}
		return comboBoxGravity;
	}

	/**
	 * This method initializes comboBoxProbability	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getComboBoxProbability()
	{
		if (comboBoxProbability == null)
		{
			comboBoxProbability = new JComboBox();
			comboBoxProbability.setBounds(new Rectangle(240, 300, 136, 25));
			comboBoxProbability.setFont(new Font("Arial", Font.BOLD, 12));
			comboBoxProbability.setModel(new DefaultComboBoxModel(getProbabilityList().toArray()));
			comboBoxProbability.setSelectedItem(null);
			
			comboBoxProbability.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					Gravity gravity = (Gravity) getComboBoxGravity().getSelectedItem();
					Probability probability = (Probability) getComboBoxProbability().getSelectedItem();
					RiskLevel riskLevel = getRiskLevel(gravity, probability);
					if (riskLevel != null)
						getComboBoxRiskLevel().setSelectedItem(riskLevel);
				}
			});
		}
		return comboBoxProbability;
	}

	/**
	 * This method initializes comboBoxRiskLevel	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getComboBoxRiskLevel()
	{
		if (comboBoxRiskLevel == null)
		{
			comboBoxRiskLevel = new JComboBox();
			comboBoxRiskLevel.setBounds(new Rectangle(390, 300, 136, 25));
			comboBoxRiskLevel.setFont(new Font("Arial", Font.BOLD, 12));
			comboBoxRiskLevel.setModel(new DefaultComboBoxModel(getRiskLevelList().toArray()));
			comboBoxRiskLevel.setSelectedItem(null);
		}
		return comboBoxRiskLevel;
	}

	public List<Gravity> getGravityList()
	{
		if (gravityList == null)
			try
			{
				gravityList = getGravityDao().findAll();
			} catch (DaoException e)
			{
				ErrorUtils.showError(e, WorkPlacesRiskSheets.this);
			}
		return gravityList;
	}

	public List<Probability> getProbabilityList()
	{
		if (probabilityList == null)
			try
			{
				probabilityList = getProbabilityDao().findAll();
			} catch (DaoException e)
			{
				ErrorUtils.showError(e, WorkPlacesRiskSheets.this);
			}
		return probabilityList;
	}

	public List<RiskLevel> getRiskLevelList()
	{
		if (riskLevelList == null)
			try
			{
				riskLevelList = getRiskLevelDao().findAll();
			} catch (DaoException e)
			{
				ErrorUtils.showError(e, WorkPlacesRiskSheets.this);
			}
		return riskLevelList;
	}

	public GravityDao getGravityDao()
	{
		if (gravityDao == null)
			gravityDao = new GravityDaoHibernate();
		return gravityDao;
	}

	public ProbabilityDao getProbabilityDao()
	{
		if (probabilityDao == null)
			probabilityDao = new ProbabilityDaoHibernate();
			return probabilityDao;
	}

	public RiskLevelDao getRiskLevelDao()
	{
		if (riskLevelDao == null)
			riskLevelDao = new RiskLevelDaoHibernate();
		return riskLevelDao;
	}

	/**
	 * 
	 * @param gravity
	 * @param probability
	 */
	private RiskLevel getRiskLevel(Gravity gravity, Probability probability)
	{
		for (Iterator iter = getRiskLevelList().iterator(); iter.hasNext();)
		{
			RiskLevel riskLevel = (RiskLevel) iter.next();

			for (Iterator iterator = riskLevel.getGpCouples().iterator(); iterator.hasNext();)
			{
				GpCouple gpCouple = (GpCouple) iterator.next();
				if ((gpCouple.getGravity() == gravity)
						&& (gpCouple.getProbability() == probability))
					return riskLevel;
			}
		}
		return null;
	}
	
	/**
	 * Afiseaza in partea dreapta detalii despe fisa de risc
	 * pentru factorul de risc selectat.
	 * 
	 * @param riskSheet
	 */
	private void displayRiskSheet(RiskSheet riskSheet)
	{
		getTextPaneManifestation().setText(riskSheet.getManifestation());
		
		RiskSheetConsequenceTableModel tableModel = new RiskSheetConsequenceTableModel();
		List<RiskSheetConsequence> list = new ArrayList<RiskSheetConsequence>();
		
		for (Iterator iter = riskSheet.getRiskSheetConsequences().iterator(); iter.hasNext();)
		{
			RiskSheetConsequence riskSheetConsequence = (RiskSheetConsequence) iter.next();
			list.add(riskSheetConsequence);
		}
		
		tableModel.setRiskSheetConsequences(list);
		
		getTableConsequences().setModel(tableModel);
		
		getComboBoxGravity().setSelectedItem(riskSheet.getGravity());
		getComboBoxProbability().setSelectedItem(riskSheet.getProbability());
		getComboBoxRiskLevel().setSelectedItem(riskSheet.getRiskLevel());
		getTextPaneMeasures().setText(riskSheet.getMeasures());
		getTextPaneCompetence().setText(riskSheet.getCompetence());
		getTextPaneTerms().setText(riskSheet.getTerms());
	}
	
	/**
	 * Copie datele din controale in obiectul riskSheet primit.
	 * @param riskSheet
	 */
	private void copyToRiskSheet(RiskSheet riskSheet)
	{
		riskSheet.setManifestation(getTextPaneManifestation().getText().trim());
		
		riskSheet.getRiskSheetConsequences().clear();
		RiskSheetConsequenceTableModel tableModel = 
			(RiskSheetConsequenceTableModel) getTableConsequences().getModel();
		for (Iterator iter = tableModel.getRiskSheetConsequences().iterator(); iter.hasNext();)
		{
			RiskSheetConsequence riskSheetConsequence = (RiskSheetConsequence) iter.next();
			riskSheet.getRiskSheetConsequences().add(riskSheetConsequence);
		}
		
		riskSheet.setGravity((Gravity) getComboBoxGravity().getSelectedItem());
		riskSheet.setProbability((Probability) getComboBoxProbability().getSelectedItem());
		riskSheet.setRiskLevel((RiskLevel) getComboBoxRiskLevel().getSelectedItem());
		riskSheet.setMeasures(getTextPaneMeasures().getText().trim());
		riskSheet.setCompetence(getTextPaneCompetence().getText().trim());
		riskSheet.setTerms(getTextPaneTerms().getText().trim());
	}
	
	/**
	 * Face ReadOnly sau nu controalele RiskSheet.
	 *
	 */
	private void setRiskSheetRO(boolean readOnly)
	{
		getTreeRiskFactor().setEnabled(readOnly);
		
		getTextPaneManifestation().setEnabled(!readOnly);
		
		getAddConsequenceAction().setEnabled(!readOnly);
		getDeleteConsequenceAction().setEnabled(!readOnly);
		
		getTableConsequences().setBackground(
				readOnly ? SystemColor.control : SystemColor.window);
		
		getComboBoxGravity().setEnabled(!readOnly);
		getComboBoxProbability().setEnabled(!readOnly);
		getComboBoxRiskLevel().setEnabled(!readOnly);
		
		getTextPaneMeasures().setEnabled(!readOnly);
		getTextPaneCompetence().setEnabled(!readOnly);
		getTextPaneTerms().setEnabled(!readOnly);
		
		// actions
		setActionsState(readOnly);
	}
	
	private void setRiskSheetEmpty()
	{
		getTextPaneManifestation().setText("");
		
		RiskSheetConsequenceTableModel tableModel = new RiskSheetConsequenceTableModel();
		tableModel.setRiskSheetConsequences(new ArrayList<RiskSheetConsequence>());
		getTableConsequences().setModel(tableModel);
		
		getComboBoxGravity().setSelectedItem(null);
		getComboBoxProbability().setSelectedItem(null);
		getComboBoxRiskLevel().setSelectedItem(null);
		getTextPaneMeasures().setText("");
		getTextPaneCompetence().setText("");
		getTextPaneTerms().setText("");
	}

	/**
	 * adaugarea unei fise de risc
	 * @return
	 */
	public Action getAddRiskSheetAction()
	{
		if (addRiskSheetAction == null)
		{
			Icon icon = IconsUtils.getInstance().getIcon("button.add");
			addRiskSheetAction = new AbstractAction("Adaugare date factor risc", icon)
			{
				public void actionPerformed(ActionEvent e)
				{
					try
					{
						RiskSheet riskSheet = new RiskSheet();
						riskSheet.setRiskFactor(currentRiskFactor);
						riskSheet.setGravity(null);
						riskSheet.setProbability(null);
						riskSheet.setRiskLevel(null);
						riskSheet.setWorkPlace(workPlace);
						getRiskSheetDao().store(riskSheet);
						workPlace.getRiskSheets().add(riskSheet);
						currentRiskSheet = riskSheet;
						getTreeRiskFactor().updateUI();
						
						displayRiskSheet(riskSheet);
						setRiskSheetRO(false);
					}
					catch (Exception ex)
					{
						ErrorUtils.showError(ex, WorkPlacesRiskSheets.this);
					}
				}};
			addRiskSheetAction.putValue(Action.SHORT_DESCRIPTION, "Adaugare date factor risc");
		}
		return addRiskSheetAction;
	}

	/**
	 * modificarea unei fise de risc
	 * @return
	 */
	public Action getEditRiskSheetAction()
	{
		if (editRiskSheetAction == null)
		{
			Icon icon = IconsUtils.getInstance().getIcon("button.edit");
			editRiskSheetAction = new AbstractAction("Modificare date factor risc", icon)
			{
				public void actionPerformed(ActionEvent e)
				{
					setRiskSheetRO(false);
				}
			};

			editRiskSheetAction.putValue(Action.SHORT_DESCRIPTION,
					"Modificare date factor risc");
		}
		return editRiskSheetAction;
	}

	/**
	 * stergerea unei fise de risc
	 * @return
	 */
	public Action getDeleteRiskSheetAction()
	{
		if (deleteRiskSheetAction == null)
		{
			Icon icon = IconsUtils.getInstance().getIcon("button.delete");
			deleteRiskSheetAction = new AbstractAction("Stergere date factor risc", icon)
			{
				public void actionPerformed(ActionEvent e)
				{
					try
					{
						RiskSheet riskSheet = currentRiskSheet;
						
						if (riskSheet != null && currentRiskFactor != null)
						{
							String msg = "Sunteti sigur ca doriti sa stegeti datele pentru factorul de risc \"{0}\" ?";
							msg = MessageFormat.format(msg, currentRiskFactor.getName());
							
							if (JOptionPane.showConfirmDialog(WorkPlacesRiskSheets.this, msg) == JOptionPane.OK_OPTION)
							{
								getRiskSheetDao().delete(riskSheet);
								workPlace.getRiskSheets().remove(riskSheet);
								currentRiskSheet = null;
								getTreeRiskFactor().updateUI();
								setRiskSheetEmpty();
								setRiskSheetRO(true);
								updateLabelRiskLevel();
							}
						}
					}
					catch (Exception ex)
					{
						ErrorUtils.showError(ex, WorkPlacesRiskSheets.this);
					}
				}};
			deleteRiskSheetAction.putValue(Action.SHORT_DESCRIPTION, "Stergere date factor risc");
		}
		return deleteRiskSheetAction;
	}

	public RiskSheetDao getRiskSheetDao()
	{
		if (riskSheetDao == null)
			riskSheetDao = new RiskSheetDaoHibernate();
		return riskSheetDao;
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
			popupMenu.add(getMenuItemAddRiskSheet());
			popupMenu.add(getMenuItemEditRiskSheet());
			popupMenu.add(getMenuItemDeleteRiskSheet());
		}
		return popupMenu;
	}

	/**
	 * This method initializes menuItemAddRiskSheet	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMenuItemAddRiskSheet()
	{
		if (menuItemAddRiskSheet == null)
		{
			menuItemAddRiskSheet = new JMenuItem();
			menuItemAddRiskSheet.setAction(getAddRiskSheetAction());
		}
		return menuItemAddRiskSheet;
	}

	/**
	 * This method initializes menuItemDeleteRiskSheet	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMenuItemDeleteRiskSheet()
	{
		if (menuItemDeleteRiskSheet == null)
		{
			menuItemDeleteRiskSheet = new JMenuItem();
			menuItemDeleteRiskSheet.setAction(getDeleteRiskSheetAction());
		}
		return menuItemDeleteRiskSheet;
	}

	/**
	 * This method initializes menuItemEditRiskSheet	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMenuItemEditRiskSheet()
	{
		if (menuItemEditRiskSheet == null)
		{
			menuItemEditRiskSheet = new JMenuItem();
			menuItemEditRiskSheet.setAction(getEditRiskSheetAction());
		}
		return menuItemEditRiskSheet;
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
			jPanel.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
			jPanel.setPreferredSize(new Dimension(200, 506));
			jPanel.add(getJScrollPane(), BorderLayout.CENTER);
			jPanel.add(getJPanel1(), BorderLayout.NORTH);
		}
		return jPanel;
	}

	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel1()
	{
		if (jPanel1 == null)
		{
			labelRiskLevel = new JLabel();
			labelRiskLevel.setBounds(new Rectangle(105, 75, 61, 16));
			labelRiskLevel.setFont(new Font("Dialog", Font.BOLD, 12));
			labelRiskLevel.setForeground(Color.red);
			labelRiskLevel.setText("2,5");
			labelRiskLevel1 = new JLabel();
			labelRiskLevel1.setBounds(new Rectangle(15, 75, 76, 16));
			labelRiskLevel1.setText("Nivel de risc:");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(15, 45, 76, 16));
			jLabel1.setText("Post lucru");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(15, 15, 76, 16));
			jLabel.setText("Companie");
			jPanel1 = new JPanel();
			jPanel1.setLayout(null);
			jPanel1.setPreferredSize(new Dimension(0, 100));
			jPanel1.setForeground(Color.red);
			jPanel1.add(jLabel, null);
			jPanel1.add(getTextFieldCompany(), null);
			jPanel1.add(jLabel1, null);
			jPanel1.add(getTextFieldWorkPlace(), null);
			jPanel1.add(labelRiskLevel1, null);
			jPanel1.add(labelRiskLevel, null);
		}
		return jPanel1;
	}

	/**
	 * This method initializes textFieldCompany	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTextFieldCompany()
	{
		if (textFieldCompany == null)
		{
			textFieldCompany = new JTextField();
			textFieldCompany.setBounds(new Rectangle(105, 15, 211, 20));
			textFieldCompany.setEditable(false);
		}
		return textFieldCompany;
	}

	/**
	 * This method initializes textFieldWorkPlace	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTextFieldWorkPlace()
	{
		if (textFieldWorkPlace == null)
		{
			textFieldWorkPlace = new JTextField();
			textFieldWorkPlace.setBounds(new Rectangle(105, 45, 211, 20));
			textFieldWorkPlace.setEditable(false);
		}
		return textFieldWorkPlace;
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
			jJToolBarBar.setFloatable(false);
			jJToolBarBar.setLocation(new Point(15, 0));
			jJToolBarBar.setSize(new Dimension(361, 32));
			jJToolBarBar.add(getButtonAdd());
			jJToolBarBar.add(getButtonEdit());
			jJToolBarBar.add(getButtonDelete());
			jJToolBarBar.addSeparator();
			jJToolBarBar.add(getButtonSave());
			jJToolBarBar.add(getButtonCancel());
		}
		return jJToolBarBar;
	}

	/**
	 * This method initializes buttonSave	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getButtonSave()
	{
		if (buttonSave == null)
		{
			buttonSave = new JButton();
			buttonSave.setFocusPainted(false);
			buttonSave.setAction(getSaveRiskSheetAction());
			buttonSave.setText("");
		}
		return buttonSave;
	}

	/**
	 * This method initializes buttonCancel	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getButtonCancel()
	{
		if (buttonCancel == null)
		{
			buttonCancel = new JButton();
			buttonCancel.setFocusPainted(false);
			buttonCancel.setAction(getAbortRiskSheetAction());
			buttonCancel.setText("");
		}
		return buttonCancel;
	}

	public Action getSaveRiskSheetAction()
	{
		if (saveRiskSheetAction == null)
		{
			Icon icon  = IconsUtils.getInstance().getIcon("button.save");
			
			saveRiskSheetAction = new AbstractAction("Salvare modificari", icon)
			{
				public void actionPerformed(ActionEvent e)
				{
					try
					{
						// salvare riskSheet curent
						if (currentRiskSheet != null)
						{
							String validationMsg = "";
							// campul manifestare trebuie completat
							if (getTextPaneManifestation().getText().trim().equals(""))
								validationMsg = "Completati forma concreta de manifestare.";
							
							// campul gravitate trebuie completat
							if (validationMsg.equals("") && getComboBoxGravity().getSelectedItem() == null)
								validationMsg = "Campul gravitate trebuie completat";
							
							// campul probabilitate trebuie completat
							if (validationMsg.equals("") && getComboBoxProbability().getSelectedItem() == null)
								validationMsg = "Campul probabilitate trebuie completat";
							
							// campul nivel de risc
							if (validationMsg.equals("") && getComboBoxRiskLevel().getSelectedItem() == null)
								validationMsg = "Campul nivel de risc trebuie completat";
							
							if (!validationMsg.equals(""))
							{
								JOptionPane.showMessageDialog(
										WorkPlacesRiskSheets.this, validationMsg);
								return;
							}
							
							copyToRiskSheet(currentRiskSheet);
							getRiskSheetDao().update(currentRiskSheet);
							setRiskSheetRO(true);
							updateLabelRiskLevel();
						}
					}
					catch (Exception ex)
					{
						ErrorUtils.showError(ex, WorkPlacesRiskSheets.this);
					}
				}
			};
			
			saveRiskSheetAction.putValue(Action.SHORT_DESCRIPTION, "Salvare modificari");
		}
		return saveRiskSheetAction;
	}

	public Action getAbortRiskSheetAction()
	{
		if (abortRiskSheetAction == null)
		{
			Icon icon  = IconsUtils.getInstance().getIcon("button.cancel");
			
			abortRiskSheetAction = new AbstractAction("Renuntare modificari", icon)
			{
				public void actionPerformed(ActionEvent e)
				{
					// renuntare mmodificari riskSheet
					if (currentRiskSheet != null)
					{
						displayRiskSheet(currentRiskSheet);
						setRiskSheetRO(true);
					}
				}
			};
			abortRiskSheetAction.putValue(Action.SHORT_DESCRIPTION, "Renuntare modificari");
		}
		return abortRiskSheetAction;
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
			buttonAdd.setAction(getAddRiskSheetAction());
			buttonAdd.setText("");
		}
		return buttonAdd;
	}

	/**
	 * This method initializes buttonEdit	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getButtonEdit()
	{
		if (buttonEdit == null)
		{
			buttonEdit = new JButton();
			buttonEdit.setFocusPainted(false);
			buttonEdit.setAction(getEditRiskSheetAction());
			buttonEdit.setText("");
		}
		return buttonEdit;
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
			buttonDelete.setAction(getDeleteRiskSheetAction());
			buttonDelete.setText("");
		}
		return buttonDelete;
	}

	/**
	 * This method initializes jJToolBarBar1	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJJToolBarBar1()
	{
		if (jJToolBarBar1 == null)
		{
			jJToolBarBar1 = new JToolBar();
			jJToolBarBar1.setLocation(new Point(15, 165));
			jJToolBarBar1.setFloatable(false);
			jJToolBarBar1.setSize(new Dimension(511, 32));
			jJToolBarBar1.add(getButtonAddConsequence());
			jJToolBarBar1.add(getButtonDeleteConsequence());
		}
		return jJToolBarBar1;
	}

	/**
	 * This method initializes buttonAddConsequence	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getButtonAddConsequence()
	{
		if (buttonAddConsequence == null)
		{
			buttonAddConsequence = new JButton();
			buttonAddConsequence.setFocusPainted(false);
			buttonAddConsequence.setAction(getAddConsequenceAction());
			buttonAddConsequence.setText("");
		}
		return buttonAddConsequence;
	}

	/**
	 * This method initializes buttonDeleteConsequence	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getButtonDeleteConsequence()
	{
		if (buttonDeleteConsequence == null)
		{
			buttonDeleteConsequence = new JButton();
			buttonDeleteConsequence.setFocusPainted(false);
			buttonDeleteConsequence.setAction(getDeleteConsequenceAction());
			buttonDeleteConsequence.setText("");
		}
		return buttonDeleteConsequence;
	}

	public Action getAddConsequenceAction()
	{
		if (addConsequenceAction == null)
		{
			Icon icon = IconsUtils.getInstance().getIcon("button.add");
			addConsequenceAction = new AbstractAction("", icon)
			{
				public void actionPerformed(ActionEvent e)
				{
					RiskSheetConsequenceDialog dialog = new RiskSheetConsequenceDialog(
							WorkPlacesRiskSheets.this);
					dialog.setConsequenceList(getConsequenceList());
					
					dialog.setVisible(true);
					
					if (dialog.isOk())
					{
						RiskSheetConsequence riskSheetConsequence = new RiskSheetConsequence();
						riskSheetConsequence.setConsequence(dialog.getConsequence());
						riskSheetConsequence.setConsequenceLocation(dialog.getConsequenceLocation());
						riskSheetConsequence.setRiskSheet(currentRiskSheet);
						
						RiskSheetConsequenceTableModel tableModel = 
							(RiskSheetConsequenceTableModel) getTableConsequences().getModel();
						tableModel.getRiskSheetConsequences().add(riskSheetConsequence);
						
						int index = tableModel.getRiskSheetConsequences().size() - 1;
						getTableConsequences().tableChanged(new TableModelEvent(tableModel, index, index,
							TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT));
						
						// recalculare gravitate maxima
						Gravity gravity = getMaxGravity(tableModel.getRiskSheetConsequences());
						getComboBoxGravity().setSelectedItem(gravity);
					}
				}
			};
		}
		return addConsequenceAction;
	}
	
	/**
	 * Determina gravitatea maxima dintr-un riskSheet
	 * @param list
	 * @return
	 */
	private Gravity getMaxGravity(List<RiskSheetConsequence> list)
	{
		Gravity maxGravity = null;
		
		for (Iterator i = list.iterator(); i.hasNext();)
		{
			RiskSheetConsequence consequence = (RiskSheetConsequence) i.next();
			ClgCouple clgCouple = consequence.getConsequence()
					.getByConsequenceLocation(consequence.getConsequenceLocation());
			if (clgCouple != null && clgCouple.getGravity() != null)
			{
				if (maxGravity == null)
					maxGravity = clgCouple.getGravity();
				else
					if (maxGravity.getCode().compareTo(clgCouple.getGravity().getCode()) < 0)
						maxGravity = clgCouple.getGravity();
			}
		}

		return maxGravity;
	}

	public Action getDeleteConsequenceAction()
	{
		if (deleteConsequenceAction == null)
		{
			Icon icon = IconsUtils.getInstance().getIcon("button.delete");
			deleteConsequenceAction = new AbstractAction("Stergere consecinta", icon)
			{
				public void actionPerformed(ActionEvent e)
				{
					RiskSheetConsequenceTableModel tableModel = 
						(RiskSheetConsequenceTableModel) getTableConsequences().getModel();
					int index = getTableConsequences().getSelectedRow();
					
					if (index < 0)
					{
						String msg = "Selectati o consecinta.";
						JOptionPane.showMessageDialog(WorkPlacesRiskSheets.this, msg);
					} else
					{
						RiskSheetConsequence riskSheetConsequence = tableModel
								.getRiskSheetConsequences().get(index);
						String msg = MessageFormat
								.format(
										"Sunteti sigur ca doriti sa stergeti consecinta \"{0}\" ?",
										riskSheetConsequence.getConsequence().getName());
						if (JOptionPane.showConfirmDialog(
								WorkPlacesRiskSheets.this, msg) == JOptionPane.OK_OPTION)
						{
							tableModel.getRiskSheetConsequences().remove(riskSheetConsequence);
							getTableConsequences().tableChanged(
									new TableModelEvent(tableModel, index,
											index, TableModelEvent.ALL_COLUMNS,
											TableModelEvent.DELETE));
							// recalculare gravitate maxima
							Gravity gravity = getMaxGravity(tableModel
									.getRiskSheetConsequences());
							getComboBoxGravity().setSelectedItem(gravity);
						}
					}
				}
			};
			deleteConsequenceAction.putValue(Action.SHORT_DESCRIPTION,
					"Stergere consecinta");
		}
		return deleteConsequenceAction;
	}

	public ConsequenceDao getConsequenceDao()
	{
		if (consequenceDao == null)
			consequenceDao = new ConsequenceDaoHibernate();
		return consequenceDao;
	}

	public List<Consequence> getConsequenceList()
	{
		if (consequenceList == null)
			try
			{
				consequenceList = getConsequenceDao().findAll();
			} catch (DaoException e)
			{
				ErrorUtils.showError(e, WorkPlacesRiskSheets.this);
			}
		return consequenceList;
	}
	
	/**
	 * setarea starii enabled/disabled a actiunilor.
	 *
	 */
	private void setActionsState(boolean readOnly)
	{
		// adaugare riskSheet - este selectat un factor de risc ,nu are fisa si
		// nu suntem in editare
		getAddRiskSheetAction().setEnabled(currentRiskFactor != null && currentRiskSheet == null && readOnly);
		
		// modificare riskSheet - este selectat un factor cu fisa de risc si 
		// nu suntem in editare
		getEditRiskSheetAction().setEnabled(currentRiskFactor!= null && currentRiskSheet != null && readOnly);
		
		// stergere riskSheet - este selectat in factor cu fisa de risc si nu suntem in editare
		getDeleteRiskSheetAction().setEnabled(currentRiskFactor!= null && currentRiskSheet != null && readOnly);
		
		// salvare fisa risc - este o fisa de risc selectata, si suntem in editare
		getSaveRiskSheetAction().setEnabled(currentRiskSheet != null && !readOnly);
		
		// renuntare fisa risc - este o fisa de risc selectata, si suntem in editare
		getAbortRiskSheetAction().setEnabled(currentRiskSheet != null && !readOnly);
	}

	private void updateLabelRiskLevel()
	{
		double l = workPlace.getGlobalRiskLevel();
		labelRiskLevel.setText(String.valueOf(l));
	}
}  //  @jve:decl-index=0:visual-constraint="10,14"
