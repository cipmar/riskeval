package ro.rmc.riskeval.domain;

public class Gravity extends NamedEntity
{
	private String code;
	
	private String description;

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}
	
	@Override
	public String toString()
	{
		return getCode() + "-" + getName() + " (" + getDescription() + ")";
	}
	
}
