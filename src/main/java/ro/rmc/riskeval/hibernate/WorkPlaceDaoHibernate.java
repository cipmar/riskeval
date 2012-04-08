package ro.rmc.riskeval.hibernate;

import java.util.List;

import ro.rmc.riskeval.dao.DaoException;
import ro.rmc.riskeval.dao.WorkPlaceDao;
import ro.rmc.riskeval.domain.WorkPlace;

public class WorkPlaceDaoHibernate implements WorkPlaceDao
{

	public void delete(WorkPlace workPlace) throws DaoException
	{
		HibernateUtils.delete(workPlace);
	}

	public List findAll()
	{
		return null;
	}

	public void store(WorkPlace workPlace) throws DaoException
	{
		HibernateUtils.store(workPlace);
	}

	public void update(WorkPlace workPlace) throws DaoException
	{
		HibernateUtils.update(workPlace);
	}
}