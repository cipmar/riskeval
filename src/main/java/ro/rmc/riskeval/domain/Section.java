package ro.rmc.riskeval.domain;

import java.util.HashSet;
import java.util.Set;

public class Section extends NamedEntity
{
	private Set<Sector> sectors;
	
	private Company company;

	/**
	 * @return the company
	 */
	public Company getCompany()
	{
		return company;
	}

	/**
	 * @param company the company to set
	 */
	public void setCompany(Company company)
	{
		this.company = company;
	}

	/**
	 * @return the sectors
	 */
	public Set<Sector> getSectors()
	{
		if (sectors == null)
			sectors = new HashSet<Sector>();
		return sectors;
	}

	/**
	 * @param sectors the sectors to set
	 */
	public void setSectors(Set<Sector> sectors)
	{
		this.sectors = sectors;
	}
	
	@Override
	public String toString()
	{
		return getName();
	}
}
