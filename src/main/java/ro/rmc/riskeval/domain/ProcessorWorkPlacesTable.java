package ro.rmc.riskeval.domain;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.sourceforge.rtf.RTFTemplate;
import net.sourceforge.rtf.helper.RTFTemplateBuilder;

public class ProcessorWorkPlacesTable
{
	private Company company;
	
	private String templFilename = "./templates/sumar posturi lucru.rtf";
	private String resultFileName = "./templates/rezultat2.rtf";

	public void generate() throws Exception
	{
		RTFTemplateBuilder builder = RTFTemplateBuilder.newRTFTemplateBuilder();
		RTFTemplate rtfTemplate = builder.newRTFTemplate();
		rtfTemplate.setTemplate(new File(templFilename));

		List<WorkPlaceRow> workPlacesList = new ArrayList<WorkPlaceRow>();
		
		int i = 1;
		Set<Section> usedSections = new HashSet<Section>();
		Set<Sector> usedSectors = new HashSet<Sector>();
		
		for (Iterator iter = company.getSections().iterator(); iter.hasNext();)
		{
			Section section = (Section) iter.next();
			String sectionName = section.getName();
			
			for (Iterator iter2 = section.getSectors().iterator(); iter2
					.hasNext();)
			{
				Sector sector = (Sector) iter2.next();
				String sectorName = sector.getName();
				
				for (Iterator iter3 = sector.getWorkPlaces().iterator(); iter3
						.hasNext();)
				{
					WorkPlace workPlace = (WorkPlace) iter3.next();
					
					WorkPlaceRow workPlaceRow = new WorkPlaceRow();
					workPlaceRow.setNo(String.valueOf(i++));
					workPlaceRow.setName(workPlace.getName());
					
					if (!usedSections.contains(section))
					{
						workPlaceRow.setSection(sectionName);
						usedSections.add(section);
					}
					else
						workPlaceRow.setSection("");
					
					if (!usedSectors.contains(sector))
					{
						workPlaceRow.setSector(sectorName);
						usedSectors.add(sector);
					}
					else
						workPlaceRow.setSector("");
					
					workPlaceRow.setPersonsNo(workPlace.getPersonsNo());
					workPlaceRow.setOperations(workPlace.getOperations());
					
					workPlaceRow.setRiskLevel(String.valueOf(workPlace.getGlobalRiskLevel()));
					workPlacesList.add(workPlaceRow);
				}
			}
		}
		
		rtfTemplate.put("workPlaces", workPlacesList);
		rtfTemplate.put("company", getCompany().getName());
		rtfTemplate.put("total", getCompany().getGlobalRiskLevel());
		rtfTemplate.merge(new File(resultFileName));
	}

	public Company getCompany()
	{
		return company;
	}

	public void setCompany(Company company)
	{
		this.company = company;
	}

	public String getResultFileName()
	{
		return resultFileName;
	}

	public void setResultFileName(String resultFileName)
	{
		this.resultFileName = resultFileName;
	}
}
