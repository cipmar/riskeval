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

import ro.rmc.riskeval.domain.Section;
import ro.rmc.riskeval.domain.Sector;
import ro.rmc.riskeval.utils.IconsUtils;

public class SectorDialog extends JDialog
{

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JLabel jLabel = null;

	private JLabel jLabel1 = null;

	private JTextField textFieldCompany = null;

	private JTextField textFieldSectionName = null;

	private JButton buttonOk = null;

	private JButton buttonCancel = null;

	private JLabel jLabel2 = null;
	
	private JTextField textFieldSector = null;
	
	private boolean ok = false;
	
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
	public SectorDialog(Frame owner)
	{
		super(owner);
		initialize();
		GuiTool.center(this, owner);
		
		addWindowListener(new WindowAdapter()
		{
			public void windowActivated(WindowEvent e)
			{
				getTextFieldSector().requestFocus();
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
		this.setSize(300, 174);
		this.setTitle("Sector");
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
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(15, 75, 38, 16));
			jLabel2.setText("Sector");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(15, 45, 36, 16));
			jLabel1.setText("Sectie");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(15, 15, 57, 16));
			jLabel.setText("Companie");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel, null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(getTextFieldCompany(), null);
			jContentPane.add(getTextFieldSectionName(), null);
			jContentPane.add(getButtonOk(), null);
			jContentPane.add(getButtonCancel(), null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(getTextFieldSector(), null);
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
			textFieldSectionName.setEditable(false);
			textFieldSectionName.setEnabled(true);
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
			buttonOk.setLocation(new Point(105, 105));
			
			ActionListener actionOk = new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					if (getTextFieldSector().getText().trim().length() == 0)
					{
						JOptionPane.showMessageDialog(SectorDialog.this,
								"Completati numele sectorului.");
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
			buttonCancel.setLocation(new Point(195, 105));
			
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
	 * This method initializes textFieldSector	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTextFieldSector()
	{
		if (textFieldSector == null)
		{
			textFieldSector = new JTextField();
			textFieldSector.setLocation(new Point(75, 75));
			textFieldSector.setSize(new Dimension(200, 20));
		}
		return textFieldSector;
	}

	/**
	 * @param parentSection the parentSection to set
	 */
	public void setParentSection(Section parentSection)
	{
		getTextFieldSectionName().setText(parentSection.getName());
		getTextFieldCompany().setText(parentSection.getCompany().getName());
	}
	
	public String getSectorName()
	{
		return getTextFieldSector().getText().trim();
	}

	public void setSector(Sector sector)
	{
		getTextFieldSector().setText(sector.getName());
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
