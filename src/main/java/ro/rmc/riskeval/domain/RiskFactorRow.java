package ro.rmc.riskeval.domain;

public class RiskFactorRow
{
	private String component;
	private String riskFactor;
	private String riskManifestation;
	private String gravity;
	private String gravityClass;
	private String probability;
	private String riskLevel;
	private String measure;
	private String competence;
	private String terms;
	
	public String getCompetence()
	{
		return competence;
	}
	public void setCompetence(String competence)
	{
		this.competence = competence;
	}
	public String getMeasure()
	{
		return measure;
	}
	public void setMeasure(String measure)
	{
		this.measure = measure;
	}
	public String getTerms()
	{
		return terms;
	}
	public void setTerms(String terms)
	{
		this.terms = terms;
	}
	public String getComponent()
	{
		return component;
	}
	public void setComponent(String component)
	{
		this.component = component;
	}
	public String getGravity()
	{
		return gravity;
	}
	public void setGravity(String gravity)
	{
		this.gravity = gravity;
	}
	public String getGravityClass()
	{
		return gravityClass;
	}
	public void setGravityClass(String gravityClass)
	{
		this.gravityClass = gravityClass;
	}
	public String getProbability()
	{
		return probability;
	}
	public void setProbability(String probability)
	{
		this.probability = probability;
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
	public String getRiskManifestation()
	{
		return riskManifestation;
	}
	public void setRiskManifestation(String riskManifestation)
	{
		this.riskManifestation = riskManifestation;
	}
	
	public RiskFactorRow()
	{
	}
}
