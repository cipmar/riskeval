package ro.rmc.riskeval.dao;

import java.util.List;

import ro.rmc.riskeval.domain.RiskLevel;

public interface RiskLevelDao
{
	public List<RiskLevel> findAll() throws DaoException;

	public void store(RiskLevel riskLevel) throws DaoException;

	public void delete(RiskLevel riskLevel) throws DaoException;

	public void update(RiskLevel riskLevel) throws DaoException;
}
