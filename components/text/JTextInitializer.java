package components.text;

import java.awt.Insets;

import javax.swing.text.JTextComponent;

import components.JInitializer;

public abstract class JTextInitializer<T extends JTextComponent, K extends JTextInitializer<T, K>> extends JInitializer<T, K>
{
	
	public K initMargin(final int margin)
	{
		get().setMargin(new Insets(margin, margin, margin, margin));
		return self();
	}
	
	public K initMargin(final int top, final int left, final int bottom, final int right)
	{
		get().setMargin(new Insets(top, left, bottom, right));
		return self();
	}
	
	public K initEditable(final boolean editable)
	{
		get().setEditable(editable);
		return self();
	}
}
