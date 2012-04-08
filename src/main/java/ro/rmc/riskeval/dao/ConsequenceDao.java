package ro.rmc.riskeval.dao;

import java.util.List;

import ro.rmc.riskeval.domain.Consequence;

public interface ConsequenceDao
{
	public List<Consequence> findAll() throws DaoException;
	
	public void store(Consequence consequence) throws DaoException;
	
	public void delete(Consequence consequence) throws DaoException;
	
	public void update(Consequence consequence) throws DaoException;
}
