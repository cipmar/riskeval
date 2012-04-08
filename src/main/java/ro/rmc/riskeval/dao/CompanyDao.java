package ro.rmc.riskeval.dao;

import java.util.List;

import ro.rmc.riskeval.domain.Company;

public interface CompanyDao
{
	public List<Company> findAll() throws DaoException;
	
	public void store(Company company) throws DaoException;
	
	public void delete(Company company) throws DaoException;
	
	public void update(Company company) throws DaoException;
}
