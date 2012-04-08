package ro.rmc.riskeval.domain;

public class RiskSheetConsequence extends Entity
{
	private Consequence consequence;
	private ConsequenceLocation consequenceLocation;
	private RiskSheet riskSheet;
	
	public RiskSheetConsequence()
	{
		
	}
	
	public Consequence getConsequence()
	{
		return consequence;
	}
	
	public void setConsequence(Consequence consequence)
	{
		this.consequence = consequence;
	}
	
	public ConsequenceLocation getConsequenceLocation()
	{
		return consequenceLocation;
	}
	
	public void setConsequenceLocation(ConsequenceLocation consequenceLocation)
	{
		this.consequenceLocation = consequenceLocation;
	}
	
	public RiskSheet getRiskSheet()
	{
		return riskSheet;
	}
	
	public void setRiskSheet(RiskSheet riskSheet)
	{
		this.riskSheet = riskSheet;
	}
	
	public Gravity getGravity()
	{
		return getConsequence().getByConsequenceLocation(
				getConsequenceLocation()).getGravity();
	}
}
