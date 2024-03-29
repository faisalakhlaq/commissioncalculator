package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Logger;

import model.Scheme;

import com.mysql.jdbc.PreparedStatement;

public class SchemeHandler {
	private DbConnection db = null;

	public SchemeHandler() {
	}

	public void deleteScheme(String schemeName) {
		DbConnection db = DbConnection.getInstance();
		Connection conn = db.getConnection();
		PreparedStatement stmt = null;

		if (conn == null) {
			return;
		}
		try {
			stmt = (PreparedStatement) conn
					.prepareStatement("DELETE from SCHEME where SCHEME_NAME = '"
							+ schemeName + "';");
			stmt.executeUpdate();
		} catch (SQLException e1) {
			Logger.getGlobal().severe(
					"Error occured while deleted the schemeName: "
							+ e1.getMessage());
			System.out.println("SQLException: " + e1.getMessage());
			e1.printStackTrace();
		} finally {
			try {
				DbConnection.getInstance();
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e1) {
				Logger.getGlobal().severe(
						"Error occured while delete schemeName: "
								+ e1.getMessage());
				System.out.println("SQLException: " + e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	public void createScheme(String sName, String cName, double a, double b,
			double c, double d, double e, double f, double g, double h)
			throws Exception {
		DbConnection db = DbConnection.getInstance();
		Connection conn = db.getConnection();
		PreparedStatement stmt = null;

		if (conn == null) {
			throw new Exception("Unable to connect to the database");
		}
		try {
			stmt = (PreparedStatement) conn
					.prepareStatement("INSERT INTO scheme(company_name, scheme_name, 1_1000, 1001_2500, 2501_4000, 4001_6000, 6001_8000, 8001_10000, 10001_13000, 13001_15000) "
							+ "VALUES (?,?,?,?,?,?,?,?,?,?)");

			stmt.setString(1, cName);
			stmt.setString(2, sName);
			stmt.setDouble(3, a);
			stmt.setDouble(4, b);
			stmt.setDouble(5, c);
			stmt.setDouble(6, d);
			stmt.setDouble(7, e);
			stmt.setDouble(8, f);
			stmt.setDouble(9, g);
			stmt.setDouble(10, h);

			stmt.executeUpdate();
		} catch (SQLException e1) {
			Logger.getGlobal().severe(
					"Error occured while creating new scheme: "
							+ e1.getMessage());
			System.out.println("SQLException: " + e1.getMessage());
			e1.printStackTrace();
			throw new SQLException("Error occured while creating new scheme"
					+ e1.getMessage());
		} finally {
			try {
				DbConnection.getInstance();
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e1) {
				Logger.getGlobal().severe(
						"Error occured while closing the database connection: "
								+ e1.getMessage());
				System.out.println("SQLException: " + e1.getMessage());
				e1.printStackTrace();
				throw new SQLException(
						"Error occured while closing the database connection: "
								+ e1.getMessage());
			}
		}
	}

	public Vector<Scheme> getAllSchemes() throws Exception {
		Vector<Scheme> schemeVector = null;
		db = DbConnection.getInstance();
		Connection con = db.getConnection();
		Statement stmt = null;

		if (con == null) {
			throw new Exception("Unable to connect to the database!");
		}
		try {
			stmt = con.createStatement();
			String query = "SELECT * FROM scheme";
			System.out.println("Query Executed: " + query);
			ResultSet rs = stmt.executeQuery(query);

			if (rs != null) {
				schemeVector = new Vector<Scheme>();
				while (rs.next()) {
					Scheme sc = new Scheme();
					sc.setSchemeName(rs.getString("scheme_name"));
					sc.setCompanyName(rs.getString("company_name"));
					sc.setOne(rs.getDouble("1_1000"));
					sc.setTwo(rs.getDouble("1001_2500"));
					sc.setThree(rs.getDouble("2501_4000"));
					sc.setFour(rs.getDouble("4001_6000"));
					sc.setFive(rs.getDouble("6001_8000"));
					sc.setSix(rs.getDouble("8001_10000"));
					sc.setSeven(rs.getDouble("10001_13000"));
					sc.setEight(rs.getDouble("13001_15000"));
					schemeVector.add(sc);
				}
			}
		} catch (Exception e1) {
			Logger.getGlobal().severe(
					"Unable to retrieve scheme details from the database. "
							+ e1.getMessage());
			System.out.println("SQLException: " + e1.getMessage());
			e1.printStackTrace();
			throw new Exception(
					"Unable to retrieve scheme details data from the database!<p>"
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
		return schemeVector;
	}

	public Vector<String> getSchemeNames() throws SQLException {
		Vector<String> names = null;
		DbConnection db = DbConnection.getInstance();
		Connection conn = db.getConnection();

		String query = "Select scheme_name from scheme;";
		Statement st = null;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			if (rs != null) {
				names = new Vector<String>();
				while (rs.next()) {
					names.add(rs.getString("scheme_name"));
				}
			}
		} catch (SQLException e1) {
			Logger.getGlobal().severe(
					"Error occured while retrieving scheme names: "
							+ e1.getMessage());
			System.out.println("SQLException: " + e1.getMessage());
			e1.printStackTrace();
			throw new SQLException(
					"Error occured while retrieving scheme names"
							+ e1.getMessage());
		} finally {
			try {
				DbConnection.closeConnection();
				if (st != null) {
					st.close();
				}
			} catch (SQLException e1) {
				Logger.getGlobal().severe(
						"Error occured while closing the database connection: "
								+ e1.getMessage());
				System.out.println("SQLException: " + e1.getMessage());
				e1.printStackTrace();
				throw new SQLException(
						"Error occured while closing the database connection"
								+ e1.getMessage());
			}
		}
		return names;
	}

	public double getProfit(String range, String schemeName)
			throws SQLException {
		double result = 0;
		DbConnection db = DbConnection.getInstance();
		Connection conn = db.getConnection();

		String query = "Select " + range + " from scheme where scheme_name = '"
				+ schemeName + "';";
		System.out.println("Executing query: " + query);
		Statement stmt = conn.createStatement();
		try {
			ResultSet rs = stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					result = rs.getDouble(range);
				}
			}
		} catch (SQLException e) {
			System.out.println("SQL Exception: " + e.getMessage());
			e.printStackTrace();
			throw new SQLException(
					"Error occured while calculating the profit: "
							+ e.getMessage());
		} finally {
			try {
				DbConnection.closeConnection();
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e1) {
				Logger.getGlobal().severe(
						"Error occured while closing the database connection: "
								+ e1.getMessage());
				System.out.println("SQLException: " + e1.getMessage());
				e1.printStackTrace();
				throw new SQLException(
						"Error occured while closing the database connection"
								+ e1.getMessage());
			}
		}
		return result;
	}

	public void updateScheme(Scheme s) throws Exception {
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
					.prepareStatement("UPDATE scheme SET scheme_name = ?, company_name = ?, 1_1000 = ?, 1001_2500 = ?, 2501_4000 = ?, 4001_6000 = ?"
							+ ", 6001_8000 = ?, 8001_10000 = ?, 10001_13000 = ?, 13001_15000 = ?"
							+ " where scheme_name = '"
							+ s.getSchemeName()
							+ "';");

			stmt.setString(1, s.getSchemeName());
			stmt.setString(2, s.getCompanyName());
			stmt.setDouble(3, s.getOne());
			stmt.setDouble(4, s.getTwo());
			stmt.setDouble(5, s.getThree());
			stmt.setDouble(6, s.getFour());
			stmt.setDouble(7, s.getFive());
			stmt.setDouble(8, s.getSix());
			stmt.setDouble(9, s.getSeven());
			stmt.setDouble(10, s.getEight());

			System.out.println("Executing query: " + stmt.toString());
			stmt.executeUpdate();
		} catch (SQLException e1) {
			Logger.getGlobal().severe(
					"Error occured while updating the scheme: "
							+ e1.getMessage());
			System.out.println("SQLException: " + e1.getMessage());
			e1.printStackTrace();
			throw new SQLException("Error occured while updating the scheme"
					+ e1.getMessage());
		} finally {
			try {
				DbConnection.getInstance();
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e1) {
				Logger.getGlobal().severe(
						"Error occured while closing the database connection: "
								+ e1.getMessage());
				System.out.println("SQLException: " + e1.getMessage());
				e1.printStackTrace();
				throw new SQLException(
						"Error occured while closing the database connection"
								+ e1.getMessage());
			}
		}
	}

	/**
	 * Updates the scheme name in scheme and transaction tables
	 */
	public void updateSchemeName(String oldName, String newName)
			throws Exception {
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
			// If one of the tables is not having the provided scheme_name then
			// the query wont effect. Therefore, we have to execute query
			// separately for the scheme table
			stmt = (PreparedStatement) conn
					.prepareStatement("UPDATE scheme, transaction SET scheme.scheme_name = ?, transaction.scheme_name = ? where SCHEME.scheme_name = '"
							+ oldName
							+ "' AND TRANSACTION.SCHEME_NAME = '"
							+ oldName + "';");

			stmt.setString(1, newName);
			stmt.setString(2, newName);

			System.out.println("Executing query: " + stmt.getPreparedSql());
			stmt.executeUpdate();

			Statement st = conn.createStatement();
			st.execute("Update scheme set scheme_name = '" + newName
					+ "' where scheme_name = '" + oldName + "';");
		} catch (SQLException e1) {
			Logger.getGlobal().severe(
					"Error occured while updateing the scheme name: "
							+ e1.getMessage());
			System.out.println("SQLException: " + e1.getMessage());
			e1.printStackTrace();
			throw new SQLException(
					"Error occured while updateing the scheme name"
							+ e1.getMessage());
		} finally {
			try {
				DbConnection.getInstance();
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e1) {
				Logger.getGlobal().severe(
						"Error occured while closing the database connection: "
								+ e1.getMessage());
				System.out.println("SQLException: " + e1.getMessage());
				e1.printStackTrace();
				throw new SQLException(
						"Error occured while closing the database connection"
								+ e1.getMessage());
			}
		}
	}
}