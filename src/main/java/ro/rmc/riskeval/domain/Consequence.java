package ro.rmc.riskeval.domain;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Consequence extends NamedEntity
{
	private String code;

	private Set<ClgCouple> consequenceLocations;

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public Set<ClgCouple> getConsequenceLocations()
	{
		if (consequenceLocations == null)
			consequenceLocations = new HashSet<ClgCouple>();
		return consequenceLocations;
	}

	public void setConsequenceLocations(
			Set<ClgCouple> consequenceLocations)
	{
		this.consequenceLocations = consequenceLocations;
	}
	
	public ClgCouple getByConsequenceLocation(
			ConsequenceLocation consequenceLocation)
	{
		for (Iterator iter = getConsequenceLocations().iterator(); iter.hasNext();)
		{
			ClgCouple clgCouple = (ClgCouple) iter.next();
			if (clgCouple.getConsequenceLocation() == consequenceLocation)
				return clgCouple;
		}
		return null;
	}
	
	@Override
	public String toString()
	{
		return getName();
	}
}
