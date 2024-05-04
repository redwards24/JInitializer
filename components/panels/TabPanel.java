package components.panels;

import javax.swing.JTabbedPane;

import components.JInitializer;

public class TabPanel extends JInitializer<JTabbedPane, TabPanel>
{

	
	public TabPanel top()
	{
		get().setTabPlacement(JTabbedPane.TOP);
		return self();
	}
	
	public TabPanel bottom()
	{
		get().setTabPlacement(JTabbedPane.BOTTOM);
		return self();
	}
	
	public TabPanel left()
	{
		get().setTabPlacement(JTabbedPane.LEFT);
		return self();
	}
	
	public TabPanel right()
	{
		get().setTabPlacement(JTabbedPane.RIGHT);
		return self();
	}

	public TabPanel wrap() 
	{ 
		get().setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT); 
		return self(); 
	}
	
	public TabPanel scroll() 
	{ 
		get().setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT); 
		return self(); 
	}
	
	public TabPanel tab(final String text, final JInitializer<?, ?> j)
	{
		get().addTab(text, j.get());
		return self();
	}
	
	@Override
	public JTabbedPane create() 
	{
		final JTabbedPane pane = new JTabbedPane();
		
		pane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		
		return pane;
	}

	@Override
	public TabPanel self() 
	{
		return this;
	}
	

}
