package ro.rmc.riskeval.dao;

import java.util.List;

import ro.rmc.riskeval.domain.ConsequenceLocation;

public interface ConsequenceLocationDao
{
	public List<ConsequenceLocation> findAll() throws DaoException;
	
	public void store(ConsequenceLocation consequenceLocation) throws DaoException;
	
	public void delete(ConsequenceLocation consequenceLocation) throws DaoException;
	
	public void update(ConsequenceLocation consequenceLocation) throws DaoException;
}
