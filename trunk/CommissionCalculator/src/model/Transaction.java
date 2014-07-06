package model;

import java.sql.Date;

public class Transaction implements ModelObject
{
	private int id;

	private double amount;

	private Date date;

	private String schemeName;

	public Transaction()
	{
	}

	public Transaction(double amount, Date date, String scheme)
	{
		this.amount = amount;
		this.date = date;
		this.schemeName = scheme;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
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

	public String getSchemeName()
	{
		return schemeName;
	}

	public void setSchemeName(String schemename)
	{
		this.schemeName = schemename;
	}
}