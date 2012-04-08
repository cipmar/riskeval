package ro.rmc.riskeval.gui.tablemodels;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import ro.rmc.riskeval.domain.Gravity;

class ColumnData
{
	private String name;
	private int size;
	
	public ColumnData(String name, int size)
	{
		super();
		this.name = name;
		this.size = size;
	}
	
	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	/**
	 * @return the size
	 */
	public int getSize()
	{
		return size;
	}
	/**
	 * @param size the size to set
	 */
	public void setSize(int size)
	{
		this.size = size;
	}
}

public class GravityTableModel extends AbstractTableModel
{
	private static final long serialVersionUID = 1L;
	
	private List<Gravity> gravityList;
	
	private ColumnData[] columnsInfo = {
		new ColumnData("Nume", 100),
		new ColumnData("Descriere", 200),
		new ColumnData("Cod", 80)};
	
	public GravityTableModel(List<Gravity> gravityList)
	{
		this.gravityList = gravityList;
	}

	public int getColumnCount()
	{
		return columnsInfo.length;
	}

	public int getRowCount()
	{
		return gravityList.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex)
	{
		Gravity gravity = (Gravity) gravityList.get(rowIndex);
		
		switch (columnIndex)
		{
		case 0:
			return gravity.getName();
		case 1:
			return gravity.getDescription();
		case 2:
			return gravity.getCode();
		default:
			return null;
		}
	}
	
	public String getColumnName(int column)
	{
		return columnsInfo[column].getName();
	}
	
	public List<Gravity> getGravityList()
	{
		return gravityList;
	}
}
