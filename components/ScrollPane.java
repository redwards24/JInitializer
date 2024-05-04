package components;

import javax.swing.JScrollPane;

public class ScrollPane extends JInitializer<JScrollPane, ScrollPane>
{

	@Override
	public JScrollPane create() 
	{
		return new JScrollPane();
	}

	@Override
	public ScrollPane self() 
	{
		return this;
	}
	
}
