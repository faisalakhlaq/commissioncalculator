package table;

import gui.dailogue.MessageDialog;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import model.Transaction;
import utils.Helper;
import database.SchemeHandler;

@SuppressWarnings("serial")
public class TransactionTableModel extends AbstractTableModel
{
	private static final int ID = 0;

	private static final int AMOUNT = 1;

	private static final int DATE = 2;

	private static final int SCHEMENAME = 3;

	private static final int PROFIT = 4;

	private String[] columnNames;

	private Vector<Transaction> data = null;

	public TransactionTableModel(Vector<Transaction> TransactionVector, String[] columns)
	{
		columnNames = columns;
		data = new Vector<Transaction>(TransactionVector);
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

	public Transaction getTransaction(int row)
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
		Transaction record = (Transaction) data.get(row);
		switch (column)
		{
		case ID:
			return record.getId();
		case AMOUNT:
			return record.getAmount();
		case DATE:
			return record.getDate();
		case SCHEMENAME:
			return record.getSchemeName();
		case PROFIT:
			return calculateProfit(record.getSchemeName(), record.getAmount());
		default:
			return new Object();
		}
	}

	public void setValueAt(Object value, int row, int col)
	{
		Transaction record = data.get(row);
		try
		{
			switch (col)
			{
			case ID:
			{
				// Do nothing as the first column is not editable
				// record.setSchemeName((String) value);
				// fireTableCellUpdated(row, col);
				break;
			}
			case AMOUNT:
			{
				record.setAmount(Helper.objectToDouble(value));
				fireTableCellUpdated(row, col);
				break;
			}
			case SCHEMENAME:
			{
				record.setSchemeName((String) value);
				fireTableCellUpdated(row, col);
				break;
			}
			case PROFIT:
			{
				break;
			}
			}
		}
		catch (Exception e)
		{
			new MessageDialog("Error", e.getMessage());
		}
	}

	private double calculateProfit(String schemeName, double amount)
	{
		double profit = 0;
		try
		{
			if (amount >= 1 && amount <= 1000)
			{
				SchemeHandler handler = new SchemeHandler();
				profit = handler.getProfit("1_1000", schemeName);
			}
			if (amount >= 1001 && amount <= 2500)
			{
				SchemeHandler handler = new SchemeHandler();
				profit = handler.getProfit("1001_2500", schemeName);
			}
			if (amount >= 2501 && amount <= 4000)
			{
				SchemeHandler handler = new SchemeHandler();
				profit = handler.getProfit("2501_4000", schemeName);
			}
			if (amount >= 4001 && amount <= 6000)
			{
				SchemeHandler handler = new SchemeHandler();
				profit = handler.getProfit("4001_6000", schemeName);
			}
			if (amount >= 6001 && amount <= 8000)
			{
				SchemeHandler handler = new SchemeHandler();
				profit = handler.getProfit("6001_8000", schemeName);
			}
			if (amount >= 8001 && amount <= 10000)
			{
				SchemeHandler handler = new SchemeHandler();
				profit = handler.getProfit("8001_10000", schemeName);
			}
			if (amount >= 10001 && amount <= 13000)
			{
				SchemeHandler handler = new SchemeHandler();
				profit = handler.getProfit("10001_13000", schemeName);
			}
			if (amount >= 13001 && amount <= 15000)
			{
				SchemeHandler handler = new SchemeHandler();
				profit = handler.getProfit("13001_15000", schemeName);
			}
		}
		catch (Exception e)
		{
			new MessageDialog("Error", e.getMessage());
		}
		return profit;
	}

}
