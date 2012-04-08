package ro.rmc.riskeval.domain;

public class RiskFactorSummaryRaw
{
	private String component;
	private String riskFactor;
	private String riskLevel;
	private String risksCount;
	private String risksPercentage;
	
	public String getComponent()
	{
		return component;
	}
	public void setComponent(String component)
	{
		this.component = component;
	}
	public String getRiskFactor()
	{
		return riskFactor;
	}
	public void setRiskFactor(String riskFactor)
	{
		this.riskFactor = riskFactor;
	}
	public String getRiskLevel()
	{
		return riskLevel;
	}
	public void setRiskLevel(String riskLevel)
	{
		this.riskLevel = riskLevel;
	}
	public String getRisksCount()
	{
		return risksCount;
	}
	public void setRisksCount(String risksCount)
	{
		this.risksCount = risksCount;
	}
	public String getRisksPercentage()
	{
		return risksPercentage;
	}
	public void setRisksPercentage(String risksPercentage)
	{
		this.risksPercentage = risksPercentage;
	}
	
	
}
