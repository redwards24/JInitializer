package components.text;

import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import components.ScrollPane;

public class TextArea extends JTextInitializer<JTextArea, TextArea> 
{
	
	@Override
	public TextArea initEditable(final boolean editable)
	{
		final Color color = get().getBackground();

		get().setEditable(editable);

		get().setBackground(color);
		
		return this;
	}
	
	@Override
	public ScrollPane toScrollPane()
	{
		final JComponent comp = this.get();
		
		return new ScrollPane()
		{
			@Override
			public JScrollPane create()
			{
				return new JScrollPane(comp);
			}
		};
	}

	@Override
	public JTextArea create() 
	{
		return new JTextArea();
	}
	
	@Override
	public TextArea self()
	{
		return this;
	}
}
