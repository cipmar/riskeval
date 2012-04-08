package ro.rmc.riskeval.dao;

import java.util.List;

import ro.rmc.riskeval.domain.ClgCouple;

public interface ClgCoupleDao
{
	public List<ClgCouple> findAll() throws DaoException;
	
	public void store(ClgCouple clgCouple) throws DaoException;
	
	public void delete(ClgCouple clgCouple) throws DaoException;
	
	public void update(ClgCouple clgCouple) throws DaoException;
}
