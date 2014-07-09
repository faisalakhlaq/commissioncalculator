package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Logger;

import model.DailyCash;

public class CashHandler
{
	public CashHandler()
	{
	}

	/**
	 * Retrieve the total cash of the day from the database table
	 * 
	 * @throws Exception
	 *             If unable to connect to the database or error occurs while
	 *             retrieving data from the database
	 */
	public double retrieveCash(Date date) throws Exception
	{
		double result = 0;
		DbConnection db = DbConnection.getInstance();
		Connection conn = db.getConnection();
		PreparedStatement stmt = null;

		if (conn == null)
		{
			throw new Exception("Unable to connection to the database");
		}
		try
		{
			stmt = (PreparedStatement) conn.prepareStatement("Select cash from daily_cash where date = ?");

			java.sql.Date d = new java.sql.Date(date.getTime());
			stmt.setDate(1, d);

			System.out.println("Executing Query: " + stmt.toString());
			ResultSet rs = stmt.executeQuery();

			if (rs != null)
			{
				while (rs.next())
				{
					result = rs.getDouble(1);
				}
			}
		}
		catch (SQLException e1)
		{
			Logger.getGlobal().severe("Error occured while retrieving cash from daily cash table: " + e1.getMessage());
			System.out.println("SQLException: " + e1.getMessage());
			e1.printStackTrace();
			throw new SQLException("Error occured while retrieving cash from daily cash table: " + e1.getMessage());
		}
		finally
		{
			try
			{
				DbConnection.closeConnection();
				if (stmt != null)
				{
					stmt.close();
				}
			}
			catch (SQLException e1)
			{
				Logger.getGlobal().severe("Error occured while closing the connection or database: " + e1.getMessage());
				System.out.println("SQLException: " + e1.getMessage());
				e1.printStackTrace();
				throw new SQLException("Error occured while closing the connection or database: " + e1.getMessage());

			}
		}
		return result;
	}

	public void insertCash(Date date, double amount) throws Exception
	{
		DbConnection db = DbConnection.getInstance();
		Connection conn = db.getConnection();
		PreparedStatement stmt = null;

		if (conn == null)
		{
			throw new Exception("Unable to connection to the database");
		}
		try
		{
			stmt = (PreparedStatement) conn.prepareStatement("INSERT INTO daily_cash(date, cash) " + "VALUES (?,?)");

			java.sql.Date d = new java.sql.Date(date.getTime());

			stmt.setDate(1, d);
			stmt.setDouble(2, amount);

			System.out.println("Executing Query: " + stmt.toString());
			stmt.executeUpdate();
		}
		catch (SQLException e1)
		{
			Logger.getGlobal().severe("Error occured while saving data in daily cash table: " + e1.getMessage());
			System.out.println("SQLException: " + e1.getMessage());
			e1.printStackTrace();
			throw new SQLException("Error occured while saving saving data in daily cash table: " + e1.getMessage());
		}
		finally
		{
			try
			{
				DbConnection.closeConnection();
				if (stmt != null)
				{
					stmt.close();
				}
			}
			catch (SQLException e1)
			{
				Logger.getGlobal().severe("Error occured while closing the connection or database: " + e1.getMessage());
				System.out.println("SQLException: " + e1.getMessage());
				e1.printStackTrace();
				throw new SQLException("Error occured while closing the connection or database: " + e1.getMessage());

			}
		}
	}

	public void updateCash(Date date, int amount) throws Exception
	{
		DbConnection db = DbConnection.getInstance();
		Connection conn = db.getConnection();
		PreparedStatement stmt = null;

		if (conn == null)
		{
			throw new Exception("Unable to connection to the database");
		}
		try
		{
			java.sql.Date d = new java.sql.Date(date.getTime());
			stmt = (PreparedStatement) conn.prepareStatement("UPDATE daily_cash SET cash = ? where date = '" + d + "';");

			stmt.setDouble(1, amount);

			System.out.println("Executing Query: " + stmt.toString());
			stmt.executeUpdate();
		}
		catch (SQLException e1)
		{
			Logger.getGlobal().severe("Error occured while updating data in daily cash table: " + e1.getMessage());
			System.out.println("SQLException: " + e1.getMessage());
			e1.printStackTrace();
			throw new SQLException("Error occured while saving updating data in daily cash table: " + e1.getMessage());
		}
		finally
		{
			try
			{
				DbConnection.closeConnection();
				if (stmt != null)
				{
					stmt.close();
				}
			}
			catch (SQLException e1)
			{
				Logger.getGlobal().severe("Error occured while closing the connection or database: " + e1.getMessage());
				System.out.println("SQLException: " + e1.getMessage());
				e1.printStackTrace();
				throw new SQLException("Error occured while closing the connection or database: " + e1.getMessage());

			}
		}
	}

	public Vector<DailyCash> getAllCash() throws Exception
	{
		Vector<DailyCash> result = null;
		DbConnection db = DbConnection.getInstance();
		Connection conn = db.getConnection();
		java.sql.Statement stmt = null;

		if (conn == null)
		{
			throw new Exception("Unable to connection to the database");
		}
		try
		{
			stmt = conn.createStatement();
			String query = "Select * from DAILY_CASH";
			System.out.println("Query Executed: " + query);
			ResultSet rs = stmt.executeQuery(query);

			if (rs != null)
			{
				result = new Vector<DailyCash>();
				while (rs.next())
				{
					DailyCash tr = new DailyCash();
					tr.setDate(rs.getDate("Date"));
					tr.setAmount(rs.getInt("cash"));
					result.add(tr);
				}
			}
		}
		catch (SQLException e1)
		{
			Logger.getGlobal().severe("Error occured while retrieving data from daily cash table: " + e1.getMessage());
			System.out.println("SQLException: " + e1.getMessage());
			e1.printStackTrace();
			throw new SQLException("Error occured while retrieving data from daily cash table: " + e1.getMessage());
		}
		finally
		{
			try
			{
				DbConnection.closeConnection();
				if (stmt != null)
				{
					stmt.close();
				}
			}
			catch (SQLException e1)
			{
				Logger.getGlobal().severe("Error occured while closing the connection or database: " + e1.getMessage());
				System.out.println("SQLException: " + e1.getMessage());
				e1.printStackTrace();
				throw new SQLException("Error occured while closing the connection or database: " + e1.getMessage());

			}
		}
		return result;
	}
}
