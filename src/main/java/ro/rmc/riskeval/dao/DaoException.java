package ro.rmc.riskeval.dao;

public class DaoException extends Exception
{
	DaoException (String message)
	{
		super(message);
	}
	
	public DaoException(Throwable e)
	{
		super(e);
	}
}
