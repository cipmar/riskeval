package ro.rmc.riskeval.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ro.rmc.riskeval.dao.DaoException;
import ro.rmc.riskeval.dao.GravityDao;
import ro.rmc.riskeval.domain.Gravity;

public class GravityDaoHibernate implements GravityDao
{

	public void delete(Gravity gravity) throws DaoException
	{
		Session session = null;
		Transaction tx = null;
		
		try
		{
			session = HibernateUtils.getSession();
			
			Query query;
			
			// legatura gravity - cuplu G/P 
			query = session.createQuery("from GpCouple where gravity = ?");
			query.setParameter(0, gravity);
			
			if (query.list().size() > 0)
				throw new RuntimeException(
						"Clasa de gravitate nu poate fi stearsa: este utilizata intr-un cuplu gravitate / probabilitate.");
			
			// legatura gravity -  risksheet
			query = session.createQuery("from RiskSheet where gravity = ?");
			query.setParameter(0, gravity);
			
			if (query.list().size() > 0)
				throw new RuntimeException(
						"Clasa de gravitate nu poate fi stearsa: este utilizata intr-o fisa de evaluare a riscurilor.");
			
			// legatura gravity -  clgcouple
			query = session.createQuery("from ClgCouple where gravity = ?");
			query.setParameter(0, gravity);
			
			if (query.list().size() > 0)
				throw new RuntimeException(
						"Clasa de gravitate nu poate fi stearsa: este utilizata ca gravitatea la un cuplu consecinta / localizare consecinta.");

			tx = session.beginTransaction();
			session.delete(gravity);
			tx.commit();
		}
		catch (RuntimeException e)
		{
			if (tx != null && tx.isActive())
			{
				tx.rollback();
			}
			throw new DaoException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public List findAll() throws DaoException
	{
		return HibernateUtils.findAll("from Gravity order by code");
	}

	public void store(Gravity gravity) throws DaoException
	{
		HibernateUtils.store(gravity);
	}

	public void update(Gravity gravity) throws DaoException
	{
		HibernateUtils.update(gravity);
	}

}
