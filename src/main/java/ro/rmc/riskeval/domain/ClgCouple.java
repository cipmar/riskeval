package ro.rmc.riskeval.domain;


public class ClgCouple extends Entity
{
	private ConsequenceLocation consequenceLocation;
	
	private Gravity gravity;
	
	private Consequence consequence;
	
	public Consequence getConsequence()
	{
		return consequence;
	}

	public void setConsequence(Consequence consequence)
	{
		this.consequence = consequence;
	}

	public ClgCouple()
	{
		
	}

	public ConsequenceLocation getConsequenceLocation()
	{
		return consequenceLocation;
	}

	public void setConsequenceLocation(ConsequenceLocation consequenceLocation)
	{
		this.consequenceLocation = consequenceLocation;
	}

	public Gravity getGravity()
	{
		return gravity;
	}

	public void setGravity(Gravity gravity)
	{
		this.gravity = gravity;
	}
}
