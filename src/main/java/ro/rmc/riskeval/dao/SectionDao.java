package ro.rmc.riskeval.dao;

import java.util.List;

import ro.rmc.riskeval.domain.Section;

public interface SectionDao
{
	public List findAll();
	
	public void store(Section section) throws DaoException;
	
	public void delete(Section section) throws DaoException;
	
	public void update(Section section) throws DaoException;
}
