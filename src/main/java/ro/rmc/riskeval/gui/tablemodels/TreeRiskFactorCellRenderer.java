package ro.rmc.riskeval.gui.tablemodels;

import java.awt.Component;
import java.awt.Font;
import java.util.Iterator;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import ro.rmc.riskeval.domain.RiskFactor;
import ro.rmc.riskeval.domain.RiskSheet;
import ro.rmc.riskeval.domain.WorkPlace;

public class TreeRiskFactorCellRenderer extends DefaultTreeCellRenderer
{
	private WorkPlace workPlace;
	
	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean sel, boolean expanded, boolean leaf, int row,
			boolean hasFocus)
	{
		super.getTreeCellRendererComponent(tree, value, sel, expanded,
				leaf, row, hasFocus);
		
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
		RiskFactor riskFactor = (RiskFactor) node.getUserObject();
		
		
		
		if (getRiskSheet(riskFactor) != null)
		{
			setFont(new Font("Arial", Font.BOLD, 12));
		} 
		else
		{
			setFont(new Font("Arial", Font.PLAIN, 12));
		}
		
		return this;
	}

	public void setWorkPlace(WorkPlace workPlace)
	{
		this.workPlace = workPlace;
	}
	
	private RiskSheet getRiskSheet(RiskFactor riskFactor)
	{
		if (workPlace == null)
			return null;
		
		for (Iterator iter = workPlace.getRiskSheets().iterator(); iter.hasNext();)
		{
			RiskSheet riskSheet = (RiskSheet) iter.next();
			if (riskSheet.getRiskFactor() == riskFactor)
				return riskSheet;
		}
		return null;
	}
}
