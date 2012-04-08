package ro.rmc.riskeval.hibernate;

import java.util.List;

import ro.rmc.riskeval.dao.ClgCoupleDao;
import ro.rmc.riskeval.dao.DaoException;
import ro.rmc.riskeval.domain.ClgCouple;

public class ClgCoupleDaoHibernate implements ClgCoupleDao
{

	public void delete(ClgCouple clgCouple) throws DaoException
	{
		HibernateUtils.delete(clgCouple);
	}

	public List<ClgCouple> findAll() throws DaoException
	{
		return null;
	}

	public void store(ClgCouple clgCouple) throws DaoException
	{
		HibernateUtils.store(clgCouple);
	}

	public void update(ClgCouple clgCouple) throws DaoException
	{
		HibernateUtils.update(clgCouple);
	}
}
