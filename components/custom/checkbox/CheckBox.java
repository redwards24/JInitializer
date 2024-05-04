package components.custom.checkbox;

import javax.swing.JCheckBox;

import components.JInitializer;

public class CheckBox extends JInitializer<JCheckBox, CheckBox>
{

	public CheckBox() {}
	
	public CheckBox(final String text) 
	{
		get().setText(text);
	}
	
	@Override
	public JCheckBox create() 
	{
		return new JCheckBox();
	}
	
	@Override
	public CheckBox self()
	{
		return this;
	}
	
}
