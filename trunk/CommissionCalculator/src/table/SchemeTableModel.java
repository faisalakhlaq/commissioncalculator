package table;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import model.Scheme;

@SuppressWarnings("serial")
public class SchemeTableModel extends AbstractTableModel
{
	private static final int Company_Name = 0;

	private static final int Scheme_Name = 1;

	private String[] columnNames;

	private Vector<Scheme> data;

	public SchemeTableModel(Vector<Scheme> SchemeVector, String[] columns)
	{
		columnNames = columns;
		data = new Vector<Scheme>(SchemeVector);
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
		Scheme record = (Scheme) data.get(row);
		switch (column)
		{
		case Company_Name:
			return record.getCompanyName();
		case Scheme_Name:
			return record.getSchemeName();
		default:
			return new Object();
		}
	}
}
