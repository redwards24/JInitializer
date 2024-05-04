package components.text;

import javax.swing.JFormattedTextField;

public class FormatField extends JTextFieldInitializer<JFormattedTextField, FormatField> 
{

	@Override
	public JFormattedTextField create() 
	{
		return new JFormattedTextField();
	}
	
	@Override
	public FormatField self()
	{
		return this;
	}

}
