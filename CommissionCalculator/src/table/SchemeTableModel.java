package table;

import gui.dailogue.MessageDialog;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import model.Scheme;
import utils.Helper;

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

	public boolean isCellEditable(int row, int col)
	{
		if (col == 0) return false;

		return true;
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

	public Scheme getScheme(int row)
	{
		return data.get(row);
	}

	public void setValueAt(Object value, int row, int col)
	{
		Scheme record = data.get(row);
		try
		{
			switch (col)
			{
			case SCHEME_NAME:
			{
				// Do nothing as the first column is not editable
				// record.setSchemeName((String) value);
				// fireTableCellUpdated(row, col);
				break;
			}
			case COMPANY_NAME:
			{
				record.setCompanyName((String) value);
				fireTableCellUpdated(row, col);
				break;
			}
			case ONE:
			{
				record.setOne(Helper.objectToDouble(value));
				fireTableCellUpdated(row, col);
				break;
			}
			case TWO:
			{
				record.setTwo(Helper.objectToDouble(value));
				fireTableCellUpdated(row, col);
				break;
			}
			case THREE:
			{
				record.setThree(Helper.objectToDouble(value));
				fireTableCellUpdated(row, col);
				break;
			}
			case FOUR:
			{
				record.setFour(Helper.objectToDouble(value));
				fireTableCellUpdated(row, col);
				break;
			}
			case FIVE:
			{
				record.setFive(Helper.objectToDouble(value));
				fireTableCellUpdated(row, col);
				break;
			}
			case SIX:
			{
				record.setSix(Helper.objectToDouble(value));
				fireTableCellUpdated(row, col);
				break;
			}
			case SEVEN:
			{
				record.setSeven(Helper.objectToDouble(value));
				fireTableCellUpdated(row, col);
				break;
			}
			case EIGHT:
			{
				record.setEight(Helper.objectToDouble(value));
				fireTableCellUpdated(row, col);
				break;
			}
			}
		}
		catch (Exception e)
		{
			new MessageDialog("Error", e.getMessage());
		}
	}

	@Override
	public Object getValueAt(int row, int column)
	{
		Scheme record = (Scheme) data.get(row);
		switch (column)
		{
		case SCHEME_NAME:
			return record.getSchemeName();
		case COMPANY_NAME:
			return record.getCompanyName();
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