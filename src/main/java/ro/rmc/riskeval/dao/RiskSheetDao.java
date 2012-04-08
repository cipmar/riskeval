package ro.rmc.riskeval.dao;

import java.util.List;

import ro.rmc.riskeval.domain.RiskSheet;

public interface RiskSheetDao
{
	public List<RiskSheet> findAll() throws DaoException;
	
	public void store(RiskSheet riskSheet) throws DaoException;

	public void delete(RiskSheet riskSheet) throws DaoException;

	public void update(RiskSheet riskSheet) throws DaoException;

}
