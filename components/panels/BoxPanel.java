package components.panels;

import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import components.JInitializer;
import guiutil.Function;

public class BoxPanel extends JInitializer<JPanel, BoxPanel>
{

	
	private List<Function> layoutList = new ArrayList<>();
	
	
	public BoxPanel glue() { get().add(Box.createGlue()); return self(); }
	
	public BoxPanel hglue() { get().add(Box.createHorizontalGlue()); return self(); }
	public BoxPanel vglue() { get().add(Box.createVerticalGlue()); return self(); }
	
	public BoxPanel hstrut(final int width ) { get().add(Box.createHorizontalStrut(width)); return self(); }
	public BoxPanel vstrut(final int height) { get().add(Box.createVerticalStrut(height)); return self(); }
	
	public BoxPanel rigid(final int x, final int y) { get().add(Box.createRigidArea(new Dimension(x, y))); return self(); }
	
	public BoxPanel hbox() { get().add(Box.createHorizontalBox()); return self(); }
	public BoxPanel vbox() { get().add(Box.createVerticalBox()); return self(); }
	
	

	public BoxPanel box(final JInitializer<?, ?>... js)
	{
		for(final JInitializer<?, ?> j: js)
		{
			get().add(j.get());
		}

		return self();
	}
	
	public BoxPanel()
	{
		this(BoxLayout.X_AXIS);
	}
	
	public BoxPanel(final int axis)
	{
		get().setLayout(new BoxLayout(get(), axis));
	}
	
	@Override
	public JPanel create() 
	{
		return new JPanel();
	}

	@Override
	public BoxPanel self() 
	{
		return this;
	}

}
