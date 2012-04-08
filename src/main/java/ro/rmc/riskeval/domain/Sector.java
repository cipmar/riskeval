package ro.rmc.riskeval.domain;

import java.util.HashSet;
import java.util.Set;

public class Sector extends NamedEntity 
{
	private Set<WorkPlace> workPlaces;
	
	private Section section;

	/**
	 * @return the section
	 */
	public Section getSection()
	{
		return section;
	}

	/**
	 * @param section the section to set
	 */
	public void setSection(Section section)
	{
		this.section = section;
	}

	/**
	 * @return the workPlaces
	 */
	public Set<WorkPlace> getWorkPlaces()
	{
		if (workPlaces == null)
			workPlaces = new HashSet<WorkPlace>();

		return workPlaces;
	}

	/**
	 * @param workPlaces the workPlaces to set
	 */
	public void setWorkPlaces(Set<WorkPlace> workPlaces)
	{
		this.workPlaces = workPlaces;
	}
	
	@Override
	public String toString()
	{
		return getName();
	}
}
