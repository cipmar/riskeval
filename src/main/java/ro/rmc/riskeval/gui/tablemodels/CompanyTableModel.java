package ro.rmc.riskeval.gui.tablemodels;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import ro.rmc.riskeval.domain.Company;

public class CompanyTableModel extends AbstractTableModel
{
	private List<Company> companyList;

	private String[] columnsInfo = { "Nume", "Adresa", "Telefon", "Fax",
			"Email" };

	public CompanyTableModel(List<Company> companyList)
	{
		this.companyList = companyList;
	}

	public int getColumnCount()
	{
		return columnsInfo.length;
	}

	public int getRowCount()
	{
		return companyList.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex)
	{
		Company company = (Company) companyList.get(rowIndex);

		switch (columnIndex)
		{
		case 0:
			return company.getName();
		case 1:
			return company.getAddress();
		case 2:
			return company.getPhone();
		case 3:
			return company.getFax();
		case 4:
			return company.getEmail();
		}
		return null;
	}
	
	@Override
	public String getColumnName(int arg0)
	{
		return columnsInfo[arg0];
	}
	
	public List<Company> getCompanyList()
	{
		return companyList;
	}
}
