package table;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import model.DailyCash;

@SuppressWarnings("serial")
public class DailyCashTableModel extends AbstractTableModel
{
	private static final int DATE = 0;

	private static final int AMOUNT = 1;

	private String[] columnNames;

	private Vector<DailyCash> data = null;

	public DailyCashTableModel(Vector<DailyCash> dailyCashVector, String[] columns)
	{
		columnNames = columns;
		data = new Vector<DailyCash>(dailyCashVector);
	}

	public String getColumnName(int column)
	{
		return columnNames[column];
	}

	public DailyCash getTransaction(int row)
	{
		return data.get(row);
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
		DailyCash record = (DailyCash) data.get(row);
		switch (column)
		{
		case DATE:
			return record.getDate();
		case AMOUNT:
		{
			return record.getAmount();
		}
		default:
			return new Object();
		}
	}

}