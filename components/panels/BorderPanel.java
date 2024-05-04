package components.panels;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import components.JInitializer;

public class BorderPanel extends JInitializer<JPanel, BorderPanel>
{

	
	public BorderPanel north(final JInitializer<?,?> j)
	{
		get().add(j.get(), BorderLayout.NORTH);
		return this;
	}
	
	public BorderPanel south(final JInitializer<?,?> j)
	{
		get().add(j.get(), BorderLayout.SOUTH);
		return this;
	}
	
	public BorderPanel east(final JInitializer<?,?> j)
	{
		get().add(j.get(), BorderLayout.EAST);
		return this;
	}
	
	public BorderPanel west(final JInitializer<?,?> j)
	{
		get().add(j.get(), BorderLayout.WEST);
		return this;
	}
	
	public BorderPanel center(final JInitializer<?,?> j)
	{
		get().add(j.get(), BorderLayout.CENTER);
		return this;
	}
	
	public BorderPanel northwest(final JInitializer<?, ?> j)
	{
		final JPanel north = new JPanel(new BorderLayout());
		final JPanel west = new JPanel(new BorderLayout());
		
		west.add(j.get(), BorderLayout.CENTER);
		north.add(west, BorderLayout.WEST);
		get().add(north, BorderLayout.NORTH);
		
		return self();
	}
	
	
	@Override
	public JPanel create() 
	{
		return new JPanel(new BorderLayout());
	}
	
	@Override
	public BorderPanel self()
	{
		return this;
	}

}
