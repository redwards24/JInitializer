package guiutil;

import javax.swing.table.DefaultTableCellRenderer;

public class MyTableCellRenderer extends DefaultTableCellRenderer
{
	
	
	public MyTableCellRenderer(final int alignment)
	{
		this.setHorizontalAlignment(alignment);
	}
}
