package ro.rmc.riskeval.domain;

public class RiskFactorMeasureRow
{
	private String no;
	private String riskManifestation;
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
	public String getNo()
	{
		return no;
	}
	public void setNo(String no)
	{
		this.no = no;
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
	public String getTerms()
	{
		return terms;
	}
	public void setTerms(String terms)
	{
		this.terms = terms;
	}
	
	public RiskFactorMeasureRow()
	{
	}
}
