package ro.rmc.riskeval.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ro.rmc.riskeval.dao.DaoException;
import ro.rmc.riskeval.dao.RiskFactorDao;
import ro.rmc.riskeval.domain.RiskFactor;

public class RiskFactorDaoHibernate implements RiskFactorDao
{

	public void delete(RiskFactor riskFactor) throws DaoException
	{
		Session session = null;
		Transaction tx = null;
		
		try
		{
			session = HibernateUtils.getSession();
			
			Query query;
			
			// legatura riskFactor -  RiskSheetConsequence
			query = session.createQuery("from RiskSheet where riskFactor = ?");
			query.setParameter(0, riskFactor);
			
			if (query.list().size() > 0)
				throw new RuntimeException(
						"Factorul de risc nu poate fi sters: este utilizat intr-o fisa de evaluare a riscurilor.");
			
			// legatura riskFactor -  riskFactor
			query = session.createQuery("from RiskFactor where parentRiskFactor = ?");
			query.setParameter(0, riskFactor);
			
			if (query.list().size() > 0)
				throw new RuntimeException(
						"Factorul de risc nu poate fi sters: exista subfactori de risc.");

			tx = session.beginTransaction();
			session.delete(riskFactor);
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
		return HibernateUtils
				.findAll("from RiskFactor where parentRiskFactor = null");
	}

	public void store(RiskFactor riskFactor) throws DaoException
	{
		HibernateUtils.store(riskFactor);
	}

	public void update(RiskFactor riskFactor) throws DaoException
	{
		HibernateUtils.update(riskFactor);
	}

}
