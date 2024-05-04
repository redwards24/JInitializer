package components.custom.checkbox;

import javax.swing.JCheckBox;

import components.JInitializer;

public class CheckBoxID extends JInitializer<JCheckBox, CheckBoxID>
{
	private final int id;
	
	public CheckBoxID(final String text, final int id)
	{
		get().setText(text);
		get().setName(text);
		this.id = id;
	}

	@Override
	public JCheckBox create() 
	{
		return new JCheckBox();
	}

	@Override
	public CheckBoxID self() 
	{
		return this;
	}
}
