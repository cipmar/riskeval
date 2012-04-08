package ro.rmc.riskeval.gui;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import ro.rmc.riskeval.domain.ConsequenceLocation;
import ro.rmc.riskeval.utils.IconsUtils;

public class ConsequenceLocationDialog extends JDialog
{

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JLabel jLabel = null;

	private JLabel jLabel1 = null;

	private JTextField textFieldCode = null;

	private JTextField textFieldName = null;

	private JButton buttonOk = null;

	private JButton buttonCancel = null;

	protected boolean ok;

	/**
	 * @param owner
	 */
	public ConsequenceLocationDialog(Frame owner)
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
		this.setSize(348, 149);
		this.setModal(true);
		this.setTitle("Localizare consecinta");
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
			jLabel1.setBounds(new Rectangle(15, 45, 38, 16));
			jLabel1.setText("Nume");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(15, 15, 38, 16));
			jLabel.setText("Cod");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel, null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(getTextFieldCode(), null);
			jContentPane.add(getTextFieldName(), null);
			jContentPane.add(getButtonOk(), null);
			jContentPane.add(getButtonCancel(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes textFieldCode	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTextFieldCode()
	{
		if (textFieldCode == null)
		{
			textFieldCode = new JTextField();
			textFieldCode.setBounds(new Rectangle(60, 15, 61, 20));
		}
		return textFieldCode;
	}

	/**
	 * This method initializes textFieldName	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTextFieldName()
	{
		if (textFieldName == null)
		{
			textFieldName = new JTextField();
			textFieldName.setBounds(new Rectangle(60, 45, 271, 20));
		}
		return textFieldName;
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
			buttonOk.setPreferredSize(new Dimension(85, 25));
			buttonOk.setSize(new Dimension(85, 25));
			buttonOk.setText("Ok");
			buttonOk.setFocusPainted(false);
			buttonOk.setIcon(IconsUtils.getInstance().getIcon("button.ok"));
			buttonOk.setMargin(new Insets(2, 2, 2, 2));
			buttonOk.setLocation(new Point(150, 75));
			
			ActionListener actionOk = new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					if (getTextFieldCode().getText().trim().length() == 0)
					{
						JOptionPane.showMessageDialog(ConsequenceLocationDialog.this,
								"Completati codul localizarii consecintei.");
					} else

					if (getTextFieldName().getText().trim().length() == 0)
					{
						JOptionPane.showMessageDialog(ConsequenceLocationDialog.this,
								"Completati numele localizarii consecintei.");
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
			buttonCancel.setPreferredSize(new Dimension(85, 25));
			buttonCancel.setSize(new Dimension(85, 25));
			buttonCancel.setText("Renunta");
			buttonCancel.setFocusPainted(false);
			buttonCancel.setIcon(IconsUtils.getInstance().getIcon("button.cancel"));
			buttonCancel.setMargin(new Insets(2, 2, 2, 2));
			buttonCancel.setLocation(new Point(240, 75));
			
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

	/**
	 * @return the ok
	 */
	public boolean isOk()
	{
		return ok;
	}

	public void setConsequenceLocation(ConsequenceLocation consequenceLocation)
	{
		getTextFieldCode().setText(consequenceLocation.getCode());
		getTextFieldName().setText(consequenceLocation.getName());
	}
	
	public String getConsequenceLocationCode()
	{
		return getTextFieldCode().getText().trim();
	}
	
	public String getConsequenceLocationName()
	
	{
		return getTextFieldName().getText().trim();
	}
}
