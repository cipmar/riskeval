package ro.rmc.riskeval.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ro.rmc.riskeval.dao.ConsequenceDao;
import ro.rmc.riskeval.dao.DaoException;
import ro.rmc.riskeval.domain.Consequence;

public class ConsequenceDaoHibernate implements ConsequenceDao
{

	public void delete(Consequence consequence) throws DaoException
	{
		Session session = null;
		Transaction tx = null;
		
		try
		{
			session = HibernateUtils.getSession();
			
			Query query;
			
			// legatura consequence -  RiskSheetConsequence
			query = session.createQuery("from RiskSheetConsequence where consequence = ?");
			query.setParameter(0, consequence);
			
			if (query.list().size() > 0)
				throw new RuntimeException(
						"Consecinta nu poate fi stearsa: este utilizata intr-o fisa de evaluare a riscurilor.");
			
			tx = session.beginTransaction();
			session.delete(consequence);
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
	public List<Consequence> findAll() throws DaoException
	{
		return HibernateUtils.findAll("from Consequence order by code");
	}

	public void store(Consequence consequence) throws DaoException
	{
		HibernateUtils.store(consequence);
	}

	public void update(Consequence consequence) throws DaoException
	{
		HibernateUtils.update(consequence);
	}

}
