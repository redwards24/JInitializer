package components;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

import components.panels.FlowPanel;


public abstract class JInitializer<T extends JComponent, K extends JInitializer<T, K>>
{
	private final T jcomponent = create();
	
	private final K jinitializer = self();
	
	public abstract T create();
	public abstract K self();
	
	
	/**
	 * 
	 * 
	 * @return JComponent associated with JInitializer object
	 */
	public T get()
	{
		return jcomponent;
	}
	
	public K add(final JInitializer<?, ?> jInitializer) 
	{
		get().add(jInitializer.get());
		return jinitializer;
	}
	
	public void add(final JInitializer<?, ?> jInitializer, final Object constraint)
	{
		try
		{
			get().add(jInitializer.get(), constraint);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public ScrollPane toScrollPane()
	{
		return toScrollPane(
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	}
	
	public ScrollPane toScrollPane(final int vp, final int hp)
	{
		final JComponent comp = this.get();
		
		final ScrollPane pane = new ScrollPane() 
		{
			@Override
			public JScrollPane create()
			{
				return new JScrollPane(comp);
			}
		};
		
		return pane.initBorder(BorderFactory.createEmptyBorder());
	}
	
	public FlowPanel toFlowPanel(final int align, final int hgap, final int vgap)
	{
		return new FlowPanel(align, hgap, vgap).add(self());
	}
	
	public K emptyBorder(final int w)
	{
		get().setBorder(BorderFactory.createEmptyBorder(w, w, w, w));
		return jinitializer;	
	}
	
	public K initName(final String name)
	{
		jcomponent.setName(name);
		return jinitializer;
	}
	
	public K initLayout(final LayoutManager layout)
	{
		jcomponent.setLayout(layout);
		return jinitializer;
	}
	
	public K initBackground(final Color color)
	{
		jcomponent.setBackground(color);
		return jinitializer;
	}

	
	public K initForeground(final Color color)
	{
		jcomponent.setForeground(color);
		return jinitializer;
	}
	
	public K initPreferredSize(final int x, final int y)
	{
		jcomponent.setPreferredSize(new Dimension(x, y));
		return jinitializer;
	}
	
	public K initMinimumSize(final int x, final int y)
	{
		jcomponent.setMinimumSize(new Dimension(x, y));
		return jinitializer;
	}
	
	public K initBorder(final Border border) 
	{
		jcomponent.setBorder(border);
		return jinitializer;
	}
	
	public K initFont(final Font font)
	{
		jcomponent.setFont(font);
		return jinitializer;
	}
	
	public K initFontSize(final int size)
	{
		final Font oldFont = jcomponent.getFont();
		
		jcomponent.setFont(new Font(oldFont.getName(), oldFont.getStyle(), size));
		
		return jinitializer;
	}
	
	public K initFontStyle(final int style)
	{
		final Font oldFont = jcomponent.getFont();
		
		jcomponent.setFont(new Font(oldFont.getName(), style, oldFont.getSize()));
		
		return jinitializer;
	}
	
	public K initToolTip(final String toolTip)
	{
		jcomponent.setToolTipText(toolTip);
		return jinitializer;
	}
	
	public K initFocusable(final boolean focusable)
	{
		jcomponent.setFocusable(focusable);
		return jinitializer;
	}
	

}
