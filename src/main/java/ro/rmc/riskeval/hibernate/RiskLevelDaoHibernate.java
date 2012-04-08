package ro.rmc.riskeval.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ro.rmc.riskeval.dao.DaoException;
import ro.rmc.riskeval.dao.RiskLevelDao;
import ro.rmc.riskeval.domain.RiskLevel;

public class RiskLevelDaoHibernate implements RiskLevelDao
{

	public void delete(RiskLevel riskLevel) throws DaoException
	{
		Session session = null;
		Transaction tx = null;
		
		try
		{
			session = HibernateUtils.getSession();
			
			Query query;
			
			// legatura riskLevel -  RiskSheet
			query = session.createQuery("from RiskSheet where riskLevel = ?");
			query.setParameter(0, riskLevel);
			
			if (query.list().size() > 0)
				throw new RuntimeException(
						"Nivelul de risc nu poate fi sters: este utilizat intr-o fisa de evaluare a riscurilor.");
			
			tx = session.beginTransaction();
			session.delete(riskLevel);
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
		return HibernateUtils.findAll("from RiskLevel");
	}

	public void store(RiskLevel riskLevel) throws DaoException
	{
		HibernateUtils.store(riskLevel);
	}

	public void update(RiskLevel riskLevel) throws DaoException
	{
		HibernateUtils.update(riskLevel);
	}

}
