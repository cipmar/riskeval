package ro.rmc.riskeval.gui;

import java.util.ArrayList;
import java.util.List;

class Module
{
	private String name;
	private String viewClassName;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getViewClassName()
	{
		return viewClassName;
	}

	public void setViewClassName(String view)
	{
		this.viewClassName = view;
	}
	
	@Override
	public String toString()
	{
		return getName();
	}
}

public class GuiUtils
{
	private static List<Module> modules;
	
	public static List<Module> getModules()
	{
		if (modules == null)
		{
			modules = new ArrayList<Module>();
			
			Module m;
			
			// company
			m = new Module();
			m.setName("Companii");
			m.setViewClassName("ro.rmc.riskeval.gui.CompanyModule");
			modules.add(m);

			// risk factors module
			m = new Module();
			m.setName("<html>Factori<br>risc</html>");
			m.setViewClassName("ro.rmc.riskeval.gui.RiskFactorsModule");
			modules.add(m);
			
			// risk consequence module
			m = new Module();
			m.setName("Consecinte");
			m.setViewClassName("ro.rmc.riskeval.gui.ConsequenceModule");
			modules.add(m);
			
			// risk consequence location module
			m = new Module();
			m.setName("<html>Localizari<br>consecinte</html>");
			m.setViewClassName("ro.rmc.riskeval.gui.ConsequenceLocationModule");
			modules.add(m);
			
			// gravity module
			m = new Module();
			m.setName("Gravitati");
			m.setViewClassName("ro.rmc.riskeval.gui.GravityModule");
			modules.add(m);
			
			// probability module
			m = new Module();
			m.setName("<html>Probabilitati<br> </html>");
			m.setViewClassName("ro.rmc.riskeval.gui.ProbabilityModule");
			modules.add(m);

			// risk levels
			m = new Module();
			m.setName("Nivele risc");
			m.setViewClassName("ro.rmc.riskeval.gui.RiskLevelModule");
			modules.add(m);
		}
		return modules;
	}
	
}
