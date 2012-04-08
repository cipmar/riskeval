package ro.rmc.riskeval.hibernate;

import java.util.List;

import ro.rmc.riskeval.dao.DaoException;
import ro.rmc.riskeval.dao.SectionDao;
import ro.rmc.riskeval.domain.Section;

public class SectionDaoHibernate implements SectionDao
{

	public void delete(Section section) throws DaoException
	{
		HibernateUtils.delete(section);
	}

	public List findAll()
	{
		return null;
	}

	public void store(Section section) throws DaoException
	{
		HibernateUtils.store(section);
	}

	public void update(Section section) throws DaoException
	{
		HibernateUtils.update(section);
	}

}
