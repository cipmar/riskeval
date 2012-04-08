package ro.rmc.riskeval.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import ro.rmc.riskeval.dao.DaoException;

public class HibernateUtils
{
	private static SessionFactory sessionFactory = null;
	private static Session session = null;
	
	public static SessionFactory getSessionFactory()
	{
		if (sessionFactory == null)
			sessionFactory = new Configuration().configure().buildSessionFactory();
		return sessionFactory;
	}
	
	public static void delete(Object o) throws DaoException
	{
		Session session = null;
		Transaction tx = null;
		
		try
		{
			session = HibernateUtils.getSession();
			tx = session.beginTransaction();
			session.delete(o);
			tx.commit();
		}
		catch (RuntimeException e)
		{
			if (tx != null)
			{
				tx.rollback();
			}
			throw new DaoException(e);
		}
	}
	
	public static void store(Object o) throws DaoException
	{
		Session session = null;
		Transaction tx = null;
		
		try
		{
			session = HibernateUtils.getSession();
			tx = session.beginTransaction();
			session.save(o);
			tx.commit();
		}
		catch (RuntimeException e)
		{
			if (tx != null)
			{
				tx.rollback();
			}
			throw new DaoException(e);
		}
	}
	
	public static void update(Object o) throws DaoException
	{
		Session session = null;
		Transaction tx = null;

		try
		{
			session = HibernateUtils.getSession();
			tx = session.beginTransaction();
			session.update(o);
			tx.commit();
		}
		catch (RuntimeException e)
		{
			if (tx != null)
			{
				tx.rollback();
			}
			throw new DaoException(e);
		}
	}
	
	public static List findAll(String queryStr) throws DaoException
	{
		Session session = null;

		try
		{
			session = HibernateUtils.getSession();
			Query query = session.createQuery(queryStr);
			List list = query.list();
			return list;
		}
		catch (RuntimeException e)
		{
			throw new DaoException(e);
		}
	}

	public static Session getSession()
	{
		if (session == null)
			session = getSessionFactory().openSession();
		return session;
	}
	
	public static void closeSession()
	{
		if (session != null)
		{
			session.close();
			session = null;
		}
	}
}
