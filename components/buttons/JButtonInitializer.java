package components.buttons;

import java.awt.event.ActionListener;

import javax.swing.AbstractButton;

import components.JInitializer;

public abstract class JButtonInitializer<T extends AbstractButton, K extends JButtonInitializer<T, K>> extends JInitializer<T, K>
{	
	
	public K initText(final String text)
	{
		get().setText(text);
		return self();
	}
	
	public K initActionListener(final ActionListener listener)
	{
		get().addActionListener(listener);
		return self();
	}
	
}
