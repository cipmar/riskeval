package ro.rmc.riskeval.gui;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import ro.rmc.riskeval.utils.IconsUtils;

public class ConsequenceCellRenderer extends DefaultTableCellRenderer
{
	private static final long serialVersionUID = 1L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column)
	{
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
				row, column);

		if (column == 0)
		{
			setIcon(IconsUtils.getInstance().getIcon("list.consequence"));
		}

		return this;
	}
}
