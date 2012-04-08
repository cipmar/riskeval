package ro.rmc.riskeval.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ro.rmc.riskeval.dao.ConsequenceLocationDao;
import ro.rmc.riskeval.dao.DaoException;
import ro.rmc.riskeval.domain.ConsequenceLocation;

public class ConsequenceLocationDaoHibernate implements ConsequenceLocationDao
{

	public void delete(ConsequenceLocation consequenceLocation) throws DaoException
	{
		Session session = null;
		Transaction tx = null;
		
		try
		{
			session = HibernateUtils.getSession();
			
			Query query;
			
			// legatura consequenceLocation -  RiskSheetConsequence
			query = session.createQuery("from RiskSheetConsequence where consequenceLocation = ?");
			query.setParameter(0, consequenceLocation);
			
			if (query.list().size() > 0)
				throw new RuntimeException(
						"Localizarea consecintei nu poate fi stearsa: este utilizata intr-o fisa de evaluare a riscurilor.");
			
			tx = session.beginTransaction();
			session.delete(consequenceLocation);
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
	public List<ConsequenceLocation> findAll() throws DaoException
	{
		return HibernateUtils.findAll("from ConsequenceLocation order by code");
	}

	public void store(ConsequenceLocation consequenceLocation) throws DaoException
	{
		HibernateUtils.store(consequenceLocation);
	}

	public void update(ConsequenceLocation consequenceLocation) throws DaoException
	{
		HibernateUtils.update(consequenceLocation);
	}

}
