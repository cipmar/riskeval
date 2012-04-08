package ro.rmc.riskeval.gui.tablemodels;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import ro.rmc.riskeval.domain.ConsequenceLocation;

public class ConsequenceLocationTableModel extends AbstractTableModel
{
	private static final long serialVersionUID = 1L;

	private List<ConsequenceLocation> consequenceLocationList = null;
	
	private String[] columnsInfo = {"Cod", "Nume"};

	public ConsequenceLocationTableModel(List<ConsequenceLocation> consequenceLocationList)
	{
		this.consequenceLocationList = consequenceLocationList;
	}

	public int getColumnCount()
	{
		return columnsInfo.length;
	}

	public int getRowCount()
	{
		return consequenceLocationList.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex)
	{
		ConsequenceLocation consequenceLocation = consequenceLocationList
				.get(rowIndex);

		switch (columnIndex)
		{
		case 0:
			return consequenceLocation.getCode();
		case 1:
			return consequenceLocation.getName();
		default:
			return null;
		}
	}

	@Override
	public String getColumnName(int column)
	{
		return columnsInfo[column];
	}

	/**
	 * @return the consequenceLocationList
	 */
	public List<ConsequenceLocation> getConsequenceLocationList()
	{
		return consequenceLocationList;
	}
}
