package ro.rmc.riskeval.gui;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import java.awt.Font;

public class RiskFactorDetails extends JDialog
{

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JComboBox comboBoxConsequence = null;

	private JComboBox comboBoxConsequenceLocation = null;

	private JLabel jLabel = null;

	private JLabel jLabel1 = null;

	private JTextArea textAreaManifestare = null;

	private JLabel jLabel2 = null;

	private JScrollPane jScrollPane = null;

	private JTable tableConsequences = null;

	private JLabel jLabel3 = null;

	private JLabel jLabel4 = null;

	private JLabel jLabel5 = null;

	private JComboBox comboBoxGravity = null;

	private JComboBox comboBoxProbability = null;

	private JComboBox comboBoxRiskLevel = null;

	private JLabel jLabel6 = null;

	private JTextArea textAreaMasuriPropuse = null;

	private JLabel jLabel7 = null;

	private JTextArea textAreaCompetente = null;

	private JLabel jLabel8 = null;

	private JTextArea textAreaTermene = null;

	private JScrollPane jScrollPane1 = null;

	/**
	 * @param owner
	 */
	public RiskFactorDetails(Frame owner)
	{
		super(owner);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize()
	{
		this.setSize(839, 526);
		this.setTitle("ere");
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
			jLabel8 = new JLabel();
			jLabel8.setBounds(new Rectangle(585, 225, 91, 16));
			jLabel8.setText("Termene");
			jLabel7 = new JLabel();
			jLabel7.setBounds(new Rectangle(300, 225, 85, 16));
			jLabel7.setText("Competente");
			jLabel6 = new JLabel();
			jLabel6.setBounds(new Rectangle(15, 225, 91, 16));
			jLabel6.setText("Masuri propuse");
			jLabel5 = new JLabel();
			jLabel5.setBounds(new Rectangle(585, 105, 93, 16));
			jLabel5.setText("Nivel de risc");
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(585, 60, 93, 16));
			jLabel4.setText("Probabilitate");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(585, 15, 93, 16));
			jLabel3.setText("Gravitate");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(15, 15, 271, 16));
			jLabel2.setText("Forma concreta de manifestare");
			jLabel1 = new JLabel();
			jLabel1.setText("Localizare consecinta");
			jLabel1.setSize(new Dimension(130, 16));
			jLabel1.setLocation(new Point(435, 15));
			jLabel = new JLabel();
			jLabel.setText("Consecinta");
			jLabel.setSize(new Dimension(130, 16));
			jLabel.setLocation(new Point(300, 15));
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getComboBoxConsequence(), null);
			jContentPane.add(getComboBoxConsequenceLocation(), null);
			jContentPane.add(jLabel, null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(getTextAreaManifestare(), null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(getJScrollPane(), null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(jLabel4, null);
			jContentPane.add(jLabel5, null);
			jContentPane.add(getComboBoxGravity(), null);
			jContentPane.add(getComboBoxProbability(), null);
			jContentPane.add(getComboBoxRiskLevel(), null);
			jContentPane.add(jLabel6, null);
			jContentPane.add(getTextAreaMasuriPropuse(), null);
			jContentPane.add(jLabel7, null);
			jContentPane.add(jLabel8, null);
			jContentPane.add(getTextAreaTermene(), null);
			jContentPane.add(getJScrollPane1(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes comboBoxConsequence	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getComboBoxConsequence()
	{
		if (comboBoxConsequence == null)
		{
			comboBoxConsequence = new JComboBox();
			comboBoxConsequence.setLocation(new Point(300, 30));
			comboBoxConsequence.setSize(new Dimension(130, 25));
		}
		return comboBoxConsequence;
	}

	/**
	 * This method initializes comboBoxConsequenceLocation	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getComboBoxConsequenceLocation()
	{
		if (comboBoxConsequenceLocation == null)
		{
			comboBoxConsequenceLocation = new JComboBox();
			comboBoxConsequenceLocation.setLocation(new Point(435, 30));
			comboBoxConsequenceLocation.setSize(new Dimension(130, 25));
		}
		return comboBoxConsequenceLocation;
	}

	/**
	 * This method initializes textAreaManifestare	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getTextAreaManifestare()
	{
		if (textAreaManifestare == null)
		{
			textAreaManifestare = new JTextArea();
			textAreaManifestare.setBounds(new Rectangle(300, 255, 271, 76));
			textAreaManifestare.setFont(new Font("Arial", Font.PLAIN, 12));
			textAreaManifestare.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		}
		return textAreaManifestare;
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
			jScrollPane.setBounds(new Rectangle(300, 90, 271, 106));
			jScrollPane.setViewportView(getTableConsequences());
		}
		return jScrollPane;
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
		}
		return tableConsequences;
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
			comboBoxGravity.setBounds(new Rectangle(585, 30, 211, 25));
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
			comboBoxProbability.setBounds(new Rectangle(585, 75, 211, 25));
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
			comboBoxRiskLevel.setBounds(new Rectangle(585, 120, 211, 25));
		}
		return comboBoxRiskLevel;
	}

	/**
	 * This method initializes textAreaMasuriPropuse	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getTextAreaMasuriPropuse()
	{
		if (textAreaMasuriPropuse == null)
		{
			textAreaMasuriPropuse = new JTextArea();
			textAreaMasuriPropuse.setBounds(new Rectangle(15, 240, 271, 91));
			textAreaMasuriPropuse.setFont(new Font("Arial", Font.PLAIN, 12));
			textAreaMasuriPropuse.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		}
		return textAreaMasuriPropuse;
	}

	/**
	 * This method initializes textAreaCompetente	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getTextAreaCompetente()
	{
		if (textAreaCompetente == null)
		{
			textAreaCompetente = new JTextArea();
			textAreaCompetente.setFont(new Font("Arial", Font.PLAIN, 12));
			textAreaCompetente.setWrapStyleWord(true);
			textAreaCompetente.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		}
		return textAreaCompetente;
	}

	/**
	 * This method initializes textAreaTermene	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getTextAreaTermene()
	{
		if (textAreaTermene == null)
		{
			textAreaTermene = new JTextArea();
			textAreaTermene.setBounds(new Rectangle(585, 240, 226, 91));
			textAreaTermene.setFont(new Font("Arial", Font.PLAIN, 12));
			textAreaTermene.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		}
		return textAreaTermene;
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
			jScrollPane1.setBounds(new Rectangle(15, 30, 271, 166));
			jScrollPane1.setViewportView(getTextAreaCompetente());
		}
		return jScrollPane1;
	}

}  //  @jve:decl-index=0:visual-constraint="17,9"
