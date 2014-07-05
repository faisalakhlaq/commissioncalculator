package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import model.Scheme;

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

	@SuppressWarnings("null")
	public void createScheme(String sName, String cName) {
		DbConnection db = DbConnection.getInstance();
		Connection conn = db.getConnection();
		PreparedStatement stmt = null;

		JOptionPane inserted = null;

		if (conn == null) {
			inserted.setValue("Cannot get a connection to the database");
			return;
		}
		try {
			stmt = (PreparedStatement) conn
					.prepareStatement("INSERT INTO scheme(company_name, scheme_name) "
							+ "VALUES (?,?)");

			stmt.setString(1, cName);
			stmt.setString(2, sName);

			stmt.executeUpdate();
		} catch (SQLException e1) {
			Logger.getGlobal().severe(
					"Error occured while adding the area code: "
							+ e1.getMessage());
			System.out.println("SQLException: " + e1.getMessage());
			e1.printStackTrace();
			inserted.setValue("Unable to add area code. " + e1.getMessage());
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
}