package components.panels;

import java.awt.FlowLayout;

import javax.swing.JPanel;

import components.JInitializer;
import components.Label;


public class TitlePanel extends JInitializer<JPanel, TitlePanel>
{
	// Default values
	private int align = FlowLayout.CENTER;
	private int hgap = 15;
	private int vgap = 15;
	private int size = 18;
	
	
	public TitlePanel(final String title)
	{
		get().setLayout(new FlowLayout(align, hgap, vgap));
		
		get().add(new Label(title).initFontSize(size).get());
	}

	@Override
	public JPanel create() 
	{
		return new JPanel();
	}

	@Override
	public TitlePanel self() 
	{
		return this;
	}

}
