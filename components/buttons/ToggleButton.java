package components.buttons;

import javax.swing.JToggleButton;

public class ToggleButton extends JButtonInitializer<JToggleButton, ToggleButton>
{
	@Override
	public JToggleButton create() 
	{
		return new JToggleButton();
	}
	
	@Override
	public ToggleButton self()
	{
		return this;
	}
}
