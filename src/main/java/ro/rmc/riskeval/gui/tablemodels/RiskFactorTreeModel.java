package ro.rmc.riskeval.gui.tablemodels;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import ro.rmc.riskeval.domain.RiskFactor;

public class RiskFactorTreeModel implements TreeModel
{
	private RiskFactor rootRiskFactor;
	
	public RiskFactorTreeModel(RiskFactor riskFactor)
	{
		this.rootRiskFactor = riskFactor;
	}
	
	public void addTreeModelListener(TreeModelListener l)
	{
		
	}

	public Object getChild(Object parent, int index)
	{
		RiskFactor riskFactor = (RiskFactor) parent;
		return riskFactor.getRiskFactors().toArray()[index];
	}

	public int getChildCount(Object parent)
	{
		RiskFactor riskFactor = (RiskFactor) parent;
		return riskFactor.getRiskFactors().size();
	}

	public int getIndexOfChild(Object parent, Object child)
	{
		//RiskFactor riskFactor = (RiskFactor) parent;
		//return riskFactor.getRiskFactors().indexOf(child);
		return 0;
	}

	public Object getRoot()
	{
		return rootRiskFactor;
	}

	public boolean isLeaf(Object node)
	{
		RiskFactor riskFactor = (RiskFactor) node;
		return riskFactor.getRiskFactors().size() == 0;
	}

	public void removeTreeModelListener(TreeModelListener l)
	{
	}

	public void valueForPathChanged(TreePath path, Object newValue)
	{
		
	}

}
