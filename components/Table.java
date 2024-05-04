package components;

import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EventObject;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.swing.JComponent;
import javax.swing.DefaultCellEditor;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import guiutil.Displayable;

public class Table<T extends Displayable<T>> extends JInitializer<JTable, Table<T>>
{

	public enum InsertMode
	{
		ASCENDING, DESCENDING
	}
	
	private Table.InsertMode mode = Table.InsertMode.ASCENDING;
	
	public final LinkedList<T> items = new LinkedList<>();
	
	public Table<T> initSelectionMode(final int mode) { get().setRowSelectionAllowed(true); get().setSelectionMode(mode);	return self(); }
	
	public Table<T> initColumnsReordering(final boolean reordering)	{ get().getTableHeader().setReorderingAllowed(false); return self(); }
	
	public Table<T> initGridLines(final boolean showGrid) { get().setShowGrid(showGrid); return self(); }
	
	public Table<T> initItems(final List<T> items) { addItems(items); return this;	}
	
	public Table<T> initResizable(final int resizable) { get().setAutoResizeMode(resizable); return self(); }
	
	public Table<T> initMode(final Table.InsertMode mode) { this.mode = (mode != null) ? mode : this.mode; return self(); }
	
	public Table<T> initHideColumns(final int... cols)
	{
		try
		{
			final TableColumnModel tcm = get().getColumnModel();
			
			for(int i = 0; i < cols.length; ++i)
			{
				tcm.removeColumn(tcm.getColumn(cols[i]));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return this;
	}
	
	public Table<T> initModel(final DefaultTableModel model)
	{ get().setModel(model); return self(); }
	
	public Table<T> initCellRenderer(final TableCellRenderer renderer)
	{
		final TableColumnModel columnModel = get().getColumnModel();

		for(int i = 0; i < columnModel.getColumnCount(); ++i)
		{
			columnModel.getColumn(i).setCellRenderer(renderer);
		}
		
		return this;
	}
	

	
	public Table<T> initColumnsWidths(final int... widths)
	{
		try
		{
			final TableColumnModel cm = get().getColumnModel();
			
			for(int i = 0; i < widths.length; ++i)
			{
				cm.getColumn(i).setPreferredWidth(widths[i]);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return this;
	}
	


	

	
	public void addItem(final T item)
	{ this.addItems(Arrays.asList(item)); }
	
	public void addItems(final List<T> items)
	{
		final DefaultTableModel model = (DefaultTableModel) get().getModel();
		
		switch(mode)
		{
		case ASCENDING: 
			
			for(final T item: items)
			{
				this.items.add(item); 
				model.addRow(item.toArray());
			}
			
			break;
			
		case DESCENDING: 
			
			for(final T item: items)
			{
				this.items.addFirst(item); 
				model.insertRow(0, item.toArray());
			}
			
			break;
		}
		
		model.fireTableDataChanged();
		get().revalidate();
		get().repaint();
	}

	/**
	 * Get the first selected item in the table.
	 * 
	 * @return the first selected item or null if no item selected
	 */
	public T getSelectedItem()
	{
		final int row = get().getSelectedRow();
		
		if(row == -1 || row > items.size())
		{
			return null;
		}
		
		return items.get(row);
	}
	
	/**
	 * Get a list of selected items in the table.
	 * 
	 * @return an ArrayList of items or empty list if no items selected
	 */
	public List<T> getSelectedItems()
	{
		try
		{
			final List<T> selectedItems = new ArrayList<>();
			
			final int[] selectedRows = get().getSelectedRows();
			
			for(final Integer i: selectedRows)
			{
				selectedItems.add(items.get(i));
			}
			
			return selectedItems;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	
	/**
	 * Test if table contains the given item.
	 * 
	 * @param item the item to be checked for
	 * @return true if table contains item, false otherwise
	 */
	public boolean contains(final T item)
	{
		return this.items.contains(item);
	}
	
	
	
	public void setSelectedRows(final int[] rows)
	{
		Arrays.sort(rows);
		
		get().clearSelection();
		
		if(rows.length > 0)
		{
			final ListSelectionModel m = get().getSelectionModel();
			
			m.setSelectionInterval(rows[0], rows[rows.length-1]);

			for(int i = 0; i < rows.length-1; ++i)
			{
				final int dif = rows[i+1] - rows[i];
				if(dif > 1)
				{
					m.removeSelectionInterval(rows[i]+1, rows[i] + dif - 1);
				}
			}
		}
	}
	
	public void removeSelectedRows()
	{
		final int[] rows = get().getSelectedRows();
		
		final DefaultTableModel model = (DefaultTableModel) get().getModel();
		
		for(int i = rows.length - 1; i >= 0; --i)
		{
			this.items.remove(i);
			model.removeRow(rows[i]);
		}
		
		model.fireTableDataChanged();
		
		get().revalidate();
	}
	
	public void clear()
	{
		final DefaultTableModel model = (DefaultTableModel) get().getModel();
		
		model.setRowCount(0);
		
		model.fireTableDataChanged();
		
		get().revalidate();
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
	public JTable create() 
	{
		return new JTable();
	}
	
	@Override
	public Table<T> self()
	{
		return this;
	}
}















