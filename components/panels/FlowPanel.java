package components.panels;

import java.awt.FlowLayout;

import javax.swing.JPanel;

import components.JInitializer;

public class FlowPanel extends JInitializer<JPanel, FlowPanel>
{

	public FlowPanel flow(final JInitializer<?, ?>... js)
	{
		for(final JInitializer<?, ?> j: js)
		{
			get().add(j.get());
		}
		
		return self();
	}

	
	public FlowPanel() 
	{
		this(FlowLayout.CENTER, 10, 10);
	}
	
	public FlowPanel(final int align, final int hgap, final int vgap) 
	{
		get().setLayout(new FlowLayout(align, hgap, vgap));
	}
	
	
	@Override
	public JPanel create()
	{
		return new JPanel();
	}
	
	@Override
	public FlowPanel self()
	{
		return this;
	}
}
