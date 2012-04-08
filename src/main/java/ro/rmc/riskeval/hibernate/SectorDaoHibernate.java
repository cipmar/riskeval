package ro.rmc.riskeval.hibernate;

import java.util.List;

import ro.rmc.riskeval.dao.DaoException;
import ro.rmc.riskeval.dao.SectorDao;
import ro.rmc.riskeval.domain.Sector;

public class SectorDaoHibernate implements SectorDao
{

	public void delete(Sector sector) throws DaoException
	{
		HibernateUtils.delete(sector);
	}

	public List findAll()
	{
		return null;
	}

	public void store(Sector sector) throws DaoException
	{
		HibernateUtils.store(sector);
	}

	public void update(Sector sector) throws DaoException
	{
		HibernateUtils.update(sector);
	}

}
