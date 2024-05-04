package components.custom;

import java.awt.Color;

import javax.swing.JTextField;

import components.JInitializer;


public class DataField<T> extends JInitializer<JTextField, DataField<T>>
{
	private T value = null;
	
	public void setValue(T value)
	{
		if(value == null)
		{
			return;
		}
		
		this.value = value;
		
		get().setText(value.toString());
	}
	
	public T getValue()
	{
		return value;
	}
	
	public DataField()
	{
		setup();
	}
	
	public DataField(final T value)
	{
		setup();
		
		setValue(value);
	}
	
	public DataField<T> initColumns(final int col)
	{
		get().setColumns(col);
		return self();
	}
	
	private void setup()
	{
		get().setHorizontalAlignment(JTextField.RIGHT);
		
		final Color bg = get().getBackground();
		
		get().setEditable(false);
		
		get().setBackground(bg);
	}

	@Override
	public JTextField create() 
	{
		return new JTextField();
	}

	@Override
	public DataField<T> self() 
	{
		return this;
	}
}
