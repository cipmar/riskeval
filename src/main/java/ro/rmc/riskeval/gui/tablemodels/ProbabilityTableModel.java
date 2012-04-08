package ro.rmc.riskeval.gui.tablemodels;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import ro.rmc.riskeval.domain.Probability;

@SuppressWarnings("serial")
public class ProbabilityTableModel extends AbstractTableModel
{

	private List<Probability> probabilityList;
	
	private String[] columnsInfo = {"Nume", "Descriere", "Cod"};
	
	public ProbabilityTableModel(List<Probability> probabilityList)
	{
		this.probabilityList = probabilityList;
	}
	
	public int getColumnCount()
	{
		return columnsInfo.length;
	}

	public int getRowCount()
	{
		return probabilityList.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex)
	{
		Probability probability = (Probability) probabilityList.get(rowIndex);
		
		switch (columnIndex) 
		{
		case 0:
			return probability.getName();
		case 1:
			return probability.getDescription();
		case 2:
			return probability.getCode();
		default:
			return null;
		}
	}
	
	public String getColumnName(int columnIndex)
	{
		return columnsInfo[columnIndex];
	}

	public List<Probability> getProbabilityList()
	{
		return probabilityList;
	}
}

