package model;

import java.sql.Date;

public class Transaction implements ModelObject {
	private int id;
	private double receivedAmount;
	private double deliveredAmount;
	private double fee;
	private Date date;
	private String schemeName;

	public Transaction() {
	}

	public Transaction(double amount, Date date, String scheme) {
		this.receivedAmount = amount;
		this.date = date;
		this.schemeName = scheme;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getReceivedAmount() {
		return receivedAmount;
	}

	public void setReceivedAmount(double receivedAmount) {
		this.receivedAmount = receivedAmount;
	}

	public double getDeliveredAmount() {
		return deliveredAmount;
	}

	public void setDeliveredAmount(double deliveredAmount) {
		this.deliveredAmount = deliveredAmount;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getSchemeName() {
		return schemeName;
	}

	public void setSchemeName(String schemename) {
		this.schemeName = schemename;
	}
}