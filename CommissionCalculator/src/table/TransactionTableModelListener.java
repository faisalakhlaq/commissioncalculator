package table;

import gui.dailogue.MessageDialog;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import model.Transaction;
import database.TransactionHandler;

public class TransactionTableModelListener implements TableModelListener
{

	@Override
	public void tableChanged(TableModelEvent e)
	{
		int row = e.getFirstRow();
		// int column = e.getColumn();
		TransactionTableModel model = (TransactionTableModel) e.getSource();
		// Object data = model.getValueAt(row, column);

		Transaction s = model.getTransaction(row);
		TransactionHandler handler = new TransactionHandler();
		try
		{
			handler.updateTransaction(s);
		}
		catch (Exception e1)
		{
			new MessageDialog("Error", e1.getMessage());
		}
	}

}
