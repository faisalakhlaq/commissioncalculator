package table;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import model.Scheme;

@SuppressWarnings("serial")
public class SchemeTableModel extends AbstractTableModel
{
	private static final int SCHEME_NAME = 0;
	
	private static final int COMPANY_NAME = 1;
	
	private static final int ONE = 2;

	private static final int TWO = 3;

	private static final int THREE = 4;

	private static final int FOUR = 5;

	private static final int FIVE = 6;

	private static final int SIX = 7;

	private static final int SEVEN = 8;

	private static final int EIGHT = 9;

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
		case COMPANY_NAME:
			return record.getCompanyName();
		case SCHEME_NAME:
			return record.getSchemeName();
		case ONE:
			return record.getOne();
		case TWO:
			return record.getTwo();
		case THREE:
			return record.getThree();
		case FOUR:
			return record.getFour();
		case FIVE:
			return record.getFive();
		case SIX:
			return record.getSix();
		case SEVEN:
			return record.getSeven();
		case EIGHT:
			return record.getEight();
		default:
			return new Object();
		}
	}
}