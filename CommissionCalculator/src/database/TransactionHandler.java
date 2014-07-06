package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Logger;

import model.Transaction;

public class TransactionHandler {

	public TransactionHandler() {
	}

	public void saveTransaction(int amount, java.util.Date date, String sName)
			throws Exception {
		DbConnection db = DbConnection.getInstance();
		Connection conn = db.getConnection();
		PreparedStatement stmt = null;

		if (conn == null) {
			throw new Exception("Unable to connection to the database");
		}
		try {
			stmt = (PreparedStatement) conn
					.prepareStatement("INSERT INTO transaction(amount, date, schemename) "
							+ "VALUES (?,?,?)");

			java.sql.Date d = new java.sql.Date(date.getTime());

			stmt.setInt(1, amount);
			stmt.setDate(2, d);
			stmt.setString(3, sName);
			stmt.executeUpdate();
		} catch (SQLException e1) {
			Logger.getGlobal().severe(
					"Error occured while saving the transaction: "
							+ e1.getMessage());
			System.out.println("SQLException: " + e1.getMessage());
			e1.printStackTrace();
			throw new SQLException(
					"Error occured while saving the transaction: "
							+ e1.getMessage());
		} finally {
			try {
				DbConnection.closeConnection();
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e1) {
				Logger.getGlobal().severe(
						"Error occured while closing the connection or database: "
								+ e1.getMessage());
				System.out.println("SQLException: " + e1.getMessage());
				e1.printStackTrace();
				throw new SQLException(
						"Error occured while closing the connection or database: "
								+ e1.getMessage());

			}
		}
	}

	public Vector<Transaction> getTransactionsByDate(Date to, Date from)
			throws Exception {
		Vector<Transaction> result = null;
		DbConnection db = DbConnection.getInstance();
		Connection con = db.getConnection();
		Statement stmt = null;
		if (con == null) {
			throw new Exception("Unable to connect to the database!");
		}
		try {
			stmt = con.createStatement();
			java.sql.Date fromDate = new java.sql.Date(from.getTime());
			java.sql.Date toDate = new java.sql.Date(to.getTime());
			String query = "SELECT * FROM TRANSACTION WHERE DATE <= '" + toDate
					+ "' AND DATE >= '" + fromDate + "';";
			System.out.println("Query Executed: " + query);
			ResultSet rs = stmt.executeQuery(query);

			if (rs != null) {
				result = new Vector<Transaction>();
				while (rs.next()) {
					Transaction tr = new Transaction();
					tr.setAmount(rs.getDouble("Amount"));
					tr.setDate(rs.getDate("Date"));
					tr.setSchemename(rs.getString("Schemename"));
					result.add(tr);
				}
			}
		} catch (Exception e1) {
			Logger.getGlobal().severe(
					"Unable to retrieve transaction details from the database. "
							+ e1.getMessage());
			System.out.println("SQLException: " + e1.getMessage());
			e1.printStackTrace();
			throw new Exception(
					"Unable to retrieve transaction details data from the database!<p>"
							+ e1.getMessage());
		} finally {
			try {
				DbConnection.closeConnection();
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e1) {
				Logger.getGlobal().severe(
						"Error occured while closing the connection or statement: "
								+ e1.getMessage());
				System.out.println("SQLException: " + e1.getMessage());
				e1.printStackTrace();
				throw new SQLException(
						"Error occured while closing the connection or statement.");
			}
		}
		return result;
	}

	public Vector<Transaction> getTransactions(Date to, Date from,
			String schemeName) throws Exception {
		Vector<Transaction> result = null;
		DbConnection db = DbConnection.getInstance();
		Connection con = db.getConnection();
		Statement stmt = null;
		if (con == null) {
			throw new Exception("Unable to connect to the database!");
		}
		try {
			stmt = con.createStatement();
			java.sql.Date fromDate = new java.sql.Date(from.getTime());
			java.sql.Date toDate = new java.sql.Date(to.getTime());
			String query = null;
			if (schemeName == null) {
				query = "SELECT * FROM TRANSACTION WHERE DATE <= '" + toDate
						+ "' AND DATE >= '" + fromDate + "';";
			} else {
				query = "SELECT * FROM TRANSACTION WHERE DATE <= '" + toDate
						+ "' AND DATE >= '" + fromDate + "' AND schemename = '"
						+ schemeName + "';";
			}
			System.out.println("Query Executed: " + query);
			ResultSet rs = stmt.executeQuery(query);

			if (rs != null) {
				result = new Vector<Transaction>();
				while (rs.next()) {
					Transaction tr = new Transaction();
					tr.setAmount(rs.getDouble("Amount"));
					tr.setDate(rs.getDate("Date"));
					tr.setSchemename(rs.getString("Schemename"));
					result.add(tr);
				}
			}
		} catch (Exception e1) {
			Logger.getGlobal().severe(
					"Unable to retrieve transaction details from the database. "
							+ e1.getMessage());
			System.out.println("SQLException: " + e1.getMessage());
			e1.printStackTrace();
			throw new Exception(
					"Unable to retrieve transaction details data from the database!<p>"
							+ e1.getMessage());
		} finally {
			try {
				DbConnection.closeConnection();
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e1) {
				Logger.getGlobal().severe(
						"Error occured while closing the connection or statement: "
								+ e1.getMessage());
				System.out.println("SQLException: " + e1.getMessage());
				e1.printStackTrace();
				throw new SQLException(
						"Error occured while closing the connection or statement.");
			}
		}
		return result;
	}

	public void updateTransaction(Transaction s) throws Exception {

		DbConnection db = DbConnection.getInstance();
		Connection conn = db.getConnection();
		PreparedStatement stmt = null;

		if (conn == null) {
			Logger.getGlobal().severe(
					"Unable to get the connection to the database");
			System.out.println("Unable to get the connection to the database");
			throw new Exception("Unable to get the connection to the database");
		}
		try {
			stmt = (PreparedStatement) conn
					.prepareStatement("UPDATE transaction SET amount = ?,"
							+ " where amount = '" + s.getAmount() + "';");

			stmt.setDouble(1, s.getAmount());

			System.out.println("Executing query: " + stmt.toString());
			stmt.executeUpdate();
		} catch (SQLException e1) {
			Logger.getGlobal().severe(
					"Error occured while adding the area code: "
							+ e1.getMessage());
			System.out.println("SQLException: " + e1.getMessage());
			e1.printStackTrace();
			throw new SQLException(e1.getMessage());
		} finally {
			try {
				DbConnection.getInstance();
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e1) {
				Logger.getGlobal().severe(
						"Error occured while inserting the area code: "
								+ e1.getMessage());
				System.out.println("SQLException: " + e1.getMessage());
				e1.printStackTrace();
				throw new SQLException(e1.getMessage());
			}
		}
	}
}