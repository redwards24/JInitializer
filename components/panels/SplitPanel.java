package components.panels;

import javax.swing.JSplitPane;

import components.JInitializer;

public class SplitPanel extends JInitializer<JSplitPane, SplitPanel>
{

	public SplitPanel vertical()
	{
		get().setOrientation(JSplitPane.VERTICAL_SPLIT);
		return self();
	}
	
	public SplitPanel horizontal()
	{
		get().setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		return self();
	}
	
	public SplitPanel left(final JInitializer<?, ?> j)
	{
		get().setLeftComponent(j.get());
		return self();
	}
	
	public SplitPanel right(final JInitializer<?, ?> j)
	{
		get().setRightComponent(j.get());
		return self();
	}
	
	public SplitPanel top(final JInitializer<?, ?> j)
	{
		return left(j);
	}
	
	public SplitPanel bottom(final JInitializer<?, ?> j)
	{
		return right(j);
	}
	
	
	
	@Override
	public JSplitPane create() 
	{
		return new JSplitPane();
	}

	@Override
	public SplitPanel self() 
	{
		return this;
	}

}
