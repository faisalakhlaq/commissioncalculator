package table;

import gui.dailogue.MessageDialog;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import model.Scheme;
import database.SchemeHandler;

public class SchemeTableModelListener implements TableModelListener
{

	@Override
	public void tableChanged(TableModelEvent e)
	{
		int row = e.getFirstRow();
		// int column = e.getColumn();
		SchemeTableModel model = (SchemeTableModel) e.getSource();
		// Object data = model.getValueAt(row, column);

		Scheme s = model.getScheme(row);
		SchemeHandler handler = new SchemeHandler();
		try
		{
			handler.updateScheme(s);
		}
		catch (Exception e1)
		{
			new MessageDialog("Error", e1.getMessage());
		}
	}

}
