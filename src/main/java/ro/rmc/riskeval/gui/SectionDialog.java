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

import ro.rmc.riskeval.domain.Company;
import ro.rmc.riskeval.domain.Section;
import ro.rmc.riskeval.utils.IconsUtils;

public class SectionDialog extends JDialog
{

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JLabel jLabel = null;

	private JTextField textFieldCompany = null;

	private JLabel jLabel1 = null;

	private JTextField textFieldSectionName = null;

	private JButton buttonOk = null;

	private JButton buttonCancel = null;
	
	private boolean ok = false;
	
	/**
	 * @return the name
	 */
	public String getName()
	{
		return getTextFieldSectionName().getText();
	}

	/**
	 * @param company the company to set
	 */
	public void setCompany(Company company)
	{
		getTextFieldCompany().setText(company.getName());
	}

	/**
	 * @return the ok
	 */
	public boolean isOk()
	{
		return ok;
	}

	/**
	 * @param owner
	 */
	public SectionDialog(Frame owner)
	{
		super(owner);
		initialize();
		GuiTool.center(this, owner);
		
		addWindowListener(new WindowAdapter()
		{
			public void windowActivated(WindowEvent e)
			{
				getTextFieldSectionName().requestFocus();
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
		this.setSize(300, 146);
		this.setModal(true);
		this.setTitle("Sectie");
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
			jLabel1.setBounds(new Rectangle(15, 45, 46, 16));
			jLabel1.setText("Sectie");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(15, 15, 61, 16));
			jLabel.setText("Companie");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel, null);
			jContentPane.add(getTextFieldCompany(), null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(getTextFieldSectionName(), null);
			jContentPane.add(getButtonOk(), null);
			jContentPane.add(getButtonCancel(), null);
		}
		return jContentPane;
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
			textFieldCompany.setEnabled(true);
			textFieldCompany.setSize(new Dimension(200, 20));
			textFieldCompany.setEditable(false);
			textFieldCompany.setLocation(new Point(75, 15));
		}
		return textFieldCompany;
	}

	/**
	 * This method initializes textFieldSectionName	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTextFieldSectionName()
	{
		if (textFieldSectionName == null)
		{
			textFieldSectionName = new JTextField();
			textFieldSectionName.setLocation(new Point(75, 45));
			textFieldSectionName.setSize(new Dimension(200, 20));
		}
		return textFieldSectionName;
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
			buttonOk.setLocation(new Point(111, 81));

			ActionListener actionOk = new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					if (getTextFieldSectionName().getText().trim().length() == 0)
					{
						JOptionPane.showMessageDialog(SectionDialog.this,
								"Completati numele sectiei.");
					}  else
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
			buttonCancel.setLocation(new Point(201, 81));
			
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

	public void setSection(Section section)
	{
		getTextFieldSectionName().setText(section.getName());
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
