package model;

import java.util.Date;

public class DailyCash implements ModelObject
{
	private Date date;

	private int amount;

	public DailyCash()
	{
	}

	public DailyCash(Date date, int amount)
	{
		this.date = date;
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

	public int getAmount()
	{
		return amount;
	}

	public void setAmount(int amount)
	{
		this.amount = amount;
	}
}
