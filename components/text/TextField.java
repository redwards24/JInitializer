package components.text;

import java.awt.Color;

import javax.swing.JTextField;

import components.JInitializer;

public class TextField extends JTextFieldInitializer<JTextField, TextField> 
{
	
	public TextField() {}
	
	public TextField(final String text)
	{
		get().setText(text);
	}
	
	@Override
	public JTextField create() 
	{
		return new JTextField();
	}
	
	@Override
	public TextField self()
	{
		return this;
	}
}
