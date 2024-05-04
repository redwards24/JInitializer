package components.panels;

import java.awt.GridLayout;

import javax.swing.JPanel;

import components.JInitializer;

public class GridPanel extends JInitializer<JPanel, GridPanel>
{

	public GridPanel(final int rows, final int cols, final int hgap, final int vgap)
	{
		get().setLayout(new GridLayout(rows, cols, hgap, vgap));
	}
	
	public GridPanel grid(final JInitializer<?, ?>... js)
	{
		for(final JInitializer<?, ?> j: js)
		{
			get().add(j.get());
		}
		
		return self();
	}
	
	@Override
	public JPanel create() 
	{	
		return new JPanel();
	}

	@Override
	public GridPanel self() 
	{
		return this;
	}

}
