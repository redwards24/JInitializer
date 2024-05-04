package guiutil;

import java.awt.Component;

import javax.swing.JOptionPane;


public class OptionPane
{
	private static Component parent = null;
	
	public static void setParent(final Component parent)
	{
		OptionPane.parent = parent;
	}
	
	public static void showError(final Component parent, final String message)
	{
		JOptionPane.showMessageDialog(parent, message, "Error!", JOptionPane.ERROR_MESSAGE);
	}
	
	public static void showError(final String message)
	{
		showError(parent, message);
	}
	
	public static void showMessage(final String message)
	{
		JOptionPane.showMessageDialog(parent, message, "Message.", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static boolean showYesNoCancel(final String message)
	{
		final int result = JOptionPane.showConfirmDialog(parent, message);
		
		if(result == JOptionPane.YES_OPTION)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static String showInput(final String message)
	{
		return JOptionPane.showInputDialog(parent, message);
	}
}