package ro.rmc.riskeval.hibernate;

import java.util.List;

import ro.rmc.riskeval.dao.CompanyDao;
import ro.rmc.riskeval.dao.DaoException;
import ro.rmc.riskeval.domain.Company;

public class CompanyDaoHibernate implements CompanyDao
{

	public void delete(Company company) throws DaoException
	{
		HibernateUtils.delete(company);
	}

	@SuppressWarnings("unchecked")
	public List<Company> findAll() throws DaoException
	{
		return HibernateUtils.findAll("from Company");
	}

	public void store(Company company) throws DaoException
	{
		HibernateUtils.store(company);
	}

	public void update(Company company) throws DaoException
	{
		HibernateUtils.update(company);
	}

}
