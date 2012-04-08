package ro.rmc.riskeval.gui.tablemodels;

import java.util.Iterator;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import ro.rmc.riskeval.domain.GpCouple;
import ro.rmc.riskeval.domain.RiskLevel;

public class RiskLevelTableModel extends AbstractTableModel
{
	private List<RiskLevel> riskLevelList = null;

	private String[] columnsInfo = { "Cod", "Nume", "Cupluri G/P" };

	public RiskLevelTableModel(List<RiskLevel> riskLevelList)
	{
		this.riskLevelList = riskLevelList;
	}

	public int getColumnCount()
	{
		return columnsInfo.length;
	}

	public int getRowCount()
	{
		return riskLevelList.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex)
	{
		RiskLevel riskLevel = riskLevelList.get(rowIndex);

		switch (columnIndex)
		{
		case 0:
			return riskLevel.getCode();
		case 1:
			return riskLevel.getName();
		case 2:
			String str = "";

			for (Iterator iter = riskLevel.getGpCouples().iterator(); iter
					.hasNext();)
			{
				GpCouple gp = (GpCouple) iter.next();
				str = str + gp.toString() + " ";
			}
			return str;
		default:
			return null;
		}
	}

	public String getColumnName(int column)
	{
		return columnsInfo[column];
	}

	public List<RiskLevel> getRiskLevelList()
	{
		return riskLevelList;
	}
}
