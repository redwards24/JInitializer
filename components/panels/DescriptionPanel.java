package components.panels;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import components.JInitializer;

public class DescriptionPanel extends JInitializer<JPanel, DescriptionPanel> 
{

	
	
	public DescriptionPanel(final String description)
	{
		get().setLayout(new BorderLayout());
		
		get().setBorder(BorderFactory.createTitledBorder(
							BorderFactory.createMatteBorder(1, 0, 0, 0, get().getForeground()), "Description"));
		
		get().add(new JLabel(description), BorderLayout.WEST);
	}
	
	
	@Override
	public JPanel create() 
	{
		return new JPanel();
	}

	@Override
	public DescriptionPanel self() 
	{
		return this;
	}
	

}
