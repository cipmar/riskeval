package ro.rmc.riskeval.dao;

import java.util.List;

import ro.rmc.riskeval.domain.WorkPlace;

public interface WorkPlaceDao
{
	public List findAll() throws DaoException;
	
	public void store(WorkPlace workPlace) throws DaoException;
	
	public void delete(WorkPlace workPlace) throws DaoException;
	
	public void update(WorkPlace workPlace) throws DaoException;
}
