package components;

import javax.swing.JFormattedTextField;

public class FormattedTextField extends JInitializer<JFormattedTextField, FormattedTextField>
{
	
	
	@Override
	public JFormattedTextField create()
	{
		return new JFormattedTextField();
	}
	
	@Override
	public FormattedTextField self()
	{
		return this;
	}

}
