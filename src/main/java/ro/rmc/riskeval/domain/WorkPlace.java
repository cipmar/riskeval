package ro.rmc.riskeval.domain;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class WorkPlace extends NamedEntity
{
	private Sector sector;
	private Set<RiskSheet> riskSheets;
	private String personsNo;
	private String operations;
	private String workingHours;
	private String evaluationTeam;

	/**
	 * @return the sector
	 */
	public Sector getSector()
	{
		return sector;
	}

	/**
	 * @param sector the sector to set
	 */
	public void setSector(Sector sector)
	{
		this.sector = sector;
	}
	
	@Override
	public String toString()
	{
		return "<html>" + getName() + " - <b>" + getGlobalRiskLevel()
				+ "</b></html>";
	}

	public Set<RiskSheet> getRiskSheets()
	{
		if (riskSheets == null)
			riskSheets = new HashSet<RiskSheet>();
		return riskSheets;
	}

	public void setRiskSheets(Set<RiskSheet> riskSheets)
	{
		this.riskSheets = riskSheets;
	}
	
	/**
	 * Calculaeaza nivelul de risc pentru un post de lucru.
	 * Formula: suma patratelor nivelurilor de risc / suma nivelurilor de risc
	 * @return
	 */
	public Double getGlobalRiskLevel()
	{
		double a = 0.0f; // suma patratelor nivelelor de risc
		double b = 0.0f; // suma nivelelor de risc
		double s = 0.0f; // nivelul de risc global 
		
		for (Iterator iter = getRiskSheets().iterator(); iter.hasNext();)
		{
			RiskSheet riskSheet = (RiskSheet) iter.next();
			if (riskSheet.getRiskLevel() != null)
			{
				double e = Double.parseDouble(riskSheet.getRiskLevel().getCode());
				a += e * e;
				b += e;
			}
		}
		
		if (b != 0.0f)
			s = a / b;
		s = Math.round(s * 100) / 100.0;

		return s;
	}
	
	public RiskSheet getRiskSheet(RiskFactor riskFactor)
	{
		for (Iterator iter = getRiskSheets().iterator(); iter.hasNext();)
		{
			RiskSheet riskSheet = (RiskSheet) iter.next();
			if (riskSheet.getRiskFactor() == riskFactor)
				return riskSheet;
		}
		return null;
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

	public String getWorkingHours()
	{
		return workingHours;
	}

	public void setWorkingHours(String workingHours)
	{
		this.workingHours = workingHours;
	}

	public String getEvaluationTeam()
	{
		return evaluationTeam;
	}

	public void setEvaluationTeam(String evaluationTeam)
	{
		this.evaluationTeam = evaluationTeam;
	}
}
