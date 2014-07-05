package model;

import java.sql.Date;

public class Transaction
{
	private double amount;

	private Date date;

	private String schemename;

	public Transaction()
	{
	}

	public Transaction(double amount, Date date, String scheme)
	{
		this.amount = amount;
		this.date = date;
		this.schemename = scheme;
	}

	public double getAmount()
	{
		return amount;
	}

	public void setAmount(double amount)
	{
		this.amount = amount;
	}

	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}

	public String getSchemename()
	{
		return schemename;
	}

	public void setSchemename(String schemename)
	{
		this.schemename = schemename;
	}
}
