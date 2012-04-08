package ro.rmc.riskeval.gui;

import java.awt.BorderLayout;
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
import ro.rmc.riskeval.utils.IconsUtils;

public class CompanyDialog extends JDialog
{

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private JPanel panelTop = null;
	private JLabel jLabel = null;
	private JTextField textFieldName = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JLabel jLabel3 = null;
	private JTextField textFieldAddress = null;
	private JTextField textFieldPhone = null;
	private JTextField textFieldFax = null;
	private JLabel jLabel4 = null;
	private JTextField textFieldEmail = null;
	private JPanel panelBottom = null;
	private JButton buttonOk = null;
	private JButton buttonCancel = null;
	
	private boolean ok = false;
	private Company company;  //  @jve:decl-index=0:
	
	/**
	 * @return the companyAddress
	 */
	public String getCompanyAddress()
	{
		return getTextFieldAddress().getText().trim();
	}

	/**
	 * @return the companyEmail
	 */
	public String getCompanyEmail()
	{
		return getTextFieldEmail().getText().trim();
	}

	/**
	 * @return the companyFax
	 */
	public String getCompanyFax()
	{
		return getTextFieldFax().getText().trim();
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName()
	{
		return getTextFieldName().getText().trim();
	}

	/**
	 * @return the companyPhone
	 */
	public String getCompanyPhone()
	{
		return getTextFieldPhone().getText().trim();
	}

	/**
	 * @return the company
	 */
	public Company getCompany()
	{
		return company;
	}

	/**
	 * @param company the company to set
	 */
	public void setCompany(Company company)
	{
		this.company = company;
		
		textFieldName.setText(company.getName());
		textFieldAddress.setText(company.getAddress());
		textFieldPhone.setText(company.getPhone());
		textFieldFax.setText(company.getFax());
		textFieldEmail.setText(company.getEmail());
	}

	/**
	 * @param owner
	 */
	public CompanyDialog(Frame owner)
	{
		super(owner);
		initialize();
		GuiTool.center(this, owner);
		
		addWindowListener(new WindowAdapter()
		{
			public void windowActivated(WindowEvent e)
			{
				getTextFieldName().requestFocus();
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
		this.setSize(420, 183);
		this.setModal(true);
		this.setResizable(false);
		this.setTitle("Companie");
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
			jContentPane.add(getPanelTop(), BorderLayout.NORTH);
			jContentPane.add(getPanelBottom(), BorderLayout.SOUTH);
		}
		return jContentPane;
	}

	/**
	 * This method initializes panelTop	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelTop()
	{
		if (panelTop == null)
		{
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(8, 94, 55, 16));
			jLabel4.setText("Email");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(8, 72, 55, 16));
			jLabel3.setText("Fax");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(8, 50, 55, 16));
			jLabel2.setText("Telefon");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(8, 28, 55, 16));
			jLabel1.setText("Adresa");
			jLabel = new JLabel();
			jLabel.setText("Nume");
			jLabel.setLocation(new Point(8, 6));
			jLabel.setSize(new Dimension(55, 16));
			panelTop = new JPanel();
			panelTop.setLayout(null);
			panelTop.setPreferredSize(new Dimension(0, 120));
			panelTop.add(jLabel, null);
			panelTop.add(getTextFieldName(), null);
			panelTop.add(jLabel1, null);
			panelTop.add(jLabel2, null);
			panelTop.add(jLabel3, null);
			panelTop.add(getTextFieldAddress(), null);
			panelTop.add(getTextFieldPhone(), null);
			panelTop.add(getTextFieldFax(), null);
			panelTop.add(jLabel4, null);
			panelTop.add(getTextFieldEmail(), null);
		}
		return panelTop;
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
			textFieldName.setSize(new Dimension(325, 20));
			textFieldName.setLocation(new Point(76, 6));
		}
		return textFieldName;
	}

	/**
	 * This method initializes textFieldAddress	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTextFieldAddress()
	{
		if (textFieldAddress == null)
		{
			textFieldAddress = new JTextField();
			textFieldAddress.setBounds(new Rectangle(76, 28, 325, 20));
		}
		return textFieldAddress;
	}

	/**
	 * This method initializes textFieldPhone	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTextFieldPhone()
	{
		if (textFieldPhone == null)
		{
			textFieldPhone = new JTextField();
			textFieldPhone.setSize(new Dimension(325, 20));
			textFieldPhone.setLocation(new Point(76, 50));
		}
		return textFieldPhone;
	}

	/**
	 * This method initializes textFieldFax	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTextFieldFax()
	{
		if (textFieldFax == null)
		{
			textFieldFax = new JTextField();
			textFieldFax.setBounds(new Rectangle(76, 72, 325, 20));
		}
		return textFieldFax;
	}

	/**
	 * This method initializes textFieldEmail	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTextFieldEmail()
	{
		if (textFieldEmail == null)
		{
			textFieldEmail = new JTextField();
			textFieldEmail.setLocation(new Point(76, 94));
			textFieldEmail.setSize(new Dimension(325, 20));
		}
		return textFieldEmail;
	}

	/**
	 * This method initializes panelBottom	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelBottom()
	{
		if (panelBottom == null)
		{
			panelBottom = new JPanel();
			panelBottom.setLayout(null);
			panelBottom.setPreferredSize(new Dimension(0, 32));
			panelBottom.add(getButtonOk(), null);
			panelBottom.add(getButtonCancel(), null);
		}
		return panelBottom;
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
			buttonOk.setIcon(IconsUtils.getInstance().getIcon("button.ok"));
			buttonOk.setFocusPainted(true);
			buttonOk.setMargin(new Insets(2, 2, 2, 2));
			buttonOk.setLocation(new Point(227, 4));
			
			ActionListener actionOk = new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					if (getTextFieldName().getText().trim().length() == 0)
					{
						JOptionPane.showMessageDialog(CompanyDialog.this,
								"Completati numele companiei.");
					} else if (getTextFieldAddress().getText().trim().length() == 0)
					{
						JOptionPane.showMessageDialog(CompanyDialog.this,
								"Completati adresa companiei.");
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
			buttonCancel.setIcon(IconsUtils.getInstance().getIcon("button.cancel"));
			buttonCancel.setMargin(new Insets(2, 2, 2, 2));
			buttonCancel.setLocation(new Point(316, 4));
			
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

}  //  @jve:decl-index=0:visual-constraint="13,12"
