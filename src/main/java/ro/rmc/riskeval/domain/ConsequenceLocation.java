package ro.rmc.riskeval.domain;

public class ConsequenceLocation extends NamedEntity
{
	private String code;

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}
	
	public ConsequenceLocation()
	{
	}
	
	@Override
	public String toString()
	{
		return getName();
	}
}
