package components.text;

import java.awt.Color;

import javax.swing.JTextField;

public abstract class JTextFieldInitializer<T extends JTextField, K extends JTextFieldInitializer<T, K>> extends JTextInitializer<T, K>
{

	public K initText(final String text)
	{
		get().setText(text);
		return self();
	}
	
	public K initColumns(final int columns)
	{
		get().setColumns(columns);
		return self();
	}
	

	public K initEditable(final boolean editable)
	{
		final Color color = get().getBackground();
		
		get().setEditable(editable);
		
		get().setBackground(color);
		
		return self();
	}
	
	
	public K initHorizontalAlignment(final int alignment)
	{
		try
		{
			get().setHorizontalAlignment(alignment);
		}
		catch(IllegalArgumentException e)
		{
			e.printStackTrace();
		}
		
		return self();
	}
	
}
