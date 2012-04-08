package ro.rmc.riskeval.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import ro.rmc.riskeval.utils.IconsUtils;

public class AboutDialog extends JDialog
{

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JLabel jLabel = null;

	private JLabel labelHyperLink = null;

	private JLabel jLabel2 = null;

	private JButton buttonOk = null;

	/**
	 * @param owner
	 */
	public AboutDialog(Frame owner)
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
		this.setSize(300, 200);
		this.setModal(true);
		this.setTitle("About");
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
			jLabel2.setBounds(new Rectangle(15, 75, 271, 31));
			jLabel2.setText("<html>Aceasta aplicatie utilizeaza setul de pictograme SILK ICONS</html>");
			labelHyperLink = new JLabel();
			labelHyperLink.setBounds(new Rectangle(15, 105, 241, 16));
			labelHyperLink.setForeground(Color.blue);
			labelHyperLink.setFont(new Font("Dialog", Font.BOLD, 12));
			labelHyperLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
			labelHyperLink.setText("http://www.famfamfam.com/lab/icons/silk");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(15, 15, 136, 16));
			jLabel.setFont(new Font("Dialog", Font.BOLD, 14));
			jLabel.setIcon(IconsUtils.getInstance().getIcon("main.icon"));
			jLabel.setText("RiskEval 1.0");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel, null);
			jContentPane.add(labelHyperLink, null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(getButtonOk(), null);
		}
		return jContentPane;
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
			buttonOk.setBounds(new Rectangle(195, 135, 84, 26));
			buttonOk.setMargin(new Insets(2, 2, 2, 2));
			buttonOk.setText("Ok");
			buttonOk.setIcon(IconsUtils.getInstance().getIcon("button.ok"));
			
			ActionListener actionOk = new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
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

}
