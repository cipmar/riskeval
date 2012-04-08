package ro.rmc.riskeval.gui.tablemodels;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.EtchedBorder;

import ro.rmc.riskeval.utils.IconsUtils;

public class ModuleListCellRenderer extends JLabel implements ListCellRenderer
{
	private static final long serialVersionUID = 1L;

	public Component getListCellRendererComponent(JList list, Object obj,
			int row, boolean sel, boolean hasFocus)
	{
		if (obj == null)
			return null;

		setOpaque(true);

		setIcon(IconsUtils.getInstance().getIcon("module.icon"));
		setText(obj.toString());
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setPreferredSize(new Dimension(0, 40));
		setForeground(SystemColor.textText);
		
		if (sel)
			setBackground(SystemColor.info);
		else
			setBackground(SystemColor.control);
		
		setBorder(new EtchedBorder());
		
		return this;
	}

}
