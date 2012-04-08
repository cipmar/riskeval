package ro.rmc.riskeval.dao;

import java.util.List;

import ro.rmc.riskeval.domain.Probability;

public interface ProbabilityDao
{
	public List <Probability> findAll() throws DaoException;
	
	public void store(Probability probability) throws DaoException;
	
	public void delete(Probability probability) throws DaoException;
	
	public void update(Probability probability) throws DaoException;
}
