package ro.rmc.riskeval.domain;

public class GpCouple extends Entity
{
	private Probability probability;
	private Gravity gravity;
	private RiskLevel riskLevel;

	public Gravity getGravity()
	{
		return gravity;
	}

	public void setGravity(Gravity gravity)
	{
		this.gravity = gravity;
	}

	public Probability getProbability()
	{
		return probability;
	}

	public void setProbability(Probability probability)
	{
		this.probability = probability;
	}

	public String toString()
	{
		return "(" + getGravity().getCode() + ", " + 
			getProbability().getCode() + ")";
	}

	public RiskLevel getRiskLevel()
	{
		return riskLevel;
	}

	public void setRiskLevel(RiskLevel riskLevel)
	{
		this.riskLevel = riskLevel;
	}
}
