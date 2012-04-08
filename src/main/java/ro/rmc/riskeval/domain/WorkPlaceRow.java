package ro.rmc.riskeval.domain;

public class WorkPlaceRow
{
	private String no;
	private String section;
	private String sector;
	private String name;
	private String operations;
	private String personsNo;
	private String riskLevel;
	
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getNo()
	{
		return no;
	}
	public void setNo(String no)
	{
		this.no = no;
	}
	public String getOperations()
	{
		return operations;
	}
	public void setOperations(String operations)
	{
		this.operations = operations;
	}
	public String getPersonsNo()
	{
		return personsNo;
	}
	public void setPersonsNo(String personsNo)
	{
		this.personsNo = personsNo;
	}
	public String getRiskLevel()
	{
		return riskLevel;
	}
	public void setRiskLevel(String riskLevel)
	{
		this.riskLevel = riskLevel;
	}
	public String getSection()
	{
		return section;
	}
	public void setSection(String section)
	{
		this.section = section;
	}
	public String getSector()
	{
		return sector;
	}
	public void setSector(String sector)
	{
		this.sector = sector;
	}
	
	public WorkPlaceRow()
	{
	}
}
