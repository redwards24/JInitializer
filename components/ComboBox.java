package components;

import java.util.List;

import javax.swing.JComboBox;

public class ComboBox<T> extends JInitializer<JComboBox<T>, ComboBox<T>>
{
	public ComboBox<T> initItems(final List<T> items)
	{
		for(final T item: items)
		{
			get().addItem(item);
		}
		
		return this;
	}
	
	public ComboBox<T> initItems(final T... items)
	{
		for(final T item: items)
		{
			get().addItem(item);
		}
		
		return this;
	}
	
	@Override
	public JComboBox<T> create() 
	{
		return new JComboBox<T>();
	}
	
	@Override
	public ComboBox<T> self()
	{
		return this;
	}
}
