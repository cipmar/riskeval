package ro.rmc.riskeval.dao;

import java.util.List;

import ro.rmc.riskeval.domain.Gravity;

public interface GravityDao
{
	public List<Gravity> findAll() throws DaoException;
	
	public void store(Gravity gravity) throws DaoException;
	
	public void delete(Gravity gravity) throws DaoException;
	
	public void update(Gravity gravity) throws DaoException;
}
