package ro.rmc.riskeval.domain;

import java.util.Collection;
import java.util.HashSet;

public class RiskLevel extends NamedEntity
{
	private String code;
	
	private Collection<GpCouple> gpCouples = null;

	public Collection<GpCouple> getGpCouples()
	{
		if (gpCouples == null)
			gpCouples = new HashSet<GpCouple>();
		return gpCouples;
	}

	public void setGpCouples(Collection<GpCouple> gpCouples)
	{
		this.gpCouples = gpCouples;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}
	
	@Override
	public String toString()
	{
		return getCode() + " (" + getName() + ")";
	}
}
