package ro.rmc.riskeval.gui.tablemodels;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import ro.rmc.riskeval.domain.Gravity;
import ro.rmc.riskeval.domain.RiskSheetConsequence;

public class RiskSheetConsequenceTableModel extends AbstractTableModel
{
	private List<RiskSheetConsequence> riskSheetConsequences;
	private String[] columnsInfo = {"Consecinta", "Localizare", "Gravitate"};
	
	public int getColumnCount()
	{
		return columnsInfo.length;
	}

	public int getRowCount()
	{
		return riskSheetConsequences.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex)
	{
		RiskSheetConsequence riskSheetConsequence = riskSheetConsequences
				.get(rowIndex);
		
		switch (columnIndex)
		{
		case 0:
			if (riskSheetConsequence.getConsequence() != null)
				return riskSheetConsequence.getConsequence().getName();
		case 1:
			if (riskSheetConsequence.getConsequenceLocation() != null)
				return riskSheetConsequence.getConsequenceLocation().getName();
		case 2:
			Gravity gravity = riskSheetConsequence.getGravity();
			if (gravity != null)
				return gravity.toString();
		}
		return null;
	}

	public List<RiskSheetConsequence> getRiskSheetConsequences()
	{
		return riskSheetConsequences;
	}

	public void setRiskSheetConsequences(
			List<RiskSheetConsequence> riskSheetConsequences)
	{
		this.riskSheetConsequences = riskSheetConsequences;
	}
	
	public String getColumnName(int column)
	{
		return columnsInfo[column];
	}
}
