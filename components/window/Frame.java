package components.window;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.JFrame;

public class Frame extends JFrame
{
	
	
	
	public Frame initLayout(final LayoutManager layout)
	{
		this.setLayout(layout);
		return this;
	}
	
	public Frame initTitle(final String title)
	{
		this.setTitle(title);
		return this;
	}
	
	public Frame initPreferredSize(final int width, final int height)
	{
		this.setPreferredSize(new Dimension(width, height));
		return this;
	}
	
	public Frame initResizable(final boolean resizable)
	{
		this.setResizable(resizable);
		return this;
	}
	
	public Frame initDefaultCloseOperation(final int op)
	{
		this.setDefaultCloseOperation(op);
		return this;
	}
	
	public Frame initLocationRelativeTo(final Component c)
	{
		this.setLocationRelativeTo(c);
		return this;
	}
	
	
	
	
	
	
}
