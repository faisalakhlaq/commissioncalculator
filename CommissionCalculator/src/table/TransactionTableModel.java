package table;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import model.Transaction;

@SuppressWarnings("serial")
public class TransactionTableModel extends AbstractTableModel
{
	private static final int amount = 0;

	private static final int date = 1;

	private static final int SchemeName = 2;

	private String[] columnNames;

	private Vector<Transaction> data = null;

	public TransactionTableModel(Vector<Transaction> TransactionVector, String[] columns)
	{
		columnNames = columns;
		data = new Vector<Transaction>(TransactionVector);
	}

	public String getColumnName(int column)
	{
		return columnNames[column];
	}

	@Override
	public int getColumnCount()
	{
		return columnNames.length;
	}

	@Override
	public int getRowCount()
	{
		return data.size();
	}

	@Override
	public Object getValueAt(int row, int column)
	{
		Transaction record = (Transaction) data.get(row);
		switch (column)
		{
		case amount:
			return record.getAmount();
		case date:
			return record.getDate();
		case SchemeName:
			return record.getSchemename();
		default:
			return new Object();
		}
	}
}
