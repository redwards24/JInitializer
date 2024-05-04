package components.buttons;

import javax.swing.JButton;

import components.ComboBox;

public class Button extends JButtonInitializer<JButton, Button>
{
	public Button() {}
	
	public Button(final String text)
	{
		get().setText(text);
	}

	@Override
	public JButton create() 
	{
		return new JButton();
	}
	
	@Override
	public Button self()
	{
		return this;
	}
}
