package ro.rmc.riskeval.domain;

import java.util.HashSet;
import java.util.Set;

public class RiskFactor extends NamedEntity
{
	private String code;
	
	private Set<RiskFactor> riskFactors = null;
	
	private RiskFactor parentRiskFactor;
	
	public RiskFactor getParentRiskFactor()
	{
		return parentRiskFactor;
	}

	public void setParentRiskFactor(RiskFactor parentRiskFactor)
	{
		this.parentRiskFactor = parentRiskFactor;
	}

	public Set<RiskFactor> getRiskFactors()
	{
		if (riskFactors == null)
			riskFactors = new HashSet<RiskFactor>();
		return riskFactors;
	}

	public void setRiskFactors(Set<RiskFactor> riskFactors)
	{
		this.riskFactors = riskFactors;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}
	
	public String toString()
	{
		return  "[" + getCode() + "] " + getName();
	}
	
	public RiskFactor getFirstParent()
	{
		RiskFactor riskFactor = this;
		
		while (riskFactor.getParentRiskFactor() != null)
			riskFactor = riskFactor.getParentRiskFactor();
		
		return riskFactor;
	}
}
