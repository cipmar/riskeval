package ro.rmc.riskeval.gui.tablemodels;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import ro.rmc.riskeval.domain.Consequence;

public class ConsequenceTableModel extends AbstractTableModel
{
	private static final long serialVersionUID = 1L;
	
	private List<Consequence> consequenceList;
	private String[] columnsInfo = {"Cod", "Nume"};
	
	public ConsequenceTableModel(List<Consequence> consequenceList)
	{
		this.consequenceList = consequenceList;
	}

	public int getColumnCount()
	{
		return columnsInfo.length;
	}

	public int getRowCount()
	{
		return consequenceList.size();
	}

	public Object getValueAt(int rowIndex, int columnnIndex)
	{
		Consequence consequence = (Consequence)consequenceList.get(rowIndex);
		
		switch (columnnIndex)
		{
			case 0:
				return consequence.getCode();
			case 1:
				return consequence.getName();
			default:
				return null;
		}
	}
	
	@Override
	public String getColumnName(int column)
	{
		return columnsInfo[column];
	}

	public List<Consequence> getConsequenceList()
	{
		return consequenceList;
	}
}
