package ro.rmc.riskeval.gui;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import ro.rmc.riskeval.domain.ClgCouple;
import ro.rmc.riskeval.domain.Consequence;
import ro.rmc.riskeval.domain.ConsequenceLocation;
import ro.rmc.riskeval.utils.IconsUtils;

public class RiskSheetConsequenceDialog extends JDialog
{

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JLabel jLabel = null;

	private JLabel jLabel1 = null;

	private JComboBox comboBoxConsequence = null;

	private JComboBox comboBoxConsequenceLocation = null;

	private JButton buttonOk = null;

	private JButton buttonCancel = null;

	private boolean ok;

	/**
	 * @param owner
	 */
	
	public RiskSheetConsequenceDialog(Dialog owner)
	{
		super(owner);
		initialize();
		GuiTool.center(this, owner);
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize()
	{
		this.setSize(455, 177);
		this.setModal(true);
		this.setTitle("Consecinta factor risc");
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
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(15, 60, 76, 16));
			jLabel1.setText("Localizare");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(15, 15, 76, 16));
			jLabel.setText("Consecinta");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel, null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(getComboBoxConsequence(), null);
			jContentPane.add(getComboBoxConsequenceLocation(), null);
			jContentPane.add(getButtonOk(), null);
			jContentPane.add(getButtonCancel(), null);
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
			comboBoxConsequence.setBounds(new Rectangle(105, 15, 316, 25));
			comboBoxConsequence.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					Consequence consequence = (Consequence) comboBoxConsequence
							.getSelectedItem();
					List<ConsequenceLocation> list = new ArrayList<ConsequenceLocation>();
					
					if (consequence != null)
					{
						for (Iterator iter = consequence.getConsequenceLocations().iterator(); iter.hasNext();)
						{
							ClgCouple element = (ClgCouple) iter.next();
							if (element.getGravity() != null)
								list.add(element.getConsequenceLocation());
						}
					}
					
					Collections.sort(list, new Comparator<ConsequenceLocation>(){

						public int compare(ConsequenceLocation o1, ConsequenceLocation o2)
						{
							return o1.getCode().compareTo(o2.getCode());
						}});

					getComboBoxConsequenceLocation().setModel(
							new DefaultComboBoxModel(list.toArray()));
				}
			});
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
			comboBoxConsequenceLocation.setBounds(new Rectangle(105, 60, 316, 25));
		}
		return comboBoxConsequenceLocation;
	}

	/**
	 * This method initializes buttonOk	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getButtonOk()
	{
		if (buttonOk == null)
		{
			buttonOk = new JButton();
			buttonOk.setText("Ok");
			buttonOk.setSize(new Dimension(85, 25));
			buttonOk.setMargin(new Insets(2, 2, 2, 2));
			buttonOk.setIcon(IconsUtils.getInstance().getIcon("button.ok"));
			buttonOk.setLocation(new Point(255, 105));
			
			ActionListener actionOk = new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					if (getComboBoxConsequence().getSelectedItem() == null)
					{
						JOptionPane.showMessageDialog(RiskSheetConsequenceDialog.this,
								"Selectati o consecinta.");
					} else

					if (getComboBoxConsequenceLocation().getSelectedItem() == null)
					{
						JOptionPane.showMessageDialog(RiskSheetConsequenceDialog.this,
								"Selectati o localizare a consecintei.");
					}

					else
						ok = true;
						
					
					if (ok)
						dispose();
				}
			};

			getRootPane().setDefaultButton(buttonOk);
			getRootPane().registerKeyboardAction(actionOk,
					KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
					JComponent.WHEN_IN_FOCUSED_WINDOW);
			
			buttonOk.addActionListener(actionOk);
		}
		return buttonOk;
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
			buttonCancel.setText("Renunta");
			buttonCancel.setSize(new Dimension(85, 25));
			buttonCancel.setMargin(new Insets(2, 2, 2, 2));
			buttonCancel.setIcon(IconsUtils.getInstance().getIcon("button.cancel"));
			buttonCancel.setLocation(new Point(345, 105));
			
			ActionListener actionCancel = new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					dispose();
				}
			};

			getRootPane().registerKeyboardAction(actionCancel,
					KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
					JComponent.WHEN_IN_FOCUSED_WINDOW);

			buttonCancel.addActionListener(actionCancel);
		}
		return buttonCancel;
	}

	public boolean isOk()
	{
		return ok;
	}

	public Consequence getConsequence()
	{
		return (Consequence) getComboBoxConsequence().getSelectedItem();
	}

	public ConsequenceLocation getConsequenceLocation()
	{
		return (ConsequenceLocation) getComboBoxConsequenceLocation()
				.getSelectedItem();
	}

	public void setConsequenceList(List<Consequence> consequenceList)
	{
		getComboBoxConsequence().setModel(
				new DefaultComboBoxModel(consequenceList.toArray()));
		getComboBoxConsequence().setSelectedItem(null);
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
