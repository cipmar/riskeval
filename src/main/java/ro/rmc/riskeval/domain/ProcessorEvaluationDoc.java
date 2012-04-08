package ro.rmc.riskeval.domain;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.sourceforge.rtf.RTFTemplate;
import net.sourceforge.rtf.helper.RTFTemplateBuilder;
import ro.rmc.riskeval.dao.DaoException;
import ro.rmc.riskeval.dao.RiskFactorDao;
import ro.rmc.riskeval.hibernate.RiskFactorDaoHibernate;

/**
 * Generate tabel factori de risc.
 * 
 * @author Marius
 * 
 */
public class ProcessorEvaluationDoc
{
	private String templEvaluationDoc 	= "./templates/fisa evaluare.rtf";
	private String templMeasuresDoc 	= "./templates/fisa masuri.rtf";
	private String templSummaryDoc 		= "./templates/sumar.rtf";
	private String resultFolderName;

	private WorkPlace workPlace;
	private RiskFactorDao riskFactorDao;
	private Set<RiskFactor> usedRiskFactorCategs = new HashSet<RiskFactor>();

	public WorkPlace getWorkPlace()
	{
		return workPlace;
	}

	public void setWorkPlace(WorkPlace workPlace)
	{
		this.workPlace = workPlace;
	}

	/**
	 * construieste lista de factori de risc
	 * @return
	 * @throws DaoException
	 */
	public List<RiskFactorRow> getRiskFactorsList() throws DaoException
	{
		List<RiskFactorRow> listRiskFactors = new ArrayList<RiskFactorRow>();
		
		// arborele cu factori de risc
		RiskFactor root = getRiskFactorDao().findAll().get(0);
		
		if (root != null)
		{
			usedRiskFactorCategs.clear();
			// iteram printre categoriile de pe primul nivel
			for (Iterator iter = root.getRiskFactors().iterator(); iter.hasNext();)
			{
				RiskFactor riskFactorCateg = (RiskFactor) iter.next();
				processRiskFactorCateg(riskFactorCateg, listRiskFactors);
			}
		}
		
		return listRiskFactors;
	}
	
	/**
	 * Pe baza listei de factori de risc, se genereaza lista de masuri
	 * @param listRiskFactors
	 * @return
	 */
	public List<RiskFactorMeasureRow> getRiscFactorsMeasues(
			List<RiskFactorRow> listRiskFactors)
	{
		List<RiskFactorMeasureRow> listRiskFactorsMeasures = new ArrayList<RiskFactorMeasureRow>();

		for (Iterator iter = listRiskFactors.iterator(); iter.hasNext();)
		{
			RiskFactorRow riskFactorRow = (RiskFactorRow) iter.next();
			RiskFactorMeasureRow measureRow = new RiskFactorMeasureRow();
			measureRow.setRiskManifestation(riskFactorRow
					.getRiskManifestation());
			measureRow.setRiskLevel(riskFactorRow.getRiskLevel());
			measureRow.setMeasure(riskFactorRow.getMeasure());
			measureRow.setCompetence(riskFactorRow.getCompetence());
			measureRow.setTerms(riskFactorRow.getTerms());
			
			listRiskFactorsMeasures.add(measureRow);
		}
		
		Collections.sort(listRiskFactorsMeasures, new Comparator<RiskFactorMeasureRow>()
		{
			public int compare(RiskFactorMeasureRow o1, RiskFactorMeasureRow o2)
			{
				return -o1.getRiskLevel().compareTo(o2.getRiskLevel());
			}
		});
		
		int i = 1;
		for (Iterator iter = listRiskFactorsMeasures.iterator(); iter.hasNext();)
		{
			RiskFactorMeasureRow measureRow = (RiskFactorMeasureRow) iter.next();
			measureRow.setNo(String.valueOf(i++));
		}
		
		return listRiskFactorsMeasures;
	}
	
	/**
	 * Pe baza listei de factori de risc, se genereaza lista cu sumarul 
	 * factorilor de risc.
	 * @param listRiskFactors
	 * @return
	 */
	private List<RiskFactorSummaryRaw> getRiskFactorsSummary(
			List<RiskFactorRow> listRiskFactors)
	{
		List<RiskFactorSummaryRaw> listRiskFactorsSummary = new ArrayList<RiskFactorSummaryRaw>();
		
		int n = 0;
		String currentComponent = null;
		RiskFactorSummaryRaw summaryRawComponent = null;
		
		for (int i =0; i < listRiskFactors.size(); i ++)
		{
			RiskFactorRow riskFactor = listRiskFactors.get(i);
			RiskFactorRow nextRiskFactor = null;
			
			RiskFactorSummaryRaw summaryRaw = new RiskFactorSummaryRaw();
			
			if (riskFactor.getComponent() != null)
			{
				currentComponent = riskFactor.getComponent();
				summaryRawComponent = summaryRaw;
			}
			
			if (i < listRiskFactors.size() - 1)
				nextRiskFactor = listRiskFactors.get(i+1);
			
			summaryRaw.setComponent(riskFactor.getComponent());
			summaryRaw.setRiskFactor("F" + String.valueOf(i+1));
			summaryRaw.setRiskLevel(riskFactor.getRiskLevel());
			
			n++;
			
			// daca urmatorul factor de risc apartine altei componente
			// sau s-a terminat lista, opresc aici numararea factorilor de risc
			if (nextRiskFactor == null || currentComponent != null
					&& nextRiskFactor.getComponent() != null
					&& !currentComponent.equals(nextRiskFactor.getComponent()))
			{
				summaryRawComponent.setRisksCount(String.valueOf(n));
				double percentage = n * 100.0 / listRiskFactors.size();
				percentage = Math.round(percentage * 100) / 100.0;
				summaryRawComponent.setRisksPercentage(String.valueOf(percentage) + "%");
				n = 0;
			} else
			{
				summaryRaw.setRisksCount("");
				summaryRaw.setRisksPercentage("");
			}
			
			listRiskFactorsSummary.add(summaryRaw);
		}
		
		return listRiskFactorsSummary;
	}
	
	/**
	 * Metoda care porneste generarea. Se genereaza 3 documente:
	 * 1. fisa de evaluare a riscurilor
	 * 2. fisa de masuri
	 * 3. sumarul factorilor de risc
	 * @throws Exception 
	 *
	 */
	public void generate() throws Exception
	{
		/* construirea listei cu factori de risc */
		List<RiskFactorRow> listRiskFactors = getRiskFactorsList();
		
		/* construirea listei cu masuri */
		List<RiskFactorMeasureRow> listRiskFactorsMeasures = getRiscFactorsMeasues(listRiskFactors);

		/* construirea sumarului factorilor de risc */
		List<RiskFactorSummaryRaw> listRiskFactorsSummary = getRiskFactorsSummary(listRiskFactors);
		
		/* generare document fisa de evaluare */
		generateEvaluationDoc(listRiskFactors);
		
		/* generare document fisa de masuri */
		generateMeasuresDoc(listRiskFactorsMeasures);
		
		/* generare document sumar factori de risc */
		generateSummaryDoc(listRiskFactorsSummary);
	}

	private void generateSummaryDoc(List<RiskFactorSummaryRaw> listRiskFactorsSummary)
		throws Exception
	{
		RTFTemplateBuilder builder = RTFTemplateBuilder.newRTFTemplateBuilder();
		RTFTemplate rtfTemplate = builder.newRTFTemplate();
		rtfTemplate.setTemplate(new File(templSummaryDoc));

		rtfTemplate.put("table1", listRiskFactorsSummary);
		rtfTemplate.put("globalRiskLevel", workPlace.getGlobalRiskLevel());
		rtfTemplate.put("riskFactorsTotal", listRiskFactorsSummary.size());
		rtfTemplate.put("company", workPlace.getSector().getSection().getCompany().getName());
		rtfTemplate.put("section", workPlace.getSector().getSection().getName());
		rtfTemplate.put("workPlace", workPlace.getName());

		String fileName = resultFolderName + "/" + workPlace.getName() + " - sumar.rtf";
		rtfTemplate.merge(new File(fileName));
	}

	private void generateMeasuresDoc(List<RiskFactorMeasureRow> listRiskFactorsMeasures)
		throws Exception
	{
		RTFTemplateBuilder builder = RTFTemplateBuilder.newRTFTemplateBuilder();
		RTFTemplate rtfTemplate = builder.newRTFTemplate();
		rtfTemplate.setTemplate(new File(templMeasuresDoc));

		rtfTemplate.put("table2", listRiskFactorsMeasures);
		rtfTemplate.put("company", workPlace.getSector().getSection().getCompany().getName());
		rtfTemplate.put("section", workPlace.getSector().getSection().getName());
		rtfTemplate.put("workPlace", workPlace.getName());
		rtfTemplate.put("persNo", workPlace.getPersonsNo());
		rtfTemplate.put("workHours", workPlace.getWorkingHours());
		rtfTemplate.put("evaluationTeam", workPlace.getEvaluationTeam());

		String fileName = resultFolderName + "/" + workPlace.getName() + " - fisa de masuri.rtf";
		rtfTemplate.merge(new File(fileName));
	}

	private void generateEvaluationDoc(List<RiskFactorRow> listRiskFactors)
			throws Exception
	{
		RTFTemplateBuilder builder = RTFTemplateBuilder.newRTFTemplateBuilder();
		RTFTemplate rtfTemplate = builder.newRTFTemplate();
		rtfTemplate.setTemplate(new File(templEvaluationDoc));

		rtfTemplate.put("table1", listRiskFactors);
		rtfTemplate.put("company", workPlace.getSector().getSection().getCompany().getName());
		rtfTemplate.put("section", workPlace.getSector().getSection().getName());
		rtfTemplate.put("workPlace", workPlace.getName());
		rtfTemplate.put("persNo", workPlace.getPersonsNo());
		rtfTemplate.put("workHours", workPlace.getWorkingHours());
		rtfTemplate.put("evaluationTeam", workPlace.getEvaluationTeam());

		String fileName = resultFolderName + "/" + workPlace.getName() + " - fisa de evaluare.rtf";
		rtfTemplate.merge(new File(fileName));
	}

	/**
	 * Proceseaza o categorie de factori de pe primul nivel (dupa root)
	 * @param riskFactorCateg
	 * @param list 
	 */
	private void processRiskFactorCateg(RiskFactor riskFactorCateg,
			List<RiskFactorRow> list)
	{
		for (Iterator iter = riskFactorCateg.getRiskFactors().iterator(); iter
				.hasNext();)
		{
			RiskFactor riskFactor = (RiskFactor) iter.next();
			processRiskfactor(riskFactor, riskFactorCateg, list);
		}
	}

	/**
	 * proceseaza un factor de risc; daca are fisa de risc, creeaza o
	 * linie in tabel
	 * @param riskFactor
	 * @param riskFactorCateg
	 * @param list 
	 */
	private void processRiskfactor(RiskFactor riskFactor,
			RiskFactor riskFactorCateg, List<RiskFactorRow> list)
	{
		RiskSheet riskSheet = workPlace.getRiskSheet(riskFactor);
		
		if (riskSheet != null && riskSheet.getRiskFactor() != null
				&& riskSheet.getRiskLevel() != null)
		{
			RiskFactorRow t = new RiskFactorRow();
			
			if (riskFactorCateg != null && !usedRiskFactorCategs.contains(riskFactorCateg))
			{
				t.setComponent(riskFactorCateg.getName());
				usedRiskFactorCategs.add(riskFactorCateg);
			}
			
			if (riskFactor != null)
				t.setRiskFactor(getRiskFactorsStr(riskFactor, riskFactorCateg));
			
			t.setRiskManifestation(getManifestation(riskSheet, list.size() + 1).trim());
			
			if (riskSheet.getGravity() != null)
			{
				t.setGravity(riskSheet.getGravity().getDescription());
				t.setGravityClass(riskSheet.getGravity().getCode());
			}
			
			if (riskSheet.getProbability() != null)
				t.setProbability(riskSheet.getProbability().getCode());
			
			if (riskSheet.getRiskLevel() != null)
				t.setRiskLevel(riskSheet.getRiskLevel().getCode());
			
			t.setMeasure(riskSheet.getMeasures());
			t.setCompetence(riskSheet.getCompetence());
			t.setTerms(riskSheet.getTerms());
			
			list.add(t);
		}
		
		// procesare copii
		for (Iterator iter = riskFactor.getRiskFactors().iterator(); iter.hasNext();)
		{
			RiskFactor clildRiskFactor = (RiskFactor) iter.next();
			processRiskfactor(clildRiskFactor, riskFactorCateg, list);
		}
	}

	/**
	 * String cu factorii de risc de la cel curent pana se ajunge
	 * la parintele lui, riskFactorCateg, mergand inapoi in ierarhie
	 * @param riskFactor
	 * @param riskFactorCateg
	 * @return
	 */
	private String getRiskFactorsStr(RiskFactor riskFactor,
			RiskFactor riskFactorCateg)
	{
		String s = riskFactor.getName();

		while (riskFactor.getParentRiskFactor() != null
				&& riskFactor.getParentRiskFactor() != riskFactorCateg)
		{
			riskFactor = riskFactor.getParentRiskFactor();
			s = riskFactor.getName() + " > " + s;
		}

		return s;
	}
	
	private String getManifestation(RiskSheet riskSheet, int index)
	{
		String s = "F" + String.valueOf(index) + ". " + riskSheet.getManifestation();
		s = s + " ";
		
		// consecintele
		s = s + "Consecinte posibile: ";
		int i = 0;
		
		for (Iterator iter = riskSheet.getRiskSheetConsequences().iterator(); iter.hasNext();)
		{
			RiskSheetConsequence e = (RiskSheetConsequence) iter.next();
			
			if (e.getConsequence() != null && e.getConsequenceLocation() != null)
			{
				i++;
				s = s + String.valueOf(i) + "." + e.getConsequence() + "-"
						+ e.getConsequenceLocation() + "; ";
			}
		}
		
		return s;
	}
	
	public RiskFactorDao getRiskFactorDao()
	{
		if (riskFactorDao == null)
			riskFactorDao = new RiskFactorDaoHibernate();
		return riskFactorDao;
	}

	public String getResultFolderName()
	{
		return resultFolderName;
	}

	public void setResultFolderName(String resultFolderName)
	{
		this.resultFolderName = resultFolderName;
	}
}
