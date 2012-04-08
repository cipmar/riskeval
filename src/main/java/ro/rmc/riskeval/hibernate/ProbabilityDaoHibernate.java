package ro.rmc.riskeval.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ro.rmc.riskeval.dao.DaoException;
import ro.rmc.riskeval.dao.ProbabilityDao;
import ro.rmc.riskeval.domain.Probability;

public class ProbabilityDaoHibernate implements ProbabilityDao
{

	public void delete(Probability probability) throws DaoException
	{
		Session session = null;
		Transaction tx = null;
		
		try
		{
			session = HibernateUtils.getSession();
			
			Query query = session.createQuery("from GpCouple where probability = ?");
			query.setParameter(0, probability);
			
			// legatura probabilitate - cuplu G/P 
			if (query.list().size() > 0)
				throw new RuntimeException(
						"Probabilitatea nu poate fi stearsa: este utilizata intr-un cuplu gravitate / probabilitate.");
			
			// legatura probabilitate -  risksheet
			query = session.createQuery("from RiskSheet where probability = ?");
			query.setParameter(0, probability);
			
			if (query.list().size() > 0)
				throw new RuntimeException(
						"Probabilitatea nu poate fi stearsa: este utilizata intr-o fisa de evaluare a riscurilor.");
			
			tx = session.beginTransaction();
			session.delete(probability);
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
		return HibernateUtils.findAll("from Probability order by code");
	}

	public void store(Probability probability) throws DaoException
	{
		HibernateUtils.store(probability);
	}

	public void update(Probability probability) throws DaoException
	{
		HibernateUtils.update(probability);
	}
}
