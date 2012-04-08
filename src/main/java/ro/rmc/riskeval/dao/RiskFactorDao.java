package ro.rmc.riskeval.dao;

import java.util.List;

import ro.rmc.riskeval.domain.RiskFactor;

public interface RiskFactorDao
{
	public List<RiskFactor> findAll() throws DaoException;

	public void store(RiskFactor riskFactor) throws DaoException;

	public void delete(RiskFactor riskFactor) throws DaoException;

	public void update(RiskFactor riskFactor) throws DaoException;

}
