package ro.rmc.riskeval.domain;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Company extends NamedEntity
{
	private String address;

	private String phone;

	private String fax;

	private String email;

	private Set<Section> sections;

	/**
	 * @return the address
	 */
	public String getAddress()
	{
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address)
	{
		this.address = address;
	}

	/**
	 * @return the email
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}

	/**
	 * @return the fax
	 */
	public String getFax()
	{
		return fax;
	}

	/**
	 * @param fax
	 *            the fax to set
	 */
	public void setFax(String fax)
	{
		this.fax = fax;
	}

	/**
	 * @return the phone
	 */
	public String getPhone()
	{
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	/**
	 * @return the sections
	 */
	public Set<Section> getSections()
	{
		if (sections == null)
			sections = new HashSet<Section>();
		return sections;
	}

	/**
	 * @param sections
	 *            the sections to set
	 */
	public void setSections(Set<Section> sections)
	{
		this.sections = sections;
	}

	@Override
	public String toString()
	{
		return getName();
	}
	
	/**
	 * Calculaeaza nivelul de risc al unei companii.
	 * Formula: suma patratelor nivelurilor de risc / suma nivelurilor de risc
	 * @return
	 */
	public Double getGlobalRiskLevel()
	{
		double a = 0.0f; // suma patratelor nivelelor de risc
		double b = 0.0f; // suma nivelelor de risc
		double s = 0.0f; // nivelul de risc global 
		
		for (Iterator iter = getSections().iterator(); iter.hasNext();)
		{
			Section section = (Section) iter.next();
			
			for (Iterator iter2 = section.getSectors().iterator(); iter2.hasNext();)
			{
				Sector sector = (Sector) iter2.next();
				
				for (Iterator iter3 = sector.getWorkPlaces().iterator(); iter3.hasNext();)
				{
					WorkPlace workPlace = (WorkPlace) iter3.next();
					double e = workPlace.getGlobalRiskLevel();
					a += e * e;
					b += e;
				}
			}
		}

		if (b != 0.0f)
			s = a / b;
		s = Math.round(s * 100) / 100.0;

		return s;
	}

}
