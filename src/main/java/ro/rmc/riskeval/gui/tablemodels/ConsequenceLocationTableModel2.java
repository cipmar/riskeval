package ro.rmc.riskeval.gui.tablemodels;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import ro.rmc.riskeval.dao.ClgCoupleDao;
import ro.rmc.riskeval.domain.ClgCouple;
import ro.rmc.riskeval.domain.Consequence;
import ro.rmc.riskeval.domain.ConsequenceLocation;
import ro.rmc.riskeval.domain.Gravity;
import ro.rmc.riskeval.hibernate.ClgCoupleDaoHibernate;
import ro.rmc.riskeval.utils.ErrorUtils;

public class ConsequenceLocationTableModel2 extends AbstractTableModel
{
	private static final long serialVersionUID = 1L;

	private List<ConsequenceLocation> consequenceLocationList = null;
	private Consequence consequence;
	private ClgCoupleDao clgCoupleDao = null;

	private String[] columnsInfo = { "Cod", "Nume", "Clasa gravitate" };

	public ConsequenceLocationTableModel2(
			List<ConsequenceLocation> consequenceLocationList,
			Consequence consequence)
	{
		this.consequenceLocationList = consequenceLocationList;
		this.consequence = consequence;
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
		case 2:
			// gravitatea
			if (consequence != null)
			{
				ClgCouple clgCouple = consequence
						.getByConsequenceLocation(consequenceLocation);
				if ((clgCouple != null) && (clgCouple.getGravity() != null))
					return clgCouple.getGravity();
			}
		default:
			return null;
		}
	}

	@Override
	public String getColumnName(int column)
	{
		return columnsInfo[column];
	}

	public List<ConsequenceLocation> getConsequenceLocationList()
	{
		return consequenceLocationList;
	}

	public void setConsequence(Consequence consequence)
	{
		this.consequence = consequence;
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex)
	{
		return (columnIndex == 2) && (consequence != null);
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex)
	{
		try
		{
			ConsequenceLocation consequenceLocation = consequenceLocationList
					.get(rowIndex);
			ClgCouple clgCouple = consequence
					.getByConsequenceLocation(consequenceLocation);
			Gravity gravity = (Gravity) aValue;
			
			if (clgCouple == null)
			{
				clgCouple = new ClgCouple();
				clgCouple.setConsequenceLocation(consequenceLocation);
				clgCouple.setGravity(gravity);
				clgCouple.setConsequence(consequence);
				consequence.getConsequenceLocations().add(clgCouple);
				getClgCoupleDao().store(clgCouple);
			} else
			{
				clgCouple.setGravity(gravity);
				getClgCoupleDao().update(clgCouple);
			}
		}
		catch (Exception e)
		{
			ErrorUtils.showError(e, null);
		}
	}

	public ClgCoupleDao getClgCoupleDao()
	{
		if (clgCoupleDao == null)
			clgCoupleDao = new ClgCoupleDaoHibernate();
		return clgCoupleDao;
	}
}
