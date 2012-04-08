package ro.rmc.riskeval.gui;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import ro.rmc.riskeval.domain.RiskFactor;
import ro.rmc.riskeval.utils.IconsUtils;

public class RiskFactorDialog extends JDialog
{

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JLabel labelCode = null;

	private JTextField textFieldCode = null;

	private JLabel labelName = null;

	private JTextField textFieldName = null;

	private JLabel labelParent = null;

	private JLabel labelParentCode = null;

	private JLabel labelParentName = null;

	private JTextField textFieldParentCode = null;

	private JTextField textFieldParentName = null;

	private JButton buttonOk = null;

	private JButton buttonCancel = null;
	
	private RiskFactor parentRiskFactor;  //  @jve:decl-index=0:
	
	private boolean ok = false;
	
	/**
	 * @return the code
	 */
	public String getCode()
	{
		return getTextFieldCode().getText().trim();
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return getTextFieldName().getText().trim();
	}

	/**
	 * @return the parentRiskFactor
	 */
	public RiskFactor getParentRiskFactor()
	{
		return parentRiskFactor;
	}

	/**
	 * @param parentRiskFactor the parentRiskFactor to set
	 */
	public void setParentRiskFactor(RiskFactor parentRiskFactor)
	{
		this.parentRiskFactor = parentRiskFactor;
		this.getTextFieldParentCode().setText(parentRiskFactor.getCode());
		this.getTextFieldParentName().setText(parentRiskFactor.getName());
		this.getTextFieldParentName().setCaretPosition(0);
	}

	/**
	 * @param owner
	 */
	public RiskFactorDialog(Frame owner)
	{
		super(owner);
		initialize();
		GuiTool.center(this, owner);
		
		addWindowListener(new WindowAdapter()
		{
			public void windowActivated(WindowEvent e)
			{
				getTextFieldCode().requestFocus();
			}
		});
	}
	
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize()
	{
		this.setSize(459, 240);
		this.setResizable(false);
		this.setTitle("Factor de risc");
		this.setModal(true);
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
			labelParentName = new JLabel();
			labelParentName.setBounds(new Rectangle(8, 72, 38, 16));
			labelParentName.setText("Name");
			labelParentCode = new JLabel();
			labelParentCode.setText("Cod");
			labelParentCode.setLocation(new Point(8, 44));
			labelParentCode.setSize(new Dimension(38, 16));
			labelParent = new JLabel();
			labelParent.setBounds(new Rectangle(8, 16, 184, 16));
			labelParent.setText("Factor de risc parinte");
			labelName = new JLabel();
			labelName.setBounds(new Rectangle(8, 148, 38, 16));
			labelName.setText("Nume");
			labelCode = new JLabel();
			labelCode.setBounds(new Rectangle(8, 119, 38, 16));
			labelCode.setText("Cod");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(labelCode, null);
			jContentPane.add(getTextFieldCode(), null);
			jContentPane.add(labelName, null);
			jContentPane.add(getTextFieldName(), null);
			jContentPane.add(labelParent, null);
			jContentPane.add(labelParentCode, null);
			jContentPane.add(labelParentName, null);
			jContentPane.add(getTextFieldParentCode(), null);
			jContentPane.add(getTextFieldParentName(), null);
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
			textFieldCode.setLocation(new Point(65, 119));
			textFieldCode.setPreferredSize(new Dimension(100, 20));
			textFieldCode.setColumns(0);
			textFieldCode.setSize(new Dimension(100, 20));
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
			textFieldName.setLocation(new Point(65, 148));
			textFieldName.setPreferredSize(new Dimension(270, 20));
			textFieldName.setColumns(0);
			textFieldName.setSize(new Dimension(370, 20));
		}
		return textFieldName;
	}

	/**
	 * This method initializes textFieldParentCode	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTextFieldParentCode()
	{
		if (textFieldParentCode == null)
		{
			textFieldParentCode = new JTextField();
			textFieldParentCode.setText("");
			textFieldParentCode.setPreferredSize(new Dimension(100, 20));
			textFieldParentCode.setLocation(new Point(65, 44));
			textFieldParentCode.setSize(new Dimension(100, 20));
			textFieldParentCode.setEditable(false);
		}
		return textFieldParentCode;
	}

	/**
	 * This method initializes textFieldParentName	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTextFieldParentName()
	{
		if (textFieldParentName == null)
		{
			textFieldParentName = new JTextField();
			textFieldParentName.setText("");
			textFieldParentName.setLocation(new Point(65, 72));
			textFieldParentName.setSize(new Dimension(370, 20));
			textFieldParentName.setPreferredSize(new Dimension(270, 20));
			textFieldParentName.setColumns(0);
			textFieldParentName.setEditable(false);
		}
		return textFieldParentName;
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
			buttonOk.setLocation(new Point(266, 179));

			ActionListener actionOk = new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					if (getTextFieldCode().getText().trim().length() == 0)
					{
						JOptionPane.showMessageDialog(RiskFactorDialog.this,
								"Completati codul factorului de risc.");
					} else if (getTextFieldName().getText().trim().length() == 0)
					{
						JOptionPane.showMessageDialog(RiskFactorDialog.this,
								"Completati numele factorului de risc.");
					} else
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
			buttonCancel.setLocation(new Point(357, 179));
			
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

	/**
	 * @param currentRiskFactor the currentRiskFactor to set
	 */
	public void setCurrentRiskFactor(RiskFactor currentRiskFactor)
	{
		getTextFieldCode().setText(currentRiskFactor.getCode());
		getTextFieldName().setText(currentRiskFactor.getName());
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
