package ro.rmc.riskeval.domain;

import java.util.HashSet;
import java.util.Set;

public class RiskSheet extends Entity
{
	private String manifestation;
	private String measures;
	private String competence;
	private String terms;
	private Gravity gravity;
	private Probability probability;
	private RiskLevel riskLevel;
	private Set<RiskSheetConsequence> riskSheetConsequences;
	
	private WorkPlace workPlace;
	private RiskFactor riskFactor;
	
	/**
	 * default constructor
	 *
	 */
	public RiskSheet()
	{
	}

	public String getCompetence()
	{
		return competence;
	}

	public void setCompetence(String competence)
	{
		this.competence = competence;
	}

	public Gravity getGravity()
	{
		return gravity;
	}

	public void setGravity(Gravity gravity)
	{
		this.gravity = gravity;
	}

	public String getManifestation()
	{
		return manifestation;
	}

	public void setManifestation(String manifestation)
	{
		this.manifestation = manifestation;
	}

	public String getMeasures()
	{
		return measures;
	}

	public void setMeasures(String measures)
	{
		this.measures = measures;
	}

	public Probability getProbability()
	{
		return probability;
	}

	public void setProbability(Probability probability)
	{
		this.probability = probability;
	}

	public RiskFactor getRiskFactor()
	{
		return riskFactor;
	}

	public void setRiskFactor(RiskFactor riskFactor)
	{
		this.riskFactor = riskFactor;
	}

	public RiskLevel getRiskLevel()
	{
		return riskLevel;
	}

	public void setRiskLevel(RiskLevel riskLevel)
	{
		this.riskLevel = riskLevel;
	}

	public String getTerms()
	{
		return terms;
	}

	public void setTerms(String terms)
	{
		this.terms = terms;
	}

	public WorkPlace getWorkPlace()
	{
		return workPlace;
	}

	public void setWorkPlace(WorkPlace workPlace)
	{
		this.workPlace = workPlace;
	}

	public Set<RiskSheetConsequence> getRiskSheetConsequences()
	{
		if (riskSheetConsequences == null)
			riskSheetConsequences = new HashSet<RiskSheetConsequence>();
		return riskSheetConsequences;
	}

	public void setRiskSheetConsequences(
			Set<RiskSheetConsequence> riskSheetConsequences)
	{
		this.riskSheetConsequences = riskSheetConsequences;
	}
}
