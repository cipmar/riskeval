package ro.rmc.riskeval.dao;

import java.util.List;

import ro.rmc.riskeval.domain.Sector;

public interface SectorDao
{
	public List findAll() throws DaoException;
	
	public void store(Sector sector) throws DaoException;
	
	public void delete(Sector sector) throws DaoException;
	
	public void update(Sector sector) throws DaoException;
}
