package components.panels;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import components.JInitializer;

public class RootPanel extends BorderPanel
{
	@Override
	public JPanel create() 
	{
		final JPanel rootPanel = new JPanel();
		
		final int t = 15; // Top
		final int l = 15; // Left
		final int b = 15; // Bottom
		final int r = 15; // Right
		
		rootPanel.setBorder(BorderFactory.createEmptyBorder(t, l, b, r));
		
		rootPanel.setLayout(new BorderLayout());
		
		return rootPanel;
	}

	@Override
	public RootPanel self() 
	{
		return this;
	}
}
