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
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;

import ro.rmc.riskeval.domain.Sector;
import ro.rmc.riskeval.domain.WorkPlace;
import ro.rmc.riskeval.utils.IconsUtils;

public class WorkPlaceDialog extends JDialog
{

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JLabel jLabel = null;

	private JLabel jLabel1 = null;

	private JLabel jLabel2 = null;

	private JLabel jLabel3 = null;

	private JTextField textFieldCompany = null;

	private JTextField textFieldSection = null;

	private JTextField textFieldSector = null;

	private JTextField textFieldWorkPlace = null;

	private JButton buttonOk = null;

	private JButton buttonCancel = null;

	private boolean ok;

	private JLabel jLabel4 = null;

	private JTextField textFieldPersonsNo = null;

	private JLabel jLabel5 = null;

	private JScrollPane jScrollPane = null;

	private JTextPane textPaneOperations = null;

	private JLabel jLabel41 = null;

	private JTextField textFieldWorkingHours = null;

	private JScrollPane jScrollPane1 = null;

	private JTextPane textPaneEvaluationTeam = null;

	private JLabel jLabel6 = null;
	
	/**
	 * @param owner
	 */
	public WorkPlaceDialog(Frame owner)
	{
		super(owner);
		initialize();
		GuiTool.center(this, owner);
		
		addWindowListener(new WindowAdapter()
		{
			public void windowActivated(WindowEvent e)
			{
				getTextFieldWorkPlace().requestFocus();
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
		this.setSize(325, 414);
		this.setModal(true);
		this.setTitle("Post lucru");
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
			jLabel6 = new JLabel();
			jLabel6.setBounds(new Rectangle(15, 270, 76, 39));
			jLabel6.setText("<html>Echipa de evaluare</html>");
			jLabel41 = new JLabel();
			jLabel41.setBounds(new Rectangle(15, 165, 91, 16));
			jLabel41.setText("Ore lucratoare");
			jLabel5 = new JLabel();
			jLabel5.setBounds(new Rectangle(16, 197, 90, 16));
			jLabel5.setText("Operatii");
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(15, 135, 91, 16));
			jLabel4.setText("Nr. persoane");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(15, 105, 91, 16));
			jLabel3.setText("Post lucru");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(15, 75, 91, 16));
			jLabel2.setText("Sector");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(15, 45, 91, 16));
			jLabel1.setText("Sectie");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(15, 15, 91, 16));
			jLabel.setText("Companie");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel, null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(getTextFieldCompany(), null);
			jContentPane.add(getTextFieldSection(), null);
			jContentPane.add(getTextFieldSector(), null);
			jContentPane.add(getTextFieldWorkPlace(), null);
			jContentPane.add(getButtonOk(), null);
			jContentPane.add(getButtonCancel(), null);
			jContentPane.add(jLabel4, null);
			jContentPane.add(getTextFieldPersonsNo(), null);
			jContentPane.add(jLabel5, null);
			jContentPane.add(getJScrollPane(), null);
			jContentPane.add(jLabel41, null);
			jContentPane.add(getTextFieldWorkingHours(), null);
			jContentPane.add(getJScrollPane1(), null);
			jContentPane.add(jLabel6, null);
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
			textFieldCompany.setLocation(new Point(105, 15));
			textFieldCompany.setEditable(false);
			textFieldCompany.setSize(new Dimension(196, 20));
		}
		return textFieldCompany;
	}

	/**
	 * This method initializes textFieldSection	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTextFieldSection()
	{
		if (textFieldSection == null)
		{
			textFieldSection = new JTextField();
			textFieldSection.setBounds(new Rectangle(105, 45, 196, 20));
			textFieldSection.setEditable(false);
		}
		return textFieldSection;
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
			textFieldSector.setBounds(new Rectangle(105, 75, 196, 20));
			textFieldSector.setEditable(false);
		}
		return textFieldSector;
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
			textFieldWorkPlace.setBounds(new Rectangle(105, 105, 196, 20));
		}
		return textFieldWorkPlace;
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
			buttonOk.setLocation(new Point(120, 345));
			buttonOk.setText("Ok");
			buttonOk.setMargin(new Insets(2, 2, 2, 2));
			buttonOk.setIcon(IconsUtils.getInstance().getIcon("button.ok"));
			buttonOk.setSize(new Dimension(85, 25));
			
			ActionListener actionOk = new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					if (getTextFieldWorkPlace().getText().trim().length() == 0)
					{
						JOptionPane.showMessageDialog(WorkPlaceDialog.this,
								"Completati numele locului de munca.");
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
			buttonCancel.setLocation(new Point(210, 345));
			buttonCancel.setText("Renunta");
			buttonCancel.setMargin(new Insets(2, 2, 2, 2));
			buttonCancel.setIcon(IconsUtils.getInstance().getIcon("button.cancel"));
			buttonCancel.setSize(new Dimension(85, 25));
			
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

	public void setParentSector(Sector sector)
	{
		getTextFieldCompany().setText(sector.getSection().getCompany().getName());
		getTextFieldSection().setText(sector.getSection().getName());
		getTextFieldSector().setText(sector.getName());
	}

	public String getWorkPlaceName()
	{
		return getTextFieldWorkPlace().getText().trim();
	}

	public void setWorkPlace(WorkPlace workPlace)
	{
		getTextFieldWorkPlace().setText(workPlace.getName());
		getTextFieldPersonsNo().setText(workPlace.getPersonsNo());
		getTextFieldWorkingHours().setText(workPlace.getWorkingHours());
		getTextPaneOperations().setText(workPlace.getOperations());
		getTextPaneEvaluationTeam().setText(workPlace.getEvaluationTeam());
	}

	/**
	 * This method initializes textFieldPersonsNo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTextFieldPersonsNo()
	{
		if (textFieldPersonsNo == null)
		{
			textFieldPersonsNo = new JTextField();
			textFieldPersonsNo.setBounds(new Rectangle(105, 135, 196, 20));
		}
		return textFieldPersonsNo;
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
			jScrollPane.setBounds(new Rectangle(105, 195, 196, 61));
			jScrollPane.setViewportView(getTextPaneOperations());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes TextPaneOperations	
	 * 	
	 * @return javax.swing.JTextPane	
	 */
	private JTextPane getTextPaneOperations()
	{
		if (textPaneOperations == null)
		{
			textPaneOperations = new JTextPane();
		}
		return textPaneOperations;
	}

	public String getPersonsNo()
	{
		return getTextFieldPersonsNo().getText().trim();
	}

	public String getOperations()
	{
		return getTextPaneOperations().getText().trim();
	}

	/**
	 * This method initializes textFieldWorkingHours	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTextFieldWorkingHours()
	{
		if (textFieldWorkingHours == null)
		{
			textFieldWorkingHours = new JTextField();
			textFieldWorkingHours.setBounds(new Rectangle(105, 165, 196, 20));
		}
		return textFieldWorkingHours;
	}

	public String getWorkingHours()
	{
		return getTextFieldWorkingHours().getText().trim();
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
			jScrollPane1.setBounds(new Rectangle(104, 270, 197, 61));
			jScrollPane1.setViewportView(getTextPaneEvaluationTeam());
		}
		return jScrollPane1;
	}

	/**
	 * This method initializes TextPaneEvaluationTeam	
	 * 	
	 * @return javax.swing.JTextPane	
	 */
	private JTextPane getTextPaneEvaluationTeam()
	{
		if (textPaneEvaluationTeam == null)
		{
			textPaneEvaluationTeam = new JTextPane();
		}
		return textPaneEvaluationTeam;
	}

	public String getEvaluationTeam()
	{
		return getTextPaneEvaluationTeam().getText().trim();
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
