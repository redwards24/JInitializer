package components.panels;

import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import components.JInitializer;
import components.Label;
import guiutil.Grid;

public class GridBagPanel extends JInitializer<JPanel, GridBagPanel> 
{

	private Grid grid;
	
	private int row = -1;
	private int col = -1;
	private int width = 1;
	private int height = 1;
	
	public GridBagPanel inset(final int top, final int left, final int bottom, final int right)
	{
		this.grid.setInset(top, left, bottom, right);
		return self();
	}
	
	public GridBagPanel anchor(final int anchor)
	{
		this.grid.setAnchor(anchor);
		return self();
	}

	
	
	public GridBagPanel gridbag(final JInitializer<?, ?> j, final int x, final int y)
	{
		get().add(j.get(), grid.set(x, y));
		return self();
	}
	
	public GridBagPanel gridbag(final JInitializer<?, ?> j, final int x, final int y, final int w, final int h)
	{
		get().add(j.get(), grid.set(x, y, w, h));
		return self();
	}
	
	public GridBagPanel pos(final int x, final int y)
	{
		this.row = x;
		this.col = y;
		return self();
	}
	

	public GridBagPanel width(final int w)
	{
		this.width = w;
		return self();
	}
	
	public GridBagPanel height(final int h)
	{
		this.height = h;
		return self();
	}
	
	/**
	 * Add to current row.
	 * 
	 * @param j
	 * @return
	 */
	public GridBagPanel row(final JInitializer<?, ?> j)
	{
		get().add(j.get(), grid.set(++col, row));
		
		return self();
	}
	
	public GridBagPanel row(final String label)
	{
		return row(new Label(label));
	}
	

	
	/**
	 * Add to current column
	 * 
	 * @param j
	 * @return
	 */
	public GridBagPanel col(final JInitializer<?, ?> j)
	{
		get().add(j.get(), grid.set(col, ++row));
		
		return self();
	}
	
	/**
	 * Set column to 0 and increment row.
	 * 
	 * @param j
	 * @return
	 */
	public GridBagPanel nextrow(final JInitializer<?, ?>... js)
	{
		++row;
		col = -1;
		
		for(final JInitializer<?, ?> j: js)
		{
			get().add(j.get(), grid.set(++col, row, width, height));
		}
		
		return self();
	}
	
	public GridBagPanel nextrow(final String label, final JInitializer<?, ?>... js)
	{
		final JInitializer<?, ?>[] array = new JInitializer<?, ?>[js.length + 1];
		
		array[0] = new Label(label);

		for(int i = 0; i < js.length; ++i)
		{
			array[i+1] = js[i];
		}

		return nextrow(array);
	}
	
	/**
	 * Set row to 0 and increment column,
	 * 
	 * @param j
	 * @return
	 */
	public GridBagPanel nextcol(final JInitializer<?, ?>... js)
	{
		++col;
		row = -1;
		
		for(final JInitializer<?, ?> j: js)
		{
			get().add(j.get(), grid.set(col, ++row));
		}
		
		return self();
	}
	
	public GridBagPanel nextcol(final String label, final JInitializer<?, ?>... js)
	{
		final JInitializer<?, ?>[] array = new JInitializer<?, ?>[js.length + 1];
		
		array[0] = new Label(label);

		for(int i = 1; i < array.length; ++i)
		{
			array[i] = js[i];
		}
		
		return nextcol(array);
	}
	

	@Override
	public JPanel create() 
	{
		// Default Values
		grid = new Grid();
		grid.setInset(10, 10, 10, 10);
		grid.setAnchor(Grid.CENTER);
		
		return new JPanel(new GridBagLayout());
	}

	@Override
	public GridBagPanel self() 
	{
		return this;
	}
	
}
