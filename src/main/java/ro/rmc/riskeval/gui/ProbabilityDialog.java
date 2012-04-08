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

import ro.rmc.riskeval.domain.Probability;
import ro.rmc.riskeval.utils.IconsUtils;

public class ProbabilityDialog extends JDialog
{

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JLabel jLabel = null;

	private JLabel jLabel1 = null;

	private JLabel jLabel2 = null;

	private JTextField textFieldCode = null;

	private JTextField textFieldName = null;

	private JTextField textFieldDescription = null;

	private JButton buttonOk = null;

	private JButton buttonCancel = null;

	protected boolean ok;

	/**
	 * @param owner
	 */
	public ProbabilityDialog(Frame owner)
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
		this.setSize(333, 174);
		this.setResizable(false);
		this.setModal(true);
		this.setTitle("Clasa de probabilitate");
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
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(15, 75, 61, 16));
			jLabel2.setText("Descriere");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(15, 45, 61, 16));
			jLabel1.setText("Nume");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(15, 15, 61, 16));
			jLabel.setText("Cod");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel, null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(getTextFieldCode(), null);
			jContentPane.add(getTextFieldName(), null);
			jContentPane.add(getTextFieldDescription(), null);
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
			textFieldCode.setBounds(new Rectangle(90, 15, 46, 20));
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
			textFieldName.setBounds(new Rectangle(90, 45, 226, 20));
		}
		return textFieldName;
	}

	/**
	 * This method initializes textFieldDescription	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTextFieldDescription()
	{
		if (textFieldDescription == null)
		{
			textFieldDescription = new JTextField();
			textFieldDescription.setBounds(new Rectangle(90, 75, 226, 20));
		}
		return textFieldDescription;
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
			buttonOk.setLocation(new Point(135, 105));
			
			ActionListener actionOk = new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					if (getTextFieldCode().getText().trim().length() == 0)
					{
						JOptionPane.showMessageDialog(ProbabilityDialog.this,
								"Completati codul clasei de probabilitate.");
					} else

					if (getTextFieldName().getText().trim().length() == 0)
					{
						JOptionPane.showMessageDialog(ProbabilityDialog.this,
								"Completati numele clasei de probabilitate.");
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
			buttonCancel.setLocation(new Point(225, 105));
			
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

	public void setProbability(Probability probability)
	{
		getTextFieldCode().setText(probability.getCode());
		getTextFieldName().setText(probability.getName());
		getTextFieldDescription().setText(probability.getDescription());
	}
	
	public String getProbabilityCode()
	{
		return getTextFieldCode().getText().trim();
	}
	
	public String getProbabilityName()
	
	{
		return getTextFieldName().getText().trim();
	}
	
	public String getProbabilityDescription()
	{
		return getTextFieldDescription().getText().trim();
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
