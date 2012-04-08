package ro.rmc.riskeval.hibernate;

import java.util.List;

import ro.rmc.riskeval.dao.DaoException;
import ro.rmc.riskeval.dao.RiskSheetDao;
import ro.rmc.riskeval.domain.RiskSheet;

public class RiskSheetDaoHibernate implements RiskSheetDao
{

	public void delete(RiskSheet riskSheet) throws DaoException
	{
		HibernateUtils.delete(riskSheet);
	}

	public List<RiskSheet> findAll()
	{
		return null;
	}

	public void store(RiskSheet riskSheet) throws DaoException
	{
		HibernateUtils.store(riskSheet);
	}

	public void update(RiskSheet riskSheet) throws DaoException
	{
		HibernateUtils.update(riskSheet);
	}

}
